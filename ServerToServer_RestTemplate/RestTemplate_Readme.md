### RestTemplate  테스트하기



**Reference**

> [내 블로그(동작 원리 정리)](#)
>
> [공식 문서](#https://spring.io/guides/gs/consuming-rest/)



RestTemplate를 사용하기 위해선 스프링 컨테이너에 빈등록을 해야한다.

**RestTemplate 빈등록**

~~~JAVA
@SpringBootApplication
public class RestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}
    
	//메인에 빈 등록
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
~~~



**도메인생성**

Json 데이터 컨버팅에 사용될 도메인 생성

~~~java
//Person Class
package restTemplateTest.restTemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor
public class Person {

    private String name;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

~~~

> Json으로 컨버터가 잘 동작하려면 생성자 + 기본생성자 2개다 있어야한다.
>
> 예제에선 Lombok으로 대체



**컨트롤러**

~~~
[경로].Controller.RestTemplateController
~~~

**서버1역할 **

~~~java
RestTemplate restTemplate;

@Autowired
private RestTemplateController(RestTemplate restTemplate){
    this.restTemplate = restTemplate;
}

@ResponseBody
@GetMapping("/restTemplateTest")
public Person restTempateTestMethod()
{
    //해당 메서드는 get방식으로 요청 후 오브젝트로 반환받음
    //restTemplate 지원 메서드를 이용해서 url endpoint로 요청
    Person person = restTemplate.getForObject("http://localhost:8080/requestSomething",Person.class);

    log.info("localhost:8080/requestSomething로 부터 데이터 받았음");
    return person;
}
~~~

getForObject 메소드를 이용해서 지정 URI로 부터 GET방식으로 요청한 후 응답 데이터를 Object로 반환받는다. 

> restTemplate의 목적은 외부 RESTful서버로부터 데이터를 받아오기 위함이지만 테스트용이기 때문에 내부요청으로 대체했다.

**서버2역할**

~~~JAVA
@ResponseBody //응답 자바 객체나 String을 json으로 알아서 컨버팅함, 뷰리졸버 안부름
//@RequestBody는 요청 http json을 자바 객체로 컨버팅
@GetMapping("/requestSomething")
public Person  requestHandle(){

    Person resultPerson = new Person("osy",10);

    log.info("Dd");

    return resultPerson; //자바 객체 -> json으로 바꿔서 응답해줌
}
~~~

getForObject 로 온 요청을 받아 자바 객체를 생성해 응답한다. 컨버터가 알아서 Json으로 변경해서 HTTP body에 담아 응답해준다.



**결과**

![image-20220104123833174](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20220104123833174.png)

정상적으로 서버2에게 서버1이 데이터를 요청해 받아옴을 확인할수있다.



**예외처리**

서버가 소통하는데 잘못된 데이터가 와서 컨버팅이 불가한경우 서버는 500에러 혹은 400에러를 낸다

이에대한 자세한 조사와 예외처리 지식이 필요하다. [추후 추가]