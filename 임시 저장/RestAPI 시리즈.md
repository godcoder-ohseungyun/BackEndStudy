# [RestAPI] REST API

---

프로젝트의 규모가 커짐에 따라 `서버의 확장 및 스케일아웃`을 하거나 여러 서버를 두고 서버간 통신을 통해 로직을 수행한다.

> 이번 프로젝트에서 나는 머신러닝 클러스터링을 담당할 서버와 WAS를 분리해서 관리하고자한다.



또는 기존 웹 서비스를 다양한 플랫폼에서도 동작하도록 `멀티 플랫폼으로 확장`한다.



즉, `여러 클라이언트가 서버에게` 요청을 보내고 응답을 받아야한다.

> 여기서 클라이언트는 사용자라기 보단 `요청을 보내는 쪽`이다.
>
> ex) 프론트서버,브라우저,백엔드 서버
>
> 서버간 통신에서만 통용되는것은 아니고 `모든 기기간 통신에 적용`될수있다.



**서로 다른 서버들은 서버를 구성하는 언어도 기능도 다를것이다.**

요청자는 각각의 서버를 잘 모르더라도 **`어떤 공통의 규칙`**이 존재하고 이 규칙을 이해한다면 각 서버를 좀더 쉽게 이용할수있다면 편리할 것이다.



이 어떠한 규칙을 `REST` 라고 하며



이 `REST`를 잘 지켜서 `RESTFUL`하게 개발된 서버를 `RESTAPI`라고 부른다.



즉, 서버의 개발에 있어서  RestAPI는 필수적인 요소이다.

> RestAPI 말고도 Graphql이라는 형식도 있지만 그건 추후에 다루도록 하겠다.



 

### **용어사전**

**Interface:** 광범위한 개념, 어떤 기능을 활용하기 위한 제어시스템

> 인터페이스는 서로 다른 두 개의 시스템, 장치 사이에서 정보나 신호를 주고받는 경우의 접점이나 경계면이다.
>
> 하드웨어 인터페이스: 하드웨어 간의 상호작용
>
> 소프트웨어 인터페이스: 소프트웨어 간의 상호작용
>
> 사용자 인터페이스: 사람과 기계간의 상호작용

**API(Application Programming Interface):** 응용프로그램에서 제공하는 기능을 쉽게 제어 및 사용 할수있게 만든 인터페이스

> 사용하고자하는 프로그램의 내부를 몰라도 쉽게 제어가 가능하다
>
> API는 리모컨과 점원처럼 `애플리케이션`과 `운영체제` 그리고 `애플리케이션`과 `프로그래밍 언어가 제공하는 기능` 사이의 '상호 작용'을 돕는다

 



## RestAPI

**개요**

인터넷상에서 기계들은 HTTP를 통해 통신을 주고받는다.

> request & response
>
> http message body에 데이터를 담아 주고받는다. ex) json , xml 데이터 포멧 이용



이때 `웹에서 일어나는 모든 기계간 통신에 공통적인 규칙을 정해둔다면 더 명확하고 확실하게 상호작용 할수있을것`이다.

 

**먼저 Rest란?**

인터넷상에서 요청자는 HTTP와 URI를 이용해, 다른 컴퓨터의 리소스에 접근해 데이터를 요청하고 해당 컴퓨터로부터 응답을 받는다.

