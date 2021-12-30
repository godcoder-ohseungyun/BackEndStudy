# 스프링 MVC 이해

---

스프링 MVC 학습에 앞서 스프링 MVC 동작 구조를 먼저 살펴보고자한다.



스프링의 웹 기술은 MVC 아키텍쳐를 기본으로 한다.

> **MVC?**  Model, View, Controller로 구성요소를 분할하여 관리성을 향상시키는 디자인 패턴을 의미한다.
>
> **Model :** domain , repository , service 계층 처럼 에플리케이션 데이터를 저장하고 이 데이터를 가공한 기능 로직을 가지고있습니다. V,C와 독립적이어야합니다.
>
> **Controller:** Controller 계층으로 V와 M사이의 다리 역할을 합니다. URL 맵핑이나 이벤트 처리를 담당합니다.
>
> **View:** 사용자에게 보여지는 부분입니다. 데이터를 출력하거나 입력을 받습니다.



MVC의 핵심은 스프링의 **Dispatcher Servlet**이다. 클라이언트의 **요청**을 제일 앞에서 **전담하여 처리**해준다.

**Front Controller**라고도 불린다.

기존엔 개발자가 DispatcherServlet의 모든 처리를 하나하나 설계해야 했지만 이제는 개발 핵심 로직에만 집중하면 된다.

 DispatcherServlet이 해당 어플리케이션으로 들어오는 모든 요청을 핸들링해주고 공통 작업을 처리면서 상당히 편리하게 이용할 수 있게 되었다.

![image](https://user-images.githubusercontent.com/68331041/141708898-8b25fc8e-ae9a-4364-bf9d-7e1b6b7adb86.png)

### 동작 순서

1. **핸들러 조회:** 핸들러 매핑을 통해 요청 URL에 매핑된 핸들러(컨트롤러)를 조회한다.
2. **핸들러 어댑터 조회:** 핸들러를 실행할 수 있는 핸들러 어댑터를 조회한다.
3. **핸들러 어댑터 실행:** 핸들러 어댑터를 실행한다.
4. **핸들러 실행:** 핸들러 어댑터가 실제 핸들러를 실행한다.
5. **ModelAndView 반환:** 핸들러 어댑터는 핸들러가 반환하는 정보를 ModelAndView로 변환해서 반환한다.
6. **viewResolver 호출:** 뷰 리졸버를 찾고 실행한다. JSP의 경우: InternalResourceViewResolver 가 자동 등록되고, 사용된다.
7. **View 반환:** 뷰 리졸버는 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체를 반환한다. JSP의 경우 InternalResourceView(JstlView) 를 반환하는데, 내부에 forward() 로직이 있다.
8. **뷰 렌더링:** 뷰를 통해서 뷰를 렌더링 한다