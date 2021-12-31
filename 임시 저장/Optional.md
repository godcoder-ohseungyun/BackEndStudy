# Optional

---

프로젝트를 진행하다가 보면 많은 NPE를 만나게 된다.

> NullPointException

자바 개발자들이 가장 골치아프게 겪는 예외는  null 참조로 인한 널 포인터 예외(NPE)라고 한다. 

자바 초보이든 고수이든 객체를 사용하여 모든 것을 표현하는 자바 개발자에게 NPE는 코드 베이스 곳곳에 깔려있는 지뢰같은 녀석이다.

~~~markdown
**이모저모**
NULL의 탄생

null 참조는 1965년에 Tony Hoare라는 영국의 컴퓨터 과학자에 의해서 처음으로 고안되었습니다. 당시 그는 “존재하지 않는 값”을 표현할 수 있는 가장 편리한 방법이 null 참조라고 생각했다고 합니다. 하지만 나중에 그는 그 당시 자신의 생각이 “10억불 짜리 큰 실수”였고, null 참조를 만든 것을 후회한다고 토로하였습니다.
~~~



기존에 자바에서는 어떻게 NPE를 방지했고 새로운 자바에서는 어떻게 NPE를 다루는지 알아보자



**[바로가기]**

> [Optional의 등장](#Optional의 등장)
>
> [Optional을 올바르게 사용하기](#Optional을 Optional답게)
>
> [정리](#정리)



### null 처리가 취약한 코드

주문을 한 회원이 살고 있는 도시를 반환하는 코드가 있다고 가정해보자.

~~~java
public String getCityOfMemberFromOrder(Order order) {
	return order.getMember().getAddress().getCity();
}
~~~

> `Order` 클래스는 `Member` 타입의 `member` 필드를 가지며, `Member` 클래스는 다시 `Address` 타입의 `address` 필드를 가진다.



위 코드를 보면 알수있듯이 많은 NPE 위험에 노출되어있다.

1. `order` 파라미터에 null 값이 넘어옴
2. `order.getMember()`의 결과가 null 임
3. `order.getMember().getAddress()`의 결과가 null 임
4. `order.getMember().getAddress().getCity()`의 결과가 null 임

적절히 null 처리를 해주지 않으면, 다음 코드와 같이 호출부에서 NPE를 계속 발생시킬 수 있다.



### 전통적인 NPE 방어 패턴

위 코드를 IF문을 이용해서 null 체크를 하도록 코딩하여 NPE를 회피한다.

~~~JAVA
if (order != null) {
		Member member = order.getMember();
		if (member != null) {
			Address address = member.getAddress();
			if (address != null) {
				String city = address.getCity();
				if (city != null) {
					return city;
				}
			}
		}
	}
~~~

객체의 필드나 메소드에 접근하기 전에 **IF문**을 이용해서 null 체크를 함으로써 NPE를 방지하고 있다. 

하지만 안타깝게도 이로 인해 초기 버전의 메소드보다 코드가 상당히 길어지고 **지저분**해졌다.



**전통적인 NPE 방어 패턴의 단점**

예외 처리 코드와 핵심 비즈니스 코드가 섞이게 된다.

어떤게 핵심 비즈니스 로직인지 파악하기 힘들다.

코드 리뷰가 힘들어지고 유지보수가 어렵다.

런타임에 NPE(NullPointerException)라는 예외를 발생시킬 수 있다.



자바 언어는 (대부분의 다른 언어들처럼) “값의 부재”를 나타내기 위해 null을 사용하도록 설계되었지만 null은 자바 개발자들에게 NPE 방어라는 끝나지 않는 숙제를 남겼다.





## Optional의 등장 

---

**JAVA!  함수형 언어에서 영감을 받다.**

> 전통적인 NPE 방어 패턴에 단점은 분명했다. 자바는 이를 획기적으로 해결하기위해 Optional을 만들었다.

~~~markdown
**이모저모**
스칼라나 하스켈과 같은 소위 함수형 언어들은 전혀 다른 방법으로 이 문제을 해결합니다. 

자바가 “존재하지 않는 값”을 표현하기 위해서 null을 사용했다면, 
이 함수형 언어들은 “존재할지 안 할지 모르는 값”을 표현할 수 있는 별개의 타입을 가지고 있습니다. 

그리고 이 타입은 이 존재할지 안 할지도 모르는 값을 제어할 수 있는 여러가지 API를 제공하기 때문에 개발자들 해당 API를 통해서 간접적으로 그 값에 접근하게 됩니다. 

Java8은 이러한 함수형 언어의 접근 방식에서 영감을 받아 java.util.Optional<T>라는 새로운 클래스를 도입하였습니다!
~~~



**Optional이란?**

`Optional`는 “존재할 수도 있지만 안 할 수도 있는 객체”, 즉, “null이 될 수도 있는 객체”을 감싸고 있는 일종의 래퍼 클래스

> 원소가 null이거나 최대 한개만 존재하는 `Collection`이나 `Stream` 은 `Optional` 타입으로 받을수있다.
>
> null이 될 수도 있는 객체를 담는 특수한 그릇



**Optional의 효과**

- NPE를 유발할 수 있는 null을 직접 다루지 않아도 된다.
- 수고롭게 null 체크를 직접 하지 않아도 된다.
- 명시적으로 해당 변수가 null일 수도 있다는 가능성을 표현할 수 있습니다. (따라서 불필요한 null 채크 로직을 줄일 수 있다.



**Optional 기본 사용법**

> `java.util.Optional<T>` 클래스를 어떻게 사용하는지 알아보자
>
> 
>
> **Optional 변수 선언하기**
>
> 제네릭을 제공하기 때문에, 변수를 선언할 때 명기한 타입 파라미터에 따라서 감쌀 수 있는 객체의 타입이 결정
>
> ```java
> Optional<Order> maybeOrder; // Order 타입의 객체를 감쌀 수 있는 Optional 타입의 변수
> Optional<Member> optMember; // Member 타입의 객체를 감쌀 수 있는 Optional 타입의 변수
> Optional<Address> address; // Address 타입의 객체를 감쌀 수 있는 Optional 타입의 변수
> ```
>
> >  “maybe”나 “opt”와 같은 접두어를 붙여서 Optional 타입의 변수라는 것을 좀 더 명확히 나타내기도 한다.
>
> 
>
> **Optional 객체 생성하기**
>
> Optional 클래스는 간편하게 객체 생성을 할 수 있도록 3가지 정적 팩토리 메소드를 제공
>
> 
>
> - **`Optional.empty()`**
>
> null을 담고 있는, 한 마디로 비어있는 Optional 객체를 얻어온다. 이 비어있는 객체는 Optional 내부적으로 미리 생성해놓은 싱글턴 인스턴스
>
> ```java
> Optional<Member> maybeMember = Optional.empty();
> ```
>
> 
>
> - **`Optional.of(value)`**
>
> null이 아닌 객체를 담고 있는 Optional 객체를 생성,  null이 넘어올 경우, NPE를 던지기 때문에 **주의해서 사용해야 한다**.
>
> ```java
> Optional<Member> maybeMember = Optional.of(aMember);
> ```
>
> 
>
> - **`Optional.ofNullable(value)`**
>
> null인지 아닌지 확신할 수 없는 객체를 담고 있는 Optional 객체를 생성, `Optional.empty()`와 `Optional.ofNullable(value)`를 합쳐놓은 메소드라고 생각하면 된다. 
>
> null이 넘어올 경우, NPE를 던지지 않고 `Optional.empty()`와 동일하게 비어 있는 Optional 객체를 얻어온다. 
>
> > 해당 객체가 null인지 아닌지 자신이 없는 상황에서는 이 메소드를 사용
>
> ```java
> Optional<Member> maybeMember = Optional.ofNullable(aMember);
> Optional<Member> maybeNotMember = Optional.ofNullable(null);
> ```
>
> 
>
> **Optional이 담고 있는 객체 접근하기**
>
> Optional 클래스는 담고 있는 객체를 꺼내오기 위해서 다양한 인스턴스 메소드를 제공한다. 
>
> 아래 메소드들은 모두 Optional이 담고 있는 객체가 존재할 경우 동일하게 해당 값을 반환 하지만 Optional이 비어있는 경우(즉, null을 담고 있는 경우), 다르게 작동한다.
>
> 다르게 작동하는 부분만 설명하겠다.
>
> - **`get()`**
>
> 비어있는 Optional 객체에 대해서, `NoSuchElementException`을 던짐.
>
> 
>
> - **`orElse(T other)`**
>
> 비어있는 Optional 객체에 대해서, 넘어온 인자를 반환.
>
> 
>
> - **`orElseGet(Supplier<? extends T> other)`**
>
> 비어있는 Optional 객체에 대해서, 넘어온 함수형 인자를 통해 생성된 객체를 반환
>
> 
>
> - `orElseThrow(Supplier<? extends X> exceptionSupplier)`
>
> 비어있는 Optional 객체에 대해서, 넘어온 함수형 인자를 통해 생성된 예외를 던짐



#### **Optional의 잘못된 사용**

 `get()` 메소드는 비어있는 Optional 객체를 대상으로 호출할 경우, 예외를 발생시키므로 다음과 같이 객재 존재 여부를 bool 타입으로 반환하는 `isPresent()`라는 메소드를 통해 null 체크가 필요하다.

```java
String text = getText();
Optional<String> maybeText = Optional.ofNullable(text);
int length;
if (maybeText.isPresent()) { //Optional isPresent를 이용한 null check
	length = maybeText.get().length();
} else {
	length = 0;
}
```



그런데 이렇게 사용하면 그냥 if문을 사용하는 코드와 다를게 없다.

~~~java
String text = getText();
int length;
if (text != null) { //전통적인 null check
	length = maybeText.get().length();
} else {
	length = 0;
}
~~~



#### 이럴꺼면 뭐하러 Optional을 사용하는 걸까? Optional을 사용하는 이점이 전혀 보이지 않는다.

> Optional을 사용하는 이유는 null 처리를 직접하지 않고 Optional에게 위임하기 위함이다.
>
> 즉, 위 코드처럼 Optional을 쓸땐 null check를 해선 안된다.



어떻게 Optional을 사용해야 Optional의 이점을 살릴수있을까?



## Optional을 Optional답게

---

**1. Stream반환값을 Optional로 받아 사용하기**

+ Optional은 **최대 1개의 원소를 가지고 있는** 특별한 Stream이다.

> Stream의 결과가 최대 1개 or null일때 Optional 클래스 타입으로 반환값을 받을수있다.
>
> 최대 1개의 원소를 가진 Collection도 Optional로 받을수있다.
>
> ~~~java
> @Override
> public List<Member> findAll() {
>     return new ArrayList<>(store.values()); //values returns map so, convert to ArrayList
> }
> 
> @Override
> public Optional<Member> findByLoginId(String loginId) {
>     return findAll().stream()
>         .filter(m -> m.getLoginId().equals(loginId)) //filter는 리턴이 없는경우 Optinal을 비워버린다.
>         .findFirst();
> }
> ~~~
>
> 위 코드는 id를 저장한 Collection을 Stream을 이용해 다루어 loginId를 가진 1개의 원소를 찾아 반환한다. 
>
> 최대 한개 혹은 null을 가질수있는 Stream 반환값임으로 Optional을 통해 받을수있다.
>
> 따라서 Return 값은 Optional이 된다.
>
> ~~~java
> Optional<Member> find = findByLoginId("effsp0112");
> log.info(find.get().getName()); 
> ~~~
>
> `java.util.Optional<T>`가 제공하는 get()메서드를 통해 Optional안에 있는 객체를 꺼내고 사용할수있다.
>
> > 위에서 get()은 null일때 NPE를 발생시킨다고 주의하라고 했었다.



**2. Optional을 Stream처럼 사용하기**

+ 위에서 Stream의 반환값을 Optional로 받아 Optional 클래스가 제공하는 메서드를 이용해 객체를 사용했다.
+ 하지만 꼭 Stream을 써서 Optional로 반환할 필요가 없다.
+ **`java.util.Optional<T>`도 Stream이 제공하는 메서드들을 제공한다.**

> filter(),map() 등등

~~~java
public String getCityOfMemberFromOrder(Order order) {
	return Optional.ofNullable(order) //order 객체를 감싸는 Optional 객체 생성
			.map(Order::getMember) //주문 회원 정보 mappin
			.map(Member::getAddress) //주문 회원들의 주소 mappin
			.map(Address::getCity) //주문 회원 주소들의 도시 mappin
			.orElse("비어있는 Optional 객체"); // 비어있는 Optional 객체에 대해서 orElse 로부터 넘어온 인자를 반환한다.
}
~~~

**전통적인 NPE 방어 패턴에 비해 훨씬 간결하고 명확해진 코드를 볼 수 있다.** 

> 기존에 존재하던 if문등 예외 처리 코드가 다 사라지고 체이닝으로 직관적으로 핵심로직을 파악할수있다.



**filter를 사용하여 조건문 없애기**

~~~java
public Member getMemberIfOrderWithin(Order order, int min) {
	if (order != null && order.getDate().getTime() > System.currentTimeMillis() - min * 1000) {
		return order.getMember();
	}
}
~~~

위의 if문은 null 채크등 예외 처리 코드이다. 핵심로직과 섞여있어 가독성에 방해가 된다.

~~~java
public Optional<Member> getMemberIfOrderWithin(Order order, int min) {
	return Optional.ofNullable(order)
			.filter(o -> o.getDate().getTime() > System.currentTimeMillis() - min * 1000)
			.map(Order::getMember);
}
~~~

`filter()` 메소드를 사용하면 if 조건문 없이 메소드 연쇄 호출만으로도 좀 더 읽기 편한 코드를 작성할 수 있다

`filter()` 메소드는 넘어온 함수형 인자의 리턴 값이 `false`인 경우, Optional을 비워버리므로 그 이후 메소드 호출은 의미가 없어지게 된다.



## 정리

---

자바8 이전에는 if문을 이용해 null check를 수행했다. 

> 핵심 로직과 null check로직이 섞여 가독성에 방해된다.
>
> 직접 null 값을 다루는 것임으로 NPE 위험에 노출되어있다.

자바8 이후에는 Optional을 이용해 null을 직접 다루지 않고 NPE를 방어할수있게 되었다.

> Optional은 **null 이 될 가능성을 가진 값을** 객체로 **감싸는 래퍼 클래스**다. 
>
> null을 하나의 값으로 보고 로직을 구현함으로 NPE를 방어할수있다.
>
> null이 넘어오면 Optional 객체를 비워버리고 orElse와 같은 대응 메서드를 이용해 처리한다.
>
> 따라서 별로도 NPE 방어 로직을 구현할 필요가 없다. 직접 null에 대응할 필요가 없다.



## Reference

---

[기본]

https://tecoble.techcourse.co.kr/post/2021-06-20-optional-vs-null/

[총 정리]

https://www.daleseo.com/java8-optional-before/

https://www.daleseo.com/java8-optional-after/

https://www.daleseo.com/java8-optional-effective/