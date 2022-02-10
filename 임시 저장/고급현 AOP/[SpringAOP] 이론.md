# Spring AOP

---



### AOP의 탄생

애플리케이션 로직은 크게 `핵심 기능`과 `부가 기능`으로 나눌 수 있다.

> 로그 작성, 예외처리 로직 등



부가 기능은 이름 그대로 핵심 기능을 보조하기 위해 존재한다.

> 클래스가 100개면 100개 모두에 동일한 코드를 추가해야 한다.



부가 기능을 별도의 유틸리티 클래스로 만든다고 해도, 해당 유틸리티 클래스를 호출하는 코드가 결국
필요하다.

> 템플릿 메서드 클래스,전략 클래스 등으로 분리해도 결국 이 클래스를 호출하는 코드 자체는 필요하다.



그리고 부가 기능이 구조적으로 단순 호출이 아니라 try~catch~finally 같은 구조가필요하다면 더욱 복잡해진다.



**부가 기능 적용 문제를 정리하면 다음과 같다.**

> 부가 기능을 적용할 때 아주 많은 반복이 필요하다.
>
> 부가 기능이 여러 곳에 퍼져서 중복 코드를 만들어낸다.
>
> 부가 기능을 변경할 때 중복 때문에 많은 수정이 필요하다.
>
> 부가 기능의 적용 대상을 변경할 때 많은 수정이 필요하다



### 누군가는 이러한 부가 기능 도입의 문제점들을 해결하기 위해 오랜기간 고민해왔다



그 결과 부가 기능을 **핵심 기능에서 분리**하고 **한 곳에서 관리**하도록 했다



그리고 해당 `부가 기능을 어디에 적용할지 선택하는 기능도 만들었다.` 이렇게 부가 기능과 부가 기능을 어디에 적용할지 선택하는 기능을 합해서 하나의 모듈로 만들었는데 이것이 바로 **애스펙트(aspect)**이다. 

> 이전에 알아본 `@Aspect` 가 바로 그것이다. 



**그리고 스프링이 제공하는 어드바이저도 어드바이스(부가 기능)과 포인트컷(적용 대상)을 가지고 있어서 개념상 하나의 애스펙트이다.**



## AOP!

이런 **애스펙트(관점)를 사용한 프로그래밍 방식**을 관점 지향 프로그래밍 **AOP(Aspect-Oriented  Programming)**이라 한다.

> **애스펙트는** 우리말로 해석하면 관점이라는 뜻인데, 이름 그대로 **애플리케이션을 바라보는 관점을 하나하나의 기능에서 횡단 관심사(cross-cutting concerns) 관점으로 달리 보는 것**이다.
>
> 
>
> ![image-20220209125006947](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20220209125006947.png)



참고로 AOP는 OOP를 대체하기 위한 것이 아니라 **횡단 관심사**를 깔끔하게 처리하기 어려운 OOP의 부족한 부분을 보조하는 목적으로 개발되었다.

> 횡단 관심사를 **한곳에서 관리**하고 **필요한 부분에 적용** 할 수 있다.
>
> ![image-20220209125109303](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20220209125109303.png)





## AspectJ 프레임워크?

