# HTTP 응답 데이터 처리

---

이전 포스팅에서 클라이언트가 서버에게 보내는 HTTP 요청 데이터를 서버측(Controller)에서 어떻게 처리하는지 알아보았다. 내용에 응답도 자연스럽게 포함되어있었다.

이번 포스트에서는 요청 처리후 응답에 대하여 자세하게 살펴보자

이번엔 서버의 응답 데이터를 클라이언트에게 어떻게 전송하는지 알아보자



### 서버가 응답데이터 RETURN하는   3가지방식

~~~
1.정적 리소스
예) 웹 브라우저에 정적인 HTML, css, js을 제공할 때는, 정적 리소스를 사용한다.

2.뷰 템플릿 동적 리소스
예) 웹 브라우저에 동적인 HTML을 제공할 때는 뷰 템플릿을 사용한다.

3.HTTP 메시지 사용 Json 통신
HTTP API를 제공하는 경우에는 HTML이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에
JSON 같은 형식으로 데이터를 실어 보낸다.
~~~



### 1.정적리소스

정적 리소스란 해당 리소스를 변경없이 그대로 클라이언트에게 보여주는것이다.

ex) 정적HTML



스프링에서의 정적 리소스 경로를 알아보자

<img src="https://user-images.githubusercontent.com/68331041/141665212-d8b1c07a-4d60-482c-a418-e9537d73f135.png" alt="image" style="zoom:67%;" />

src/main/resources 는 클래스 패스의 시작경로이고 하위 디렉토리 리소스를 정적으로 제공한다.

다음 경로에 파일이 들어있으면
src/main/resources/static/basic/hello-form.html

웹 브라우저에서 다음과 같이 실행하면 된다.
http://localhost:8080/basic/hello-form.html



### 2.뷰템플릿

템플릿을 거쳐서 HTML이 생성되고, 뷰가 응답을 만들어서 전달한다.

일반적으로 HTML을 동적으로 생성하는 용도로 사용하지만, 다른 것들도 가능하다. 뷰 템플릿이 만들 수 있는 것이라면 뭐든지 가능하다



**스프링 부트는 기본 뷰 템플릿 기본 경로**

src/main/resources/templates



아래와 같은 타임리프를 이용해 동적 뷰 템플릿을 생성했다고 하자

~~~html
//src/main/resources/templates/response/hello.html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
 	<meta charset="UTF-8">
 	<title>Title</title>
</head>
<body>
	<p th:text="${data}">empty</p>
</body>
</html>

~~~



Controller에 응답 데이터를 활용하여 뷰 템플릿을 동적으로 완성해보자



~~~java
/*
@ResponseBody 가 없으면 response/hello 로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링 한다.

@ResponseBody 가 있으면 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 response/hello 라는
문자가 입력된다.
*/

//@ResponseBody //for json response
@RequestMapping("/response-view-v2")
public String responseViewV2(Model model) {
 	model.addAttribute("data", "hello!!"); //데이터 동적으로 넘겨줌
 	return "response/hello";
}
~~~

뷰의 논리 이름인 response/hello 를 반환하면 templates/response/hello.html 경로의 뷰 템플릿이 렌더링 되는 것을 확인할 수 있다.

동적으로 넘긴 데이터를 이용하여 동적 페이지 생성후 클라이언트에게 렌더링 해준다.






### 3. HTTP 응답 - HTTP API, 메시지 바디에 직접 입력

HTTP API를 제공하는 경우에는  HTTP 메시지 바디에 JSON  같은 형식으로 데이터를 실어 보낸다.

> HTTP API란? https://www.inflearn.com/questions/126743

> **참고**
> HTML이나 뷰 템플릿을 사용해도 HTTP 응답 메시지 바디에 HTML 데이터가 담겨서 전달된다. 여기서 설명하는 내용은 정적 리소스나 뷰 템플릿을 거치지 않고, 직접 HTTP 응답 메시지를 전달하는 경우를 말한다.



~~~JAVA
@ResponseBody //JSON 통신
@PostMapping("/request-body-string-v4")
public String requestBodyStringV4(@RequestBody String messageBody) {
	  log.info("messageBody={}", messageBody);
 	  return "ok"; //문자열 전송
}

@ResponseBody
@PostMapping("/request-body-json-v5")
public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
 	log.info("username={}, age={}", data.getUsername(), data.getAge());
 	return data; //객체 JSON으로 전송
}
~~~

클라이언트는 html이나 뷰를 거치지 않고 순수 json 코드나 string을 직접 확인하게 된다.



### **@RestController**
@Controller 대신에 @RestController 애노테이션을 사용하면, 해당 컨트롤러에 모두 @ResponseBody 가 적용되는 효과가 있다. 따라서 뷰 템플릿을 사용하는 것이 아니라, HTTP 메시지 바디에 직접 데이터를 입력한다. 이름 그대로 Rest API(HTTP API)를 만들 때 사용하는 컨트롤러이다.

~~~
@Controller
@ResponseBody
//내부에 두 에너테이션이 정의되어있다.
~~~

