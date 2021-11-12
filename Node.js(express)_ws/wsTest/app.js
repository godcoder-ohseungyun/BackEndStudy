const express = require("express") //node_modules에 저장된 express module을 바라봄
const app = express() //express 모듈을 변수 app에 저장
const path = require('path')

const http  = require('http') //웹 소켓을 사용하기위해 http 모듈이 필요: ws프로토콜은 http프로토콜을 이용한다.
const server = http.createServer(app); //express app을 http 서버로 실행하도록 변수로 저장

const socketIO = require('socket.io'); //소켓 아이오 모듈
const io = socketIO(server)//소켓 아이오에 http 서버

const moment = require("moment")


//console.log(__dirname); //__dirname == 현재 프로젝트 경로

app.use(express.static(path.join(__dirname,"src"))); //서버 기본 시작 경로

const PORT = process.env.PORT || 5000; //프로세스 환경 포트가 있으면 그걸 사용하고 || 아니면 5000 번

server.listen(PORT,()=>console.log(`server is running ${PORT}`)); //서버 실행시 해당 포트로 연결 :''과 ``차이 뭐지?


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
