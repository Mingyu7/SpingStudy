package hellomin.hellomin.repositroy;

import hellomin.hellomin.Domain.Member;
import hellomin.hellomin.repository.MemoryMemberRepositroy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*; // Assertions import

class MemoryMemberRepositroyTest {

    MemoryMemberRepositroy repository = new MemoryMemberRepositroy();
    @AfterEach
    public void afterEach() {
        repository.clearStore(); // 하나의 테스트 끝날때 마다 데이터 지우기
    }
    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member);
        //then
        Member result = repository.findId(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("string1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("string2");
        repository.save(member2);

       // Optional<Member> result = repositroy.findName("spring1");
        Member result = repository.findName("string1").get();

        assertThat(result).isEqualTo(member1);

//        Member result = repositroy.findName("string2").get(); // Error
//        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void FindAll(){
        Member member1 = new Member();
        member1.setName("string1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("string2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result).isEqualTo(2); //  isEqualTo 에 3넣으면 오류 2개가 만들어졌지 때문
    }
}
