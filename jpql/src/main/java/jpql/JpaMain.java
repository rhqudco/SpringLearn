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
            Team team1 = new Team();
            team1.setName("team1");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("team2");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("test1");
            member1.setAge(1);
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("test2");
            member2.setAge(1);
            member2.setTeam(team1);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("test3");
            member3.setAge(1);
            member3.setTeam(team2);
            em.persist(member3);

            em.flush();
            em.clear();

            String query = "select m from Member m join fetch m.team";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();
            for (Member member : result) {
                System.out.println("username = " + member.getUsername() + ", " + "teamName = " + member.getTeam().getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}