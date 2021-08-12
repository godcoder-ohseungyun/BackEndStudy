package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;


/*
 * 다형성에 맞게 설계 스프링핵심원리.md
 * Model: Controller 에서 생성된 데이터를 담아서 View 로 전달할 때 사용하는 객체
 */
@Controller //스프링 빈 타겟
@RequestMapping("/basic/items") //요청 맵핑
@RequiredArgsConstructor //final 맴버변수 생성자 자동생성!
public class BasicItemController {

    private final ItemRepository itemRepository; //도메인

    //@RequiredArgsConstructor //final 맴버변수 생성자 자동생성
    //이렇게 생성자가 딱 1개만 있으면 스프링이 해당 생성자에 @Autowired 로 의존관계를 자동 주입해준다.

    /*
     * return thymeleaf templates path
     * 상품 목록 컨트롤러
     * GET 요청 *조회*
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items"; //data 넘겨줄 타겟 동적 html 경로
    }

    /*
     * param {itemId} 경로 변수
     * 상품 상세 컨트롤러
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /*
     * 상품등록 컨트롤러
     * 동일경로 맵핑 get 으로 오는경우 & post 로 오는경우 구분
     * <p>
     * 상품 등록 폼: GET /basic/items/add
     * 상품 등록 처리: POST /basic/items/add
     * 이렇게 하면 하나의 URL 로 등록 폼과, 등록 처리를 깔끔하게 처리할 수 있다.
     */
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //비활성화 @ModelAttribute("item") 기능 확인용
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
        //model.addAttribute("item", item); //@ModelAttribute("item") naming 지정 -> item 이란 이름으로 add Attribute 자동 추가,so 생략 가능
        return "basic/item";
    }

    /*
     * RedirectAttributes:  URL 인코딩도 해주고, path Variable , 쿼리 파라미터까지 처리해준다.
     * SpringPractice1.md: PRG 설계 redirect 쓰는이유 부분 정리에 따라
     * POST 새로고침 문제점 수정
     */
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId()); //id
        redirectAttributes.addAttribute("status", true); //리다이렉트시 status 쿼리 파라미터로 넘겨줌 //저장되었다는 문구등 사용자 친화적 기능에 도움됨
        return "redirect:/basic/items/{itemId}";
    }
    /*
     * -@RequestParam 으로 넘겨준 파라미터 사용
     * -@ModelAttribute 으로 넘겨준 파라미터 객체화 하여 RETURN -> more easy
     * - 맴버변수 name 과 매칭된다.
     * -@ModelAttribute 자체도 생략가능하다. but, 가시성을 위해 생략 x
     */


    /*
     * 상품수정 컨트롤러
     * GET & POST
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    //Model 필요 x
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


    /*
     * 테스트용 데이터 추가
     * PostConstruct : 해당 빈의 의존관계가 모두 주입되고 나면 초기화 용도로 호출된다.
     * 여기서는 간단히 테스트용 테이터를 넣기 위해서 사용했다
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }
}