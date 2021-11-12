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





[바닐라 자바스크립트 채팅 앱! 초보도 할 수 있다! Node JS (express), Socket.io, Flex - YouTube](https://www.youtube.com/watch?v=UoKoPP91Qx0)



# Socket.io 사용하기

---

### + node.js(express) 와 socket.io 모듈을 이용하여 간단한 채팅 구현하기



**개발툴 설정**

~~~bash
#node 설치후 잘 설치되었는지 확인
node -v
npm -v 
~~~

~~~bash
#express 설치
cmd (명령 프롬프트) 실행
프로젝트를 만들고자 하는 경로로 이동 [ex] cd Desktop
express --ejs (프로젝트 폴더이름) 명령어 실행 [ex] express --ejs test
프로젝트 폴더로 경로 이동 [ex] cd test
npm install 명령어 실행
~~~

~~~bash
#express 프로젝트 생성
cmd (명령 프롬프트) 실행
프로젝트를 만들고자 하는 경로로 이동 [ex] cd Desktop
express --ejs (프로젝트 폴더이름) 명령어 실행 [ex] express --ejs test
프로젝트 폴더로 경로 이동 [ex] cd test
npm install 명령어 실행
~~~

~~~bash
#nodemon 설치: 라이브 서버기능
cmd (명령 프롬프트) 실행
npm install -g  nodemon 명령어 실행
nodemon -v 명령어 실행
~~~

~~~bash
#실행중지 및 취소
ctrl+c

#nodemon으로 서버 실행 app.js는 메인 js파일
nodemon app.js
~~~



**express 계층구조**

| 계층                               | 역할                                               |
| ---------------------------------- | -------------------------------------------------- |
| bin                                | 웹  서버 구성                                      |
| node_modules                       | 설치한  npm 모듈 저장소                            |
| public                             | 리소스(javascript,  image, css 등등..) 관리 저장소 |
| routes                             | 클라이언트  리퀘스트 관리                          |
| views                              | 웹  화면 관리                                      |
| app.js                             | 웹 서버 운영 관리                                  |
| package-lock.json     package.json | npm  모듈 관리                                     |



**개발환경 설정**

~~~js
//app.js
//server 운영 관리

 //========================================================================================================================

//*express 모듈
const express = require("express") //node_modules에 저장된 express module
const app = express() //express 모듈을 변수 app에 저장

//*http 모듈
const http  = require('http') //웹 소켓을 사용하기위해 http 모듈이 필요: ws프로토콜은 http프로토콜을 이용한다.
const server = http.createServer(app); //express app을 http 서버로 

//*socket.io 모듈
const socketIO = require('socket.io');
const io = socketIO(server) //socket.io http 서버

//*Date()사용을 위해 moment 모듈
const moment = require("moment");


//*path 모듈: 서버 기본 시작 경로 설정
const path = require('path');
app.use(express.static(path.join(__dirname,"src")));
//console.log(__dirname); //__dirname은 현재 프로젝트 경로를 의미함


//*포트 설정 및 서버 시작 포트 지정
const PORT = process.env.PORT || 5000; //프로세스 환경 포트가 있으면 그걸 사용하고 || 아니면 5000 번
server.listen(PORT,()=>console.log(`server is running ${PORT}`)); //서버 시작시 PORT로 연결: ``(백틱)사용으로 ${} 표현식 사용

 //========================================================================================================================

//client -> server
io.on("connection",(socket)=>{
    socket.on("chatting",(param)=>{
        const {name , msg } = param; //html에서 받은 param 분해
        io.emit("chatting",{
            name: name,
            msg: msg,
            time: moment(new Date()).format("h:ss A")
        })
    }) 
})
~~~

+ 서버부분을 관리한다. 클라이언트 요청에 대한 처리 및 서버 관리



~~~js
//chat.js
//client 관리

"ust strict" //오류최소화

const socket = io(); //client 에 socket io 모듈 불러오기

//* from index.html

const nickname = document.querySelector("#nickname");
const chatList = document.querySelector(".chatting-list");
const chatInput = document.querySelector(".chatting-input");
const sendButton = document.querySelector(".send-button");
const displayContainer = document.querySelector(".display-container");


//* client 이밴트 처리

//client -> server: param send
function send(){
    
    const param = { //json object
        name: nickname.value,
        msg: chatInput.value
    }
    socket.emit("chatting",param); //서버에 객체 전송
}

//enterKey press event
chatInput.addEventListener("keypress",(event)=>{
    if(event.keyCode === 13){
        send();
        chatInput.value = "" //입력창 초기화
    }
})
//전송 click event
sendButton.addEventListener("click",()=>{
    send();
    chatInput.value = "" //입력창 초기화
})



//* server -> client 처리
socket.on("chatting",(param)=>{
    
    const {name,msg,time} = param; //param parsing data 분리
    const item = new LiModel(name,msg,time);
    //const item = new LiModel(param.name,param.msg,param.time);
    
    item.makeLi();
    displayContainer.scrollTo(0,displayContainer.scrollHeight); //scroll 자동 갱신
})

function LiModel(name,msg,time){
    this.name = name;
    this.msg = msg;
    this.time = time;

    this.makeLi = ()=>{
        const li  = document.createElement("li");

        li.classList.add(nickname.value === this.name ? "sent" : "received") 
        //정보 전달자 nickname이 현재 사용자와 같으면 ls name = "sent"
        //name="send" 면 우측, received면 좌측에 float 되도록 css에서 설계

        const dom = `<span class="profile">
        <span class="user">${this.name}</span>
        <img class="image" src="" alt="any">
        </span>
        <span class="message">${this.msg}</span>
        <span class="time">${this.time}</span>`;
        
        li.innerHTML = dom;
        chatList.appendChild(li) //display에 표기
    }

}
~~~

+ 서버로 부터 받은 응답을 처리하고, 클라이언트 HTML구조를 필요에 맞게 변경한다.
+ 채팅창 display는 css를 통해 구현한다.



### ETC

~~~js
//package.json

"scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "start": "nodemon app.js" //이와 같이 명령어 스크립트를 생성해서 간단히 사용가능
  },
      
