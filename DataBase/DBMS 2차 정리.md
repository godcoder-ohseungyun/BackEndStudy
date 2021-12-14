# 저장 프로시저

---

+ Stored Procedure

DBMS에서 제공하는 프로그램 SQL 이다. 프로그래밍의 함수(메서드)를 생각하면 이해하기 쉽다.

여러 쿼리문의 집합으로 IF,WHILE등의 간단한 프로그램문을 이용해 일련의 동작 집합을 만들어두고 사용할수있어서 코드의 재사용성을 높여 코드의 복잡성을 줄일 수 있다.

매개변수를 넘겨주고 넘겨받을수있다.



### 사용자 정의 함수와의 차이점

사용자 정의함수와 매우 유사해 보인다. 

이 둘의 차이점은 프로시저는 수행하는 절차가 목적이라 리턴이 없어도 되지만 사용자 정의함수는 결과 도출이 목적이라 리턴이 반드시 있어야한다.

또한  프로시저는 여러개의 리턴을 가질수있지만 사용자 정의함수는 단 한개의 리턴만 가진다.

또한 select문과 같은 쿼리문 내부에서는 사용자 정의함수는 사용될수있지만 프로시저는 사용될수없다.



### 장점

**네트워크 트래픽 감소**

> 코드 캡슐화가 없을때는 개별 쿼리가 모두 네트워크 통신 하지만, 프로시저를 이용하면 일련의 쿼리들을 캡슐화 하여
>
> 프로시저만 네트워크를 통신을 하기때문에 네트워크 트래픽을 줄일수있다.

**강력한 보안**

> 내부 작업에 접근하지 않고 프로시저를 통해 작업을 수행할수있다.
>
> 개별수준의 보완이 필요없고 프로시저를 통해 보완작업을 수행할수있다.
>
> 내부를 은닉할수있다.

**코드 재사용**

> 작업을 캡슐화 해두고 필요할때 호출하여 사용한다.
>
> 여러 사용자나 클라이언트 응용프로그램에서 공유되서 사용할수있다.
>
> 저장 프로시저의 내부 코드를 쉽게 수정할수있어 유연하다.

**성능향상**

> 실행단계에서 프로시저들이 이미 생성됨으로 중간 생성이 없어 처리시간이 향상된다.
>
> > 실행단계에서 버퍼풀에 미리 저장



### 생성

~~~MSSQL
CREATE PROCEDURE [프로시저명] ([파라미터])
AS
BEGIN
	[쿼리문]
END
GO
~~~

~~~MSSQL
CREATE PROCEDURE Schema_Name.Procedure_Name
(
  @site NVARCHAR(500)
  ,@name NVARCHAR(100)
  ,@ret INT OUTPUT --리턴
)
AS
BEGIN
	SELECT @site --파라미터 사용
END
GO
~~~



### 수정

~~~MSSQL
ALTER PROCEDURE Schema_Name.Procedure_Name -- CREATE -> ALTER
(
  @site NVARCHAR(500) --수정
  ,@name NVARCHAR(100)
)
AS
BEGIN
  SELECT 1 --수정
END
GO
~~~



### 호출

~~~MSSQL
--매개변수가 없을때
exec Schema_Name.Procedure_Name

--매개변수가 있을때
DECLARE @site NVARCHAR(500)
DECLARE @name NVARCHAR(100)
DECLARE @ret  INT
 
SET @site = 'tistory.com'
SET @name = 'mozi'
 
exec Schema_Name.Procedure_Name @site, @name, @ret --매개변수와 함께 호출
 
SELECT @ret -- '생성'에서 OUTPUT 지정한 매개 변수는 리턴되어 사용할수있음
~~~



### 삭제

~~~MSSQL
DROP PROCEDURE Schema_Name.Procedure_Name
~~~





# 트리거

---

+ Trigger

#### DBMS내에서 특정 이밴트가 발생한 경우 특정 동작을 하도록 하는 저장 프로시저의 일종이다.

> 일반적으로 유저에 의해 실행될수없고, 파라미터를 가질수없다.



### 종류

> **DMLtriggers:** INSERT, UPDATE, DELETE 이밴트에 발동합니다.
>
> 
>
> **DDL triggers:**  CREATE, ALTER, DROP 이밴트에 발동합니다.
>
> 
>
> **Iogon triggers: ** LOGON 이밴트에 발동합니다.  사용자 세션이 설정될 때 발생



### 언제 사용하나?

트리거는 다양한 용도로 사용할수있는데 보통 데이터 변화(입력,수정 등)시 발동해 이밴트 로그를 기록하거나 데이터를 추가 조작 데이터 기록전 무결성 채크와 유효성 검사등에 사용된다.



## 생성

> **FOR** 또는 **AFTER**는 이밴트 발생 이후 발동됨
>
> **INSTEAD OF** 이밴트 발생 쿼리 대신에 발동됨

**DML**

~~~MSSQL
CREATE TRIGGER [dbo].[TRG] on [dbo].[TABLE] --TABLE이라는 테이블의 트리거 
FOR [INSTEAD OF | AFTER] INSERT,UPDATE --insert,update 쿼리 이후에 발동함
AS
  [쿼리문]
~~~

~~~MSSQL
--예시
CREATE TRIGGER M_TRIGGER on MEMBER
INSTEAD OF INSERT,UPDATE --insert,update 쿼리 발생시 대신 발생함

AS
  select * from MEMBER
~~~

**DDL**

~~~MSSQL
CREATE TRIGGER [dbo].[TRG] on [dbo].[TABLE] --TABLE이라는 테이블의 트리거 

FOR DROP_TABLE, ALTER_TABLE --DROP이후

