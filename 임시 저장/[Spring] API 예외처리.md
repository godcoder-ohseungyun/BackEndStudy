# [Spring] API 예외처리

---

예외 페이지를 통해 예외를 처리 할때는 그냥 예외 페이지를 보여주기만 하면 되기때문에 어려울게 없다.

> 상태코드에 따라 /error 에 등록한 html을 보여주기만 하면 됨

***하지만 API 예외는 고려해야하는 내용이 더 많다.***

고객에게 보여주는 화면이 아니라 정확한 데이터를 다뤄야한다.

API는 각 오류 상황에 맞는 오류 응답 스펙을 정하고, JSON으로 정확한 데이터를 내려주어야 한다.

> 내 서버가 브라우저하고만 소통한다면 예외 페이지를 이용하면 되겠지만, 보통은 확장성을 고려해서
>
> JSON으로 응답하게 설계하기때문에 **API 예외처리를 쓰는 경우가 많을것**이다.
>
> > JSON을 이용해 브라우저 외 다른 기기들과도 소통할수있음



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

이 경우 정상적인  경우는 문제가 없지만 

예외가 발생한 경우 500상태 코드와 함께 예외가 WAS까지 전파되어  이전에 만든 예외 페이지가 View가 반환된다. (즉, HTML이 반환된것)

~~~
1. WAS(여기까지 전파) <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러(예외발생:throw new RuntimeException)
2. WAS 기본제공 오류 페이지 [HTTP Status 500 – Internal Server Error] 화면 송출
~~~

> 이번 예제는 response.sendError가 아니라 Exception (예외)



이전에 설명했듯이 예외 페이지를 적용했다면 아래와 같은 과정을 통해 View가 반환 되었을 것

~~~
1. WAS(여기까지 전파) <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러(예외발생: throw new RuntimeException)

2. WAS 예외 페이지 정보 확인

3. WAS (`/error-page/500` 다시 요청) -> 필터 -> 서블릿 -> 인터셉터 -> *컨트롤러(/errorpage/500)
-> View
~~~

> 예외가 발생해서 서블릿을 넘어 WAS까지 예외가 전달되면 HTTP 상태코드가 500으로 처리된다. 



하지만 브라우저를 제외 하고  HTML을 해석할수있는 경우가 많지 않다.

> 서버는  모바일 등 브라우저가 아닌 다양한 기기로부터 요청을 받을 수 있다.

그렇기 때문에 JSON 으로 데이터를 반환해 응답해야 하는데, 오류발생의 경우 Html오류 페이지가 나와버린다.

이건 원하는 바가 아니다.

오류 페이지 컨트롤러를 확장해서 요청 HTTP Header의 Accept 의 값이 application/json 일 때 html 오류 페이지 대신에 json으로 반환 하도록 할수있다.



**API 예외처리는 API에서 예외가 발생했을때 오류 상황에 맞는 오류 응답 스펙을 정하고, JSON으로 정확한 데이터를 내려주기위한 처리이다.**

> 물론 요청 Http가 json이 아닌경우 그냥 html을 돌려주면된다.



## 스프링 부트 API 오류 처리

---

이전에 학습했던 스프링 제공 예외 페이지 컨트롤러 **BasicErrorController에 들어가 보면** /error 동일한 경로를 처리하는 errorHtml() , error() **두 메서드를 확인할 수 있다.**

> 이전 포스트 꼭 파악하기

요청에 따라 하나는 html을 반환하고 하나는 json을 반환한다.



**errorHtml() :** 클라이언트 요청의 Accept 해더 값이 text/html 인 경우에는 errorHtml() 을 호출해서 view를 제공한다.

**error() :** 그외 경우에 호출되고 ResponseEntity 로 HTTP Body에 <u>JSON 데이터를 반환한다.</u>

