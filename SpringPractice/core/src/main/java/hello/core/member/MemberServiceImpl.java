
package hello.core.member;

//구현이 확정된 구현체는 이름 뒤에 impl을 붙여주는것이 관례이다.
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    //인터페이스로 구현 객체를 받음으로써 한 인터페이스로 다양한 구현객체를 받을수있게 한다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}