# SQL의 이해

---

## [목차]

[기본쿼리문](#기본쿼리문)

>  기본 조회 /삽입 /수정 /삭제

[조건문](#조건식)

> 조회시 다양한 조건 부여

[조인연산](#조인연산)

[LIKE](#LIKE)

> 값 포함 여부 필터링

[변환함수](#변환함수)

> 데이터들을 변환하는 함수들
> 데이터 길이반환, **대소문자 변환**
> **문자포함여부** , 날짜형식
> 문자열 자르기

[CASE](#CASE)

>조건문

[GROUP_BY](#GROUP_BY)

> 데이터를 **특정 그룹군**으로 **묶어서 반환**
> COUNT MAX SUM 등 **그룹함수** 존재
> HAVING SUM



### **DDL DML DCL**

<img src="https://user-images.githubusercontent.com/68331041/135019757-25b041d3-6170-48f3-8e21-e6bfcdbc676f.png" alt="image" style="zoom: 67%;" />

<img src="https://user-images.githubusercontent.com/68331041/135019781-9a3041c9-4906-4ecf-8837-f65d73383e7c.png" alt="image" style="zoom:67%;" />

<img src="https://user-images.githubusercontent.com/68331041/135019829-15df1710-f8f6-4769-bab7-65a8100b632d.png" alt="image" style="zoom:67%;" />



## 사전지식

### sql에서 외래키란?

+ 테이블간 JOIN을 위해 즉, 연관된 데이터 조회를 위해 보유하고있는 연관 테이블의 프라임 키

+ 사원(**ID**,이름,나이,회사(**FK**))   /       회사(**ID**,이름)

  > 사원테이블의 회사속성은 회사테이블의 ID를 가짐으로써 **연결 조인이 가능**하다



### SQL에서 외래키 데이터의 삽입을 통해 관계를 맺는다. 중복되는 쿼리문이 너무 많아진다. JPA와 같은 ORM을 이용하여 편리하게 개발하자 

+ 단, 데이터베이스의 구조 파악과 더 빠른 연산의 처리를 위해 **쿼리문도 알아둘 필요가 있다.** 
+ 쿼리문은 ORM에 비해 변환과정이 없어 더 빠른 속도를 가지고있다.

# 기본쿼리문

---

+ **이 글은 ORACLE SQL 방언을 기준으로 작성되어있다.**
+ ['프로그래밍/SQL' 카테고리의 글 목록 (tistory.com)](https://lcs1245.tistory.com/category/프로그래밍/SQL)
+ 문자열은 ' '를 사용한다.
+ 행 열은 0 부터 시작



### 기본 쿼리문

+ **테이블 기본 조회 /삽입 /수정 /삭제**

<img src="https://user-images.githubusercontent.com/68331041/135194144-e1a1d757-390b-4e30-838a-692375d61a9f.png" alt="image" style="zoom: 67%;" />

<img src="https://user-images.githubusercontent.com/68331041/135194163-948b203c-f520-4e49-bf31-33a0cfd558c6.png" alt="image" style="zoom:67%;" />

<img src="https://user-images.githubusercontent.com/68331041/135194176-0458356d-e019-4d99-aa3f-8a6d6b1c2663.png" alt="image" style="zoom:67%;" />



### 조건식

+ **BETWEEN & NOT BETWEEN**

> 범위 검색
>
> LIMIT나 TOP을 통해 범위 제어도 가능

<img src="C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20210929114630059.png" alt="image-20210929114630059" style="zoom: 67%;" />

+ **IN & NOT IN**

> 필터 검색

<img src="C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20210929114724002.png" alt="image-20210929114724002" style="zoom: 67%;" />

+ **REPLICATE**

  <img src="https://user-images.githubusercontent.com/68331041/135194957-3cb2de12-830e-402c-a297-6b007070f45a.png" alt="image" style="zoom: 67%;" />

<img src="https://user-images.githubusercontent.com/68331041/135194865-4bf9ec95-3ebf-4c8f-9eed-3eca379fbf77.png" alt="image" style="zoom: 67%;" />

+ **요소 합치기 CONCAT**

<img src="https://user-images.githubusercontent.com/68331041/135195182-216975a0-4b40-4162-949a-02eb220bd87c.png" alt="image" style="zoom: 67%;" />

+ **날짜 계산 함수**

<img src="C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20210929115635903.png" alt="image-20210929115635903" style="zoom: 67%;" />

<img src="https://user-images.githubusercontent.com/68331041/135206489-145e014e-2320-406e-9cce-378e082d49a1.png" alt="image" style="zoom:67%;" />

+ **NULL 처리**

<img src="https://user-images.githubusercontent.com/68331041/135195573-25e4a75b-d144-4f90-8d78-6ff389d93342.png" alt="image" style="zoom:67%;" />



### 조인연산

+ **LEFT JOIN / RIGHT JOIN / INNER JOIN / FULL JOIN /SELF JOIN **

  [SQL JOIN - INNER JOIN / OUTER JOIN / LEFT JOIN / RIGHT JOIN / SELF JOIN (tistory.com)](https://lcs1245.tistory.com/entry/SQL-JOIN-INNER-JOIN-OUTER-JOIN-LEFT-JOIN-RIGHT-JOIN-SELF-JOIN?category=348747)

~~~sql
<left join>
select * from a left join b on a.id = b.id
/**
a는 모두 출력 + b가 연결된 경우 함께 리턴 안된경우는 null로 리턴
right는 반대
**/

<inner join>
select * from a inner join b on a.id = b.id
/**
a와 b가 연결된 결과만 리턴
**/


<full join>
/**
양측 다 연결관계 상관없이 풀로 리턴
**/

<self join>
select * from a a join a b on a.manager = b.id
/**
a가 a에 조인하는 경우
a를 a,b로 칭한다
**/

~~~



## LIKE

<img src="https://user-images.githubusercontent.com/68331041/135199796-ce3eb41a-fe97-4468-b242-412764fd2a4b.png" alt="image" style="zoom:67%;" />

<img src="https://user-images.githubusercontent.com/68331041/135199824-fc512564-1b9f-42e7-b8d0-41ad38a18241.png" alt="image" style="zoom:67%;" />

## 변환함수

+ 데이터의 길이를 구하는 함수

~~~SQL
/*LENGTH: 바이트 수를 읽어서 가져옴*/
SELECT LENGTH(NAME) FROM A;

/*CHAR_LENGTH: 문자 수를 읽어서 가져옴*/
SELECT CHAR_LENGTH(NAME) FROM A;

/*응용: NAME 컬럼이 8 이상인 ROW 리턴*/
SELECT * FROM A WHERE CHAR_LENGTH(NAME) >=8;

/*대소문자 변환 UPPER LOWER: 대소문자 검색 모두 지원하도록 할수있음*/
SELECT * FROM A WHERE UPPER(NAME) = 'A' AND NAME ='a'

/*현재시간 조회 함수*/
GETDATE();

/*문자포함여부: E-MAIL 칼럼에서 NAVER라는 글자가 포함된 결과 리턴*/
SELECT NAME FROM TABLE WHERE CHARINDEX('NAVER',E-MAIL)>0  /*TRUE = 1*/

/*날짜 형 변환 & 문자열 자르기 함수도 있다.*/
~~~



## CASE

+ 조건문

![image](https://user-images.githubusercontent.com/68331041/135206207-a3139bf1-bab5-40b7-9371-ff7e5b9431e6.png)

**TABLE의 모든 칼럼을 리턴하고 IDENTITY라는 칼럼을 생성해 조건문을 이행**

![image](https://user-images.githubusercontent.com/68331041/135206254-f57f3fba-d27f-4bf8-9314-76d7822fdf1f.png)



## GROUP BY

<img src="https://user-images.githubusercontent.com/68331041/135206957-adb0f850-c27c-4116-baf1-0514dc7f994f.png" alt="image" style="zoom:67%;" />

~~~SQL
SELECT COMPANY,SUM(SALARY) FROM KOREACORP GROUP BY COMPANY HAVING SUM(SALARY)>=1200;
/*한국회사들 테이블에서 회사별로 급여의 총합이 1200이상인 회사와 합계 급여 칼럼을 보고싶다*/
~~~





## 집합연산



### UNION & UNIONALL(합집합) 

+ Union은 두 쿼리의 결과의 중복값은 제거하고 보여줌

+ UnionAll은 중복된값도 전부 다 보여줌

  ~~~
  Union 사용시 주의점
  
  ☞ 칼럼명이 같아야한다. (같지않을경우 AS를 사용하여 같게 만들어주면 됩니다.)
  
  ☞ 칼럼별 데이터타입이 같아야한다.
  ~~~

  ~~~SQL
  SELECT * FROM EX_TABLE1
  Union/UnionAll
  SELECT * FROM EX_TABLE2
  -- 테이블A와 테이블B의 모든 데이터를 출력한다.
  -- 조회하려는 컬럼의 수는 일치해야 한다.
  -- UNION은 중복된 행을 하나의 행으로 출력하고, UNION ALL은 중복된 행을 그대로 출력한다.
  -- 즉, 중복을 제거하며 결과를 가져오는 UNION보다는 UNION ALL의 속도가 더 빠르다.
  ~~~

  

  ###  **INTERSECT(교집합)**

  ~~~SQL
  SELECT * FROM 테이블A 
  INTERSECT 
  SELECT * FROM 테이블B
  -- 일치하는 데이터만 추출
  ~~~

  

  ### EXCEPT(차집합)

  ~~~SQL
  SELECT * FROM 테이블A
  EXCEPT
  SELECT * FROM 테이블B
  -- 테이블A에 있는 데이터에서 테이블B와 중복을 제외한 데이터를 출력한다.
  ~~~

  




# 실전 변수

---



## 제약조건 



+ 기본키 설정 1줄로

~~~sql
CONSTRAINT F_PK PRIMARY KEY (FID,WID), -- FID와 WID를 PK로
~~~



+ 외래키는 연관테이블 PK에 따라 받는법이 다름

~~~SQL
CONSTRAINT F_W_FK
		FOREIGN KEY (WID) REFERENCES Worker(WID) --  연관테이블이 단일 PK인 경우
		
CONSTRAINT FM_F_FK
		FOREIGN KEY (FID,WID) REFERENCES FAMILY(FID,WID) --  연관테이블이 복합 PK인 경우
~~~



+ 복합 키를 갖는 테이블로부터 외래키를 받아올때 외래키도 복합으로 받아야한다.

~~~SQL
-- create FAMILY
CREATE TABLE FAMILY(
	FID   CHAR(4) NOT NULL, --PK
	WID   CHAR(4) NOT NULL, --FK,PK
	FNUMBER     VARCHAR(25) NOT NULL, 

	CONSTRAINT F_PK
		PRIMARY KEY (FID,WID), -- 복합PK 
	CONSTRAINT F_W_FK
		FOREIGN KEY (WID) REFERENCES Worker(WID) -- 외래키 
	);
-- create FAMILY
CREATE TABLE FMEMBER(
	FID   CHAR(4) NOT NULL, --FK,PK
	WID   CHAR(4) NOT NULL, --FK,PK
	FMEMBER    VARCHAR(25) NOT NULL,--PK 

	CONSTRAINT FM_PK
		PRIMARY KEY (FID,WID,FMEMBER), -- 복합 PK 
	
	CONSTRAINT FMEMBER_MEMBER_CHK
           CHECK (FMEMBER IN ('M','F','B','S')),
	CONSTRAINT FM_F_FK
		FOREIGN KEY (FID,WID) REFERENCES FAMILY(FID,WID), -- 연관엔티티 FAMILY 가 복합 PK를 가지고 있음으로 외래키도 그에 따라 복합으로 받아야한다.
	);
~~~

