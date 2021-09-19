package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    private Long id;
    private Long memberId;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)  // 스트링으로 사용할것,이넘 수정시 발생 오류 방지
    private OrderStatus status;

}
