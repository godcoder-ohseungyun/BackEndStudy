# MVC HTTP 데이터 처리

---

컨트롤러에서 맵핑을 통하여 HTTP요청을 받아왔으면 HTTP에 담긴 데이터들을 다룰수 있어야한다.

스프링은 HTTP 조회에 사용되었던 서블릿의 기능을 더 효율적이고 간편하게 만들어 HTTP 데이터들을 조회 및 사용할수있다.



~~~
*클라이언트 데이터 전달 방식*

1. url에 쿼리파라미터를 노출해서 전송: GET

2. HTTP 바디에 쿼리파라미터를 숨겨서 전송: POST
- 3번과 혼동이 올수있지만 html Form으로 POST 되는경우 쿼리파라미터로 인정된다고 이해하면 된다.

/*예시
POST /request-param ...
content-type: application/x-www-form-urlencoded
(공백)
username=hello&age=20  <- 바디
*/

3. HTTP 바디에 데이터를 직접 담아서 전송: POST,PUT,PATCH:  데이터 형식은 주로 JSON
~~~



## HTTP 정보를 조회하는것을 예시로 서블릿과 스프링의 차이점을 보겠다.



### 서블릿으로 HTTP 데이터 조회



**서블릿으로 HTTP 헤더 정보 조회**

~~~JAVA
    @Slf4j //for log
    @RestController
    public class RequestHeaderController {
        @RequestMapping("/headers")
        public String headers(HttpServletRequest request, //요청
                              HttpServletResponse response, //응답
                              HttpMethod httpMethod, //HTTP 메서드 조회
                              Locale locale, //Locale 정보
                              @RequestHeader MultiValueMap<String, String> headerMap, //모든 HTTP 헤더를 MultiValueMap 형식으로 조회한다
                              @RequestHeader("host") String host, //특정 HTTP 헤더를 조회한다. ex) host정보
                              @CookieValue(value = "myCookie", required = false) //특정 쿠키 조회 ex) myCookie
                                      String cookie
        ) {
            
            log.info("request={}", request);
            log.info("response={}", response);
            log.info("httpMethod={}", httpMethod);
            log.info("locale={}", locale);
            log.info("headerMap={}", headerMap);
            log.info("header host={}", host);
            log.info("myCookie={}", cookie);
            return "ok";
        }
    }
~~~

> MultiValueMap이란? keyA=value1&keyA=value2와 같은 여러데이터가 동시에 넘어오는 경우 여러 데이터 값을 하나의 키에 저장해야할때 사용한다.
>
> Map 과 달리 1키당 n개의 데이터를 담을수있음



HttpMethod , Locale 등등 여러 파라미터를 사용하여 데이터를 조회하는데 @Controller 계층에서 사용할수있는 파라미터들은 아래 공문에서 확인할수있다.

[Web on Servlet Stack (spring.io)](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments)



**서블릿으로 HTTP 쿼리 파라미터 정보 조회**

위에서 클라이언트가 데이터를 전송하는 3가지 방식중 1,2 번째 방식 GET , POST는 url에 담거나 Http 바디에 담는 차이만 있을뿐  동일하게 쿼리파라미터를 전송한다. 

따라서 이 둘의 조회 방식은 동일하다.

~~~JAVA
 @RequestMapping("/request-param-v1") //GET POST 둘다 동일하게 조회
public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
 	String username = request.getParameter("username");
 	int age = Integer.parseInt(request.getParameter("age"));
    
 	log.info("username={}, age={}", username, age);
    
 	response.getWriter().write("ok");
 	}
}
~~~



## 이제 스프링을 통해 HTTP 데이터를 조회해 보겠다.



### 스프링으로 HTTP 쿼리 파라미터 조회

**@RequestParam 이용하기**

~~~JAVA
/**
 * @RequestParam 사용
 * - 파라미터 이름으로 바인딩
 * @ResponseBody 추가
 * - View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
 */