> 이때 아래와 같이 예외 관련 정보를 Json으로 반환한다.
>
> ~~~
> {
>  "timestamp": "2021-04-28T00:00:00.000+00:00",
>  "status": 500,
>  "error": "Internal Server Error",
>  "exception": "java.lang.RuntimeException",
>  "trace": "java.lang.RuntimeException: 잘못된 사용자\n\tat 
> hello.exception.web.api.ApiExceptionController.getMember(ApiExceptionController
> .java:19...,
>  "message": "잘못된 사용자",
>  "path": "/api/members/ex"
> }
> ~~~



즉, 요청 HTTP Header 의 Accept 값이 text/html 가 아니고  application/json 일때 html 오류 페이지 대신에 json으로 예외 정보를 반환 하도록하는 메서드가 이미 정의 되어있다.

> 스프링이 제공하는 BasicErrorController는 이미 요청 Http Header의 Accept가 text/html 일때와 application/json일때를 처리하는 컨트롤러들이 각각 정의되어있는 것이다.



BasicErrorController 는 HTML 페이지를 제공하는 경우에는 매우 편리하다. 

> /error 경로에 매핑한 html을 보여주기만 하면 되기때문

그런데 **API 오류 처리는 다른 차원의 이야기이다**. API 마다, **각각의 컨트롤러나** 예외마다 **서로 다른 응답 결과를 출력해야 할 수도 있다.** 

> 단순히 오류관련 정보나 페이지를 반환하는게 아니라 API에 따라서 각기 다른 JSON 데이터를 반환해야한다.



### 즉, BasicErrorController가 API 오류 처리를 하는 메서드를 지원하긴 하지만 이 메서드 기능 만으로는 부족하다.

> @ExceptionHandler를 이용하면 BasicErrorController를 확장해서 예외마다 각기 다른 JSON 데이터를 반환하도록 할수있다.
>
> 아래서 설명한다.



## HandlerExceptionResolver (aka. ExceptionResolver)

---

BasicErrorController는 오류페이지 컨트롤러로 다음과 같은 동작 절차를 거친다고 설명했었다.

~~~
1. WAS(여기까지 전파) <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러(예외발생: response.sendError())

2. WAS 예외 페이지 정보 확인

3. WAS (`/error-page/500` 다시 요청) -> 필터 -> 서블릿 -> 인터셉터 -> *컨트롤러(/errorpage/500) -> View
~~~

기본적으로 Html과 Json을 다 컨트롤 할수있긴하지만 Json의 경우 그 기능도 부족하고 **에초에 WAS까지 예외가 올라갔다가 다시 내려오는 과정 자체가**

**비효율적**으로 느껴진다.



### **스프링은 이를 해결하고자 HandlerExceptionResolver 줄여서 ExceptionResolver를 도입했다.**

![image-20211228201047564](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20211228201047564.png)

기존에는 예외가 발생하면 WAS까지 예외가 전달 되었지만

![image-20211228200858914](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20211228200858914.png)

ExceptionResolver를 적용하면 디스패쳐 서블릿이 예외를 WAS로 던지지 않고  ExceptionResolver에게 예외를 해결하도록 한다.

즉, **예외를 중간에서 받아서 처리하고 WAS에겐 정상흐름처럼 보이도록 할수있다.**

> 예외가 WAS까지 올라가지 않는다.

또한 API 데이터 , 뷰 템플릿 , 예외 상태코드 변환 등을 자유롭게 적용할수있어서 WAS까지 예외를 보내지않고 예외를 이곳에서 모두 처리할 수 있다는 것이 핵심이다.



ExceptionResolver 클래스를 정의해서 예외 처리 로직을 짜고 ,WebConfig에 등록해서 사용한다.

> ~~~JAVA
> //MyHandlerExceptionResolver.java
> @Slf4j
> public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
>     //예외처리 로직
> }
> 
> //WebConfig.java
> @Override
> public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver>
> resolvers) { 
>     resolvers.add(new MyHandlerExceptionResolver());  //등록
> }
> ~~~



## **스프링 부트가 기본으로 제공하는 HandlerExceptionResolver (aka. ExceptionResolver)**

----

