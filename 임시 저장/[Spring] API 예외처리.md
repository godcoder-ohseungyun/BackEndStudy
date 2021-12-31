# [Spring] API 예외처리

---

일반적으로 4xx, 5xx와 같은 예외는 그에 상응하는 오류 페이지를 만들어서 보여주면 끝이지만,



API 예외는 고려해야하는 내용이 더 많다.

고객에게 보여주는 화면이 아니라 정확한 데이터를 다뤄야한다.

API는 각 오류 상황에 맞는 오류 응답 스펙을 정하고, JSON으로 정확한 데이터를 내려주어야 한다.



아래와 같은 API가 있다.

~~~java
@GetMapping("/api/members/{id}")
public Member getMember(@PathVariable("id") String id) { 
    
    if (id.equals("ex")) {
 		throw new RuntimeException("잘못된 사용자"); //잘못된 사용자의 경우 예외 발생
 	}
    
 	return new Member(id); //정상정인 경우 id json return
 }
~~~

정상적인  경우는 문제가 없지만 예외가 발생한 경우 500상태 코드와 함께 이전에 만든 예외 페이지가 View를 통해 반환된다.

즉, html 이 반환된것 하지만 브라우저가 아니고서는 html을 해석할수있는 경우가 많지 않다.

요청은 모바일 등 다양한 기기로 부터 올수있다.

그렇기 때문에 JSON 으로 데이터를 반환해 응답해야 하는데

오류발생의 경우 html 오류 페이지가 나와버린다.

이건 원하는 바가 아니다.



오류 페이지 컨트롤러를 확장해서

요청 HTTP Header의 Accept 의 값이 application/json 일 때 html 오류 페이지 대신에 json으로 반환 하도록 할수있다.



## 스프링 부트 API 오류 처리

---

이전에 학습했던 스프링 제공 예외 페이지 컨트롤러 BasicErrorController에 들어가 보면 /error 동일한 경로를 처리하는 errorHtml() , error() **두 메서드를 확인할 수 있다.**



**errorHtml() :** 클라이언트 요청의 Accept 해더 값이 text/html 인 경우에는 errorHtml() 을 호출해서 view를 제공한다.

**error() :** 그외 경우에 호출되고 ResponseEntity 로 HTTP Body에 <u>JSON 데이터를 반환한다.</u>

> 이때 예외 관련 정보를 함께 반환한다.



즉, 요청 HTTP Header 의 Accept 값이 text/html 가 아니고  application/json 일때 html 오류 페이지 대신에 json으로 반환 하도록하는 메서드가 이미 정의 되어있다.



스프링 부트가 제공하는 BasicErrorController 는 HTML 페이지를 제공하는 경우에는 매우 편리하다. 

> 4xx, 5xx 등등 모두 잘 처리해준다. 



그런데 **API 오류 처리는 다른 차원의 이야기이다**. API 마다, 각각의 컨트롤러나 예외마다 **서로 다른 응답 결과를 출력해야 할 수도 있다.** 

> 단순히 오류관련 정보를 반환하는게 아니라 API에 따라서 각기 다른 JSON 데이터를 반환해야한다.



@ExceptionHandler를 이용하면 쉽게 API 오류처리시 API마다 각각 다른 JSON 데이터를 반환하도록 할수있다.



ExceptionResolver 

![image-20211228201047564](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20211228201047564.png)

WAS로 예외가 던져진다.

![image-20211228200858914](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20211228200858914.png)

> 참고: ExceptionResolver 로 예외를 해결해도 postHandle() 은 호출되지 않는다.

WAS로 예외가 던져지지 않고 ExceptionResolver의 응답을 정상응답한다.

예외를 ExceptionResolver에서 마무리한다.

WAS까지 왔다갔다 하는것은 매우 비효율적이다.



ExceptionResolver 가 ModelAndView 를 반환하는 이유는 마치 try, catch를 하듯이, Exception 을
처리해서 정상 흐름 처럼 변경하는 것이 목적이다. 이름 그대로 Exception 을 Resolver(해결)하는 것이
목적이다.

> WAS까지 예외를 던지지 않고 ExceptionResolver 처리후 데이터를 정상 응답하여 WAS에게 전달해준다.
>
> WAS는 정상적인 경우처럼 동작한다.



ExceptionResolver 를 사용하면 컨트롤러에서 예외가 발생해도 ExceptionResolver 에서 예외를
처리해버린다.
따라서 예외가 발생해도 서블릿 컨테이너까지 예외가 전달되지 않고, 스프링 MVC에서 예외 처리는 끝이난다.
결과적으로 WAS 입장에서는 정상 처리가 된 것이다. 이렇게 예외를 이곳에서 모두 처리할 수 있다는 것이
핵심이다.



**스프링이 제공하는 ExceptionResolver**

그런데 직접 ExceptionResolver 를 구현하려고 하니 상당히 복잡하다. 지금부터 스프링이 제공하는
3가지 ExceptionResolver 들을 알아보자.







### @ExceptionHandler

~~~java
@ResponseStatus(HttpStatus.BAD_REQUEST) //상태코드 200 -> 400으로 바꾸기 for log
 @ExceptionHandler(IllegalArgumentException.class)
 public ErrorResult illegalExHandle(IllegalArgumentException e) {
     log.error("[exceptionHandle] ex", e); //로그만 남기기
     return new ErrorResult("BAD", e.getMessage()); //정상 응답으로 반환 json
 }
~~~

ExceptionHandler 에너테이션으로 해당 컨트롤러의 예외를 잡는 메서드로 지정할수있음



@ExceptionHandler 에는 마치 스프링의 컨트롤러의 파라미터 응답처럼 다양한 파라미터와 응답을 지정할
수 있다.



뷰,json 등 다됨



[but]

@ExceptionHandler 를 사용해서 예외를 깔끔하게 처리할 수 있게 되었지만, 정상 코드와 예외 처리
코드가 하나의 컨트롤러에 섞여 있다. @ControllerAdvice 또는 @RestControllerAdvice 를 사용하면
둘을 분리할 수 있다.



 @ControllerAdvice