//terminal에서 nodemon app.js를 npm start로 실행가능 긴 문장을 짧게 사용하도록 할수있다
~~~

~~~CSS
#HTML

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css"> <!--왜 src는 안돼는거지?-->
</head>
<body>
    <div class="wrapper">
        <div class="user-container">
            <label for="nickname">사용자</label>
            <input type="text" id="nickname">
        </div>
        <div class="display-container">
            <ul class="chatting-list">
            
            </ul>
        </div>
        <div class="input-container">
            <span>
                <input type="text" class="chatting-input">
                <button class="send-button">전송</button>
            </span>
        </div>
    </div>



    <script src="/socket.io/socket.io.js">
        //node_modules에 있는 soket.io/socket.io.js 파일을 로드
    </script>

    <script src="js/chat.js"> //chat.js 불러오기
    </script>
</body>
</html>
~~~

~~~css
#CSS
* {
    margin:0;
    padding:0;
}

html,body{
    height:100%;
}

.wrapper {
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
    overflow: hidden;
}

.user-container {
    background: #a9bdce;
    flex:1;
    display: flex;
    justify-content: flex-start;
    align-items:center;
    padding: 0.5rem;
}

.user-container label{
    font-size: 14px;
    margin-right:1rem;
}

.user-container input{
    border-radius: 3px;
    border:none;
    height: 100%;
}


.display-container {
    flex: 12;
    background: #b2c7d9;
    overflow-y:scroll;
}


.input-container{
    flex:1;
    display: flex;
    justify-content: stretch;
    align-items: center;
}

.input-container span{
    display: flex;
    justify-content: flex-start;
    align-items: center;
    padding: 0.3rem;
    width: 100%;
}

.chatting-input{
    font-size: 12px;
    height:100%;
    flex:8;
    border: none;
}

.send-button {
    flex:1;
    background: #ffeb33;
    border:none;
    height: 100%;
    border-radius: 3px;
}


.chatting-list li{
    width: 90%;
    padding: 03rem;
    display:flex;
    justify-content: flex-start;
    align-items: flex-end;
    margin-top: 0.5rem;
}

.sent {
    flex-direction: row-reverse;
    float: right;
}

.sent .message {
    background: darkorange;
}

.received .message{
    background: deepskyblue;
}


.profile {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.profile .user {
    font-size: 10px;
    margin-bottom: 0.3rem;
}

.profile .image{
    border-radius: 50%;
    object-fit: cover;
    width: 50px;
    height: 50px;
}

.message {
    border-radius: 5px;
    padding: 0.5rem;
    font-size: 12px;
    margin: 0 5px;
    flex: 7;
}

.time {
    font-size: 10px;
    margin:0 5px;
}

~~~



















