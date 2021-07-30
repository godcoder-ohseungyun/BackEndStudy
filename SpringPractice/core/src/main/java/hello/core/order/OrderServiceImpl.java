package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    //인터페이스에만 의존 AppConfig에서 구현객체 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) //-> (MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy)
    {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //Qualifier 사용하기
//    @Override
//    public Order createOrder(Long memberId, String itemName, int itemPrice) {
//        Member member = memberRepository.findById(memberId);
//        int discountPrice = discountPolicy.discount(member, itemPrice);
//        return new Order(memberId, itemName, itemPrice, discountPrice);
//    }
//
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository,
//                            @Qualifier("mainDiscountPolicy") DiscountPolicy
//                                    discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
}