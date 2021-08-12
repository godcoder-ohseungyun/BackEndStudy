package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass()); //로그 선언
    /**
     * 기본 요청
     * 둘다 허용 /hello-basic, /hello-basic/ 같은 url로 취급
     * HTTP 메서드 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping("/hello-basic") //{"/hello-basic", "/hello-go"} 다중 가능
    //method 속성으로 HTTP 메서드를 지정하지 않으면 HTTP 메서드와 무관하게 호출된다.
    //모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE

    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }
}

/**
 * 매핑 정보(한번 더)
 * @RestController
 *
 * @Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
 *
 * @RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
 * 따라서 실행 결과로 ok 메세지를 받을 수 있다. @ResponseBody 와 관련
 *
 * @RequestMapping("/hello-basic")
 * /hello-basic URL 호출이 오면 이 메서드가 실행되도록 매핑한다.
 * 대부분의 속성을 배열[] 로 제공하므로 다중 설정이 가능하다. {"/hello-basic", "/hello-go"}
 */