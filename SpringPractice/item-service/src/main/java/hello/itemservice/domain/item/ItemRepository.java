package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //저장소로 지정
//컴포넌트 스캔 기본 대상: 저장소 계층에서 사용하는 어노테이션
//컴포넌트 에노테이션을 포함한다.
public class ItemRepository {
    /**
     * default 싱글톤: 동시에 접근하는 경우 에러가 날수있다
     * 여러 쓰래드가 접근한는 경우 HahMap, Long 등 사용하면 안됨.
     * HahMap -> currentHachMap
     *
     * 아래 코드 이해하기위해 HahMap API에 대한 지식이 필요함.
     */
    //Entry(key,value) key(object type),value(object type)
    private static final Map<Long, Item> store = new HashMap<>(); //alt+click: "check out the HashMap method"
    private static long sequence = 0L; //static 사용 //데이터 카운트



    /**
     * control methods
     */
    public Item save(Item item) {
        item.setId(++sequence); //id

        store.put(item.getId(), item); //HashMap method key:item id , value: item object
        return item;
    }

    public Item findById(Long id) {
        return store.get(id); //object(객체) 반환 Hash map value or null
    }


    public List<Item> findAll() {
        return new ArrayList<>(store.values()); //collection values(){}
    }

    public void update(Long itemId, Item updateParam)
    {
        Item findItem = findById(itemId);
        //updateParam.getId를 사용하지 x, 협업시 혼란 -> 원래는 별로도 id가 없는 변경용 임시 객체 class 생성해서 사용
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}