# Json

---

+ JavaScript Object Notation
+ 경량의 데이터 교환 방식
+ javascript에서 객체를 생성할때 사용하는 양식
+ 그러나 javascript가 아니라 데이터를 나타내는 일종의 포멧일뿐이다.
+ 특정 프로그래밍 문법에 종속되지 않고 java,c++ 등 다양한 언어에서도 사용가능하다.



### 특징

클라이언트와 서버의 통신에 사용된다.

> HTTP message body에 직접 데이터를 담아서 요청,응답한다.

간단한 문자형식으로 표현되며 가볍고 파악하기 편하다.



### Json api를 사용하여 json통신

**Serialize vs Deserialize**

Serialize : 서버에 전송하기 위해 클라이언트의 객체 데이터를 json으로 변환

Deserialize: 서버의 응답데이터 json을 클라이언트에서 사용할수있도록 객체로 변환



### 표현식

~~~json
//key: value
{ 
  "firstName": "Kwon",
  "lastName": "YoungJae",
  "email": "kyoje11@gmail.com"
}
/*
key값이나 문자열은 항상 쌍따옴표를 이용하여 표기
객체, 배열 등의 표기를 사용가능
원하는 만큼 중첩시켜서 사용
null, number, string, array, object, boolean사용가능
*/
~~~





















































