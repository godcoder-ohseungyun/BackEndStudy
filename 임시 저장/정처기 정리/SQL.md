# SQL

---

**DDL(데이터 정의어)**

| 명령어 | 기능                                    |
| ------ | --------------------------------------- |
| CREATE | SCHEMA, DOMAIN, TABLE, VIEW, INDEX 정의 |
| ALTER  | TABLE에 대한 정의 변경                  |
| DROP   | SCHEMA, DOMAIN, TABLE, VIEW, INDEX 삭제 |



**DML(데이터 조작어)**

| 명령어 | 기능                                    |
| ------ | --------------------------------------- |
| SELECT | 테이블에서 조건에 맞는 튜플 검색        |
| INSERT | 테이블에 새로운 튜플 삽입               |
| DELETE | 테이블에서 조건에 맞는 튜플 삭제        |
| UPDATE | 데이블에서 조건에 맞는 튜플의 내용 변경 |



**DCL(데이터 제어어)**

| 명령어   | 기능                                                         |
| -------- | ------------------------------------------------------------ |
| COMMIT   | 명령에 의해 수행된 결과를 실제 물리적 디스크로 저장, 작업이 정상적으로 완료되었음을 알림 |
| ROLLBACK | 데이터베이스 조작 작업이 비정상적으로 종료되었을 때 원래의 상태로 복구 |
| GRANT    | 데이터베이스 사용자에게 사용자 권한 부여                     |
| REVOKE   | 데이터베이스 사용자의 사용권한 취소                          |



# SQL문

---



## 1.DDL - 테이블을 조작

---

### **CREATE** 

