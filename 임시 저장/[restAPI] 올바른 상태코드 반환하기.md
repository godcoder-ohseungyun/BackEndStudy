# [restAPI] 올바른 상태코드 반환하기(feat. 예외처리)

---

restAPI에서 클라이언트에게 응답을 하면서 명확한 상태코드를 함께 반환하는것은 요청의 결과를 명확하게 표현하고 다양한 의미를 간단한 방법으로 클라이언트에게 알릴수있기때문에 매우 중요하다.



스프링 환경에서 제공하는 `HttpStatus Enum` 을 알아두면 도움이 된다.



[공식문서] https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html



상태코드는 숫자를 직접 입력하게 되면 실수로 오탈자가 생길수있기때문에 `HttpStatus 가 제공하는 상수를 사용`하면 실수를 줄일수있다.





### **ResponseEntity 를 통해 상태 코드 반환**

~~~java
@GetMapping("/status")
@ResponseBody
public ResponseEntity sendViaResponseEntity() {
    return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE); //HttpStatus enum이 제공하는 상수 사용
    
    //return ResponseEntity.status(HttpStatus.OK).header("Custom-Header","foo").body("Custom header set");
}
~~~

> ResponseEntity 에 대한 자세한 설명은 다른 포스트에 정리했다.
>
> 생성자나 빌더를 이용해서 손쉽게 응답 HTTP와 상태코드를 객체로 만들수있다.



### **예외처리를 통해 상태 코드 반환**

보통 상태코드라함은 서버측에서 클라이언트에게 어떤 메시지를 전달하기 위해 사용된다.

서버의 실패에대한 정확한 의미전달을 할때 주로 사용되는데,

즉, 서버에서 예외가 발생했을때 다뤄줘야하는 일이 많다.



스프링에서 예외처리를 하면서 발생한 예외에 맞는 상태코드를 반환해주는것이 중요하다.



`Spring Exeption Handling 탭에서 예외처리 구조나 리졸버들의 동작 목적 제공하는 기능들을 정리했었다.`





-----

---

---

---

---



# 예외처리

---

~~~java
package hello.exception.exhandler;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ErrorResult {
 private String code;
 private String message;
}
~~~

반환에 쓰일 객체



~~~java
@Slf4j
@RestController
public class ApiExceptionV2Controller {
    
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ExceptionHandler(IllegalArgumentException.class)
 public ErrorResult illegalExHandle(IllegalArgumentException e) {
 log.error("[exceptionHandle] ex", e);
 return new ErrorResult("BAD", e.getMessage());
 }
    
 @ExceptionHandler
 public ResponseEntity<ErrorResult> userExHandle(UserException e) {
 log.error("[exceptionHandle] ex", e);
 ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
 return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
 }
    
 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
 @ExceptionHandler
 public ErrorResult exHandle(Exception e) {
 log.error("[exceptionHandle] ex", e);
 return new ErrorResult("EX", "내부 오류");
 }
    
 @GetMapping("/api2/members/{id}")
 public MemberDto getMember(@PathVariable("id") String id) {
 if (id.equals("ex")) {
 throw new RuntimeException("잘못된 사용자");
 }
 if (id.equals("bad")) {
 throw new IllegalArgumentException("잘못된 입력 값");
 }
 if (id.equals("user-ex")) {
 throw new UserException("사용자 오류");
 } return new MemberDto(id, "hello " + id);
 }
    
 @Data
 @AllArgsConstructor
 static class MemberDto {
 private String memberId;
 private String name;
 }
}
~~~

각 예외에 매핑될 예외처리 리졸버들

부모는 자식을 처리 가능

디테일한 예외가 우선순위 high



~~~
@ExceptionHandler({AException.class, BException.class})

~~~

다양한 예외 묶음 처리 가능



@ExceptionHandler 에 예외를 생략할 수 있다. 생략하면 메서드 파라미터의 예외가 지정된다.

하지만 사용하지 않는다. 편의를 위하면 협업에 악영향

모르거나 빠르게 훑거나 하는 상황

무조건 최대한 정보를 표기할것

메모리 누수에 원인이 안된다면





분리작업

~~~
@Slf4j
@RestControllerAdvice
public class ExControllerAdvice { ...}
~~~

모든 restcontroller대상으로 적용된다.



@ControllerAdvice
@ControllerAdvice 는 대상으로 지정한 여러 컨트롤러에 @ExceptionHandler , @InitBinder 기능을
부여해주는 역할을 한다.

지정 없으면 광역적용



