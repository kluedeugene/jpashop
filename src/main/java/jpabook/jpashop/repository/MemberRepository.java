package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository     //스프링 빈으로 등록된다(컴포넌트스캔되서)
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);     //멤버에 객체를 넣는다. 트랜잭션이 커밋될떄 db에 반영.
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);       //단건조회. type, pk
    }

    public List<Member> findAll() {     //jpql- sql은 테이블 상대로 jpql은 엔티티객체를 대상으로 조회.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