> **스키마 생성**
>
> + 스키마란 하나의 저장공간
> + 스키마란 하나의 이름 공간이다: 이 스키마 안에, 여러 개체들(테이블, 자료형, 함수, 연산자)이 담긴다.
>
> ~~~JAVA
> CREATE SCHEMA 대학교 AUTHORIZATION 철수; //철수가 접근할수있는 대학교라는 이름의 스키마
> ~~~
>
> 
>
> **도메인 생성**
>
> + 일종의 이름변수 (상수)
>
> ~~~JAVA
> CREATE DOMAIN SEX CHAR(1)    //정의된 도메인 이름은 'SEX', 문자형이고 크기는 1자
> DEFAULT '남'    //도메인 SEX를 지정한 속성의 기본값은 '남'
> CONSTRAINT VALID-SEX CHECK (VALUE IN ('남', '여'));    //SEX를 지정한 속성에는 '남', '여' 중 하나의 값만 지정할 수 있음
> ~~~
>
> + 활용
>
> ~~~JAVA
> CREATE TABLE EMP (
> empid	   int(7),
> emp_name   varchar(20),
> manager	   int(7),
> address    varchar(100),
> SEX	   SEX  //이런식으로 사용됨
> ); 
> ~~~
>
> 
>
> **테이블 생성**
>
> ~~~
> CREATE TABLE 테이블명(
> 
> 속성명 data_type [DEFAULT 기본값] [NOT NULL], ...,
> 
> [PRIMARY KEY (기본키_속성명)],
> 
> [UNIQUE (대체키_속성명)],
> 
> [FOREIGN KEY (외래키_속성명) REFERENCES 참조테이블(기본키_속성명)]
> 
> [ON DELETE 옵션]
> 
> [ON UPDATE 옵션],
> 
> [CONSTRAINT 제약조건명] [CHECK (조건식)]
> 
> );
> ~~~
>
> ~~~java
> CREATE TABLE 학생(    //<학생>테이블 생성
> 
> 이름 VARCHAR(15) NOT NULL,    //'이름'속성은 최대 문자 15자, NULL값 갖지 않음
> 
> 학번 CHAR(8) ,    //'학번'속성은 문자 8자
> 
> 전공 CHAR(5),    //'전공'속성은 문자 5자
> 
> 성별 SEX,    
> 
> 생년월일 DATE
> 
> PRIMARY KEY(학번),    //'학번'을 기본키로 정의
> 
> FOREIGN KEY(전공) REFERENCES 학과(학과코드)    //'전공'속성은 <학과>테이블의 '학과코드'속성 참조하는 외래키
> 	ON DELETE SET NULL    //<학과> 테이블에서 튜플이 삭제되면 관련된 모든 튜플의 '전공;속성 값 NULL로 변경
> 	ON UPDATE CASCADE,   // <학과>테이블에서 '학과코드' 변경되면 관련된 모든 튜플의 '전공'속성 값도 같은 값으로 변경 
> 
> CONSTRAINT 생년월일제약 CHECK(생년월일>='1980-01-01');   // '생년월일' 속성에는 1980-01-01 이후의 값만 저장            
> 
> CONSTRAINT VALID-SEX CHECK (VALUE IN ('남', '여'));    //SEX를 지정한 속성에는 '남', '여' 중 하나의 값만 지정할 수 있음
> ~~~
>
> > **CASCADE?** 참조 테이블의 튜플이 변경되면 해당 변경을 참조하고있는 테이블에도 적용함
> >
> > > 삭제되면 삭제, 변경되면 변경
>
> 
>
> 
>
> **인덱스 생성**
>
> + 특정 컬럼의 값을 가지고 정렬해 인덱스로 보관함으로써 검색연산의 효율성을 높힌다.
> + 자료구조로 배열, 해쉬 , 트리를 사용한다.
> + 단, 별도의 메모리와 데이터 변화에따라 재정렬(최신화)이 필요하다. (이때마다 연산비용 발생) 
>
> > https://choicode.tistory.com/27
>
> ![img](https://blog.kakaocdn.net/dn/bPb8pb/btrePWRO9HY/qrzMfX84KAAuFgkyZkKtKK/img.png)
>
> ~~~java
> CREATE INDEX 고객번호_INX
> ON 고객(고객번호 DESC); // <고객> 테이블에서 '고객번호' 속성에 대해 내림차순으로 정렬해 '고객번호_INX'라는 이름으로 인덱스 정의
> ~~~



### ALTER 

+ 변경작업

~~~java
ALTER TABLE 테이블명 ADD 속성명 data_type [DEFAULT '기본값']; //ALTER TABLE 학생 ADD 학년 VARCHAR(3);

ALTER TABLE 테이블명 ALTER 속성명 [SET DEFAULT '기본값']; //기본값 변경

ALTER TABLE 테이블명 DROP COLUMN 속성명 [CASCADE]; //속성 삭제
~~~



### DROP

+ 스키마,도메인,테이블,뷰,인덱스 삭제

~~~JAVA
/**
- CASCADE : 삭제할 요소를 참조하는 다른 모든 개체를 함께 삭제한다. 즉, Main Table의 데이터 삭제 시 각 외래키와 관계를 맺고 있는 모든 데이터를 제거

- RESTRICT : 다른 개체가 제거할 요소를 참조중일 때는 제거 취소
*/
DROP SCHEMA 스키마명 [CASCADE | RESTRICT];

DROP DOMAIN 도메인명 [CASCADE | RESTRICT];

DROP TABLE 테이블명 [CASCADE | RESTRICT]; //DROP TABLE 학생 CASCADE;

DROP VIEW 뷰명 [CASCADE | RESTRICT];

DROP INDEX 인덱스명;
~~~

---



## 2. DML - 테이블의 데이터를 조작

---

### SELECT

+ 검색 및 조회

~~~java
SELECT  [PREDICATE] [테이블명.]속성명1, [테이블명.]속성명2, ......,

[그룹함수(속성명)],

FROM 테이블명1, 테이블명2, ...

[WHERE 조건]

[GROUP BY 속성명1, 속성명2, ...]

[HAVING 조건]

[ORDER BY 속성명 [ASC | DESC]]
~~~

> [PREDICATE] 에는 ALL  또는 DISTINCT 가 들어간다.
>
> > ALL : 모든 튜플 검색, (생략가능)
> >
> > **DISTINCT :** 중복된 튜플이 있으면 그중 첫 번째 한 개만 검색



~~~java
//기본검색(FROM)
SELECT * FROM 사원;
SELECT DISTINCT 주소 FROM 사원;


//조건검색(WHERE) with AND, OR, LIKE, BETWEEN AND, IS
SELECT * FROM 사원 WHERE 부서='기획';
SELECT * FROM 사원 WHERE 부서='기획' AND 주소='대흥동'; // <사원>테이블에서 "기획"부서에 근무하면서 "대흥동"에 사는 사람의 튜플 검색
SELECT * FROM 사원 WHERE 부서='기획' OR 부서='인터넷'; // <사원>테이블에서 '부서'가 "기획"이거나 "인터넷"인 튜플 검색
SELECT * FROM 사원 WHERE 이름 LIKE "김%"; //<사원>테이블에서 성이 "김"인 사람의 튜플 검색.
SELECT * FROM 사원 WHERE 생일 BETWEEN #01/01/69# AND #12/31/73#;
SELECT * FROM 사원 WHERE 주소 IS NULL; //<사원>테이블에서 '주소'가 NULL인 튜플 검색


//정렬검색(ORDER BY)
SELECT * FROM 사원 ORDER BY 주소 DESC;
SELECT * FROM 사원 ORDER BY 부서 ASC, 이름 DESC; //<사원>테이블에서 '부서'를 기준으로 오름차순 정렬, 같은 '부서'에 대해서는 '이름'을 기준으로 내림차순 정렬시켜 검색


//서브쿼리()
SELECT 이름, 주소 FROM 사원 WHERE 이름=(SELECT 이름 FROM 여가활동 WHERE 취미='나이트댄스'); //'취미'가 "나이트댄스"인 사원의 '이름'과 '주소' 검
SELECT * FROM 사원 WHERE 이름 NOT IN(SELECT 이름 FROM 여가활동); //취미활동을 하지 않는 사원들 검색


//복수검색
SELECT 사원.이름, 사원.부서, 여가활동.취미, 여가활동.경력 FROM 사원, 여가활동 WHERE 여가활동.경력>=10 AND 사원.이름=여가활동.이름; //사원 테이블과 여가활동 테이블 동시 검색

//그룹검색(GROUP BY) - 그룹함수(COUNT , AVG , SUM) , HAVING
SELECT type, COUNT(name) AS cnt FROM hero_collection WHERE type > 1 GROUP BY type HAVING cnt >= 2 ORDER BY type DESC;


//결과 합치기 - UNION(중복허용x) . UNION ALL(중복허용o) , INTERSECT(공통만) , EXCEPT(첫번째에서 두번째 조회결과 제외하고 반환)
SELECT * FROM 사원 UNION SELECT * FROM 직원;
~~~

> [GROUP BY HAVING 모두 정리]https://extbrain.tistory.com/56
>
> WHERE는 그룹화 하기 전에 조건 HAVING은 그룹화 후에 조건





### INSERT

+ 데이터 삽입

~~~
INSERT INTO 테이블명([속성명1, 속성명2, .....]) VALUES(데이터1, 데이터2, ...);
~~~

~~~java
INSERT INTO 사원(이름, 부서) VALUES('홍길동', '인터넷');
INSERT INTO 편집부원(이름, 생일, 주소, 기본급) SELECT 이름, 생일, 주소, 기본급 FROM 사원 WHERE 부서 = '편집';
~~~



### DELETE

+ 데이터 삭제

~~~
DELETE FROM 테이블명 [WHERE조건]; 
~~~

~~~java
DELETE FROM 사원 WHERE 이름 = '황진이'; // <사원>테이블에서 "황진이"에 대한 튜플을 삭제
DELETE FROM 사원;
~~~



### UPDATE 

~~~
UPDATE 테이블명 SET 속성명 = 데이터, [속성명=데이터, .....] [WHERE 조건];
~~~

~~~java
UPDATE 사원 SET 주소='수색동' WHERE 이름='홍길동'; //<사원>테이블에서 "홍길동"의 '주소'를 "수색동"으로 수정

UPDATE 사원 SET 부서='기획', 기본급=기본급+5 WHERE 이름='홍길동'; // <사원>테이블에서 "홍길동"의 '부서'를 "기획부"로 변경하고 '기본급'을 5만원으로 인상시키시오
~~~

---



## 3. DCL

---

###  **GRANT / REVOKE**

- GRANT : 권한 부여
- REVOKE : 권한 취소

~~~
GRANT 사용자 등급 TO 사용자_ID_리스트;
REVOKE 사용자 등급 FROM 사용자_ID_리스트;
~~~

> DBA : 데이터 베이스 관리자
>
> RESOURCE : 데이터베이스 및 테이블 생성 가능자
>
> CONNECT : 단순 사용자

+ 예제

~~~
GRANT RESOURCE TO NABI;
~~~

**COMMIT**

+ 트랜잭션이 성공적으로 끝나면 데이터베이스가 새로운 일관성 상태를 가지기 위해 변경된 모든 내용을 데이터베이스에 반영하여야 하는데, 이때 사용하는 명령어



**ROLLBACK**

+ 아직 COMMIT되지 않은 변경된 모든 내용들을 취소하고 데이터베이스를 이전 상태로 되돌리는 명령어



**SAVEPOINT**

+ 트랜잭션 내에 ROLLBACK 할 위치인 저장점을 지정하는 명령어

---