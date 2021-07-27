package hello.core.member;

public interface MemberRepository {

    void save(Member member); //회원 객체 저장기능

    Member findById(Long memberId); //회원 아디이 return
}