AOP를 대표적으로 구현한 프레임워크로 **AspectJ 프레임워크(https://www.eclipse.org/aspectj/)**가 있다. 

> 스프링 AOP는  AspectJ의 문법을 차용하고, AspectJ가 제공하는 기능의 일부만 제공한다.





AspectJ 프레임워크는 스스로를 다음과 같이 설명한다.

> 자바 프로그래밍 언어에 대한 완벽한 관점 지향 확장
>
> 횡단 관심사의 깔끔한 모듈화
>
> 오류 검사 및 처리
>
> 동기화
>
> 성능 최적화(캐싱)
>
> 모니터링 및 로깅





### **AOP를 적용하는 3가지  방식(시점)**

> 부가기능을 핵심 기능에 적용하는 방식 3가지 



**1. 컴파일 시점**

> 컴파일 시점에 실제로 부가기능 코드를 실제 코드에 붙여버린다.
>
> 따로 관리되고있지만 컴파일때 합쳐지면서 부가기능이 추가되는 구조
>
> **특별한 컴파일러가 필요하는 등 여러가지로 복잡하다**



**2. 클래스 로딩 시점**

> .class 파일을 JVM 내부의 클래스 로더에 보관할때 중간에서 .class 파일을 조작해 부가기능을 추가해서 JVM에 올릴 수 있다. 
> 
>**클래스 로더 조작기를 지정해야 하는데, 이 부분이 번거롭고 운영하기 어렵다.**



**3. 런타임 시점(프록시) - 스프링 AOP**

> **런타임 시점이란?** 
>
> > 컴파일도 다 끝나고, 클래스 로더에 클래스도 다 올라가서 이미 자바가 실행되고 난 다음을 말한다. 
>
> 
>
> **스프링과 같은 컨테이너의 도움을 받고 프록시와 DI, 빈 포스트 프로세서 같은 개념들을 총 동원해야 한다**
>
> **그러나 특별한 컴파일러나, 자바를 실행할 때 복잡한 옵션과 클래스 로더 조작기를 설정하지 않아도 된다.** 
>
> 
>
> **스프링만 있으면 얼마든지 AOP를 적용할 수있다!**
>
> ![image-20220208152240825](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20220208152240825.png)
>
> **부가 기능이 정의된 프록시를 동적으로 생성하고 빈 후처리기를 통해 프록시 객체를 빈으로 바꿔 등록함으로써 구현된다.**
>
> 
>
> ### 스프링은 이 방법을 이용해서 AOP를 구현한다.
>
> > 이제껏 정리했던 방식





### 원래 AOP는 지금까지 학습한 메서드 실행 위치 뿐만 아니라 다음과 같은 다양한 위치에 적용할 수 있다.

> **스프링 AOP는** 런타임 시점에 프록시를 사용했기 때문에 메서드실행 시점에만 적용 할 수 있는 것이다.
>
> 컴파일 시점이나 클래스 로딩 시점을 이용하면 생성자,필드값 등 다양한 위치에 부가기능(Advice)을 적용할수있다.



### 그럼  기능에 제한이 있는 스프링 AOP를 사용할게 아니라 어디든 적용가능한 AspectJ 프레임웤을 사용해서 AOP를 구현하면 안될까?

> AspectJ를 사용하려면 공부할 내용도 많고, 자바 관련 설정(특별한 컴파일러, AspectJ 전용 문법, 자바 실행 옵션)도 복잡하다. 
>
> 반면에 스프링 AOP는 프록시를 사용하기 때문에 메서드 실행시점에만 적용가능하지만 별도의 추가 자바 설정 없이 스프링만 있으면 편리하게 AOP 를 사용할 수 있다. 
>
> **실무에서는 스프링이 제공하는 AOP 기능만 사용해도 대부문의 문제를 해결할 수 있다.** 
>
> **또한, 스프링 AOP는 편의 기능을 제공하기 때문에 @Aspect를 이용해 Advisor만 정의해주면 쉽게 AOP를 적용할 수 있다.**



### AOP라는건 스프링만의 고유한 개념이 아니다.

> 스프링에서는 AspectJ가 제공하는 애노테이션이나 관련 인터페이스만 사용하는 것이고, 실제 AspectJ가 제공하는 컴파일, 로드타임 위버 등을 사용하는 것은 아니다. 
>
> **스프링은 지금까지 우리가 학습한 것 처럼 프록시 방식의 AOP만을 사용한다.**



## AOP 용어 정리

> 스프링 AOP를 알아보기 전에 AOP에서 쓰이는 용어들의 개념을 정리해야한다.
>
> **조인 포인트(Join point)**
>
> > 어드바이스가 적용될 수 있는 위치
> >
> > 조인 포인트는 추상적인 개념이다. **AOP를 적용할 수 있는 모든 지점**이라 생각하면 된다.
> >
> > **스프링 AOP는 프록시 방식을 사용하므로 조인 포인트는 항상 메소드 실행 지점으로 제한된다**
>
> 
>
> **포인트컷(Pointcut)**
>
> > 조인 포인트 중에서 어드바이스가 적용될 위치를 선별하는 기능
> >
> > 주로 **AspectJ 표현식**을 사용해서 지정
> >
> > 프록시를 사용하는 **스프링 AOP는 메서드 실행 지점만 포인트컷으로 선별 가능**
>
> 
>
> **타켓(Target)**
>
> > **어드바이스를 받는 객체**, 포인트컷으로 결정
>
> 
>
> **어드바이스(Advice)**
>
> > 적용할 **부가 기능**
>
> 
>
> **애스펙트(Aspect)**
>
> > **어드바이스 + 포인트컷을 모듈화 한 것**
> >
> > @Aspect 를 생각하면 됨
> >
> > 여러 어드바이스와 포인트 컷이 함께 존재
>
> 
>
> **어드바이저(Advisor)**
>
> > 하나의 어드바이스와 하나의 포인트 컷으로 구성
> >
> > **스프링 AOP에서만 사용되는 특별한 용어**
>
> 
>
> **위빙(Weaving)**
>
> > 포인트컷으로 결정한 타켓의 **조인 포인트에 어드바이스를 적용하는 행위**를 의미
>
> 
>
> **AOP 프록시**
>
> > **AOP 기능을 구현하기 위해 만든 프록시 객체**, 스프링에서 AOP 프록시는 JDK 동적 프록시 또는
> > CGLIB 프록시이다.
> >
> > 이는 프록시 팩토리로 보다 쉽게 만들수있다.





## AOP 구현

스프링은 `@Aspect`를 이용해서 쉽게 AOP를 적용 할 수 있다.

> 스프링 AOP를 구현하는 일반적인 방법은  @Aspect 를 사용하는 방법이다.



### **기본버전**

~~~JAVA
@Slf4j
@Component //Aspect를 스프링 빈에 등록해야한다.
@Aspect //@Aspect는 이 객체가 애스펙트라는 표식
public class AspectV1 {
    
     @Around("execution(* hello.aop.order..*(..))")
     public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
         log.info("[log] {}", joinPoint.getSignature());
         
         return joinPoint.proceed(); //자바 리플랙션 기술
 	}
    
}
~~~

