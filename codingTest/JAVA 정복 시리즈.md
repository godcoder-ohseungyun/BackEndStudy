





# [자바 정복 시리즈] java.lang 패키지

----

java.lang 패키지는 자바프로그래밍에 기본이 되는 클래스들을 구현하고있다.



+ **Object , String, StringBuffer,StringBuilder , Math,Wrapper 등 핵심 클래스들이 존재한다..**



## Object class

모든 자바 클래스들의 최고 조상으로 Oject class가 정의하는 메서드는 모든 클래스들이 사용할수있다.

> 필요시 재정의 해서 사용

`java.util.Objects` 라는 보조 클래스가 존재한다.



**Object class가 가지고있는 맴버 메서드**

> 몇가지 주요 메서드만 소개하겠다.

| Modifier and Type  | Method                          | Description                                                  |
| :----------------- | :------------------------------ | :----------------------------------------------------------- |
| `protected Object` | **`clone()`**                   | Creates and returns a copy of this object.                   |
| `boolean`          | **`equals(Object obj)`**        | Indicates whether some other object is "equal to" this one.  |
| `protected void`   | `finalize()`                    | Deprecated.The finalization mechanism is inherently problematic. |
| `Class<?>`         | **`getClass()`**                | Returns the runtime class of this `Object`.                  |
| `int`              | **`hashCode()`**                | Returns a hash code value for the object.                    |
| `void`             | `notify()`                      | Wakes up a single thread that is waiting on this object's monitor. |
| `void`             | `notifyAll()`                   | Wakes up all threads that are waiting on this object's monitor. |
| `String`           | **`toString()`**                | Returns a string representation of the object.               |
| `void`             | `wait()`                        | Causes the current thread to wait until it is awakened, typically by being *notified* or *interrupted*. |
| `void`             | `wait(long timeout)`            | Causes the current thread to wait until it is awakened, typically by being *notified* or *interrupted*, or until a certain amount of real time has elapsed. |
| `void`             | `wait(long timeout, int nanos)` | Causes the current thread to wait until it is awakened, typically by being *notified* or *interrupted*, or until a certain amount of real time has elapsed. |



**`Object class` 메서드들의 일부는 목적에 따라 오버라이드가 필요한 경우가 있다.**



### **equals**

> **Object class**는 equals 메서드를 다음과 같이 정의하고있다.
>
> ~~~java
> public boolean equals(Object obj) {
>         return (this == obj);
> }
> ~~~
>
> ==을 이용해 객체의 주소값을 비교하고있음으로 값이 같더라도 주소가 다르면 false를 반환할 것이다.
>
> equals 메서드를 오버라이딩 해서 객체 안의 값을 비교하도록 해야한다.
>
> **Person class**
>
> ~~~java
> private String name ="";
> 
> public boolean equals(Object obj) {
>     if(obj!=null && obj instance of Person){
>         return this.name == ((Person)obj).name; //이름으로 비교하게끔
>     }else
>         return false;
> }
> ~~~
>
> > name은 String 객체 타입인데 == 비교를해도 false가 나오지 않는다.
> >
> > 그 이유는 `String도 자체적으로 equals 메서드를 재정의`해서 문자열을 비교하도록 했기때문에 객체 주소를 비교하지 않는다.

### hashCode

> + 해싱에 사용되는 해시함수이다.
>
> Object 클래스는 해당 객체의 주소로 해시코드를 반환한다.
>
> 그렇기 때문에 값이 같아도 주소가 다르면 다른 해시코드를 반환한다.
>
> `String`은 문자열이 같으면 주소가 달라도 같은 해시코드를 반환하도록 재정의 했다.
>
> 해시함수를 만드는 공식은 다양하게 존재하니 찾아보고 목적에 맞게 재정의해서 사용하면 된다.

### toString

> Object 클래스는 해시코드를 문자열로 반환한다.
>
> > ex) @19e0bfd
>
> ~~~java
> private String name ="osy";
> 
> public String toString() {
>     return this.name; 
> }
> ~~~
>
> 이렇게 재정의 할수도있다.
>
> String class는 자신의 문자열이 반환되도록 재정의 되어있다.

### clone

