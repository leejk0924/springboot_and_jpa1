package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    // 스프링이 @PersistenceContext 애너테이션에 JPA Entity 매니저를 주입해준다.
//    @PersistenceContext
//    private EntityManager em;
    private final EntityManager em;

    // EntityManagerFactory를 주입받고 싶으면 아래와 같이 하면 주입 받을 수 있다. (PersistenceContext 가 있어서 거의 사용 X)
//    @PersistenceUnit
//    private EntityManagerFactory emf;


   public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);       // 첫번째 타입 값, 두번째 primaryKey 값
    }

    // JPQL 기능적으로는 동일하지만, SQL 은 테이블을 대상으로 쿼리를 하지만, JPQL 은 Entity 객체를 대상으로 쿼리를 한다.
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
