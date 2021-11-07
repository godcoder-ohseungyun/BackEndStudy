# 웹 프론트엔드 개요

---

+ **W3C(world wide web consortium):** html을 비롯한 웹 표준을 정의하는 단체 
+ **인터넷(internet)**: 네트워크의 집단으로 전세계를 하이퍼링크를 통해 상호연결
+ **웹 브라우저:**  웹 서버에서 이동하며(navigate) 쌍방향으로 통신하고 HTML문서나 파일을 출력하는 그래픽 사용자 인터페이스기반의 응용 소프트웨어이다. 
+ **웹 서버**

> 1. **웹 서버  :** 웹 브라우저와 같은 클라이언트로부터 HTTP요청을 받아들이고, HTML 문서와 같은 웹 페이지를 반환하는 **컴퓨터 프로그램**
> 2. **웹 서버 (하드웨어)** : 위에 언급한 기능을 제공하는 컴퓨터 프로그램을 실행하는 **컴퓨터**

+ **브라우저 전쟁**: 웹 브라우저들의 발전경쟁 , w3c가 브라우저의 발전 속도를 따라가지 못하는 것에 불만을 품고 웹 브라우저들은 플러그인이라는 개념을 통해 브라우저의 확장 기능을 구축했었다.



## [목차]

> #### HTML파트
>
> > [HTML5 정의](#HTML5)
> >
> > [HTML5 기본 태그](#웹 코딩)
> >
> > [HTML5 시멘틱 구조 기본 코드](#[중요] 시멘틱 태그)
>
> #### CSS파트
>
> > 



## HTML5

---

+ #### 웹 표준 기술의 총칭(HTML+CSS3+JS 등)

+ #### 주요기능

> 플러그인 없이 **멀티미디어를 지원**한다.
>
> 2,**3차원 그래픽**을 지원한다.
>
> HTML5는 **폴링이 필요없고 서버와 소캣 통신이 가능**하다. (실시간 양방향 통신 지원)
>
> > 폴링: 일정한 주기로 HTTP요청을 송신하여 마치 양방향 통신처럼 꾸미는 웹 소켓 이전 기술
> >
> > 롱폴링: 서버가 폴링데이터를 잠시 대기 시켜 부하를 그나마 줄이는 기법
> >
> > 둘다 서버의 부하가 심했다.
>
> 장치에 접근해 **장치 데이터를 활용**할수있다. ex) 인공위성 데이터를 이용하는 GPS웹
>
> **오프라인**에서도 **동작**할수있다.
>
> **시멘틱 웹**을 지원한다.
>
> > ~~~html
> > //의미론적 태그
> > //<header>라는 의미론적 테그를 활용할수있다.
> > <div id="header">기존헤더표현</div>   ->   <header>시멘틱헤더표현</header>
> > ~~~
> >
> > ~~~html
> > //웹의 지능화
> > --기존엔 바나나와 노란색의 관계를 웹은 이해하지 못함, 단순 태그를 읽고 동작
> > --시멘틱 웹은 바나나가 노랗고 길다는것을 데이터로 저장, 활용할수있음
> > ~~~
>
> **[중요] 웹으로** 일반 **데스크탑용 응용프로그램도 구현**할수있다.
>
> > 애플리케이션 수준의 웹 실현이 가능하다.
> >
> > 리엑트 등의 엔진을 이용해 모바일 웹앱 개발도 가능하다.





## 웹 코딩

---

+ **1. 오류검증 F12**

> [Console]: 오류확인 및 자바 스크립트 추가 입력
>
> [Element]: HTML 계층 구조와 CSS 파악

+ **2. HTML 코딩 기본구조**

~~~html
<!DOCTYPE html> <!--html문서임을 명시-->

<html lang="ko"> <!--모든 태그는 html 태그 안에 작성: 속성값 lang=""을 갖는다.-->


<head> <!--웹 페이지 추가 정보 및 body태그에 필요한 데이터를 제공: meta,title,script,link,style,base 태그 존재-->
    <!--meta 태그: 웹페이지 추가정보-->
    <meta charset="UTF-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Document</title>

    <link rel="stylesheet" href="Style.css"> <!--link: 외부 css 파일 추가 및 외부 파일 추가-->
    <script src="OuterJavaScript.js"></script> <!--외부 js파일 추가-->
    
</head>

<body>
    <!--구성-->
</body>

</html>
~~~

+ **3. HTML 기본태그**

> ### 문자태그
>
> | <h1>, <h2>, ... | 제목                                                  |
> | :-------------: | ----------------------------------------------------- |
> |       <p>       | 문단                                                  |
> |      <br>       | 줄 바꿈, 닫기태그 없음                                |
> |  <blockquote>   | 인용문, 들여쓰기 적용됨                               |
> |    <strong>     | 텍스트 굵게, 주로 중요한 내용일 때                    |
> |       <b>       | 텍스트 굵게, 단순히 굵게 표시할 때                    |
> |      <em>       | 텍스트 기울임, emphasis의 준말, 강조할 때             |
> |       <i>       | 텍스트 기울임, italic의 준말, 단순히 기울여 표시할 때 |
> |       <u>       | 텍스트 밑줄                                           |
> |       <s>       | 텍스트 취소선                                         |
> |     <&nbsp>     | 공백                                                  |
>
> > **HTML은 여러개의 공백을 1개로 인지**한다. 따라서 공백 태그를 사용해야한다.
>
> 
>
> ### 앵커태그
>
> | <a href="" target="_blank"></a> | 하이퍼 링크    |
> | ------------------------------- | -------------- |
> | href=                           | 링크 주소      |
> | target="_blank"                 | 새 탭에서 열기 |
>
> > 웹 페이지 내부 연결은 href="#id"
>
> 
>
> ### 목록태그
>
> | <ol type=""> | 순서가 있는 목록(ordered list)       |              |
> | ------------ | ------------------------------------ | ------------ |
> | type=        | "1"                                  | 숫자(기본값) |
> | "a"          | 영문 소문자                          |              |
> | "A"          | 영문 대문자                          |              |
> | "i"          | 로마 숫자 소문자                     |              |
> | "I"          | 로마 숫자 대문자                     |              |
> | start="3"    | 3부터 시작                           |              |
> |              |                                      |              |
> | <ul>         | **순서가 없는 목록(unordered list)** |              |
>
> > ~~~html
> > <ul>  
> >     <li>항목 1</li>  
> >     <li>항목 2</li> 
> > </ul>
> > ~~~
>
> 
>
> ### 테이블 태그
>
> ~~~html
> <table>
>   <tr>
>     <td>1행 1열</td>
>     <td>1행 2열</td>
>   </tr>
>   <tr>
>     <td>2행 1열</td>
>     <td>2행 2열</td>
>   </tr>
> </table>
> ~~~
>
> > 외에도 구체적 기능 존재
>
> 
>
> ### 미디어 태그
>
> **img:** 내용물을 가질수없음: 닫는태그 x
>
> **audio,video:** 내용물을 가질수있음:닫는태그o
>
> ![image](https://user-images.githubusercontent.com/68331041/139182178-f16ca4a6-0fa8-4f55-976c-0b18d8d384d3.png)
>
> > ~~~html
> > <!--audio,video 기본사용-->
> > <audio src="" controls="controls"> </audio>
> > 
> > <!--위 처럼 하면 브라우저마다 지원하는 audio,video 타입이 다르기 때문에 문제발생. 확장자 형식 문제는 아래와 같이 source를 이용해 다양한 타입 추가가능 해결-->
> > <audio controls="controls">
> > 	<source src=".mp3" type="audio/mp3">
> >     <source src=".mp4" type="audio/mp4">
> > </audio>
> > ~~~
> >
> > > **type속성을 꼭 넣어주자.** type 속성이 없으면 브라우저에서 재생가능한지 다운로드해서 직접 확인해야하는 낭비 발생
>
> 
>
> ### 입력을 위한 태그 
>
> <img src="https://user-images.githubusercontent.com/68331041/139190518-64178c20-ad8e-43cc-b9cf-73a9cf9a89a7.png" alt="image" style="zoom:67%;" />
>
> ~~~html
> <form action="전송위치" method="전송방식(GET,POST..)">
>     <label for="name">이름</label> <!--for="대상id"-->
>     <input type="text" id="name">
>     
>     <input type="file" id="search">
>     
>     <select name="sex">
>         <option>남</option>
>         <option>여</option>
>     </select>
>     
>     <textarea name="메모장"></textarea>
>     
>     <input type="submit">
> </form>
> ~~~
>
> > **label 태그**를 이용해서 for="대상 input 태그 id" 로 **인풋테그 라벨링 가능**
> >
> > **select 태그**로 **선택옵션** 만들기 가능
> >
> > **textarea 태그**로 여러줄 입력 가능

+ **4. HTML 구조화 태그**

  > ####  HTML  요소들의 공간을 div와 span등으로 구분해야 css와 js 적용이 용이하다.
  >
  > **인라인태그 vs 블록태그**
  >
  > > **인라인태그:** 요소 크기만큼만 공간을 차지합니다.
  > >
  > > **블록태그:** 요소크기와 상관없이 무조건 웹 화면 한줄을 차지합니다.
  >
  > 
  >
  > ### [중요] 시멘틱 태그
  >
  > > • 특정 태그에 의미를 부여한 웹
  > >
  > > > header, nav, aside ..etc
  > >
  > > • 프로그램이 코드를 읽고 의미를 인식할 수 있는 지능형 웹
  > >
  > > 
  > >
  > > <img src="https://user-images.githubusercontent.com/68331041/139192019-8b03e3b3-8201-4c2b-8664-3a33d0bc4c55.png" alt="image" style="zoom:67%;" />
  > >
  > > 
  > >
  > > **기본 공간분할 코드**(시멘틱 구조)
  > >
  > > ~~~html5
  > >    <body>
  > >         <!-- 전체를 감싸는 태그 -->
  > >         <div id="page-wrapper">
  > > 
  > >             <!-- 헤더 -->
  > >             <header id="main-header"></header> 
  > > 
  > >             <!-- 네비게이션 -->
  > >             <nav id="main-navigation"></nav> 
  > >             
  > >             <!-- 본문 -->
  > >             <div id="content">
  > >                 <!-- 본문 좌측-->
  > >                 <section id="main-section"></section> 
  > >                 <!-- 본문 우측 -->
  > >                 <aside id="main-aside"></aside>
  > >             </div> 
  > >             
  > >             <!-- 푸터 -->
  > >             <footer id="main-footer"></footer>
  > >         </div>
  > >     </body>
  > > ~~~



## CSS3

---

+ **HTML 요소의 구조와 스타일을 정의**



+ **선택자**

> https://developer.mozilla.org/ko/docs/Web/CSS/CSS_Selectors
>
> 
>
> ### 1.기초 선택자
>
> > class는 중복을 허용 id는 중복을 허용하지 않는다.
>
> | 선택자       | 사용 예시                               |
> | ------------ | --------------------------------------- |
> | 전체 선택자  | * {}                                    |
> | id 선택자    | #id {}                                  |
> | class 선택자 | .header {}                              |
> | 테그 선택자  | h1 {}                                   |
> | 후손 선택자  | header h1 {}  \|    .header h2 {}       |
> | 자손 선택자  | header > h1 {}   \|     #header > h1 {} |
> | 속성선택자   | input[type="text"] {}                   |
>
> 이 외에도 반응선택자,구조선택자 등등이 있다.
>
> ~~~html
> <!-- 선택자 종합 활용-->
> .class #id #person1 { }
> ~~~
>
> 
>
> #### 후손 vs 자손
>
> <img src="https://user-images.githubusercontent.com/68331041/139211599-39fcfa7c-6a07-485c-a98c-5c0c24a9b4be.png" alt="image" style="zoom:67%;" />
>
> ~~~html
> <div id="id">
>     <h1></h1>
>     <h2></h2>
>     <ul>
>         <li></li>
>         <li></li>
>         <li></li>
>     </ul>
> </div>
> ~~~
>
> 부모 바로 한단계 아래의 태그를 **자손**
>
> 부모 아래 존재하는 모든 태그를 **후손**
>
> **헷갈리는 사항**
>
> ~~~html
> <!--후손선택자: #id 후손인 h1과 h2를 선택하고싶을때-->
> 1.  #id h1,h2 {}  --#id 태그의 후손 h1 과 일반적인 h2 태그를 선택  <x>
> 2.  #id h1,#id h2 --#id태그의 후손 h1,h2 태그를 선택  <o>
> ~~~
>
> ~~~html
> <!--자손 선택자-->
> #id > h2 {} --자손 h2만 선택 후손 h2는 선택되지 않는다.
> 
> <!--테이블에 스타일 적용시 자손 선택자를 사용하지 말자. 자동으로 <tbody>라는 태그를 추가해 버리기때문에 자손이 아니라 후손선택자를 사용해야한다.-->
> ~~~
>
> 
>
> ### 2. 반응 ,상태,구조 선택자
>
> + **반응 선택자**
>
> > ~~~html
> > h1:hover {} <!--마우스 커서시-->
> > h1:active {} <!--마우스 클릭시-->
> > ~~~
>
> + **상태 선택자**
>
> > ~~~html
> > #checkbox:checked {} <!--인풋 테그의 사용자 입력에 반응하여 작업을 수행할때 사용한다.-->
> > ~~~
> >
> > ![image](https://user-images.githubusercontent.com/68331041/139215137-98f25b03-d31c-4d00-b437-701124f32122.png)
> >
> > 
>
> + **구조 선택자**
>
> > 형제관계의 요소들을 선택할때 사용
> >
> > ~~~html
> > <!--구조 선택자-->
> > li:first-child {} --1 선택
> > li:last-child {} --5 선택
> > li:nth-child(2n) --짝수번째 요소들 선택
> > 
> > <ul>
> >     <li>1</li> --li들은 형제관계이다.
> >     <li>2</li>
> >     <li>3</li>
> >     <li>4</li>
> >     <li>5</li>
> > </ul>
> > ~~~



+ **CSS 단위**

  > | **%**  | **백분율** |
  > | ------ | ---------- |
  > | **em** | **배수**   |
  > | **px** | **픽셀**   |
  >
  > ~~~html
  > <!--em-->
  > 100% = 1em
  > 150% = 1.5em
  > ~~~
  >
  > 
  >
  > | **rgba(red,green,blue,alpha)** | **rgb + alpha는 투명도** |
  > | ------------------------------ | ------------------------ |
  > | **#000000**                    | **HEX 코드**             |
  >
  > > 색상 코드 사이트 참고
  >
  > 
  >
  > | url('경로') | url단위 |
  > | ----------- | ------- |
  >
  > ~~~
  > background-image: url('경로')
  > ~~~



+ **CSS 속성**

> + **height & width는 요소 글자 크기임**
>
> 
>
> #### 1.**margin & padding**
>
> > - **상하좌우 개별**
> >
> > > **<img src="https://user-images.githubusercontent.com/68331041/139222575-437da221-3a75-4327-bbca-a1a7cbf6bf4f.png" alt="image" style="zoom:50%;" />**
> >
> > + **네방향 속성 지정 & 두방향 묶음 속성 지정**
> >
> > > **<img src="https://user-images.githubusercontent.com/68331041/139222808-29f5164a-a9d8-4475-9611-bdca71ef6c28.png" alt="image" style="zoom:50%;" />**
>
> 
>
> ### 2. border
>
> > **<img src="https://user-images.githubusercontent.com/68331041/139223383-a32639f9-33c4-4063-91ae-d3874221d4a1.png" alt="image" style="zoom:50%;" />**
> >
> > 
> >
> > **<img src="https://user-images.githubusercontent.com/68331041/139223410-dd535fc7-1bb2-43cf-b2d5-5a92e9c1a241.png" alt="image" style="zoom:50%;" />**
> >
> > 
> >
> > **<img src="C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20211028180143904.png" alt="image-20211028180143904" style="zoom:50%;" />**
>
> 
>
> #### **3. 가시속성**
>
> > ~~~html
> > display: none         --보이지 않음
> > 		block 		 -- 블록 형식으로 지정 : 블록태그의 width는 부모요소의 width이다.
> > 		inline		 -- 인라인 형식으로 지정
> > 		inline-block  --인라인형식이지만 블록 형식으로 지정
> > ~~~
> >
> > > **inline-block이란?**
> > >
> > > > inline 형식은 요소 자체의 크기를 갖기때문에 height margin등 속성이 적용이 안됨
> > > >
> > > > **inline-block**은 inline 처럼 **개행이 없게** 하지만 **height margin등 속성 적용이 가능** 
>
> #### **4. 배경속성**
>
> > **<img src="https://user-images.githubusercontent.com/68331041/139225548-3e38373e-d128-446b-be81-ea14910eb0ff.png" alt="image" style="zoom:50%;" />**
> >
> > ~~~html
> > <style>
> >     body {
> >         background-image: url('경로1'),url('경로2'); /*여러개의 이미지 레이어드 가능: 왼쪽 부터 우선순위*/
> >         background-size: 1em;
> >     }
> > </style>
> > ~~~
>
> #### **5. 글자속성**
>
> > ~~~html
> > font-size: 크기
> > font-weight: 두께
> > font-family: 서체
> > ----------------------------------------------------
> > text-align: start|end|left|right|center|justify|..
> > line-height: px 로 높낮이 조절도 가능하다.
> > <!--
> > 
> > [주의!]인라인 태그는 요소 넓이가 없기때문에 text-align: center를 해도 적용이 안된다.
> > 
> > left : 왼쪽 정렬입니다.
> > right : 오른쪽 정렬입니다.
> > center : 가운데 정렬입니다.
> > justify : 양쪽 정렬입니다.
> > -->
> > 
> > ~~~
>
> 
>
> ### 6.위치속성
>
> > ~~~html
> > position: static|absolute|relative|fixed|inherit
> > 
> > static --기본값, 다른 태그와의 관계에 의해 자동으로 배치되며 위치를 임의로 설정해 줄 수 없습니다.
> > absolute --절대 좌표와 함께 위치를 지정해 줄 수 있습니다.
> > relative --원래 있던 위치를 기준으로 좌표를 지정합니다.
> > fixed --스크롤과 상관없이 항상 문서 최 좌측상단을 기준으로 좌표를 고정합니다.
> > inherit --부모 태그의 속성값을 상속받습니다.
> > ---------------------------------------------------------------------------------------
> > 
> > 좌표를 지정 해주기 위해서는 left, right, top, bottom 속성과 함께 사용합니다.
> > position을 absolute나 fixed로 설정시 가로 크기가 100%가 되는 block 태그의 특징이 사라지게 됩니다.
> > 
> > <!--예제-->
> > #box1 { position: static; top: 20px; left: 30px; }
> > #box2 { position: relative; top: 20px; left: 30px; }
> > #box3 { position: absolute; top: 20px; right: 30px; }
> > #box4 { position: fixed; top: 20px; right: 30px; }
> > ~~~
> >
> > #### **[중요!] 실전예제)**
> >
> > > **body**
> > >
> > > <img src="C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20211028184438166.png" alt="image-20211028184438166" style="zoom:67%;" />
> > >
> > > **좌표 적용**
> > >
> > > > left,right,top,bottom 속성으로 좌표 지정
> > > >
> > > > position: absolute -> 절대 좌표
> > >
> > > <img src="https://user-images.githubusercontent.com/68331041/139231020-d4476d8c-2cdc-4e43-80e5-666e4d7c5af6.png" alt="image" style="zoom:50%;" />
> > >
> > > **z-index적용**
> > >
> > > <img src="C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20211028184340254.png" alt="image-20211028184340254" style="zoom:50%;" />
> > >
> > > 
> > >
> > > ### absolute 키워드 주의사항
> > >
> > > ~~~html
> > > absolute 키워드를 부여받은 요소는 영역할당에서 제외됨으로 다른 요소를 가리게 될수있다.
> > > 
> > > <h1>제목</h1>
> > > <div>
> > >     <div class="box">red</div>
> > >     <div class="box">green</div>
> > >     <div class="box">blue</div>
> > > </div>
> > > 
> > > 3가지 해결법
> > > 1. 예제의 경우 부모 div 태그에 height 속성으로 원래 박스들이 다른 요소를 가리지 않게 영역을 확보해준다.
> > > 2. 부모 div태그에 position: relative 속성 부여, 자손의 포지션이 부모div 태그 위치를 기준으로 영역을 할당받는다.
> > > 3. 스크롤적용 *아래 사진
> > > ~~~
> > >
> > > **<img src="https://user-images.githubusercontent.com/68331041/139233755-5e05eae9-29d6-457d-9607-311e1a1ff52c.png" alt="image" style="zoom:50%;" />**
> > >
> > > > 특정 방향으로만 스크롤가능하게 할수도있다.
>
> 
>
> ### 7. 유동속성
>
> > **float**
> >
> > > 공지사항 같은 것을 표기할때 사용
> >
> > ~~~html
> > float: left|right
> > <!--
> > 태그를 왼쪽 또는 오른쪽에 고정
> > 여러개를 float하는경우 순차적으로 옆으로 쌓인다.
> > -->
> > ~~~
>
> 
>
> ### 8.그레이디언트 속성
>
> > **그레이디언트 속성:** 두가지 색을 혼합채색하는 기능
> >
> > >  ex) 위는 연두색 아래는 진한 녹색인 nav 바



## **[중요]CSS 레이아웃 배치** 

> #### 1.**레이아웃 기초** 
>
> **<img src="https://user-images.githubusercontent.com/68331041/139221357-dddcf5f3-1781-40d5-bc9a-192e4a5c31d0.png" alt="image" style="zoom:50%;" />**
>
> 
>
> > **요소 전체 사이즈 측정:** border padding margin은 요소 양쪽에 위치함으로 x2
> >
> > > **넓이** = width + 2* (border + padding + margin)
> > >
> > > **높이** = height+ 2* (border + padding + margin)
>
> 
>
> ### 2. 레이아웃 정렬법
>
> + #### **레이아웃수평정렬**
>
>   > overflow 와 float 속성 사용
>
> **<img src="https://user-images.githubusercontent.com/68331041/139515392-1cc8dc2f-32ec-4339-bf80-a629642734c6.png" alt="image" style="zoom:67%;" />**
>
> > 부모 태그 overflow: hidden -> 부모테그 인라인화
> >
> > > **overflow: hidden 미적용**
> > >
> > > ![image](https://user-images.githubusercontent.com/68331041/139515776-6aa51d2d-e9a9-41eb-9970-1638f484a8a2.png)
> > >
> > > **overflow: hidden 적용**
> > >
> > > ![image](https://user-images.githubusercontent.com/68331041/139515743-ec2b838c-09fb-45f6-9637-f13700d5e5fb.png)
> >
> > 자손 float: left로 왼쪽부터 적재
> >
> > #### **overflow** 
> >
> > > \- **visible** : 기본 값입니다. 넘칠 경우 컨텐츠가 상자 밖으로 보여집니다.
> > >
> > > \- **hidden** : 넘치는 부분은 잘려서 보여지지 않습니다.
> > >
> > > \- **scroll** : 스크롤바가 추가되어 스크롤할 수 있습니다.(가로, 세로 모두 추가 됩니다.)
> > >
> > > \- **auto** : 컨텐츠 량에 따라 스크롤바를 추가할지 자동으로 결정됩니다.( 필요에 따라 가로, 세로 별도로 추가될 수도 있습니다.)
>
> 
>
> + #### **레이아웃 중앙정렬**
>
> > width 속성을 부여하고 margin 속성을 ‘0 auto’로 입력
>
> 
>
> + #### **One True 정렬 레이아웃**
>
> **<img src="https://user-images.githubusercontent.com/68331041/139515570-df8daae8-1ebf-4b05-b8ae-a3c9f0837276.png" alt="image" style="zoom:67%;" />**
>
> > **수평정렬,중앙정렬 등 다양한 정렬을 이용하여 독립된 요소들을 배치**
>
> ~~~html
> <!--간단한 예시(수평,수직정렬만 사용)-->
> <!DOCTYPE html>
> <html>
> 
> <head>
>     <title>One True Layout</title>
> 
>     <style>
>         body {
>             width: 500px; /*레이아웃중앙정렬*/
>             margin: 10px auto;  
>         }
> 
>         #middle { /*레이아웃 수평정렬을 위한 부모요소 hidden*/
>             overflow: hidden; 
>             }
> 
>         #left {
>             float: left; /*레이아웃 수평정렬 왼*/
>             width: 150px;
>             background: red;
>             }
> 
>         #right {
>             float: right; /*레이아웃 수평정렬 오*/
>             width: 350px;
>             background: blue;
>             }
> 
>         #top {
>             background: green;
>             }
> 
>         #bottom {
>             background: purple;
>             }
>     </style>
> </head>
> 
> <body>
>     <div id="top">Lorem ipsum dolor sit amet, consectetur adipiscing. </div>
> 
>     <div id="middle">
>         <div id="left">Lorem ipsum dolor sit amet, consectetur adipiscing. </div>
>         <div id="right">Lorem ipsum dolor sit amet, consectetur elit.</div>
>     </div>
> 
>     <div id="bottom">Lorem ipsum dolor sit amet, consectetur.</div>
> </body>
> 
> </html>
> ~~~
>
> > **결과**
> >
> > ![image](https://user-images.githubusercontent.com/68331041/139515679-de32f109-4e04-45c8-82a4-433e4cf1f1a0.png)
>
> + ### 절대요소배치를 통한 레이아웃
>
> > ~~~html
> > <!DOCTYPE html>
> > <html>
> > 
> > <head>
> >     <title>Absolute Position</title>
> >     <style>
> >         #container {
> >             width: 500px;
> >             height: 300px; /*자손에 absolute시 부모요소 height 필요*/
> >             border: 3px solid black;
> >             overflow: hidden;
> >             position: relative; /*절대요소 부모는 relative*/
> >         }
> > 
> >         .circle {
> >             position: absolute; /*absolute 기본은 전체 기준 절대배치, 부모요소 relative시 부모위치 기준 절대배치*/
> >             width: 100px;
> >             height: 100px;
> >             border-radius: 50% 50%;
> >         }
> > 
> >         #red {
> >             background: red;
> >             left: 20px;
> >             top: 20px;
> >         }
> > 
> >         #green {
> >             background: green;
> >             right: 20px;
> >             top: 20px;
> >         }
> > 
> >         #blue {
> >             background: blue;
> >             right: 20px;
> >             bottom: 20px;
> >         }
> > 
> >         #yellow {
> >             background: yellow;
> >             left: 20px;
> >             bottom: 20px;
> >         }
> >     </style>
> > </head>
> > 
> > <body>
> >     <h1>Dummy Text</h1>
> >     <div id="container">
> >         <div id="red" class="circle"></div>
> >         <div id="green" class="circle"></div>
> >         <div id="blue" class="circle"></div>
> >         <div id="yellow" class="circle"></div>
> >     </div>
> >     <h1>Dummy Text</h1>
> > </body>
> > 
> > </html>
> > ~~~
> >
> > <img src="https://user-images.githubusercontent.com/68331041/139516616-96f15a7a-fc79-459f-afc8-ed470b32da05.png" alt="image" style="zoom:67%;" />
> >
> > 
> >
> > **중앙배치**
> >
> > > ~~~css
> > > div {
> > > 	position:absolute;
> > > 	left:50%
> > > 	top:50%
> > > }
> > > ~~~
> >
> > **고정배치**
> >
> > > ~~~css
> > > div {
> > > 	position:fixed;
> > > 	left:px; /*원하는 위치 조정*/
> > > 	top:px;
> > > }
> > > ~~~
> >
> > **글자생략**
> >
> > > ~~~css
> > > div{
> > >     text-overflow: ellipsis; /* 안녕하세요 -> 안녕..(생략) */
> > > }
> > > ~~~
>
> + #### **반응형 웹**
>
> > **데스크톱, 태블릿PC, 스마트폰에 맞게 디자인이 자동으로 반응해서 변경되는 웹 페이지**
> >
> > 
> >
> > #### 1. 뷰포트
> >
> > > meta 태그로 웹 정보를 정의할수있음
> >
> > ~~~html
> > <meta name="title" content="ITCookbook HTML5 프로그래밍을 위한 페이지">\
> > <meta name="description" content="meta 태그의 title 속성과 description 속성입니다.">
> > ~~~
> >
> > ![image](https://user-images.githubusercontent.com/68331041/139611546-e0e7df7d-259a-4cfe-bb49-360295c3b1bd.png)
> >
> > 
> >
> > **뷰포트 메타 테그**
> >
> > **<img src="https://user-images.githubusercontent.com/68331041/139611673-5a2af6c2-6c4b-40c7-b0ef-e22ea9a3450e.png" alt="image" style="zoom:67%;" />**
> >
> > > **content="뷰포트 메타테그"**
> >
> > **<img src="https://user-images.githubusercontent.com/68331041/139611688-2bb2de07-04b6-49ae-9ac4-6230ee671806.png" alt="image" style="zoom:67%;" />**
> >
> > > 일부 브라우저는 **일부 뷰포트 메타테그를 인식하지 못하는 경우**가 있다.
> > >
> > > ~~~html
> > > <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
> > > <!--이와 같이 확대 및 축소 가능 여부를 no로 설정했어도 최소 축소비율과 최대 확대 비율을 또 명시하는 이유는 일부 브라우저에서 user-scalable을 인식할수 없는 경우가 있기때문에 추가로 조치를 취해주는것-->
> > > ~~~



## [중요] 미디어쿼리

> 미디어 쿼리를 통해 특정 상태의 규칙을 정의할수있다.

~~~css
/*내부css: @media규칙   */
@media (<미디어 쿼리>) {
    <css code>
}

/*외부css: @media속성   */
<link rel="stylesheet" href="파일.css" media="<미디어쿼리>">
~~~

> **@media규칙**
>
> > <img src="https://user-images.githubusercontent.com/68331041/139612903-ef88bb6c-ae16-4538-9186-ba68669d8f45.png" alt="image" style="zoom:67%;" />.
> >
> > 스크린: red     인쇄시: green
>
> **@media속성**
>
> >  <img src="https://user-images.githubusercontent.com/68331041/139613016-ab246d50-3deb-406b-b750-babf87d4fa06.png" alt="image" style="zoom:67%;" />
> >
> > media 속성을 사용하여 외부 css를 불러와 위와 같은 결과를 만들수있다.
>
> 
>
> **적용가능한 미디어 타입**
>
> <img src="C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20211101112635473.png" alt="image-20211101112635473" style="zoom:67%;" />.
>
> **적용가능한 미디어 특징**
>
> <img src="https://user-images.githubusercontent.com/68331041/139613231-0be6d92c-f36e-4375-8e37-0a7a096b9484.png" alt="image" style="zoom:67%;" />.
>
> > and 연산자로 조건 결합가능
>
> #### **미디어 타입과 미디어 특징을 사용하여 반응형 웹을 설계할수있다.**
>
> ![image](https://user-images.githubusercontent.com/68331041/139613424-778c4d8f-ab58-4657-8eca-bf43e345ee04.png)
>
> 



## 레이아웃 배치와 미디어쿼리를 활용하여 반응형 웹 설계가 가능하다



