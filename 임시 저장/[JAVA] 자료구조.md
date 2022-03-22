# 자료구조

---

+ **리스트**
+ **연결리스트**
+ **스택**
+ **큐**
+ **덱**
+ **힙**

----

![img](https://blog.kakaocdn.net/dn/cr7G8i/btrvwipdBU4/7luVOyNzc0mPNbpqXWcvJK/img.png)

----

### Collection interface

+ [Collection (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collection.html)

> ### List interface
>
> + [List (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html)
>
> > **[구현]**
> >
> > ### ArrayList class
> >
> > + 조회가 빠름
> > + 적절한 초기 capacity 설정이 필요
> > + [ArrayList (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)
> >
> > 
> >
> > ### LinkedList class
> >
> > + 삽입 삭제가 효율적
> > + 연결리스트 구조
> > + [LinkedList (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html)
>
> **[비교설명]**
>
> **ArrayList**
>
> + 배열의 <u>capacity 초과시 2배 메모리로 증가</u>시켜 요소 추가함
> + 배열의 capacity 변화가 적고 중간 삽입 삭제가 빈번하지 않는경우사용할것
> + <u>선언시 적절한 capacity 설정</u> 필요
> + 배열구조임으로 요소 조회가 매우빠름
>
> **LinkedList** 
>
> + <u>양방향 연결 구조</u>로 이뤄져있음
> + 추가되는 요소만큼만 capacity 증가
> + capacity 변화가 잦고 중간 삽입 삭제가 빈번한경우 사용
> + <u>다음 노드정보를 유지하는데 추가 메모리가 사용</u>됨 **(배열구조과 다른점)**
> + 요소 조회가 느림
>
> ![image-20220321153952453](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20220321153952453.png)
>
> 
>
> 
>
> ### Set interface
>
> + [Set (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html)
>
> > **[구현]**
> >
> > ### HashSet class
> >
> > + 성능 가장 빠름
> >
> > + <u>값을 순서없이 해쉬테이블에 저장(매번 출력순서가 다름)</u>
> >
> > + [HashSet (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashSet.html)
> >
> >   
> >
> > ### LinkedHashSet class (extends HashSet)
> >
> > + <u>값을 순서가 있는 해쉬테이블에 저장(삽입순)(매번 출력순서가 같음)</u>
> > + [LinkedHashSet (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedHashSet.html)
> >
> > 
> >
> > ### TreeSet class
> >
> > + 성능이 좋지않음, 사용하지 않는걸 권장
>
> **[비교설명]**
>
> https://selina-park.tistory.com/97
>
> https://velog.io/@gillog/HashSet





### Stack class

+ [Stack (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Stack.html)
+ 단독으로 사용함(고유 메서드 존재  peek pop push 등)





### Queue interface

+ [Queue (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html)
+ 단독으로 사용함(고유 메서드 존재  peek poll offer 등)

> **[구현]**
>
> **PriorityQueue class(힙 자료구조로 활용)**
>
> + **힙 트리 구조**로 구현되어 있음(기본적으로 내림차순, compartor 적용시 기준변경)
> + [PriorityQueue (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html)
>
> 
>
> ### LinkedList class
>
> + 선입 선출
> + [LinkedList (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html)





### Deque interface

+ [Deque (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Deque.html)
+ 단독으로 사용함(고유 메서드 존재  addFirst , addLast 등)

> **[구현]**
>
> ### ArrayDeque class
>
> + 배열 형태로 관리되어 조회
> + LinkedList 보다 성능이 우수
> + [ArrayDeque (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayDeque.html)
>
> 
>
> ### LinkedList class
>
> + 연결리스트 구조

중간 삽입 삭제가 x  양끝에서만, 다음 노드에대한 메모리를 보유할 필요 x 

https://chucoding.tistory.com/52





### Map interface

+ [Map (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html)

> **[구현]**
>
> ### **HashMap**
>
> + [HashMap (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html)
> + key , value에 null을 사용할수있다.
> + **No Thread safe**
> + 삽입 삭제 탐색 O(1)
>
> 
>
> ### **TreeMap**
>
> + [TreeMap (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/TreeMap.html)
> + 목록조회시 key 값 기준으로 정렬한 순서를 보장한다.
> + Comparator 구현시 정렬기준 지정가능
> + 삽입 삭제 탐색 O(log n)  <u>(트리구조이기 때문)</u>
>
> 
>
> ### LinkedHashMap
>
> + [LinkedHashMap (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedHashMap.html)
> + 목록조회시 입력(put)순서를 보장한다.
> + 삽입 삭제 탐색 O(1) <u>(but. 메모리 유지 비용 있음)</u>
>
> 
>
> ### **HashTable**
>
> + [Hashtable (Java SE 11 & JDK 11 ) (oracle.com)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Hashtable.html)
> + key , value에 null을 사용할수없다.
> + **Thread safe**
> + 삽입 삭제 탐색 O(1)

해쉬이론 어레이 주소 hasing   조회 삽입 삭제 o1 정리



https://onsil-thegreenhouse.github.io/programming/java/2018/02/22/java_tutorial_1-24/

https://soft.plusblog.co.kr/70

https://tomining.tistory.com/168

https://memostack.tistory.com/233



특별한 이유가 없다면 get() 성능이 빠른 HashMap을 사용하자. 순서 보장이 필요하다면 TreeMap이나 LinkedHashMap을 고려해 볼 수도 있다. 

만약 동기화 이슈가 있다면 HashTable도 고려할 수 있다.