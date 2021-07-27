package hello.core.discount;

import hello.core.member.Member; //package 구조가 바뀌어서 import 필요

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);

}
