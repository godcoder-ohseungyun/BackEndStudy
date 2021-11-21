# 웹 서버 VS 웹 애플리케이션 서버

----

웹을 공부하기에 앞서 웹의 클라이언트 & 서버간 구성을 알아두고자 웹 서버와 was서버에 대하여 정리하고자 한다.



**웹은 HTTP를 기반으로 상호 통신**이 이루어진다.

> 웹 통신에 대한 부분은 이후 포스트에서 다루겠다.



### 웹 서버와 웹 애플리케이션 서버의 차이점

**웹 서버**

<img src="https://user-images.githubusercontent.com/68331041/141705717-6210c4fa-c80d-47bd-a1a0-ef22708101df.png" alt="image" style="zoom:67%;" />

+ 정적 리소스를 제공해 준다.

**웹 애플리케이션 서버**(WAS)

<img src="https://user-images.githubusercontent.com/68331041/141705730-23216f16-9bd8-49c1-99ff-9f31217a0b18.png" alt="image" style="zoom:67%;" />

+ WAS라고 불리며 프로그램 코드를 이용해 로직을 수행하며 정적리소스뿐만 아니라 동적 HTML과 HTTP API(JSON)를 클라이언트에게 제공해준다.
+ Spring 서블릿 등이 WAS기반으로 동작한다.



최근엔 둘의 경계가 모호하지만, 웹 서버는 정적 리소스를 제공하고 WAS는 애플리케이션 로직을 실행하는데 특화 되어있다고 생각하면 된다.



### WAS가 모든 역할을 담당하게 되면?

너무 많은 역할 담당,과부화,서버 다운의 문제가 발생한다.

![image](https://user-images.githubusercontent.com/68331041/141706142-2c926d85-7bff-4df5-84a3-10d968a0a49d.png)



### [문제해결]웹 서버와 WAS서버를 함께 사용하기

![image](https://user-images.githubusercontent.com/68331041/141706120-e8adf1a1-0e33-404d-a600-c1dd4a420f7c.png)

+ 정적 리소스는 웹서버에게 애플리케이션 로직 처리는 WAS에게 전담시킨다.

![image](https://user-images.githubusercontent.com/68331041/141706123-44dde4ec-c6da-4216-8e85-f235a9f1593e.png)

+ 효율적인 리소스 관리
+  정적 리소스가 많이 사용되면 Web 서버 증설
+  애플리케이션 리소스가 많이 사용되면 WAS 증설필요에 따라 



WAS 서버는 무겁고 복잡한 로직을 수행하기때문에 서버가 잘 죽는 단점이있다. 웹 서버와 함께 사용하면 WAS서버가 죽었을때 웹 서버를 이용해 오류.HTML을 클라이언트에게 전달할수있다.