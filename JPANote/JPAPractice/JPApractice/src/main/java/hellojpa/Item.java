package hellojpa;

import javax.persistence.*;

@Entity
public class Item {
    @Id @Column(name = "ITEM_ID",length = 10,nullable = false) @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;
}
