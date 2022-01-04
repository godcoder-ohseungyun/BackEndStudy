package restTemplateTest.restTemplate.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import restTemplateTest.restTemplate.domain.Person;




@Controller
@Slf4j
public class RestTemplateController {
    RestTemplate restTemplate;

    @Autowired
    private RestTemplateController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @ResponseBody
    @GetMapping("/restTemplateTest")
    public Person restTempateTestMethod(){
        Person person = restTemplate.getForObject("http://localhost:8080/requestSomething",Person.class);

        log.info("localhost:8080/requestSomething로 부터 데이터 받았음");
        return person;
    }

    @ResponseBody
    @GetMapping("/requestSomething")
    public Person  requestHandle(){

        Person resultPerson = new Person("osy",10);

        log.info("Dd");

        return resultPerson;
    }


}
