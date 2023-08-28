package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기 전용
@RequiredArgsConstructor // final인 생성자 자동생성
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    /** 회원가입 **/
    @Transactional
    public Long join(Member member){
        validataDulicateMember(member); // 중복 회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validataDulicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }

    }

    /** 전체 회원 조회 **/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
