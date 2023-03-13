package hellomin.hellomin.Service;

import hellomin.hellomin.repository.MemberRepository;
import hellomin.hellomin.repository.MemoryMemberRepositroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean //자바 코드로 스프링 빈에 등록
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepositroy();
    }
}
