package hello.itemservice.domain.item;

import lombok.Data;

/**
 * alt+insert  클래스 요소 자동생성
 * 주의!! @Data 자체를 사용하는건 위험요소가 있다
 * - @Getter @Setter 정도만 사용하자
 */

@Data //Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
//@Getter @Setter
public class Item {

    //default: null
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    //constructor(생성자)
    public Item(){

    }

    //constructor(생성자)
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
