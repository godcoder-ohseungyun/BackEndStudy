# Docker

---

~~~
[동작흐름]
도커 클라이언트 - 도커서버 - 도커캐시 - 도커허브

1. 도커 클라이언트에 도커 이미지 실행 명령어 입력
2. 도커 서버가 로컬에 존재하는 도커 캐시에 해당 이미지가 있는지 찾음
3. 없으면 도커허브에 해당 이미지를 검색해서 받아와서 실행해줌
4. 도커 허브는 다양한 이미지(node.js , python 등)를 보유하고있음
~~~



~~~
[사용자 정의 도커 이미지 만들기]
Dockerfile 작성 - 도커 클라이언트 - 도커서버 - 이미지 생성

1. Docker image를 생성하기위한 설정 파일을 작성한다. (컨테이너가 어떻게 행동해야할지 설정)
2. 작성한 Dockerfile을 도커 클라이언트에게 전달한다.
3. 도커 클라이언트가 도커 서버에게 전달한다.
4. 도커 허브에 이미지 생성후 저장
~~~







## 기본 명령어

---

### 도커 컨테이너 생성

+ 이미지로 컨테이너를 생성할때 이미지에 담긴 파일 스냅숏을 기반으로 컨테이너 하드웨어에 필요한 파일 구조를 올린다.
+ 그후 시작 명령어를 실행



**도커 컨테이너 생성 및 실행**

~~~bash
docker create [imageName] #이미지를 통해 컨테이너 생성 , 없으면 도커 허브에서 받아옴
~~~

~~~bash
docker run [imageName] #이미지를 통해 컨테이너 생성 후 실행 , 없으면 도커 허브에서 받아옴
~~~

+ 시작 명령어 무시하기

~~~bash
docker create [imageName] ls #이미지에 담긴 시작 명령어를 무시하고 ls라는 명령어를 실행한다.
~~~



**도커 컨테이너 모니터링**

~~~bash
docker ps #실행중인 컨테이너 보기
docker ps -a #존재하는 모든 컨테이너 보기
~~~



**도커 컨테이너 생명주기**

~~~
생성 - > 시작 -> 실행 -> 중지 -> 삭제
~~~

~~~bash
docker create [imageName] # 생성만
docker start [containerName or id] # 컨테이너 실행  , start -a로 하면 화면에 output표시
docker run [imageName] # 컨테이너 생성후 실행까지 일괄수행
docker run -p 8080 : 5000 [imageName] # *포트연결(아래 실습에서 설명)

docker stop [containerName or id] # 컨테이너 중지, 진행중이던 작업 마무리후 중지
docker kill [containerName or id] # 컨테이너 중지, 바로 중지

docker rm [containerName or id] #컨테이너 삭제
docker rm `docker ps -a -q` #모든 컨테이너 삭제
docker rmi [imageName or id] #이미지 삭제
docker system prune #사용중이지 않은 이미지,네트워크,컨테이너 모두 삭제 - 도커 정리용으로 사용
~~~



**실행중인 컨테이너에 명령어 전달하기**

~~~bash
docekr exec -it [containerName or id] [명령어] #실행중인 컨테이너에 명령어 전달
docekr exec -it [containerName or id] sh #실행중인 컨테이너에 명령어 전달, 해당 컨테이너 shell로 접속을 유지함으로 계속 명령어를 줄수있다. [종료: ctrl+d]
# sh는 각 os에서 공통으로 쓰인다. bash나 powershell도 가능하다.
~~~



---

### 도커 이미지 생성

+ 도커 허브엔 기본제공되는 여러가지 기본 이미지들이 담겨있다.
+ 이것들을 가져와서 사용할 수도 있고 필요한 이미지를 직접 만들어 사용할 수도 있다.
+ 그리고 직접 만든 이미지를 허브에 공유할 수도 있다.



+ 이미지는 컨테이너를 생성하는데 사용된다. 언제 어디서나 이미지만 있으면 컨테이너를 빠르게 만들어낼수있다.

~~~bash
docker create [imageName] #이미지를 통해 컨테이너 생성
~~~



**도커 이미지 생성 순서**

1. <u>도커파일</u> 작성
2. 도커 클라이언트가 도커 파일을 도커 서버에게 전달
3. 도커 서버가 도커 파일을 기반으로 이미지를 생성



**도커파일 작성하기**

~~~dockerfile
#베이스로 사용될 이미지
FROM ubuntu:14.0.4    #<이미지 이름>:<버전>으로 작성 <버전>생략시 최신버전으로 다운

