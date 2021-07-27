package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

/**
 * 고정할인
 */

public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { //for VIP //enum은 == 이 맞다 (객체비교와 다름)
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}