> @Around 애노테이션의 값인 execution(* hello.aop.order..*(..)) 는 **포인트컷**이 된다.
>
> @Around 애노테이션의 메서드인 doLog 는 **어드바이스( Advice )**가 된다.
>
> execution(* hello.aop.order..*(..)) 는  **AspectJ 포인트컷 표현식**이다.



**스프링은 프록시 방식의 AOP를 사용하므로 프록시를 통하는 메서드만 적용 대상이 된다.**



### **포인트컷 분리 버전**

~~~JAVA
@Slf4j
@Aspect
public class AspectV2 {
    
     //hello.aop.order 패키지와 하위 패키지  
     @Pointcut("execution(* hello.aop.order..*(..))") //pointcut expression
     private void allOrder(){} //void 빈 메서드로 정의해야한다. //메서드 이름을 통해 포인트컷 유추가 가능하도록 설계한다.
    
     @Around("allOrder()") 
     public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
         log.info("[log] {}", joinPoint.getSignature());
         return joinPoint.proceed();
 	}
    
}
~~~

> @Around 에 포인트컷 표현식을 직접 넣을 수 도 있지만, @Pointcut 애노테이션을 사용해서 별도로 **분리**할 수 도 있다.



~~~java
@Pointcut("execution(* hello.aop.order..*(..))") //pointcut expression
public void allOrder(){}
~~~

> 포인트컷 메서드를 **public**으로 설정하면 **다른 애스펙트에서도 사용이 가능**하다.





### **여러개의 어드바이스 관리 버전**

~~~java
@Slf4j
@Aspect
public class AspectV3 {
    
    //hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder() {
    }

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {
    }

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
~~~

> doLog 와 doTransaction 두개의 Advice를 관리하고있다.
>
> 
>
> **참고로,**  **보통** allOrder와 같은 **포인트컷 분리 코드들은 별도의 객체로 구분**하고 **@Aspect에서는 어드바이스만 관리하도록 설계**한다.
>
> ~~~java
> public class Pointcuts {
>      //hello.springaop.app 패키지와 하위 패키지
>      @Pointcut("execution(* hello.aop.order..*(..))")
>      public void allOrder(){}
>     
>      //타입 패턴이 *Service
>      @Pointcut("execution(* *..*Service.*(..))")
>      public void allService(){}
>     
>      //allOrder && allService
>      @Pointcut("allOrder() && allService()")
>      public void orderAndService(){}
> }
> ~~~
>
> **사용하는 방법은** 아래와 같이은 패키지명을 포함한 클래스 이름과 포인트컷 시그니처를 모두 지정하면 된다.
>
> ~~~java
> ....
> 
> @Around("hello.aop.order.aop.Pointcuts.allOrder()") //패키지명을 포함한 클래스 이름과 포인트컷 시그니처 모두 지정
> public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
>      log.info("[log] {}", joinPoint.getSignature());
>      return joinPoint.proceed();
> }
> 
> ....
> ~~~





**여러개의 어드바이스 관리 버전을 보면 doLog -> doTransaction 순으로 어드바이스가 적용될텐데, 이 순서를 바꿀수도있다.**



### **어드바이스 순서 바꾸기 버전**

~~~java
@Slf4j
public class AspectV5Order {
    @Aspect
    @Order(2) //순서지정
    public static class LogAspect {  //별도의 @Aspect 클래스로 만듬
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1) //순서 지정
    public static class TxAspect { //별도의 @Aspect 클래스로 만듬
        @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws
                Throwable {
            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }
        }
    }
}
~~~

