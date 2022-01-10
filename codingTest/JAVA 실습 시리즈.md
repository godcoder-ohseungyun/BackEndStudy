# Map

---

+ key & value를 한쌍으로 묶어 저장하는 컬랙션 클래스들의 인터페이스
+ 구현클래스로 Hashtable, HashMap, LinkedHashMap 등이 있다.

<img src="C:\Users\afrad\AppData\Roaming\Typora\typora-user-images\image-20220110122306387.png" alt="image-20220110122306387" style="zoom:50%;" />





# HashMap

---

+ JAVA Collection    Map Interface를  구현하고있는 구현 클래스
+ Map을 구현함으로 Map의 특징인 k&v를 다룬다.
+ `해싱을 내부적으로 사용`함으로써 `많은 데이터를 검색하는데 뛰어난 성능`을 보인다.
+ k는 unique v는 중복을 허용한다.
+ 데이터를 같은 곳에 넣으면 최신값으로 덮어씌어진다.



**생성자**

생성자는 기본 16의 크기를 가지고있고 파라미터를 통해 크기를 지정해줄수도있다.

| Constructor                        | Description                                                  |
| :--------------------------------- | :----------------------------------------------------------- |
| **`HashMap()`**                    | Constructs an empty `HashMap` with the default initial capacity (16) and the default load factor (0.75). |
| **`HashMap(int initialCapacity)`** | Constructs an empty `HashMap` with the specified initial capacity and the default load factor (0.75). |

> 이 밖에도 여러 생성자가 존재한다.



**주목할만한 메서드**

| 반환값                | 메서드                                              | 설명                                                         |
| :-------------------- | --------------------------------------------------- | ------------------------------------------------------------ |
| `Set<Map.Entry<K,V>>` | `entrySet()`                                        | 모든 엔트리를 Set에 담아서 반환                              |
| `Object`              | **`getOrDefault(Object key, Object defaultValue)`** | 지정된 키의 값을 반환한다. 지정된 키가 없다면 defaultValue를 반환한다. |
| `Set<K>`              | `keySet()`                                          | 키값을 Set에 담아 반환                                       |
| `Collection<V>`       | `values()`                                          | 모든 값을 컬랙션으로 반환  -값은 중복을 허용하기 때문에 Set이 아니다. |

> 이 4개의 메서드는 코딩테스트에서 유용하게 쓰일것이다.



**Map.Entry**

+ HashMap은 내부에 Entry라는 클래스를 정의하고있다. `일종의 노드`라고 생각하면 된다.
+ Map Interface 내부에 존재하는 `Map.Entry Interface` 는 `엔트리를 다루기 위한 여러 메서드가 존재`한다.

> Map 구현 클래스들인 이를 구현하고있는 것이다.

| Modifier and Type | Method              | Description                                                 |
| :---------------- | :------------------ | :---------------------------------------------------------- |
| `boolean`         | `equals(Object o)`  | Compares the specified object with this entry for equality. |
| `K`               | `getKey()`          | Returns the key corresponding to this entry.                |
| `V`               | `getValue()`        | Returns the value corresponding to this entry.              |
| `int`             | `hashCode()`        | Returns the hash code value for this map entry.             |
| `V`               | `setValue(V value)` | entry의 value를 지정된 객체로 바꾼다.                       |



**Map으로 Iterator 사용하기**

Map은 List Set과 달리 설계 구조상 Collection interface를 상속받고 있지 않기 때문에 Iterator를 직접 사용할수없다.

따라서    `entrySet()` , `keySet()` , ``values()``를 사용해서 컬랙션으로 변형후 사용해야한다.



**실습**

~~~java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Solution_Hash {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap<>();
        for (String player : participant) map.put(player, map.getOrDefault(player, 0) + 1);  //getOrDefault 사용
        for (String player : completion) map.put(player, map.get(player) - 1);

        Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator(); //key, value를 같이 가져올 때는 항상 entrySet을 사용
        //entrySet은 entry를 Set에 담아 반환함으로 Iterator 사용가능


        
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            if (entry.getValue() != 0) { //entry를 다루는 메서드들
                answer = entry.getKey();
                break;
            }
        }
        return answer;
    }
}
~~~



