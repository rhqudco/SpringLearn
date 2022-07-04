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

            String query = "SELECT DISTINCT t FROM Team t join fetch t.members";
            List<Team> result = em.createQuery(query, Team.class)
                    .getResultList();
            for (Team team : result) {
                System.out.println("team = " + team.getName() + " ㅣ members = " + team.getMembers().size());
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