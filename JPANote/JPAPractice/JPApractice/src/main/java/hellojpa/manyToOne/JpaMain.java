package hellojpa.manyToOne;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유

        EntityManager em = emf.createEntityManager(); //엔티티 매니저는 쓰레드간에 공유X -> 사용 후 패기

        EntityTransaction tx = em.getTransaction(); //JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

        tx.begin();

        /**
         * Member Jpa로 생성하기
         */
        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);  //영속성 컨텍스트에 저장  //em은 쓰래드간 공유x 각각따로 호출

            //회원 저장
            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member); //영속성 컨텍스트에 저장

            //team.getMembers().add(member);  //양쪽에 값을 다 넣어주자. 영속성 컨텍스트단계라 데이터거ㅏ 없음 -> 연관관계 편의 메소드로

            //조회
            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();

            /**
             * 식별자로 다시 조회, 객체 지향적인 방법은 아니다
             * 
             * 테이블은 외래 키로 조인을 사용해서 연관된 테이블을 찾는다. 
             * 객체는 참조를 사용해서 연관된 객체를 찾는다. 
             */


            tx.commit(); //DB로 쿼리문 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        /**
         * JPQL > 나이가 18살 이상인 회원을 모두 검색과 같은 복잡한 쿼리를 구현할수있음
         *  > 객체지향 sql -> 객체를 대상으로 쿼리문 지원
         */


        emf.close();
    }
}
