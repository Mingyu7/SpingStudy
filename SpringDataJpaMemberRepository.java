package hellomin.hellomin.repository;

import hellomin.hellomin.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository { // JpaRepository 기본 메소드 제공
    @Override
    Optional<Member> findName(String name); // 공통인터페이스로 사용할수 없기에 직접 만들어 준다



}