@ResponseBody
@RequestMapping("/request-param-v2")
public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {
 	log.info("username={}, age={}", memberName, memberAge);
 	return "ok"; //ResponseBody를 사용하면 view 호출이 아니라 String을 응답 http 바디에 담아 보내준다. -클라이언트에 "ok"가 찍힘
}
~~~

~~~java
@RequestParam("username") String memberName

//"username" 이라는 name 속성을 가진 value를 memberName변수에 담아 사용한다.


@RequestParam String username 
//변수명과 Http 파라미터 이름이 같으면 생략가능

    
@RequestParam(required = false,defaultValue ="홍길동") String username 
//username 파라미터가 필수로 넘어오지 않아도 되도록 required false
//defalutValue로 디폴트 값 설정 가능
~~~



**@ModelAttribute 이용하기**

~~~java
//@RequestParam
public String RP(@RequestParam String username,@RequestParam int age){
    Member member = new Member(username,age);
    service.join(member);
}
~~~

 실제 개발에선 대부분 객체를 활용한다. @RequestParam을 사용하여 쿼리파라미터를 받아온 경우 위 코드처럼 별도로 객체화 하여 활용해야하는데

 @ModelAttribute는 위 과정을 완전 자동화 해준다. 즉, 받아온 쿼리파라미터를 바로 객체로 만들어서 제공해준다.

~~~java
//@ModelAttribute
@ResponseBody
@RequestMapping("/model-attribute-v1")
public String modelAttributeV1(@ModelAttribute Member member) {
 		log.info("username={}, age={}", member.getUsername(),
		member.getAge());
 return "ok";
}

//domain계층
@Data //getter setter 제공 lombok
public class Member {
 private String username;
 private int age; 
}
~~~

도메인 계층의 Member에 바로 쿼리파라미터를 담아서 객체를 생성해준다.

별도로 객체를 만들어줄필요없이 즉시 사용가능하다.

> 객체화 단계에서 파라미터 타입이 안맞으면 바인딩오류가 발생한다.



## 이제까지 쿼리파라미터 GET,POST 데이터 처리에 대해 알아보았다.



### Json 처럼 HTTP Message body에 데이터가 직접 담겨서 오는 경우는 @RequestParam , @ModelAttribute 를 사용할 수 없다.

> 단, HTML Form 형식으로 POST되는 경우는 쿼리파라미터로 인정된다.



### 이 경우 처리에 대하여 알아보자. HTTP 메세지 바디를 직접 열어서 조회해야한다.



### 서블릿을 이용하기

**InputStream 사용**

~~~JAVA
@Slf4j
@Controller
public class RequestBodyStringController {
    
	@PostMapping("/request-body-string-v1")
	public void requestBodyString(HttpServletRequest request,HttpServletResponse response) throws 		IOException {
 		ServletInputStream inputStream = request.getInputStream();
 		String messageBody = StreamUtils.copyToString(inputStream,StandardCharsets.UTF_8); //스트림은 바이트 코드이기때문에 어떤 인코딩을 사용할지 명시해줘야한다, 안하면 DEFAULT 사용
 		log.info("messageBody={}", messageBody);
 		response.getWriter().write("ok");
 	}
}
~~~





### 스프링 이용하기

**HttpEntity 사용**

~~~JAVA
/**
 * HttpEntity: HTTP header, body 정보를 편라하게 조회
 * - 메시지 바디 정보를 직접 조회하는데 @RequestParam , @ModelAttribute 사용 불가
 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
 *
 * 응답에서도 HttpEntity 사용 가능
 * - 메시지 바디 정보 직접 반환함 view 조회X 
 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
 */
@PostMapping("/request-body-string-v3")
public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
 	String messageBody = httpEntity.getBody();
 	log.info("messageBody={}", messageBody);
 	return new HttpEntity<>("ok");
}
~~~



















