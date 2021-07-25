package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
/*

            // 비영속 상태
            Member member1 = new Member();
            member1.setName("A");

            Member member2 = new Member();
            member2.setName("B");

            Member member3 = new Member();
            member3.setName("C");

            // 영속 상태
            System.out.println("======================");
            em.persist(member1);    // 1, 51 (50번까지 할당받으려고 call next value 2번 호출, 왜냐하면 처음에 -49부터 시작)
            em.persist(member2);    // MEMORY
            em.persist(member3);    // MEMORY
            System.out.println("member.getId() = " + member1.getId());
            System.out.println("member.getId() = " + member2.getId());
            System.out.println("member.getId() = " + member3.getId());
            System.out.println("======================");
*/

            // 저장


            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            // 양방향 매핑시 team에도 member를 추가해줘야, DB insert가 돌기 전 1차 캐시에도 team이 member를 들고있게 됨.
            // 따라서 아래 한 줄을 Member 엔티티의 메소드에 추가해줄 것을 권장함
            // team.getMembers().add(member);

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("==============");
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
            System.out.println("==============");

/*
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
*/

/*
            // member 객체의 외래키 업데이트
            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);
*/

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
