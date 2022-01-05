

# JAR? WAR?

---

스프링 프로젝트를 만들다보면 JAR WAR를 선택하는 부분이 나오는데 이 둘의 차이점이 뭔지 궁금해서 찾아보게 되었다.



**JAR** (**J**ava **Ar**chive) **WAR** (**W**eb **A**pplication A**r**chive) 모두 JAVA의 jar 툴을 이용하여 생성된 압축(아카이브) 파일이며 **어플리케이션을 쉽게 배포하고 동작시킬 수 있도록 있도록 관련 파일(리소스, 속성파일 등)들을 패키징**해주는 것이다.



**JAR ( Java Archive )**

**.jar** 확장자 파일에는 Class와 같은 Java 리소스와 속성 파일, 라이브러리 및 액세서리 파일이 포함되어 있다.

쉽게 JAVA 어플리케이션이 동작할 수 있도록 **자바 프로젝트를 압축한 파일**이다.

JAR 파일은 **원하는 구조로 구성이 가능**하며 J **JRE(Java Runtime Environment)만으로도 실행이 가능**하다.



**WAR ( Web Application Archive )**

**.war** 확장자 파일은 servlet / jsp 컨테이너에 배치 할 수 있는 **웹 어플리케이션(Web Application) 압축 파일 포맷**

JSP, SERVLET, JAR, CLASS, XML, HTML, JAVASCRIPT 등 Servlet Context 관련 파일들로 패키징

WAR는 웹 응용 프로그램를 위한 포맷이기 때문에 **웹 관련 자원만 포함하고 있으며** **이를 사용하면 웹 어플리케이션을 쉽게 배포**하고 테스트 할 수 있다.

**WAR파일을 실행하려면 Tomcat, Weblogic, Websphere 등의 웹 서버 (WEB)또는 웹 컨테이너(WAS)가 필요**하다.







