# Web Socket protocol

---

+ ### 실시간 양방향 송수신을 위한 프로토콜 



### 기존의 통신방식

기존 웹의 전형적인 브라우저 통신 방식은 HTTP 프로토콜을 사용한 Request & Response 이다.

이 방식은 새로운 데이터를 받아서 DOM을 다시 렌더링 하거나 브라우저를 새로고침하여 전체 렌더링을 통해 클라이언트에게 변경점을 적용했었다.

> **DOM**
>
> 웹 페이지를 스크립트 또는 프로그래밍 언어들에서 사용될 수 있게 연결시켜주는 역할을 담당한다.

HTTP 프로토콜은  클라이언트에 요청에 따른 반응만 할 수 있었을 뿐, 능동적으로 무언가 클라이언트에게 해주기엔 어려움이 있었다.

Polling기법을 통해 양방향 통신을 모방하긴했지만  1요청 1응답 후 통신을 끊는 HTTP프로토콜은 많은 낭비가 있었다.



**HTTP VS WebSocket**

<img src="https://user-images.githubusercontent.com/68331041/141105689-21aa74ca-43b1-4c52-9464-272870790df6.png" alt="image" style="zoom:67%;" />

> 요청 응답관계를 벗어나 자유롭게 송수신이 가능하다.
>
> 채팅,주식창 등 실시간 데이터 양방향 처리가 사용되는 서비스에 WebSocket protocol이 쓰인다.



## WebSocket protocol

+ http를 이용할 때, http를 이용하는 것처럼, 웹소켓을 이용할 때의 프로토콜은 ws를 활용한다.

+ http의 보안 이슈를 해결하기 위해 https를 활용하는 것처럼, 웹소켓에서도 wss를 통해 보안이 강화된 프로토콜을 사용

+ 웹소켓은 http를 통히 연결을 수립한다. 

  > 핸드쉐이킹





## 사용

다양한 언어로 WebSocket protocol을 사용 할수있지만 JS언어 엔진인 Node.js의 모듈 Socket.io가

대표적인 WebSocket protocol API이다. 매우 잘 정리되어있는 API로 WS을 공부한다면 한번은 공부하길 추천한다.

