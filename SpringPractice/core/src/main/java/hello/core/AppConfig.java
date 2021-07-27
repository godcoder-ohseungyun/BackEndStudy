package hello.core;

/**
 * 앱 전체의 연결구조를 관리하는 CLASS 모든 전선을 여기서 갈아 끼워주는 느낌
 * 각 impl의 구현 클래스를 오직 여기서만 직접 주입해준다.
 * 사용자 영역의 코드는 수정할 필요가 없다.
 */

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //생성자에 구현객체 주입
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());  //before :: (new MemoryMemberRepository)
    }
    //생성자에 구현객체 주입
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), DiscountPolicy()); // before :: (new MemoryMemberRepository,new FixDiscountPolicy)
        //return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }
    //리펙토링
    //중복을 제거하고, 역할에 따른 구현이 보이도록 리팩터링 하자
    //구현체가 변경 되었을때 리팩토링한 부분만 수정하면 된다.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy DiscountPolicy() {
        return new FixDiscountPolicy();
    }
}