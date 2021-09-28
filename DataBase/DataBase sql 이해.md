# SQL의 이해

---

### **DDL DML DCL**

<img src="https://user-images.githubusercontent.com/68331041/135019757-25b041d3-6170-48f3-8e21-e6bfcdbc676f.png" alt="image" style="zoom: 67%;" />

<img src="https://user-images.githubusercontent.com/68331041/135019781-9a3041c9-4906-4ecf-8837-f65d73383e7c.png" alt="image" style="zoom:67%;" />

<img src="https://user-images.githubusercontent.com/68331041/135019829-15df1710-f8f6-4769-bab7-65a8100b632d.png" alt="image" style="zoom:67%;" />



### 쿼리문

+ **테이블 생성 /조회 /삽입 /수정 /삭제**

https://lcs1245.tistory.com/4



### 조인

+ **LEFT JOIN / RIGHT JOIN / INNER JOIN / FULL JOIN**

https://yoo-hyeok.tistory.com/98



### sql에서 외래키란?

+ 테이블간 JOIN을 위해 즉, 연관된 데이터 조회를 위해 보유하고있는 연관 테이블의 프라임 키

+ 사원(**ID**,이름,나이,회사(**FK**))   /       회사(**ID**,이름)

  > 사원테이블의 회사속성은 회사테이블의 ID를 가짐으로써 **연결 조인이 가능**하다



### SQL에서 외래키 데이터의 삽입을 통해 관계를 맺는다. 중복되는 쿼리문이 너무 많아진다. JPA와 같은 ORM을 이용하여 편리하게 개발하자 

+ 단, 데이터베이스의 구조 파악과 더 빠른 연산의 처리를 위해 **쿼리문도 알아둘 필요가 있다.** 
+ 쿼리문은 ORM에 비해 변환과정이 없어 더 빠른 속도를 가지고있다.

