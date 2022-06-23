package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 앤티티 팩토리 매니저
        EntityManager em = emf.createEntityManager(); // 앤티티 매니저

        EntityTransaction tx = em.getTransaction(); // 트랜잭션
        tx.begin();
        try {
            Member member = em.find(Member.class, 100L);
            member.setName("ZZZZZ");
            em.detach(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}