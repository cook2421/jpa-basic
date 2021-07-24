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

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
