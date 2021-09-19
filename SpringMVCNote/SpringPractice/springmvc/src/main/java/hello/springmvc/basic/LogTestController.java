package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Slf4j 에노테이션을 추가하면  private final Logger log = LoggerFactory.getLogger(getClass()); 선언 생략 가능
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = "+name);
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        //레벨 조절상 로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 로그 출력은 안하는데 메모리는 잡아먹음 이런 방식으로 사용하면 X
        //log.debug("String concat log=" + name); x

        return "ok";
    }
}