# MVC Redirect

---

+ 새로고침시 가장 최근 요청을 반복한다.
+ POST를 사용한경우 새로고침시 데이터가 중복등록 된다.
+ Redirect로 설계하여 데이터 중복을 방지하자.

> Redirect에 관한 설명은 HTTP 탭에 있다.

~~~java
/**
 * RedirectAttributes
 */
@PostMapping("/add")
public String addItemV6(@ModelAttributes Item item, RedirectAttributes redirectAttributes) {
 Item savedItem = itemRepository.save(item);
 redirectAttributes.addAttribute("itemId", savedItem.getId()); //
 redirectAttributes.addAttribute("status", true);
 return "redirect:/basic/items/{itemId}";
}
~~~

### RedirectAttributes

실행해보면 다음과 같은 리다이렉트 URL 결과가 나온다.

http://localhost:8080/basic/items/3?status=true

{itemId}는 url인코딩을 통해 바인딩 처리 해주고

status와 같은 나머지는 전부 쿼리 파라미터로 처리해준다. ? status = true



즉, RedirectAttributes 를 사용하면 URL 인코딩도 해주고, pathVarible , 쿼리 파라미터까지 처리해준다.



**뷰 템플릿에서 사용**

~~~html
<div class="container">
 <div class="py-5 text-center">
 <h2>상품 상세</h2>
 </div>
 <!-- 저장완료 문구 출력 -->
 <h2 th:if="${param.status}" th:text="'저장 완료!'"></h2>
~~~