#추가적으로 필요한 파일 다운로드를 위한 쉘 명령어
RUN command

#컨테이너 시작시 실행될 명령어 (DockerFile 내 1번만 사용)
CMD ["executable"]

~~~



**도커파일 빌드하기**

작성된 도커 파일이 존재하는 dir로 이동해서 아래 명령어를 입력한다.

~~~bash
docker build -t osy/pu:latest ./  
~~~

> **./ or .**    현재 디랙토리를 의미함
>
> **-t osy/pu:latest**   이미지 id 대신 알아보기 쉬운 이름을 정의하는것이다. 명명 규칙은 사용자이름 / 프로젝트 이름 : 버전으로 구성한다.







---

### 실습

노드로 만든 서버프로그램을 도커이미지로 만들것이다.

> [파일구조]
>
> **package.json:** 노드 앱에 필요한 설정과 종속성들이 작성된 파일 npm은 이 파일을 해석해서 필요한 것들을 다운받는다.
>
> **server.js:** 노드 앱의 실행파일



### 1. 도커 파일 작성

~~~dockerfile
FROM node:10

#베이스 이미지의 파일구조에 소스코드가 담길 파일구조를 생성
WORKDIR /user/src/app #작업공간을 분리하지 않으면 파일구조가 매우 복잡해진다.

#만들어질 이미지의 파일 스냅숏에 해당 파일들을 넣음
COPY package.json ./  # 현재 디랙토리의 package.json 을 컨테이너의 내부 기본 디랙토리 로 복사

# 위의 COPY가 조금만 수정되어도 RUN을 처음부터 재실행한다.
# 따라서 소스코드 수정시 RUN이 불필요하게 실행되는것을 막기위해 pakage.json을 RUN 위에, 나머지 파일들은 RUN 아래 배치한다.
#npm install은 package.json이 변경되었을때만 재실행되면 된다. (소스코드 수정시에는 x)
RUN npm install

COPY ./ ./

EXPOSE 8080

CMD ["node", "server.js"]
~~~





### 2. 도커 이미지 생성

도커 파일이 있는 dir로 이동 

~~~bash
docker build -t osy/pu:latest ./  
~~~





### 3. 도커이미지로 도커 컨테이너 생성 및 실행(포트연결과 도커 볼륨)

컨테이너는 독립적인 공간임으로 컨테이너 내부에서 서버를 가동해도 로컬(localhost)에서 접속할수없다.

따라서 컨테이너를 실행할때 **로컬포트와 컨테이너 내부 서버의 포트를 연결**해줘야한다.

~~~bash
docker run -p 8080 : 5000 [imageName] -v  %cd%:/usr/src/app 
# -p localhost:8080으로 접속하면 컨테이너속 서버 5000포트로 연결된다.
# -v 도커 볼륨을 이용해 현재디랙토리(cd명령어) 와 도커의 WORKDIR 과 맵핑
~~~

> 맥에서
>
> -v $(pwd):/usr/src/app
>
> 윈도우에서
>
> -v %cd%:/usr/src/app



**도커 볼륨**을 이용하면 소스코드를 수정하면 바로 컨테이너에 반영된다.



### 4. 컨테이너끼리 연동하기(도커 Compose)

도커 컨테이너들은 각각 독립적인 구조를 가지고있기때문에 기본적으로 서로 연동되어있지 않다.

도커 Compose를 이용하면 서로 연동할수있다.

예를들어 스프링서버가 돌아가는 컨테이너와 플라스크 서버가 돌아가는 컨테이너끼리 통신하도록 할수있다.

도커 Compose는 YAML이나 YML로 작성한다.

> 일반적으로 구성 파일 및 데이터가 저장되거나 전송되는 응용 프로그램에서 사용되고원래는 XML이나 json 포맷으로 많이 쓰였지만,좀 더 사람이 읽기 쉬운 포맷으로 나타난 게 yaml

~~~yaml
version: "3" #도커 컴포즈 버전
services:    
	redis-server: #컨테이너1 이름
		image: "redis" #사용 이미지
    node-app:  #컨테이너2 이름
    	build: ./ #해당 디랙토리에 있는 도커파일로 이미지를 생성해 사용하는경우
    	ports:
    		- "5000:8080" #포트 맵핑
~~~



도커 Compose YML이 있는 경로에서 해당 명령어 실행

~~~bash
docker-compose up #컴포즈로 컨테이너들 실행
docker-compose down #컴포즈 다운
~~~