![image](https://user-images.githubusercontent.com/68331041/147249019-19ea9dd3-01b3-42c9-8aec-85696a366163.png)

> 구글 서버의 게시물 post 리소스에 접근



이때 해당 기기 리소스의 위치는 **HTTP URI**를 통해 명시된다.

이 URI를 통해 접근한 리소스의 데이터를 CRUD하여 처리할수있어야하는데 이때 **HTTP Method**를 적극 활용하여 처리하도록 되어있다.

이런 일련의 약속을 **Rest**라고 한다.



**즉 Rest란**

1. HTTP URI(Uniform Resource Identifier)를 통해 자원(Resource)을 명시하고,
2. HTTP Method(POST, GET, PUT, DELETE)를 통해
3. 해당 자원(URI)에 대한 CRUD Operation을 적용하는 것을 의미한다.

```
Create : 데이터 생성(POST)
Read : 데이터 조회(GET)
Update : 데이터 수정(PUT)
Delete : 데이터 삭제(DELETE)
```



**REST의 특징**

**Server-Client(서버-클라이언트 구조)**

> 자원이 있는 쪽이 Server, 자원을 요청하는 쪽이 Client가 된다.
>
> > `REST Server: `API를 제공하고 비즈니스 로직 처리 및 저장을 책임진다.
> >
> > `Client:` 사용자 인증이나 context(세션, 로그인 정보) 등을 직접 관리하고 책임진다.
>
> 
>
> 서로 간 `의존성이 줄어든다.`



**Stateless(무상태)**

> HTTP 프로토콜은 Stateless Protocol이므로 REST 역시 `무상태성`을 갖는다.
>
> Server는 `각각의 요청을 완전히 별개`의 것으로 인식하고 처리한다.



**Cacheable(캐시 처리 가능)**

> 웹 표준 HTTP 프로토콜을 그대로 사용하므로 `웹에서 사용하는 기존의 인프라를 그대로 활용`할 수 있다.
>
> 캐시 사용을 통해 응답시간이 빨라지고 REST Server 트랜잭션이 발생하지 않기 때문에 전체 응답시간, 성능, 서버의 자원 이용률을 향상시킬 수 있다.



**Layered System(계층화)**

>Client는 REST API Server만 호출한다.
>
>`REST Server는 다중 계층`으로 구성될 수 있다.
>
>> 순수 비즈니스 로직, 암호화, 보안, 인증을 계층화 하여 유연하게 개발이 가능하다.



**Uniform Interface(인터페이스 일관성)**

> URI로 지정한 Resource에 대한 조작을 통일되고 한정적인 인터페이스로 수행한다.
>
> `HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 사용이 가능`하다.



### ***REST가 필요한 이유**



다양한 기기의 등장과 어플리케이션 분산과 통합에 따라 **각 기기간 종류에 상관없이 통신할수있어야한다.**

**다양한 기기가 서비스 리소스에 대한 공통된 규칙으로 서로 통신**하도록 하기위해 REST가 필요하다.

 



## 그럼 RestAPI란?



**REST의 원리를 따라 RESTFUL하게 개발된 서비스 API**

+ 많은 업체들이 Rest API를 제공한다.

> OpenAPI 등

 

**즉 Rest API란**



**`REST원리에 따라 URI로 리소스 구조를 표현하고 + Http Method를 이용해 상호작용하도록 설계하는 API라는 말이다.`**



예시를 보자.

```
www.moviestar.com
```

이와 같은 영화정보를 제공해주는 서버가 있다.

이 서버에 영화 목록 리소스에 접근하려면

```
[GET] www.moviestar.com/movies
```

GET 방식으로 해당 URI에 request한다.

만약 영화를 등록하고싶다면

```
[POST] www.moviestar.com/movies
```

POST 방식으로 해당 URI에 request한다.

즉 같은 URI라도 HTTP method에 따라 하는 동작이 달라진다.

```
 Create : 데이터 생성(POST)
 Read : 데이터 조회(GET)
 Update : 데이터 수정(PUT)
 Delete : 데이터 삭제(DELETE)
```

다음과 같은 4가지 method를 사용하여 crud를 실현한다.

 

**URI도 설계하는 규칙이 존재한다.**



**동사를 사용하지말것**

```
www.moviestar.com/getmovies
www.moviestar.com/postmovies
```

동사를 사용하게 되면 이처럼 동작에 따라 많은 URI가 생성되어야 하고 복잡해진다.

 

**엘리먼트는 ID로 구분한다.**

```
[GET] www.moviestar.com/movies/1/actors/2
```

위 요청은 영화목록의 1번째 영화의 2번째 배우 정보 열람을 요청한다는것을 알수있다.

 

**URI 마지막 문자로 슬래시(/)를 포함하지 않는다.**



**하이픈(-)은 URI가독성을 높이는데 사용**

> 불가피하게 긴 URI경로를 사용하게 된다면 하이픈을 사용해 가독성을 높일 수 있다.



**URI 경로에는 소문자가 적합하다.**



**파일 확장자는 URI에 포함시키지 않는다.**

~~~
http://restapi.example.com/members/soccer/345/photo.jpg (X)
~~~

> Accept header를 사용할것



**REST원칙을 잘 지켜 RESTFUL하게 API를 설계해서 REST API를 잘 제공한다면**

1. 같은 URI로도 HTTP method를 다르게하여 다양한 동작을 실행시킬수있다.
2. 요청 URI+method 만으로도 어떤 요청인지 직관적으로 파악할수있다.
3. 정형화된 원칙으로 설계했기 때문에 **다른 팀원들과 공유하는 경우에도 이해하기 쉽다.**

 



## Reference

------

[노마드코더](https://www.youtube.com/watch?v=4DxHX95Lq2U)

[생활코딩](https://www.youtube.com/watch?v=PmY3dWcCxXI)







# [RestAPI] HTTP header

---

**RestAPI를 개발하면서** 클라이언트에게 응답을 내려줄때 HTTP body에 json만 담아주는게 아니라

> 물론 xml 등 다양한 데이터 포멧을 담기도한다.



**응답 HTTP header를 직접 다뤄야하는 경우가 있다.**

> location uri 정보라던가..



`Spring이 제공하는 HttpEntity를 이용해 HTTP header를 다루게 되면서, HTTP 자체의 스펙에 대해서 한번 더 정리할 필요가 있다고 생각했다.`



### **HTTP header에는 어떤 정보가 존재하고 각 정보는 어떤것을 담고있는지 정리하고자 한다.**



HTTP나 TCP,UDP등 다양한 프로토콜을 이해하려면 해당 프로토콜의 헤더만 파악하면 된다고 한다.

> `프로토콜의 헤더의 내용`은 특정 프로토콜의 기능을 제공하기 위해 담고 있는 최소한의 정보이기 때문



### **request HTTP header**(요청 헤더)

> **Host** : 요청하려는 서버 호스트 이름과 포트번호
>
> 
>
> **User-agent** : 클라이언트 프로그램 정보 ex) Mozilla/4.0, Windows NT5.1
>
> > 이 정보를 통해서 서버는 클라이언트 프로그램(브라우저)에 맞는 최적의 데이터를 보내줄 수 있다.
>
> 
>
> **Referer** : 바로 직전에 머물렀던 웹 링크 주소(해당 요청을 할 수 있게된 페이지)
>
> 
>
> **Accept** : 클라이언트가 처리 가능한 미디어 타입 종류 나열 ex) */* - 모든 타입 처리 가능, application/json - json데이터 처리 가능.
>
> 
>
> **Accept-charset** : 클라이언트가 지원가능한 문자열 인코딩 방식
>
> 
>
> **Accept-language** : 클라이언트가 지원가능한 언어 나열
>
> 
>
> **Accept-encoding** : 클라이언트가 해석가능한 압축 방식 지정 ex) gzip, deflate
>
> > 압축이 되어있다면 content-length와 content-encoding으로 압축을 해제한다.
>
> 
>
> **Content-location** : 해당 개체의 실제 위치
>
> 
>
> **Content-disposition** : 응답 메세지를 브라우저가 어떻게 처리할지 알려줌. ex) inline, attachment; filename='jeong-pro.xlsx'
>
> 
>
> **Content-Security-Policy** : 다른 외부 파일을 불러오는 경우 차단할 리소스와 불러올 리소스 명시
>
> > ex) default-src https -> https로만 파일을 가져옴
> >
> > ex) default-src 'self' -> 자기 도메인에서만 가져옴
> >
> > ex) default-src 'none' -> 외부파일은 가져올 수 없음
>
> 
>
> **If-Modified-Since** : 여기에 쓰여진 시간 이후로 변경된 리소스 취득. 페이지가 수정되었으면 최신 페이지로 교체하기 위해 사용된다.
>
> 
>
> **Authorization** : 인증 토큰을 서버로 보낼 때 쓰이는 헤더
>
> 
>
> **Origin** : 서버로 Post 요청을 보낼 때 요청이 어느 주소에서 시작되었는지 나타내는 값 이 값으로 요청을 보낸 주소와 받는 주소가 다르면 CORS 에러가 난다.
>
> 
>
> **Cookie** : 쿠기 값 key-value로 표현된다. ex) attr1=value1; attr2=value2



### **response HTTP header(응답헤더)**

> **Location** : 301, 302 상태코드일 떄만 볼 수 있는 헤더로 서버의 응답이 다른 곳에 있다고 알려주면서 해당 위치(URI)를 지정한다.
>
> 
>
> **Server** : 웹서버의 종류 ex) nginx
>
> 
>
> **Age** : max-age 시간내에서 얼마나 흘렀는지 초 단위로 알려주는 값
>
> 
>
> **Referrer-policy** : 서버 referrer 정책을 알려주는 값 ex) origin, no-referrer, unsafe-url
>
> 
>
> **WWW-Authenticate** : 사용자 인증이 필요한 자원을 요구할 시, 서버가 제공하는 인증 방식
>
> 
>
> **Proxy-Authenticate** : 요청한 서버가 프록시 서버인 경우 유저 인증을 위한 값



### **common header(공통해더)**

> **Date** : 현재시간 (Sat, 23 Mat 2019 GMT)
>
> 
>
> **Pragma** : 캐시제어 (no-cache), HTTP/1.0에서 쓰던 것으로 HTTP/1.1에서는 Cache-Control이 쓰인다.
>
> 
>
> **Cache-Control** : 캐시 제어
>
> > \+ no-store : 캐시를 저장하지 않겠다.
> >
> > \+ no-cache : 모든 캐시를 쓰기 전에 서버에 해당 캐시를 사용해도 되는지 확인하겠다.
> >
> > \+ must-revalidate : 만료된 캐시만 서버에 확인하겠다.
> >
> > \+ public : 공유 캐시에 저장해도 된다.
> >
> > \+ private : '브라우저' 같은 특정 사용자 환경에만 저장하겠다.
> >
> > \+ max-age : 캐시의 유효시간을 명시하겠다.
>
> 
>
> **Transfer-Encoding** : body 내용 자체 압축 방식 지정
>
> > 'chunked'면 본문의 내용이 동적으로 생성되어 길이를 모르기 때문에 나눠서 보낸다는 의미다.
> >
> > 본문에 데이터 길이가 나와서 야금야금 브라우저가 해석해서 화면에 뿌려줄 때 이 기능을 사용한다.
>
> 
>
> **Upgrade** : 프로토콜 변경시 사용 ex) HTTP/2.0
>
> 
>
> **Via** : 중계(프록시)서버의 이름, 버전, 호스트명
>
> 
>
> **Content-Encoding** : 본문의 리소스 압축 방식 (transfer-encoding은 body 자체이므로 다름)
>
> 
>
> **Content-type** : 본문의 미디어 타입(MIME) ex) application/json, text/html
>
> 
>
> **Content-Length** : 본문의 길이
>
> 
>
> **Content-language** : 본문을 이해하는데 가장 적절한 언어 ex) ko
>
> > 한국사이트여도 본문을 이해하는데 영어가 제일 적절하면 영어로 지정된다.
>
> 
>
> **Expires** : 자원의 만료 일자
>
> 
>
> **Allow** : 사용이 가능한 HTTP 메소드 방식 ex) GET, HEAD, POST
>
> 
>
> **Last-Modified** : 최근에 수정된 날짜
>
> 
>
> **ETag** : 캐시 업데이트 정보를 위한 임의의 식별 숫자
>
> 
>
> **Connection** : 클라이언트와 서버의 연결 방식 설정 HTTP/1.1은 kepp-alive 로 연결 유지하는게 디폴트.



이 밖에도 다양한 헤더 데이터가 존재한다.





## Reference

---

https://rangken.github.io/blog/2015/http-headers/

https://jeong-pro.tistory.com/181





# [RestAPI] HTTP 상태코드

---



**RestAPI를 개발할때** 응답 HTTP를 생성해주는것도 중요하지만 `알맞은 상태코드를 응답`해야한다. 



상태 코드의 기능은 정해져 있다.



`알맞은 상태코드를 회신함으로써 클라이언트에게 다음 작업을 알릴수있다.`

> 예를 들면 상태 코드 `200`은 요청이 성공적으로 완료되었다는 메세지를 전달하는 기능을 갖고 이것을 이용해 클라이언트에 다음 작업을 이어 나가도 좋다는 신호의 목적으로 쓰일 수 있다.



**이렇게 정확한 응답의 상태코드만으로 많은 정보를 전달할 수가 있기 때문에 응답의 상태코드 값을 명확히 돌려주는 것이 중요하다.**



주로 사용되는 상태코드들을 정리하고자 한다.

> `1XX 상태 코드`는 프로토콜을 교체해도 된다거나 계속 요청을 보내도 된다거나하는 식의 단순 정보성을 띄고 있는 상태를 의미함으로 에플리케이션을 개발할때 마주칠 일이 거의 없다. 



## 2xx (성공)

+ 서버가 클라이언트의 요청을 성공적으로 처리했다는 의미.

> **200 ok** : 클라이언트의 요청을 서버가 정상적으로 처리했다.
>
> > `모든 성공 처리`를 200 으로 처리해도 된다.
> >
> > 자세한 정보를 제공하기 위해선 아래 상태코드들을 사용한다.
>
> 
>
> **201 create** : 클라이언트의 요청을 서버가 정상적으로 처리했고 새로운 리소스가 생겼다.
>
> >`POST`, `PATCH` , `PUT` 요청에 대한 응답에 주로 사용된다.
>
> 
>
> **202 accepted** : 클라이언트의 요청은 정상적이나, 서버가 아직 요청을 완료하지 못했다.
>
> > 로직이 `비동기`로 수행될때 응답에 사용된다.
>
> 
>
> **204 No Content** : 클라이언트의 요청은 정상적이다. 하지만 컨텐츠를 제공하지 않는다.
>
> > `DELETE`요청에 응답할 수 있다.



## 3xx (리다이렉션 완료)

+ 클라이언트는 요청을 마치기 위해 리다이랙션 등 추가 동작을 취해야 한다는 의미.
+ **리소스가 변경 혹은 삭제되어 다른 URL을 통해서 그 리소스에 접근해야하는 경우** 해당 리소스의 위치를 알려준다.

> **300 Multiple Choice:** 요청에 대해 하나 이상의 리소스가 존재함을 의미한다.
>
> > 클라이언트가 요청할수있는 리소스 목록을 반환한다.
>
> 
>
> **301 Moved Permanetly: **  요청 리소스가 영구적으로 이동되었다는 뜻
>
> > 서버는  HTTP 헤더에  `Location` 필드에 해당 리소스의 위치를 URL로 담아준다.
> >
> > 클라이언트는  HTTP 헤더에 들어있는 `Location` 필드에 담긴 URL로 `자동으로 리다이렉션`합니다.
>
> 
>
> 그외 `302`,`303` 등이 있다.



## 4xx (요청 오류)

+ 클라이언트의 요청이 유효하지 않아 서버가 해당 요청을 수행하지 않았다는 의미.

> **400 Bad Request :** 클라이언트의 요청이 유효하지 않아 더 이상 작업을 진행하지 않는 경우
>
> > 서버는 클라이언트 요청시 요청에 대한 `유효성 검사`(검증 등)를 한 후 로직을 수행한다.
> >
> > 유효하지 않을경우 `400` 상태코드로 유효하지 않음을 응답한다.
> >
> > 
> >
> > 이때 어떠한 이유로 유효하지 않은지 에러 이유를 명시해 주는것이 좋다.
> >
> > ~~~JSON
> > HTTP/1.1 400 Bad Request
> > {
> >     "errors": [
> >         {
> >         "location": "body",
> >         "param": "name",
> >         "value": 123,
> >         "error": "TypeError",
> >         "msg": "must be String"
> >         }
> >     ]
> > }
> > ~~~
>
> 
>
> 
>
> **401 Unauthorized:** 인증된 클라이언트가 아니기 때문에 작업을 진행할 수 없는 경우
>
> > 세션이나 JWT를 통한 `로그인 유지`가 안되었을 경우 사용한다.
> >
> > 인증된 사용자가 아닌경우 응답한다.
>
> 
>
> **403 Forbidden:** 클라이언트가 권한이 없기 때문에 작업을 진행할 수 없는 경우
>
> > 권한의 문제이다. `401과 혼돈하지 말자.`
> >
> > 로그인된 인증된 사용자여도 조회권한이 없는 데이터에 접근하는 경우 사용한다.
>
> 
>
> **404 Not Found:** 클라이언트가 요청한 자원이 존재하지 않다.
>
> > 경로나 자원이 존재하지 않는경우 사용한다.
>
> 
>
> **405 Method Not Allowed:** 클라이언트의 요청이 허용되지 않는 메소드인 경우
>
> 
>
> **409 Conflict:** 클라이언트의 요청이 서버의 상태와 충돌이 발생한 경우
>
> > 비즈니스 로직상 불가능하거나 모순이 생기는 경우 사용한다.
> >
> > > 예를들어 삭제요청시 사용자가 로그인 중이면 삭제가 불가능한 로직이 있는 경우
> >
> > 
> >
> > 또는 `400, 401, 403, 404, 405` 상태 코드에 속하기 애매한 오류의 상황들을 `409`로 응답한다.
>
> 
>
> **429 Too Many Requests:** 클라이언트가 일정 시간 동안 너무 많은 요청을 보낸 경우
>
> > Dos 공격을 1차적으로 `억제`하거나, 한 사용자가 트래픽을 많이 소모할 경우 발생시킬수있다. 
> >
> > > ~~~json
> > > HTTP/1.1 429 Too Many Requests
> > > Retry-After: 3600
> > > ~~~
> > >
> > > 3600초 후에 다시 요청할수있도록 한다.



## 5xx (서버 오류)

+ 상태 코드들은 서버 오류로 인해 요청을 수행할 수 없다는 의미.
+ 클라이언트의 요청은 유효하여 작업을 진행했는데 도중에 오류가 발생한 경우.

> `500, 502, 503` 등의 오류를 만나봤을 거다.
>
> API 서버의 응답에서 `5XX`오류가 발생해서는 안된다.
>
> **WAS는 로직 수행중 예외가 발생하면 무조건  `5XX` 상태코드를 발생**시키는데, 이는 개발자의 부주의에서 온다.
>
> 
>
> Spring에서처럼 WAS를 개발할때 **예외처리**를 통해 발생하는 예외들을 `4xx`,`2xx` 등 **다른 상태코드로 처리**하기 때문에 `5xx`이 발생했다면
>
> 
>
> **개발자가 예외처리를 놓쳤거나 요청에 대한 검증을 제대로 수행하지 않은것이다.**  
>
> 
>
> **개발자는 완벽한 예외처리를 통해 5XX 서버 오류 상태 코드를 반환하지 않도록 해야 한다.**





## 정리

**정확한 응답의 상태코드만으로 클라이언트에게 많은 정보를 전달할 수가 있기 때문에 응답의 상태코드 값을 명확히 돌려주는 것이 중요하다.**



상태코드에 따라 응답 하는 방법은 유연하다.

예를들어 HTTP header의 location에 URL을 담아 주는건  `301` 에서 사용하지만

회원 가입 후에 프로필 링크를 location에 담아주는등 `200`에서도 활용할수있다.

> 단 이경우 프론트엔드 개발자에게 location에 리소스 경로를 담아주었으시 필요하면 쓰라고 명시해줘야한다.



## Reference

---

https://sanghaklee.tistory.com/61

https://ko.wikipedia.org/wiki/HTTP_%EC%83%81%ED%83%9C_%EC%BD%94%EB%93%9C









# [RestAPI]  ResponseEntity<T> , @ResponseBody 로 응답  HTTP 다루기

------



상태코드와 HTTP header 스펙에 대해서 대략 알아보았으니 `Spring에서 응답 데이터를 생성하는 법`을 알아보자



**Spring에서 restAPI를 개발할때 HTTP 응답을 다루는 방법이 2가지 있다.**

>  ResponseEntity 클래스 사용 `혹은` @ResponseBody 에너테이션 사용





**@ResponseBody**

*@ResponseBody* 는 *HTTP* 규격에 맞는 응답을 만들어주기 위한 *Annotation*

```java
@RequestMapping(value = "/message")
@ResponseBody
public Message get() {
    return new Message(penguinCounter.incrementAndGet() + " penguin!");
}
```

+ 컨트롤러에 @RestController를 붙이면 내부 메서드들에게 자동으로 @ResponseBody가 적용된다.

+ JAVA 객체나 String을 **HttpMessageConverter**를 이용해서 json으로 변환해서 응답 HTTP body에 담아준다.

+ @RequestBody는 반대로 요청 HTTP body에 담겨온 json 데이터를 JAVA 객체로 변환하는 역할을 한다.

+ HTTP header 정보는 따로 다루지 않기 떄문에 서블릿으로 호출한 request나 response객체에서 따로 변경해줘야하는 번거로움이 있다.

 

 **HTTP body에 담긴 데이터만 다루는 경우 에너테이션을 사용해서 간단하게 사용할수있다.**



**ResponseEntity<T>**

HTTP body의 데이터만 다루는 @ResposeBody를 보완해서 `상태코드 + HTTP body + HTTP header를 하나의 객체`로 다루도록 해주는 클래스

```java
@RequestMapping(value = "/message")
public ResponseEntity<Message> get() {
    Message message = new Message(penguinCounter.incrementAndGet() + " penguin!");
    return new ResponseEntity<Message>(message, HttpStatus.OK);
}
```

+ 스프링은 Http 프로토콜을 이용하는 통신의 header와 body 관련 정보를 다루는기능을 지원하는 클래스 `HttpEntity란 클래스` 를 제공한다.

> 즉, 통신 HTTP 관련 `header와 body 값들을 하나의 객체로 저장` 하는 것이 HttpEntity 클래스 객체라는 것이다.



+ 이 `HttpEntity` 를 상속받은 `RequestEntity`와`ResponseEntity` 클래스가 존재한다.

 

**상태코드 + HTTP body + HTTP header를 모두 다뤄야 하는 경우 사용할수있다.**

 



### 정리

 **ResponseEntity를 사용: **상태코드 + HTTP body + HTTP header를 모두 다뤄야 하는 경우

 **@ResponseBody를 사용:**  HTTP body에 담긴 데이터만 다루는 경우





## Reference

---

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html

https://tecoble.techcourse.co.kr/post/2021-05-10-response-entity/





# [RestAPI]  ResponseEntity<T> 자세히 알아보기 (feat. 상태코드)

----



프로젝트로 restAPI를 개발하다보니 응답에 간단한경우 @ResponseBody를 쓰지만  `HTTP header`라던지 `상태코드 제어` 등  HTTP 스펙을 다뤄야하는 경우가 훨씬 많아 **ResponseEntity를 쓰게 되는 경우가 훨씬 많다.**

> **상태코드 차이**
>
> @ResponseBody의 경우 @ResponseStatus(HttpStatus.OK)로 따로 상태코드를 다뤄줘야 하는데 조건별로 응답 상태코드가 다른경우 번거롭다. 
>
> ~~~java
>@ResponseBody
> @ResponseStatus(HttpStatus.OK)  //상태코드 지정
>public MoveResponseDto move(@PathVariable String name, @RequestBody MoveDto moveDto) {
> 
>...
> 
>return moveResponseDto;
> }
>~~~
> 
> 
> 
> 반면에 ResponseEntity<T>는 하나객체로 응답을 다루기 때문에 조건별로 응답 상태코드가 다른경우에도 문제없다.
>  
>   ~~~java
>       public ResponseEntity<MoveResponseDto> move(@PathVariable String name,
>    @RequestBody MoveDto moveDto) {
> 
>  ...
>
>  return new ResponseEntity<MoveResponseDto>(moveResponseDto, headers, HttpStatus.valueOf(200)); // ResponseEntity를 활용한 응답 생성
>}
> ~~~



**글을 읽기에 앞서 공식문서를 함께 참조하자**

**[공식문서]** https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html





## ResponseEntity<T>

---



**상태코드 + HTTP body + HTTP header를 포함해 하나의 객체로 만들어 준다.**

> 응답 HTTP로 변환될 정보를 모두 담은 요소들을 객체로 만들어서 반환
>
> 스프링이 HTTP로 변환해서 클라이언트에서 response한다.



**구조 살펴보기**



ResponseEntity는 스프링이 HTTP를 다루기위해 제공하는 HttpEntity를 상속 받고있다.

> RequestEntity도 있다.

~~~java
//ResponseEntity 선언 구조
public class ResponseEntity extends HttpEntity {

  private final Object status; //Status를 필드값으로 가지고있음. 
    
  ...
}
~~~



HttpEntity를 보면 HTTP header 와 body를 필드로 가지고있다.

~~~JAVA
//HttpEntity 선언 구조
public class HttpEntity<T> {
    public static final HttpEntity<?> EMPTY = new HttpEntity<>();
  
  
    private final HttpHeaders headers; //HttpHeaders -> header를 설정하는데 사용하는 클래스
  
    @Nullable
    private final T body; //제너릭으로 바디에 들어갈 객체를 받음 -> HttpMessageConverter 
}
~~~

> body가 제너릭으로 선언되어있음을 인지하자



즉, *ResponseEntity* 는 *HttpEntity* 를 상속하고있기 때문에 상태코드 + HTTP body + HTTP header를 모두 설정해 하나의 객체로 만들수 있다,





**객체 생성하기  Constructor vs Builder**



**Constructor** 



공식문서를 살펴보면 여러개의 Constructor(생성자)가 있음을 알수있다.

위 구조에서 살펴보았듯이 ResponseEntity는 바디,헤더,상태코드를 설정해서 생성할수있다.



*Constructor* 를 활용하여 *ResponseEntity* 를 사용한 예시는 다음과 같다

> 주로  *Body* , *Header* , *StatusCode* 를 차례로 입력하는 Constructor를 사용한다

~~~java
public ResponseEntity<MoveResponseDto> move(@PathVariable String name,
    @RequestBody MoveDto moveDto) {
    
    //header data
    HttpHeaders headers = new HttpHeaders();
    headers.set("Game", "Chess");
    
    String command = makeMoveCmd(moveDto.getSource(), moveDto.getTarget());
    springChessService.move(name, command, new Commands(command));
    
    //body data
    MoveResponseDto moveResponseDto = new MoveResponseDto(springChessService
        .continuedGameInfo(name), name);

    return new ResponseEntity<MoveResponseDto>(moveResponseDto, headers, HttpStatus.valueOf(200)); // ResponseEntity를 활용한 응답 HTTP 생성
}
~~~





**Builder**



*ResponseEntity* 를 사용할 때, *Constructor* 를 사용하기보다는 *Builder* 를 활용하는 것을 권장한다.

~~~java
//Constructor  
return new ResponseEntity<MoveResponseDto>(moveResponseDto, headers, HttpStatus.valueOf(200));

//Builder
return ResponseEntity.ok() //상태코드 메서드
        .headers(headers)
        .body(moveResponseDto);
~~~

차이라면 Constructor는 상태코드 수를 직접 입력하는데 반해 Builder는 Builder가 제공하는 메서드 ok()를 이용해 상태코드를 설정했기 때문이다.

Builder가 제공하는 메서드를 이용해 ResponseEntity 객체를 구축함으로써 좀더 이해하기도 쉽고 상태코드 value를 잘못 넣는 일을 방지하는 등 실수를 줄일 수 있기 때문이다.



## 정리

ResponseEntity를 사용하면   *Body* , *Header* , *StatusCode* 를 설정한 응답 HTTP를 쉽게 생성해서 응답할수있다.



## Reference

---

https://tecoble.techcourse.co.kr/post/2021-05-10-response-entity/

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html





# [RestAPI]  CORS (Cross Origin Resource Sharing)

---

백엔드 서버로부터 프론트엔드 서버를 분리해하고 서로 통신을 시도한 순간 이 CORS라는 녀석을 마주쳤다.







> 이전에 RestTamplaste를 사용했을때 같은 서버끼리 통신했었는데 포트번호까지 동일했으니 `동일 출처`로 판단 해 CORS가 발생하지 않았던것같다.



## Reference

---

evan-moon.github.io/2020/05/21/about-cors/



# [RestAPI] "당신의 RestAPI 이대로 괜찮은가?" 를 보고

---
