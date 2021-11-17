"ust strict" //오류방지용 문구

const socket = io(); //client 에 socket io 모듈 불러오기

//console.log(socket) //소켓 상태
/*
Socket {connected: false, disconnected: true, receiveBuffer: Array(0), sendBuffer: Array(0), ids: 0, …}
*/ 


/*
socket.emit("chatting","client message..");//메세지 강제로 보내는 method

socket.on("chatting",(msg)=>{
    console.log(msg);
})
*/

const nickname = document.querySelector("#nickname");
const chatList = document.querySelector(".chatting-list");
const chatInput = document.querySelector(".chatting-input");
const sendButton = document.querySelector(".send-button");
const displayContainer = document.querySelector(".display-container");

/* c -> s */
function send(){
    const param = { //json object
        name: nickname.value,
        msg: chatInput.value
    }
    socket.emit("chatting",param); //서버에 객체 전송
}

/* 이밴트 핸들러 */
chatInput.addEventListener("keypress",(event)=>{
    //enter이면
    if(event.keyCode === 13){
        send();
        chatInput.value = "" //입력창 초기화
    }
})

sendButton.addEventListener("click",()=>{ //버튼 클릭 이밴트 발생시 아래 펑션 실행 html -> server
    send();
    chatInput.value = "" //입력창 초기화
})


//server -> client
socket.on("chatting",(param)=>{
    const {name,msg,time} = param; //param parsing data 분리
    const item = new Li(name,msg,time);
    item.makeLi();
    displayContainer.scrollTo(0,displayContainer.scrollHeight);
})

function Li(name,msg,time){
    this.name = name;
    this.msg = msg;
    this.time = time;

    this.makeLi = ()=>{
        const li  = document.createElement("li");

        li.classList.add(nickname.value === this.name ? "sent" : "received")

        const dom = `<span class="profile">
        <span class="user">${this.name}</span>
        <img class="image" src="/image/human.PNG" alt="any">
        </span>
        <span class="message">${this.msg}</span>
        <span class="time">${this.time}</span>`;
        li.innerHTML = dom;
        chatList.appendChild(li)
    }

}

