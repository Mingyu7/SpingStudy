package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Configuration 해줘야지 바이트 코드를 조작한다
public class AppConfig {
    @Bean // 스프링 컨테이너에 스프링 빈 등록
    public MemberService memberService(){
        System.out.println("Call AppConfig.memberService"); // 호출 로그
        return new MemberServiceImpl(memberRepository()); // 생성자를 통해서 의존관계 주입
    }

    @Bean
    public OrderService orderService(){
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(),discountPolicy()); // OrderServiceImpl는 실행에만 집중한다
    }
    @Bean
    public MemberRepository memberRepository(){ // 리팩터링해서 중복 제거
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();  코드 한줄만 바꾸면 할인 정책을 바꿀수가 있다
        return new RateDiscountPolicy();
    }
}
