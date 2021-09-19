package hellojpa;

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

            //em.persist(); //저장

            tx.commit();
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
