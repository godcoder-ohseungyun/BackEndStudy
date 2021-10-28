--컨닝페이퍼 공식
/**
테이블 생성========================================================================================================================================================
**/
create database DreamHome --DB생성
go 

CREATE TABLE Registration (
	clientNo   VARCHAR(7) NOT NULL,
	branchNo   CHAR(4) NOT NULL, 
	staffNo    VARCHAR(5) NOT NULL, 
	dateJoined DATE NOT NULL,
	CONSTRAINT registration_PK   PRIMARY KEY (clientNo, branchNo),
	CONSTRAINT Regist_Client_FK  FOREIGN KEY (clientNo) REFERENCES Client(clientNo),
);
go
--데이터 타입: INT FLOAT TEXT(길이) VARCHAR(가변길이) CHAR(고정길이) DATE DATETIME
/**
기본쿼리========================================================================================================================================================
*/

SELECT DISTINCT * FROM Branch
-- DISTINCT 중복제거

SELECT  staffNo, fName, IName, salary/12 AS monthlySalary FROM Staff; --salary/12 계산식
--salary/12 계산식

SELECT  * FROM Branch WHERE city = London OR city = Glasgow; --조건절 WHERE OR AND
--조건절 WHERE OR AND


SELECT * FROM Staff WHERE position IN (Manager, Supervisor);  
-- IN + NOT IN

SELECT * FROM Staff WHERE position LIKE '%문자_'; -- 앞에는 상관없고 맨 뒤 한글자 앞에 문자라는 글자가 있는것
--  _ 는 한글자를 의미 %는 여러글자를 의미
--LIKE

SELECT * FROM Viewing WHERE propertyNo = PG4 AND comment IS NULL;
-- IS NULL & IS NOT NULL

SELECT * FROM Staff ORDER BY salary DESC;
SELECT * FROM Staff ORDER BY salary,id DESC; --id는 부 정렬키
--order by  DESC:내림차순  ASC:오름차순


SELECT COUNT(DISTINCT propertyNo) AS myCount FROM Viewing WHERE viewDate BETWEEN 1-May-13 AND 31-May-13; --기본함수 이용예제
SELECT COMPANY,SUM(SALARY) FROM KOREACORP GROUP BY COMPANY HAVING SUM(SALARY)>=1200; /*한국회사들 테이블에서 회사별로 급여의 총합이 1200이상인 회사와 합계 급여 칼럼을 보고싶다*/
--group by + having
-- COUNT SUM AVG MIN MAX 등과 함께 사용


-- union except intersect
SELECT * FROM 테이블A
EXCEPT -- 합집합 차집합 교집합 사용가능
SELECT * FROM 테이블B
-- 두 테이블의 컬럼명과 데이터타입이 같아야한다.


/**
서브쿼리========================================================================================================================================================
*/
SELECT staffNo, fName, IName, position, salary FROM Staff WHERE (SELECT AVG(salary) FROM Staff) < salary; --WHERE절 서브쿼리
--조건절 등 필요한곳에 서브쿼리를 사용할수있다.
SELECT staffNo, fName, IName, position, salary FROM Staff WHERE salary > ALL(SELECT salary FROM Staff WHERE branchNo = B003);
--ALL ANY SOME

/**
멀티쿼리(join)========================================================================================================================================================
*/
<inner join>
SELECT c.clientNo, v.fName, IName, propertyNo FROM Client c, Viewing v  WHERE c.clientNo = v.clientNo; --MSSQL INNER JOIN절: [INNER] JOIN ON /[INNER] JOIN USING등 표준문법과 동일
SELECT * FROM Branch b, Staff s, PropertyForRent p WHERE b.branchNo = s.branchNo AND s.staffNo = p.staffNo --여러개의 테이블 JOIN
ORDER BY b.branchNo, s.staffNo, propertyNo;
--c. v. 으로 각 테이블 접근가능
--INNER JOIN: 서로 연결된 결과만 리턴

<left join>
select * from a left join b on a.id = b.id
/**
a는 모두 출력 + b가 연결된 경우 함께 리턴 안된경우는 null로 리턴
right는 반대
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
*/





/**========================================================================================================================================================
내장함수
*/

--날짜시간 함수

SYSDATETIME() --현재날짜 및 시간 반환
GETDATE()

DATEADD(day,100,'2014/10/19') --year week minute 등 올수있음 --100일 더한 날짜 반환
DATEDIFF() --뺀 날짜 반환

DATEPART(year,'2014/10/19') --날짜의 연도를 반환 day month 가능
DATENAME(weekday,'2014/10/19') --요일 반환

DAY('2020/10/19') -- 일 반환
MONTH() --달 반환
YEAR() --연반환

select convert(varchar, getdate(), 101)  -- mm/dd/yyyy --날짜형식 변환 코드 101외에도 여러개있음




--수학함수

ABS()
COS()
PI()


--문자함수
CONCAT('','','',..) --문자열 더하기
LEN()
UPPER()
LOWER()
REVERSE()
FORMAT(GETDATE(),'ss/hh/dd/mm/yyyy') --양식대로 반환 --초 시 일 달 연

--집계함수

AVG()
COUNT()
MAX()
SUM()
MIN()