package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 앤티티 팩토리 매니저
        EntityManager em = emf.createEntityManager(); // 앤티티 매니저

        EntityTransaction tx = em.getTransaction(); // 트랜잭션
        tx.begin();
        try {
            Team team = new Team();
            team.setName("team");
            em.persist(team);

            Member member = new Member();
            member.setUsername("test");
            member.setAge(1);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select m from Member m inner join m.team t"; // inner는 생략 가능.
//            String query = "select m from Member m left outer join m.team t"; // outer는 생략 가능.
            String query = "select m from Member m, Team t where m.username = t.name";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}