> + 자신을 복제하여 새로운 인스턴스를 생성하는 일을 한다.
>
> Cloneable 인터페이스를 구현해야 clone()을 사용할수있다.
>
> `Object 클래스`는 단순히 값만을 복제하기 때문에  `얕은 복사`를 한다.
>
> > 복제본도 같은 주소를 참조하고있음
> >
> > 결국 복제본의 작업이 본 객체에 영향을 미침
>
> ~~~JAVA
> public Object clone() {
>     Object obj = null; //새로생성
>     try{
>         obj = super.clone();
>     } catch(CloneNotSupportedException e) {..} //super와 본객체 둘다 Cloneable 인터페이스를 구현해야 clone()을 사용할수있다.
>     return obj;
> }
> ~~~
>
> 새로운 객체를 아예 생성해서 데이터를 담아 반환하도록 `재정의`하여 `깊은 복사`가 가능하게 한다.
>
> 
>
> ~~~java
> public Person clone() { //공변환 가능
>     Object obj = null; 
>     try{
>         obj = super.clone();
>     } catch(CloneNotSupportedException e) {..} 
>     return (Person)obj;
> }
> ~~~
>
> > 공변환이란?
> >
> > 재정의시 부모 타입을 자손 타입으로 변경하는것을 허용한다.
>
> 반환을 자손으로 해 형변환 번거로움을 줄일수있다.

### getClass

> 자신이 속한 클래스의 Class 객체를 반환한다.
>
> Class 객체는 이름이 Class인 class로 모든 클래스는 한개의 Class객체를 갖는다.
>
> Class 객체에는 해당 클래스에 대한 정보가 들어있다.
>
> getName() 등



## String Class

String은 문자열을 다루는 클래스이다.

"+" 연산자를 수행할 경우 새로운 인스턴스를 만들기 때문에 별도의 저장공간과 주소가 할당된다.

>  많이쓰면 안좋음
>
> 이경우엔 StringBuffer를 사용

String은 Object의 equals를 내부적으로 재정의했다.

> 문자열을 비교하도록 재정의함.

같은 문자열을 바라보는 인스턴스는 모두 같은 주소지를 참조하게된다.

> ~~~java
> String str = "12";
> String str2 = "12";
> ~~~
>
> "12"라는 문자열이 저장된 주소를 str,str2가 동시에 바라보게 된다.

문자열을 다루는 메서드들을 제공한다.

> split , join ,parseInt 등



## StringBuffer Class

String 클래스는 생성된 인스턴스의 지정된 문자열을 변경할수없지만 StringBuffer 는 변경할수있다.

> append delete insert와 같은 문자열 변경 메서드를 지원한다.

내부적으로 버퍼를 가지고 있기 때문에 문자열 내용을 변경할수있지만 버퍼의 크기를 정해줘야하고

크기가 초과하면 늘려야하는 비용이 발생한다.



StringBuffer 는 String 과 달리` equals를 재정의 하지 않아`서 주소지를 비교한다.

> 반면에 toString은 재정의 되어있기 때문에
>
> `비교를 원한다면` StringBuffer 를 toString을 사용해서 내용을 String으로 변환한 다음에 String의 equals를 사용하면 된다. 



StringBuffer 에서 `멀티쓰래드 동기화`기능을 뺀 StringBuilder라는 클래스도 존재한다.



## Math

수학계산에 유용한 메서드들을 지원한다.



## Wrapper

객체 지향 개념에서 모든것은 객체로 다뤄져야한다.

때로는 원시타입(int,float,double 등)을 객체(참조타입)로 다뤄야하는 경우가 있다.

> Collection, 객체간 비교 등

이때 사용하는 클래스가 Wrapper 클래스이다.

래퍼 클래스는 객체 생성시에 원시타입을 내부적으로 저장한다.

이들은 내부적으로 equals, toString, compareTo 등 Object 클래스 메서드들을 재정의 하고있다.

> equals는 주소가 아닌 내부 값으로 비교하도록 재정의 되어있다.







# reflection API

---

Class class 동적생성









# [자바 정복 시리즈] java.util 패키지

---

+ **Objects, Random, 정규식 ,StringTokenizer 등의  실무에서 활용할만한 클래스들이 존재한다.**



## Objects

java.lang의 Object 클래스를 보조하는 클래스로 모든 메서드가 static 이다.

주로 `null check에 유용한 메서드들을 제공`한다.

대부분의 메서드들이 null check을 구현하고있다.