**직접 ExceptionResolver 를 구현하려고 하니 상당히 복잡하다.** 

스프링 부트는 기본으로 ExceptionResolver를 제공한다. 

> 3가지 ExceptionResolver를 제공한다.

**우선 순위**

~~~python
1. ExceptionHandlerExceptionResolver # @ExceptionHandler 을 처리한다. API 예외 처리는 대부분 이 기능으로 해결한다.

2. ResponseStatusExceptionResolver # HTTP 상태 코드를 지정해준다. 에너테이션으로 쉽게 상태코드를 바꿀수있다. 500->404

3. DefaultHandlerExceptionResolver # 스프링 내부 기본 예외를 처리한다.
~~~



ExceptionHandlerExceptionResolver는 매우 중요함으로 다음 포스트에서 자세하게 설명한다.



**2. ResponseStatusExceptionResolver** 

> + **ResponseStatusExceptionResolver 는 예외에 따라서 HTTP 상태 코드를 지정해주는 역할을 한다**
> + `@ResponseStatus 가 달려있는 예외` 또는 `ResponseStatusException 예외`의 경우 상태코드를 원하는 것으로 변경해주고 해당 상태코드로 response.sendError() 를 발생시켜주는 리졸버이다.
>
> ~~~java
> //사용자 정의 exception에 에너테이션을 활용해서 상태코드 지정
> @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청 오류")
> public class BadRequestException extends RuntimeException {
> }
> ~~~
>
> ~~~java
> @GetMapping("/api/response-status-ex1")
> public String responseStatusEx1() {
>  	throw new BadRequestException(); //예외 발생
> }
> ~~~
>
> 원래는 서버에서 처리되지 못한 예외는 모두 500상태코드를 가지지만 ResponseStatusExceptionResolver를 사용하면 내가 지정한 HttpStatus.BAD_REQUEST 즉 400 상태코드로 해당 예외를 다룰수있다.
>
> > 에너테이션 내부를 살펴보면 입력값을 바탕으로 sendError()를 호출하는것을 볼수있다.
> >
> > 결과적으로 sendError()를 호출했기때문에  WAS에서 다시 오류 페이지( /error )를 내부 요청한다.
>
> + **단점**
>
> 개발자가 정의한 exception이 아닌 시스템 내부 예외같은경우엔 적용할수없다.
>
> > ResponseStatusException 예외에 대해서 찾아보자



**3. DefaultHandlerExceptionResolver**

> 스프링 내부 예외를 알아서 처리해주는 리졸버



















## ExceptionHandlerExceptionResolver

---

+ 스프링 초보자들은 바로 이 개념만 알면 예외처리 기능을 사용할수있다.
+ 깊은 이해를 위해서는 이전 포스트를 참고해야한다.



### 예외를 처리할때 크게 2가지 경우를 고려해야한다.

**1. 예외페이지로 예외 처리하는 경우**

Json이 아닌 Html로 돌려줘도 되는경우  오류가 발생하면 스프링이 제공하는 **BasicErrorController** 를 사용하는게 편하다.

>  5xx, 4xx 관련된 오류 페이지를 보여주면 된다.

**2. API 예외처리하는 경우**

Json으로 돌려줘야하는 API 예외처리의 경우  예외에 따라서 각각 다른 데이터를 출력해야 할 수도 있다. 그리고 같은 예외라고 해도 어떤 컨트롤러에서 발생했는가에 따라서 다른 예외 응답을 내려주어야 할 수 있다

> 즉 세밀한 제어가 필요하다.

**BasicErrorController**도 Json 처리를 지원하는 메서드가 있지만 세밀한 제어를 하기엔 기능이 부족하다.

**HandlerExceptionResolver**를 직접 구현해서 사용하기엔 너무 번거롭다.

> 비유하자면 스프링의 편리함없이 서블릿으로 WAS를 하나하나 만드는것 같다.

즉, BasicErrorController와 HandlerExceptionResolver 둘다 사용하기 번거롭다.



### @ExceptionHandler

