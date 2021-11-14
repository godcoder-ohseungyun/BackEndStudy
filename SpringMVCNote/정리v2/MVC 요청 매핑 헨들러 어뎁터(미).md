

# MVC 요청 매핑 헨들러 어뎁터

---

이전 포스트에서 MVC 구조에 대하여 알아본적이있다.

HTTP 요청이 오면 스프링에서는 다음과 같은 절차로 요청을 처리한다.

![image](https://user-images.githubusercontent.com/68331041/141668684-dfe9dccc-bd8e-4fc3-ae41-5a5b4eeadf89.png)

Dispatcher Servlet은 HTTP요청이 들어오면, 핸들러 매핑이후 핸들러 어뎁터 목록을 조회하여 적절한 헨들러 어뎁터를 불러온다.

핸들러 어뎁터가 요청 데이터를 핸들러(컨트롤러)에게 전달해주는 과정을 보자

![image](https://user-images.githubusercontent.com/68331041/141668769-798a2005-3b6a-43e1-88af-43159ff50d3e.png)

RequestMappingHandlerAdaptor 는 바로 이 ArgumentResolver 를 호출해서 컨트롤러(핸들러)가 필요로 하는 다양한 파라미터의 값(객체)을 생성한다. 

그리고 이렇게 파리미터의 값이 모두 준비되면 컨트롤러를 호출하면서 값을 넘겨준다.

다양한 파라미터를 유연하게 처리할 수 있는 이유가 바로 ArgumentResolver 덕분이다.

ReturnValueHandle 핸들러는 다양한 응답 파라미터를 유연하게 처리해준다.

 

### 동작 방식
ArgumentResolver 의 supportsParameter() 를 호출해서 해당 파라미터를 지원하는지 체크하고, 지원하면 resolveArgument() 를 호출해서 실제 객체를 생성한다. 그리고 이렇게 생성된 객체가 컨트롤러 호출시 넘어가는 것이다



### 그렇지만 이대로라면 핸들러 처리가 끝난후 뷰리졸버를 통해 뷰처리를 할것이다. 하지만 JSON을 통신을 하고싶다. HTTP컨버터는 어디에서 동작할까?



![image](https://user-images.githubusercontent.com/68331041/141669143-e7b909d5-3af7-4b3f-903c-d47108f4708e.png)

**요청의 경우** @RequestBody 를 처리하는 ArgumentResolver 가 있고, HttpEntity 를 처리하는 ArgumentResolver 가 있다. 이 ArgumentResolver 들이 HTTP 메시지 컨버터를 사용해서 필요한 객체를 생성하는 것이다. 

**응답의 경우** @ResponseBody 와 HttpEntity 를 처리하는 ReturnValueHandler 가 있다. 그리고 여기에서 HTTP 메시지 컨버터를 호출해서 응답 결과를 만든다.

