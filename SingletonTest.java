package hello.core.sigleton;

import hello.core.member.MemberService;
import hello.core.order.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 자바 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회: 호출할때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        // 참조값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 컨테이너")
    void SingletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        assertThat(singletonService1).isSameAs(singletonService2);
        // same 참초값이 같음
        // equals 값이 같음 
    }

    @Test
    @DisplayName("스프링 컨테이너")
    void SpringContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService",MemberService.class);

        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);


        assertThat(memberService1).isSameAs(memberService2);
    }

    @Test
    void configurationDeep(){
       ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
