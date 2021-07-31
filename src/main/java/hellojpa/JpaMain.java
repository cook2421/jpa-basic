package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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


/*

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
*/

/*
            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");

            // 아래 코드는 다른 테이블에 업데이트쳐야하는 부분
            team.getMembers().add(member);

            em.persist(team);
*/

 /*
            Member member = new Member();
            member.setUsername("user1");
            member.setCreatedBy("kim");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(10000);
            em.persist(movie);

            em.flush();
            em.clear();

            Item item = em.find(Item.class, movie.getId());
            System.out.println("findMovie = " + item);
*/


/* 프록시1
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember.getClass() = " + refMember.getClass());
            refMember.getUsername();

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember.getClass() = " + findMember.getClass());
            // 영속성 컨텍스트에 있으면 프록시로 조회해도(getReference) 실제 엔티티 반환
            // 준영속 상태에서 프록시로 먼저 조회하면 그 뒤에 find로 조회해도 프록시로 반환
            // '==' 비교를 true로 만들기 위함
*/


/* 프록시2

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember.getClass() = " + refMember.getClass());

            em.detach(refMember);

            refMember.getUsername();    // LazyInitializationException 터짐
*/


/* 프록시3
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember.getClass() = " + refMember.getClass());   // Proxy 여부 확인
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); // false
            refMember.getUsername();         // 강제 초기화 방법1
            Hibernate.initialize(refMember); // 강제 초기화 방법2
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); // true
            // 프록시 초기화 여부
*/

/* 지연로딩, 즉시로딩

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member1.getId());
            System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass());

            System.out.println("=================");
            m.getTeam().getName();
            System.out.println("=================");
*/

/* 영속성 전이, 고아 객체
            Child child2 = new Child();
            Child child1 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent p = em.find(Parent.class, parent.getId());
            p.getChildList().remove(0);
*/

/* 임베디드 타입

           Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(new Address("city", "street", "100"));
            member.setWorkPeriod(new Period());

            em.persist(member);
*/


/* 값 타입과 불변 객체

            Address address = new Address("city", "street", "100");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Address copyAddress = new Address(address.getCity(),
                    address.getStreet(),
                    address.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

            //member1.getHomeAddress().setCity("new City");
            // setter를 빼서 공유 참조를 변경하는 행위 원천 차단
*/

/* 값 타입 컬렉션

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================start================");
            Member findMember = em.find(Member.class, member.getId());

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address.getCity() = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }

            // 1. 값 타입 변경시 새 인스턴스 넣어줄 것. ex) homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity");   // 이렇게 하면 안 됨.
            Address a = findMember.getHomeAddress();
            //findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 2. String 값 타입 컬렉션 변경. ex) 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            // 3. 값 타입 컬렉션 변경. old1만 지우고 새로 갈아끼워도 쿼리는 해당 member_id 데이터 다 날리고 새로 다 넣음. 따라서, 쓰면 안 됨.
            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "10000")); // equals, hashcode 오버라이드가 잘 되어있어야 함!!
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "10000"));
*/

/* 간단한 JPQL

            List<Member> result = em.createQuery(
                    "select m from Member m where m.username like '%kim%'",
                    Member.class
            ).getResultList();

단점: String으로 SQL을 짜다 보니 디버깅, 동적 쿼리 힘듦

*/

/* 간단한 JPA Criteria

            // Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m);

            String username = "asdfasdf";
            if(username != null){
                cq = cq.where(cb.equal(m.get("username"), "kim"));
            }

            List<Member> resultList = em.createQuery(cq).getResultList();

장점: 자바로 쿼리문을 만들기 때문에 디버깅, 동적쿼리 용이
단점: 사용법이 은근 어려우며 SQL스럽지가 않음

*/


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