> `@Order` 애노테이션을 적용하여 순서를 지정 할 수 있다.
>
> **단,** **`@Order` 애노테이션은 어드바이스 단위가 아니라 클래스 단위로 적용할 수 있다**
>
>  따라서 애스펙트를 별도의 클래스로 분리해야 한다.
>
> **위처럼 분리하면 `@Order` 애노테이션을 적용하여 순서를 지정 할 수 있다.**



**이제 지정한 것 처럼 doTransaction -> doLog  순으로 어드바이스가 적용된다.**



### 어드바이스 종류

~~~
@Around : 메서드 호출 전후에 수행, 가장 강력한 어드바이스, 조인 포인트 실행 여부 선택, 반환 값 변환, 예외 변환 등이 가능

@Before : 조인 포인트 실행 이전에 실행

@AfterReturning : 조인 포인트가 정상 완료후 실행

@AfterThrowing : 메서드가 예외를 던지는 경우 실행

@After : 조인 포인트가 정상 또는 예외에 관계없이 실행(finally)
~~~

> `@Around` 가 붙은 메서드가 어드바이스라고 했는데, 다양한 에너테이션으로 `@Around`를 쪼개서 어드바이스를 적용할수있다.
>
> 
>
> **why?**
>
> @Around 가 가장 넓은 기능을 제공하는 것은 맞지만, 전체 어드바이스 동작을 한번에 정의해 두기 때문에 실수할 가능성이 있다. 
>
> 나머지 에터네이션들을 사용하면 역할을 소분해서 정의하기때문에 실수를 줄이고 가독성 또한 올릴 수 있다.



**@Around 어드바이스만 사용해도 필요한 기능을 모두 수행할 수 있다. 알아두고 필요한 시점이 오면 공부하자**





## 포인트컷 표현식(AspectJ 표현식)



 ### AspectJ 표현식

프로젝트 규모가 커지고 AOP를 적용하다보면 원치 않는 곳까지 매칭되어 AOP가 동작하는 경우가 발생한다. 

따라서 포인트컷을 디테일하고 편리하게 정의해서 지정해줄 필요가있다.



AspectJ는 포인트컷을 ''디테일''하고 ''편리''하게 표현하기 위한 특별한 표현식을 제공한다. 

~~~
ex)  @Pointcut("execution(* hello.aop.order..*(..))")
~~~



### **포인트컷 지시자?**

포인트컷 표현식은 `execution` 같은 포인트컷 지시자(Pointcut Designator)로 시작한다. 

> 줄여서 PCD라 한다.



**`execution` : 메소드 실행 조인 포인트를 매칭한다.** 



사실, within , args , this , target .. 등등 다양한 포인트컷 지시자가 존재한다.

~~~
within : 특정 타입 내의 조인 포인트를 매칭한다.

args : 인자가 주어진 타입의 인스턴스인 조인 포인트

this : 스프링 빈 객체(스프링 AOP 프록시)를 대상으로 하는 조인 포인트

target : Target 객체(스프링 AOP 프록시가 가르키는 실제 대상)를 대상으로 하는 조인 포인트

@target : 실행 객체의 클래스에 주어진 타입의 애노테이션이 있는 조인 포인트

@within : 주어진 애노테이션이 있는 타입 내 조인 포인트

@annotation : 메서드가 주어진 애노테이션을 가지고 있는 조인 포인트를 매칭

@args : 전달된 실제 인수의 런타임 타입이 주어진 타입의 애노테이션을 갖는 조인 포인트

bean : 스프링 전용 포인트컷 지시자, 빈의 이름으로 포인트컷을 지정한다.
~~~





### execution 을 가장 많이 사용하고, 나머지는 자주 사용하지 않는다. 필요할때 공부해서 사용하자.

> 스프링 AOP는 프록시를 사용하기 때문에 대부분 메서드 실행 시점에만 조인포인트를 걸어 해결 할 수 있기때문 



### execution 문법

포인트컷을 지정할때 사용되는 AspectJ 표현식 문법을 알아보자.

 

~~~java
execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? namepattern(param-pattern) throws-pattern?)
    
//번역
execution(접근제어자? 반환타입 선언타입? 메서드이름(파라미터) 예외?)
~~~

> `?` 가 붙은 부분을 생략이 가능하다.
>
> `*` 같은 표현을 사용 할 수 있다. `*` 은 모든 값을 허용한다는 뜻이다.



**예시**

+ 정확한 매칭