AS
  [쿼리문]
~~~

~~~MSSQL
--예시
CREATE TRIGGER M_TRIGGER on MEMBER
FOR DROP_TABLE, ALTER_TABLE --DROP이후

AS
  select * from MEMBER
~~~



**LOGON**

~~~~MSSQL
CREATE TRIGGER [dbo].[TRG] ON ALL SERVER  WITH EXECUTE AS 'login03'
FOR LOGON
AS
  [쿼리문]
~~~~

~~~MSSQL
--예시
CREATE TRIGGER [connection_limit_trigger03] ON ALL SERVER WITH EXECUTE AS 'login03'
FOR LOGON
AS
BEGIN
IF ORIGINAL_LOGIN()= 'login03' AND (SELECT COUNT(*) FROM sys.dm_exec_sessions
					WHERE is_user_process = 1 AND
					original_login_name = 'login03') > 3
END;
GO
~~~



## Reference

---

https://docs.microsoft.com/ko-kr/sql/relational-databases/triggers/logon-triggers?view=sql-server-ver15





# 커서

---

+ CURSOR

MSSQL에서 여러개의 행의 결과값을 보통의 경우는 한번에 처리하는게 좋으나 행별로 처리해야될경우가 있다. 

행별로 처리할경우에 커서를 이용하여 처리할수 있다. 



# 트랜잭션

---

+ TRANSACTION

일련의 SQL묶음으로 여러 쿼리문을 트랜잭션으로 묶어 사용한다.

트랜잭션이 성공하면 트랜잭션동안 이루어진 작업은 데이터베이스에 영구 반영되며, 실패하거나 롤백되면 작업내역은 모두 취소된다.

> 데이터의 일관성을 유지하면서 안정적으로 데이터를 복구하기 위해 트랜잭션을 사용한다.



### 어떨때 사용?

테이블A가 있다고 예를 들어보자.

이 A에 엄청나게 많은 쿼리(수정,삭제,추가)등을 처리한다고했을때 쿼리를 사용한다면 분명히 실수가 발생할것이다.

쿼리가 실행될때마다 영구 반영 되어버린다면 실수를 발견한후에는 이미 늦었다.



쿼리를 반영한 결과를 확인하고 되돌리거나 확인이 끝난이후 적용한다면 어떨까?



**트랜잭션**은 이런 기능을 제공한다.

트랜잭션을 시작하고나서 쿼리문을 적용한 후 문제가 없는지 검증한 이후 커밋 혹은 롤백한다.

> 영구반영되기전 한번 확인할 기회를 주는것

~~~MSSQL
BEGIN TRAN --트랜잭션 시작

ROLLBACK TRAN --트랜잭션 이전상태로 ROLL BACK

COMMIT TRAN --트랜잭션 완료, 영구반영
~~~

**롤백**

> 트랜잭션 내용을 모두 취소하고 이전상태로 되돌린다.

~~~mssql
BEGIN TRAN --트랜잭션 시작
[쿼리문1]
[쿼리문2]
[쿼리문N]

ROLLBACK TRAN --트랜잭션 이전상태로 ROLL BACK
~~~

**커밋**

> 트랜잭션 내용을 모두 영구반영한다.

~~~MSSQL
BEGIN TRAN --트랜잭션 시작
[쿼리문1]
[쿼리문2]
[쿼리문N]

COMMIT TRAN --트랜잭션 완료, 영구반영
~~~



**예제**

~~~MSSQL
BEGIN TRAN -- 트랜잭션 시작
UPDATE My_Table SET DT= '20190101'  WHERE CD_COMPANY ='0327' AND DT LIKE '2017%' --Update수행

SELECT * FROM My_Table WHERE COMPANY ='0327' AND DT LIKE '2019%' --바뀐 데이터 확인 

COMMIT TRAN -- 이상없다면 트랜잭션 반영
~~~



# Reference

---

https://jerryjerryjerry.tistory.com/48

https://coding-factory.tistory.com/82







# JDBC

---

**JDBC(Java DataBase Connectivity)**는 **데이터베이스에 연결 및 작업**을 하기 위한 **자바 표준 인터페이스**.

DBMS의 종류에 상관없이 하나의 JDBC API를 이용해서 데이터베이스 작업(CRUD)을 처리한다.

> JDBC는 DB에 접근해서 CRUD를 쉽고 효율적이게 할 수 있게 하고, 고성능에서의 세련된 메소드를 제공하며 쉽게 프로그래밍 할 수 있게 도와줍니다.

DBMS에 종속적으로 개발하지 않고 독립적인 개발이 가능하도록 한다.



### 탄생배경

DBMS(Oracle, MySQL, MongoDB 등)는 매우 다양하며 각기 쓰이는 SQL문도 차이가 상이하다.

DBMS마다 쿼리를 새로 작성해야하는등 매우 불편함이 따라오게 되는데, 공통적으로 이를 처리하고자 JDBC가 등장하였다.



### 한계와  JPA

JAVA는 객체지향언어로써 객체의 이점을 살려야한다. 하지만

JDBC만으로는 쿼리종류에 관련없이 쿼리문을 쓸수있는 정도에서 그치기 때문에 여전히 DB와 자바코드간의 괴리를 좁힐수없다.

> DB의 데이터를 다시 객체로 바꾸거나 JAVA 코드의 객체를 DB로 넣기전 파싱해야하는 번거로움이 여전히 존재한다.
>
> 또한 객체 타입의 요소를 테이블로 만들어 내는건 매우 복잡하다.

최근 공부중인 **JPA**를 활용하면 위와 같은 문제를 모두 해결해준다.



# Reference

---

https://jaehoney.tistory.com/29