| Modifier and Type | Method                                                     | Description                                                  |
| :---------------- | :--------------------------------------------------------- | :----------------------------------------------------------- |
| `static int`      | `checkFromIndexSize(int fromIndex, int size, int length)`  | Checks if the sub-range from `fromIndex` (inclusive) to `fromIndex + size` (exclusive) is within the bounds of range from `0` (inclusive) to `length` (exclusive). |
| `static int`      | `checkFromToIndex(int fromIndex, int toIndex, int length)` | Checks if the sub-range from `fromIndex` (inclusive) to `toIndex` (exclusive) is within the bounds of range from `0` (inclusive) to `length` (exclusive). |
| `static int`      | `checkIndex(int index, int length)`                        | Checks if the `index` is within the bounds of the range from `0` (inclusive) to `length` (exclusive). |
| `static <T> int`  | `compare(T a, T b, Comparator<? super T> c)`               | Returns 0 if the arguments are identical and `c.compare(a, b)` otherwise. |
| `static boolean`  | **`deepEquals(Object a, Object b)`**                       | Returns `true` if the arguments are deeply equal to each other and `false` otherwise. |
| `static boolean`  | **`equals(Object a, Object b)`**                           | Returns `true` if the arguments are equal to each other and `false` otherwise. |
| `static int`      | **`hash(Object... values)`**                               | Generates a hash code for a sequence of input values.        |
| `static int`      | **`hashCode(Object o)`**                                   | Returns the hash code of a non-`null` argument and 0 for a `null` argument. |
| `static boolean`  | **`isNull(Object obj)`**                                   | Returns `true` if the provided reference is `null` otherwise returns `false`. |
| `static boolean`  | **`nonNull(Object obj)`**                                  | Returns `true` if the provided reference is non-`null` otherwise returns `false`. |
| `static <T> T`    | `requireNonNull(T obj)`                                    | Checks that the specified object reference is not `null`.    |
| `static <T> T`    | `requireNonNull(T obj, String message)`                    | Checks that the specified object reference is not `null` and throws a customized [`NullPointerException`](https://docs.oracle.com/javase/10/docs/api/java/lang/NullPointerException.html) if it is. |



~~~java
public void setName(String name){
    if(name == null)
        new NullPointerException("npe");
    else
    this.name = name;
}
~~~

위와 같은 기본에 null check 코드들을 Objects 메서들를 이용해 간단히 끝낼수있다.

~~~java
public void setName(String name){
    this.name = Objects.requireNonNull(name,"npe x"); 
}
~~~



대소 비교를 위한 compare도 추가되었다.



Objects equals는 Object equals를 재정의해서 내부적으로 null check를 시행한다.

~~~java
if(a!=null && a.equals(b))
    //do

if(Objects.equals(a,b)) //null check이 필요없다, 내부적으로 구현되어있음
    //do
~~~



나머지 hash,deepEquals도 마찮가지로 null check을 알아서 해준다.



즉, Objects 클래스는 Object 클래스의 메서드들을 null check을 내부적으로 구현하여 재정의 한것이다.

> compare 같은 본인만의 메서드도 존재한다. document를 살펴보자.

npe 처리에 대한 코드를 직접 작성하지 않아도 된다는 장점이 있다.



## Random

Math.random() 말고도 난수를 얻을수있다.

난수를 얻기위한 메서드들을 제공한다.



## 정규식

정규식이란 텍스트 데이터 중에서 원하는 조건에 일치하는 문자열을 찾아 내기 위해 사용하는 것으로 미리 정의된 기호와 문자를 이용해서 작성한 문자열을 말한다.

자바 뿐 아니라 다양한 언어에서 지원한다.

~~~
java.util.regex.Pattern
~~~

을 찾아보면 정규식에 사용되는 작성법이 정리되어있다.



특정 형식이나 패턴을 보유하는 문자열을들 찾아내는데 적합하다.

~~~
이메일 형식을 띄는 문자열들을 추출
특정 단어로 시작하는 문자열들을 추출
등등
~~~



## StringTokenizer

문자를 구분자를 이용해 나누는 기능을 제공한다.

> 토큰으로 나누고 반복자 같은 메서드를 제공하기때문에 사용하기 편하다.

String의 split보다 더 직관적이다.

하지만 `구분자를 1개만 적용`할수있기 때문에 여러가지 구분자를 사용해야 하는경우엔 `정규식`을 사용해야한다.





# [자바 정복 시리즈] Collection

----

Java에서 컬렉션(Collection)이란 데이터의 집합, 그룹을 의미하며 이를 다루기 위해

Java는 이러한 데이터, 자료구조를 정의하는 인터페이스와 구현 클래스를 제공한다.

> 컬렉션의 데이터 타입은 Wrapper, Object 등 `객체 타입`이다.

 

Collection Interface의 계층구조와 각 인터페이스와 그 구현클래스들의 특징을 파악해보자.



#### 컬렉션 프레임워크 주요 인터페이스

컬렉션 프레임워크에서는 데이터를 저장하는 자료 구조에 따라 다음과 같은 `핵심이 되는 3가지 주요 인터페이스`를 정의하고 있다.

1. List 인터페이스
2. Set 인터페이스
3. Map 인터페이스

**컬랙션 프레임워크 상속 계층도**

![image-20220106112823259](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20220106112823259.png)

> 이 중에서 `List와 Set 인터페이스는 모두 Collection 인터페이스를 상속`받지만, 구조상의 차이로 인해 `Map 인터페이스는 별도`로 정의.
>
> 따라서 List 인터페이스와 Set 인터페이스의 공통된 부분을 Collection 인터페이스에서 정의. 



**컬랙션 프레임워크 전체 상속도**

![image-20220107143556410](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20220107143556410.png)



실제 개발시에는 다루고자 하는 컬렉션의 특징을 파악하고 `어떤 인터페이스를 구현한 컬랙션 클래스를 사용`해야하는지 결정해야 하기때문에 각 인터페이스의 특징과 차이를 이해해야한다.

~~~java
List<> list = new LinkedList<>(); //ok
List<> list = new HashSet<>(); //error List -> Set
~~~

> 구현 클래스를 올바르게 사용해야한다.

또한 목적에 맞게 컬렉션을 선택해서 사용해야한다.

~~~java
Set<> set = new HashSet<>(); //해싱을 이용하여 중복이 없게 저장하고싶다.
~~~





### Collection Interface 

+ List 와 Set 인터페이스의 조상

+ `컬랙션 데이터를 crud하는 기본적인 메서드`들을 정의하고있다.

List 와 Set 인터페이스가 상속하고있는 Collection 인터페이스는 다음과 같은 메서드를 정의하고있다.

![image-20220106115454507](C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20220106115454507.png)





### 컬렉션 프레임워크 주요 인터페이스

각 인터페이스들은 `Collection 인터페이스로부터 상속받은 메서드`들 **+** `자신이 정의하는 메서드`들을 정의하고있다. 

각 인터페이스들의 특징을 잘 파악하고 사용해야한다.

각 인터페이스들의 구현 클래스들의 특징을 잘 파악하고 사용해야한다.



### List Interface

+ `중복을 허용`하면서 `저장 순서가 있는` 컬랙션에 사용
+ `구현 클래스`: LinkedList , ArrayList , Stack 등

> LinkedList : 노드를 이용해 연결구조를 가진다. 데이터 중간 삽입삭제 유리
>
> ArrayList: 일반적인 배열의 특성을 갖는다. 데이터 조회 유리
>
> Stack:  후입선출

### Queue Interface

+ 큐 인터페이스
+ `구현 클래스`: LinkedList , PirorityQueue , Deque

> LinkedList: 큐는 선입선출로 pop시 요소 이동이 필요하기때문에 LinkedList Class도 큐를 구현하고있음
>
> PirorityQueue: 우선순위 큐
>
> Deque: 덱큐

### Set Interface

+ `중복을 허용하지 않고` `저장순서가 없는` 컬랙션에 사용한다.
+ `구현 클래스`: HashSet, Sorted Set , TreeSet

> HashSet: 해시 집합
>
> TreeSet: 트리구조를 띄는 집합

### Map Interface

+ key & value를 하나의 쌍으로 묶어서 저장하는 컬렉션을 구현하는데 사용된다.
+ key는 unique하며 value는 중복을 허용한다.
+ 기존 데이터와 동일한 key값의 데이터를 넣으면 기존 데이터가 최신화 된다.
+ `구현 클래스`: HashMap , TreeMap등

> HashMap: key&Value를 이용한 해시
>
> TreeMap:  key&Value를 이용한 트리

+ **Map.Entry Interface**

  Map 인터페이스의 내부 인터페이스로 별도로 재정의 하는게 가능하다.

  Map에 저장되는 key-value쌍을 다루기 위해 존재한다.

  ~~~java
  interface Entry {
      Object getKey();
      Object getValue();
      boolean equals();
      //...
  }
  ~~~

  

  

  

  





# Iterator

---

컬랙션 프레임워크는 요소들을 편리하게 순차 조회할수있는 반복자를 제공한다.

컬랙션 프레임워크 계층도를 보면 `Collection Interface`는 `Iterator Interface`를 상속하는 `Iterable Interface를  상속`하고 있다.

**Iterator Interface**

| Modifier and Type | Method                                         | Description                                                  |
| :---------------- | :--------------------------------------------- | :----------------------------------------------------------- |
| `default void`    | `forEachRemaining(Consumer<? super E> action)` | Performs the given action for each remaining element until all elements have been processed or the action throws an exception. |
| `boolean`         | `hasNext()`                                    | Returns `true` if the iteration has more elements.           |
| `E`               | `next()`                                       | Returns the next element in the iteration.                   |
| `default void`    | `remove()`                                     | Removes from the underlying collection the last element returned by this iterator (optional operation). |

**Iterable Interface** extends Iterator 

| Modifier and Type        | Method                                | Description                                                  |
| :----------------------- | :------------------------------------ | :----------------------------------------------------------- |
| `default void`           | `forEach(Consumer<? super T> action)` | Performs the given action for each element of the `Iterable` until all elements have been processed or the action throws an exception. |
| `Iterator<T>`            | `iterator()`                          | Returns an iterator over elements of type `T`.               |
| `default Spliterator<T>` | `spliterator()`                       | Creates a [`Spliterator`](https://docs.oracle.com/javase/10/docs/api/java/util/Spliterator.html) over the elements described by this `Iterable`. |



그럼 당연히 `Collection Interface`를 상속하는 `Set , List Interface`도  `Iterable Interface를  상속`하고 있다.



**따라서  List 나 Set Interface를 구현하는 ` 구현 클래스들은 모두 내부적으로 Iterable Interface를 구현`하고있다.**

**ex)** ArrayList Class

~~~java
//in ArrayList implements List
public Iterator<E> iterator() { 
        return new Itr();
    }
    
private class Itr implements Iterator<E> { 
    int cursor;       
    int lastRet = -1; 
    int expectedModCount = modCount;
    
    Itr() {}

    public boolean hasNext() {
        return cursor != size;
    }

    @SuppressWarnings("unchecked")
    public E next() {
        checkForComodification();
        int i = cursor;
        if (i >= size)
            throw new NoSuchElementException();
        Object[] elementData = ArrayList.this.elementData;
        if (i >= elementData.length)
            throw new ConcurrentModificationException();
        cursor = i + 1;
        return (E) elementData[lastRet = i];
    }
~~~



### 활용

~~~java
List<> list = new ArrayList<>();  //ArrayList가 아니더라도 List,Set 구현 클래스는 모두 사용할수있음

Iterator it = list.iterator();

while(it.hasNext()){
    log.info(it.next()); //순차적으로 요소 logging
}
~~~

List,Set 구현 클래스는 모두 Iterator를 사용해서 컬랙션 내부 요소를 조회할 수 있다.



### Map 에서의 iterator

`Collection Interface를 상속하지 않는 Map Interface`는 어떻게 Iterator를 이용해야할까?

Map Interface 구현 클래스들은 KeySet()이나 entrySet() 같은 메서드롤 통해 키와 값을 따로 Set 형태로 얻어서 Iterator를 이용할수있다.

~~~java
Map map = new HaseMap();
Iterator iterator = map.keySet().iterator(); //keySet(): map의 key값들을 Set에 담아 반환
~~~







# Arrays

---

Arrays 클래스에는 배열을 다루는데 유용한 메서드들이 정의되어있다.

> 배열을 채우고 복사하고 나누고 추출하고 비교하는 등 많은 기능이 구현되어있음

몇가지 대표적인 메서드를 정리하겠다.

~~~JAVA
//배열 복제: copyOf ,copyOfRange
int[] arr = new int[5]; //default 0
int[] arr2 = Arrays.copyOf(arr,arr.length);
int[] arr3 = Arrays.copyOf(arr,3); // 크기가 3인 arr3를 생성

int[] arr4 = Arrays.copyOfRange(arr,0,2); // 0~1범위 복사: 마지막 포함 x


//배열 채우기: fill ,setAll
int[] arr = new int[5];
Arrays.fill(arr,9); // arr = [9,9,9,9,9]  : 지정 값으로 채움
Arrays.setAll(arr,()->(int)(Math.random()*5)+1); // arr=[1,5,2,3,1] : 지정한 함수형 인터페이스 혹은 람다식으로 채움

//정렬과 탐색: sort ,binarySearch
int[] arr = {1,5,7,6};
Arrays.sort(arr); // arr = [1,5,6,7]
Arrays.binarySearch(7)// return 3 : 파라미터의 값이 있는 인덱스를 반환
//이진 탐색은 배열이 정렬된 상태에서 진행해야한다.
    

//비교와 출력: equals,toString
int[] arr = {1,5,7,6};
int[][] arr2 = {{1,2},{3,4}};
Arrays.toString(arr); //배열의 요소를 문자열로 반환:배열의 타입에 따라 다 구현되어있다.  [1,5,7,6]
Arrays.deepToString(arr2); //다차원 배열은 deepToString으로 해야한다. [[1, 2], [3, 4]]

Arrays.equals(arr,arr); //얕은 비교: 원시타입 1차원 배열 비교에 사용
Arrays.deepEquals(arr2,arr2); //깊은 비교: 객체(String..) 혹은 다차원 배열 비교

//배열을 List로 변환: asList(Object o)
Integer[] a = {1,2,3};
List list2  = Arrays.asList(a); //래퍼객체 배열을 List로 변환 , 원시타입 배열은 다른방법이 있음
Iterator it = list2.iterator();

while (it.hasNext()){
    System.out.println(it.next());
}
~~~









# Comparator & Comparable

---

Comparator & Comparable는  Integer와 같은 Wrapper 클래스나 String,Date 등 `서로 비교가 가능한 클래스`들이 기본으로 구현하고있다.

> ~~~java
> public final class Integer extends Number implements Comparable<Integer>{ //Comparable을 구현하고있다.
>     //..
>     
>   	public int compareTo(Integer anotherInteger) {
>     		return compare(this.value, anotherInteger.value);
> 	}
> 
>        public static int compare(int x, int y) {
>         	return (x < y) ? -1 : ((x == y) ? 0 : 1);
>        }  
> }
> ~~~



**사용자 정의 클래스의 경우**

비교기준을 기본으로 즉,오름차순으로 할꺼면`Comparable`

사용자가 직접 만든 기준으로 하고 싶으면 `Comparator`

를 구현하면 된다.

~~~java
class Descending implements Comparator {
    //사용자 정의 비교 기준
    @Override
    public int compare(Object ol, Object o2) {
        if (ol instanceof Comparable && o2 instanceof Comparable) {
            Comparable cl = (Comparable) ol;
            Comparable c2 = (Comparable) o2;
            return cl.compareTo(c2) * -1; // 내림차순
        }
        return -1;
    }
}
~~~



Comparator 와 Comparable의 차이는 정렬 기준의 사용자 정의 유무에 있다.

~~~java
// Comparable을 구현하는 클래스들은 오름 차순 정렬 기준으로 구현하고있다. 사용자 정의 시에도 오름차순으로 구현해야한다.
public interface Comparable {
    int compareTo(Object o); //-1,0,1
}

// Comparator는 기존의 정렬 기준이 아닌 내가 만든 정렬 기준으로 하고싶을때 사용한다. 
public interface Comparator {
    int compare(Object o1,Object o2);//-1,0,1
    boolean equals (Object obj);
}
~~~



### Arrays.sort에서 활용

정렬할때 직접 만든 정렬 기준을 적용할수있다.

~~~java
Arrays.sort(strArr,new Descending()); //위에서 예시로 보인 사용자 정의 정렬기준
~~~

~~~java
//Arrays class
public static <T> void sort(T[] a, Comparator<? super T> c) {
        if (c == null) {
            sort(a);
        } 
    
    	else {
            if (LegacyMergeSort.userRequested)
                legacyMergeSort(a, c); //정렬 기준을 가지고 내부적으로 머지소트 진행
            else
                TimSort.sort(a, 0, a.length, c, null, 0, 0);
        }
}
~~~





# Collections

---

Arrays가 배열을 쉽게 다루는 메서드들을 구현하고있는것 처럼 Collections는 컬랙션을 쉽게 다루는 메서드를 제공한다.

컬랙션을 동기화



싱글톤으로 컬랙션 만들기



컬랙션 객체 제한하기