스프링은 API 예외 처리 문제를 해결하기 위해 **@ExceptionHandler**라는 애노테이션을 사용하는 매우
편리한 예외 처리 기능을 제공하는데, 이것이 바로 **ExceptionHandlerExceptionResolver** 이다. 

> 실무에서 API 예외 처리는 대부분 이 기능을 사용한다.



**@ExceptionHandler 예외 처리 방법**

@ExceptionHandler 애노테이션을 선언하고, 해당 컨트롤러에서 처리하고 싶은 예외를 지정해주면 된다. 
해당 컨트롤러에서 예외가 발생하면 이 메서드가 호출된다. 

> 참고로 지정한 예외 또는 그 예외의 자식 클래스는 모두 잡을 수 있다.
>
> 우선순위는 항상 자세한것부터 임으로 자식 예외가 있다면 자식먼저 실행된다.

~~~java
@Controller
public class APItest{
    
    //등록
    @ResponseStatus(HttpStatus.BAD_REQUEST) //상태코드도 변경 가능
    @ExceptionHandler(IllegalArgumentException.class) //IllegalArgumentException예외에 적용
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e); 
        return new ErrorResult("BAD", e.getMessage());
    }


    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
         if (id.equals("ex")) {
         throw new IllegalArgumentException("잘못된 파라미터"); //IllegalArgumentException 발생!
    }
}
~~~

> 컨트롤러 내부에서 IllegalArgumentException가 발생하면 illegalExHandle이 실행되어 처리한다.
>
> 
>
> **ResponseStatus를 사용**
>
> 이전 포스트에서 배운 ResponseStatusExceptionResolver의 @ResponseStatus를 이용해서 상태코드를 변경할수있다.
>
> ---------정확한 수정 필요-----



**실행 흐름**

![image-20211228200858914](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20211228200858914.png)

~~~~
1. 컨트롤러를 호출한 결과 IllegalArgumentException 예외가 컨트롤러 밖으로 던져진다.

2. 예외가 발생했으로 WAS로 전달되지 않고 대신 ExceptionResolver 가 작동한다. 가장 우선순위가 높은 ExceptionHandlerExceptionResolver 가 실행된다.

3. ExceptionHandlerExceptionResolver 는 해당 컨트롤러에 IllegalArgumentException 을 처리할 수 있는 @ExceptionHandler 가 있는지 확인한다.

4. illegalExHandle() 를 실행한다. @RestController 이므로 illegalExHandle() 에도 @ResponseBody 가 적용된다. 따라서 HTTP 컨버터가 사용되고, 응답이 다음과 같은 JSON으로 반환된다.

5. @ResponseStatus(HttpStatus.BAD_REQUEST) 를 지정했으므로 HTTP 상태 코드 400으로 응답한다.
~~~~

> 4. 번에서 응답이 Json으로 반환한다고했는데 실제로는 컨트롤러처럼 다양한 반환을 가질수있다. (뷰도 가능)



ExceptionHandler 즉, ResponseStatusExceptionResolver의 목적은 WAS까지 예외를 던지지 않고 대신 처리한후 정상흐름으로 바꿔버리는 것이다. 

>  상태코드 200 OK 이 된다.
>
>  ResponseStatus 를 이용해서 원하는 상태코드로 변경해줘도 된다.



예외가 발생해서 서블릿을 넘어 WAS까지 예외가 전달되면 HTTP 상태코드가 500으로 처리된다. 

`발생하는 예외에 따라서 400, 404 등등 다른 상태코드도 처리하고 싶다.` 또는 `오류 메시지, 형식등을 API마다 다르게 처리하고 싶다`면 ExceptionHandler 를 사용해서 쉽게 예외처리를 할수있다.



**아쉬운 점**

ExceptionHandler 코드가 해당 컨트롤러에 같이 섞여있어 핵심로직과 함께 있다는 것이다. 

클래스를 분리하고 @ControllerAdvice 또는 @RestControllerAdvice 를 사용하면 따로 분리할수있다.











