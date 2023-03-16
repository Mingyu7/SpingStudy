package hellomin.hellomin.Service;

import hellomin.hellomin.repository.JdbcMemberRepository;
import hellomin.hellomin.repository.MemberRepository;
import hellomin.hellomin.repository.MemoryMemberRepositroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource); // 데이터 베이스에 저장
    }

}
