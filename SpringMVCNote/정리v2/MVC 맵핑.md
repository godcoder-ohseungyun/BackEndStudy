#  MVC 맵핑

---

스프링 컨트롤러 계층은 클라이언트 요청을 처리하는 계층으로, 처리를 위해선 맵핑이 필요하다. 

클라이언트는 보통 HTTP프로토콜을 이용하여 서버에게 요청을 하게 되는데 스프링은 HTTP Method에 따라 맵핑 에너테이션을 제공한다.

>  HTTP 요청 메서드는 GET POST DELETE PATCH 등이 있다.

클라이언트로부터 HTTP요청방식에 따라 컨트롤러에서 맵핑하도록 해보자.



```
/** URL설계는 다음과 같다.
 * 회원 등록 POST: /member
 * 회원 수정 PATCH: /member/{memberId}
 * 회원 삭제 DELETE: /member/{memberId}
 */
```

컨트롤러

~~~JAVA
@Controller
@RequestMapping("/member") //URL 공통부 통합
public class memberController {

    private memberService memberService;

    @Autowired
    public memberController(memberService memberService){
        this.memberService = memberService;
    }

    @PostMapping //POST
    public String joinMember(){
        ...
    }

    @PatchMapping("/{memberId}") //PATCH
    public String updateMember(@PathVariable String memberId){
        ...
    }

    @DeleteMapping("/{memberId}") //DELETE
    public String deleteMember(@PathVariable String memberId){
        ...
    }
~~~



