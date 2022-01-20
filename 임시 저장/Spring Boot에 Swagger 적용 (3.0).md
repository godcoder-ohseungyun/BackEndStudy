# Spring Boot에 Swagger 적용 (3.0)

---



Spring Boot에 Swagger 적용을 위해 build.gradle 파일에 spring-fox 3.0을 적용

```
dependencies {
    ...
    implementation group: 'io.springfox', name: 'springfox-boot-starter', version: '3.0.0'
 }
```



SwaggerConfigclass를 생성하고 다음과 같이 정의한다.

```java
@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket swaggerAPI(){
        //Docket : swagger Bean
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true) //기본 응답 메시지 표시 여부
                .select()
                .apis(RequestHandlerSelectors.basePackage("my.swagger.controller")) //swagger 탐색 대상 패키지
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Batch Swagger") 
                .description("batch execute swagger")
                .version("1.0")
                .build();
    }

}
```

> **@EnableWebMvc** : swagger2.0과 달리 swagger 3.0 버전은 이 에너테이션을 추가한다.

 


#### spring boot 재실행 후 localhost:port/swagger-ui/index.html 로 접속

 



 