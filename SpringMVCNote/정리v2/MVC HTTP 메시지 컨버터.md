# MVC HTTP 메시지 컨버터

---

뷰 템플릿으로 HTML을 생성해서 응답하는 것이 아니라, HTTP API처럼 JSON 데이터를 HTTP 메시지
바디에서 직접 읽거나 쓰는 경우 HTTP 메시지 컨버터를 사용하면 편리하다.



앞선 포스팅에서 Controller 계층에서  HTTP Message body 데이터 조회와 응답에서 모두 HTTP 메시지 컨버터가 실행된다.

> JSON 포스트도 읽어보면 도움이 된다.
>
> JSON 통신의 Serialize & Deserialize 과정 처리를 해주는 기능이라고 생각하면 된다.



### 스프링 MVC는 다음의 경우에 HTTP 메시지 컨버터를 적용한다

**HTTP 요청데이터 읽기:** @RequestBody , HttpEntity(RequestEntity) 사용시 실행
**HTTP 응답데이터 생성:** @ResponseBody , HttpEntity(ResponseEntity) 사용시 실행



### **예시) @ResponseBody **

![image](https://user-images.githubusercontent.com/68331041/141666653-6782832f-8b6c-46b8-9d7d-c378d877dae2.png).

@ResponseBody를 사용하면  **viewResolver 대신에 HttpMessageConverter 가 동작**한다.

HTTP의 BODY에 문자 내용을 직접 반환하게 된다.

뷰를 거치지 않고 JSON통신을 직접하게된다.



### **동작과정**

~~~
HttpMessageConverter 에 
ByteArrayHttpMessageConverter, StringHttpMessageConverter, MappingJackson2HttpMessageConverter 등 다양한 처리 컨버터가 내장되어있다.
--왼쪽부터 우선순위가 높다.

타입과 미디어 타입 둘을 체크해서 어떤 컨버터로 처리할지 결정한다. 만약 만족하지 않으면 다음 메시지 컨버터로 우선순위가 넘어간다.
~~~

**HTTP 요청 데이터 읽기**

> 1.HTTP 요청이 오고, 컨트롤러에서 @RequestBody , HttpEntity 파라미터를 사용한다
>
> 2.메시지 컨버터가 메시지를 읽을 수 있는지 확인하기 위해 canRead() 를 호출한다.
>
> 3.대상 클래스 타입을 지원하는가.
>
> > 예) @RequestBody 의 대상 클래스 ( byte[] , String , HelloData )
>
> 4.HTTP 요청의 Content-Type 미디어 타입을 지원하는가.
>
> > 예) text/plain , application/json , */*
>
> 5.canRead() 조건을 만족하면 read() 를 호출해서 객체 생성하고, 반환한다.

**HTTP 응답 데이터 생성**

> 1.컨트롤러에서 @ResponseBody , HttpEntity 로 값이 반환된다. 
>
> 2.메시지 컨버터가 메시지를 쓸 수 있는지 확인하기 위해 canWrite() 를 호출한다.
>
> 3.대상 클래스 타입을 지원하는가.
>
> > 예) return의 대상 클래스 ( byte[] , String , HelloData )
>
> 4.HTTP 요청의 Accept 미디어 타입을 지원하는가.(더 정확히는 @RequestMapping 의 produces )
>
> > 예) text/plain , application/json , */*
>
> 5.canWrite() 조건을 만족하면 write() 를 호출해서 HTTP 응답 메시지 바디에 데이터를 생성한다.

