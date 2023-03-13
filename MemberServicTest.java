package hellomin.hellomin.Service;

import hellomin.hellomin.Domain.Member;
import hellomin.hellomin.repository.MemoryMemberRepositroy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepositroy memberRepository;
    @BeforeEach // DI 가능하게 한다
    public void beforeEach() {
        memberRepository = new MemoryMemberRepositroy();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // DB 초기화
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());

    }

    @Test
    void findMembers() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2= new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then


    }

    @Test
    void findOne() {
    }
}