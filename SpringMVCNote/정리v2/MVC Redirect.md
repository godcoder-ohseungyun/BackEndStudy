# MVC Redirect

---

+ 브라우저는 새로고침시 가장 최근 요청을 반복한다.
+ 때문에 POST를 사용한경우 새로고침시 재 요청되서 데이터가 중복등록 된다.
+ Redirect로 설계하여 데이터 중복을 방지하자.

> Redirect를 사용하면 **설정한 URL로 GET방식으로 요청을 보내게 된다.**

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
//리다이렉트란??
//서버는 클라이언트로부터 요청을 받은 후, 클라이언트에게 "특정 URL을 이동하라고 요청"할 수 있다. 이를 리다이렉트라고 한다.
//ex) 특정 페이지 접근시도 -> 로그인 필요 로그인창으로 자동 리다이렉트 -> 로그인후 접근시도 -> 접근 허용
~~~

> /basic/items/{itemId} 로 GET방식으로 request한다.
>
>  redirect를 사용하지 않으면 /add로 POST 방식으로 한 요청이 최근 요청이 되기 때문에 새로고침시 한번더
>
> POST요청을 하게되어 중복 요청이 된다.
>
> **redirect를 이용하면 최근 요청이 GET**이 되기때문에 새로고침을 해도 **중복 요청이 되더라도 데이터 중복등록이 되지 않는다.**



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



