# HTML, HTTP API, CSR, SSR

---

웹 서버의 응답 방식에 대하여 알아둘 필요가 있다.

웹은 HTTP를 기반으로 request & response 한다. 클라이언트의 요청에 따라 서버가 응답할수있는 3가지 방식에 대하여 설명하고자한다. 



### 1.정적리소스 응답

![image](https://user-images.githubusercontent.com/68331041/141707776-2ffb0996-5c5c-441b-9e9f-7acc001e75a6.png)

+ 클라이언트가 URL을 통해 요청시
+ 서버에 이미 생성되어있는 고정된 HTML 파일, CSS, JS, 이미지, 영상 등을 클라이언트에게 제공

### 2. 동적 리소스 응답

![image](https://user-images.githubusercontent.com/68331041/141707786-860dce9a-955a-4d7d-a471-caf4eef3cc62.png)

+ 클라이언트가 URL을 통해 요청시
+ 서버에서 동적으로 필요한 HTML 파일을 생성해서 전달 클라이언트에게 제공

### 3. HTTP API 응답

![image](https://user-images.githubusercontent.com/68331041/141707802-41a07fab-8393-42cd-8993-2e49c379b462.png)

+ HTML이 아니라 데이터포멧을 전달한다.
+ 주로 JSON 형식
+ 브라우저뿐 아니라 **다양한 시스템과의 통신이 가능**하다.
+ 데이터만 주고 받음, 데이터활용은 클라이언트가 별도 처리
+ **앱, 웹 클라이언트,머신,서버 to 서버 등**



### SSR & CSR

**SSR - 서버 사이드 렌더링**
• HTML 최종 결과를 서버에서 만들어서 웹 브라우저에 전달: 동적 HTML
• 주로 간단한 화면에 사용
• 관련기술: JSP, 타임리프 -> 백엔드 개발자



**CSR - 클라이언트 사이드 렌더링**
• HTML 결과를 **자바스크립트를 사용**해 웹 브라우저에서 동적으로 생성해서 적용
• 주로 SPA 화면에 사용, 웹 환경을 마치 앱 처럼 필요한 부분부분 변경할 수 있음

> 서버와 JSON 통신하여 AJAX를 이용해 SPA 구현

• 예) 구글 지도, Gmail, 구글 캘린더
• 관련기술: React, Vue.js -> 웹 프론트엔드 개발자