~~~java
pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
~~~

> 예외는 생략 되었다.
>
> 메서드 이름엔 메서드가 존재하는 경로를 작성해 정확히 저 메서드에만 매칭한다.



+ 최대한 생략한 버젼

~~~java
pointcut.setExpression("execution(* *(..))");
~~~

> 최대한 생략한 버전
>
> 접근제어자 생략 , 반환타입 ALL , 선언타입 생략 , 메서드 ALL , 파리미터 ALL , 예외 생략
>
> 파리미터 (..) 은 파라미터 개수와 타입에 상관없이 허용한다는 뜻이다.



+ 메서드 이름으로 매칭

~~~java
pointcut.setExpression("execution(* hello(..))");

pointcut.setExpression("execution(* *el*(..))"); //!
~~~

> 메서드 이름 앞뒤에 `*` 을 붙일 수 있다.
>
> 메서드 이름 중간에 el이 들어가면 매칭된다.
>
> 앞뒤로 어떤 이름이 와도 상관없다는 뜻



+ 패키지 매칭

~~~java
pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");

pointcut.setExpression("execution(* hello.aop.member.*.*(..))");

pointcut.setExpression("execution(* hello.aop.*.*(..))"); // . 은 정확히 그 패키지만 포함

pointcut.setExpression("execution(* hello.aop.member..*.*(..))"); // .. 은 하위 패키지도 포함한다는 뜻
~~~



+ 파라미터 매칭

~~~java
pointcut.setExpression("execution(* *(String))"); // 정확히 하나의 String 파라미터만 허용

pointcut.setExpression("execution(* *(*))"); //정확히 하나의 파라미터 허용, 모든 타입 허용

pointcut.setExpression("execution(* *(..))"); //숫자와 무관하게 모든 파라미터, 모든 타입 허용

pointcut.setExpression("execution(* *(String, ..))"); //String 타입으로 시작, 숫자와 무관하게 모든 파라미터, 모든 타입 허용
~~~





## 정리

요약하자면  로그 추적기 같은 **공통 관심사를 중복코딩 없이 한곳에 모아서 관리하고 원하는 곳에 적용하는것을 AOP**라고 할수있겠는데,

**AspectJ 프레임워크**가 이 AOP를 구현한 대표 프레임워크이다.



이 **AOP를 적용하는 방식**은 컴파일시점,클래스 로딩 시점 , 런타임 시점 3가지가 있다.

**스프링 AOP는** AspectJ 프레임워크를 차용해서 만들어졌는데 이중에서 **프록시를 활용한 런타임 시점 적용법**을 사용한다.

프록시 객체에 공통 관심사 부가기능을 작성하고 대상 객체 메서드를 호출해 실행한다. 이 방법을 통해 **핵심 로직과 부가기능 로직의 완벽한 분리**가 가능하다.

이를 위해 스프링은 **프록시 팩토리**를 사용해서 "동적 프록시 기술"을 개발자가 편리하게 사용하도록 하며, 이때 동적 프록시는 **자바 리플랙션**이라는 기술을 사용해서 메타 데이터를 가지고 동적으로 대상 객체를 호출한다.

이때, **어드바이저**를 이용해 어떤 타겟 객체에 프록시를 동적으로 생성하고 부가기능을 적용할지 정할수있다.



이전에, 프록시를 사용하려면 실제 객체 대신 프록시 객체를 스프링 빈에 저장해 사용해야하는데, 컴포넌트 스캔으로 빈이 자동등록 된 경우
실제 객체가 등록되기 때문에 **빈 후처리기**를 통해 프록시 객체로 바꿔치기 해야한다.

스프링은 이 빈 후처리기 또한 이미 구현해서 지원한다.



프록시 팩토리와 스프링이 제공하는 빈 후처리기가 이미 존재하기 때문에 프록시나 빈 후처리기를 직접 생성할 필요가 없다.



개발자는 @Aspect 를 사용해서 어드바이저만 생성해주면 해당 어드바이스를 가지고 프록시 팩토리가 프록시를 동적으로 생성하고
포인트컷을 조회해 대상 타겟 메서드에 해당 어드바이스를 적용한다. 이때 컴포넌트 스캔이 사용 되었다면 빈 후처리기가 알아서
프록시객체로 빈에 바꿔서 등록해준다.

**결론은 개발자는 @Aspect 클래스만 정의하면  된다.**

이때 **AspectJ 표현식**을 사용해서 포인트컷을 정밀하게 제어한다.
포인트컷을 설정하는 다른 방법들도 있지만 위 방법이 가장 쉽고 정밀하다.

