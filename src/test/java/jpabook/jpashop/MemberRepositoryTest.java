package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

   @Test
   @Transactional()
   @Rollback(false)
   public void testMember() throws Exception {
       //given
       Member member = new Member();
       member.setUsername("memberA");
       //when
       Long saveId = memberRepository.save(member);
       Member findMember = memberRepository.find(saveId);

       //then
       Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
       Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//       Assertions.assertThat(findMember).isEqualTo(member); //조회한것가 저장한것의 멤버는 같다 왜냐면 같은 트랜젝션에서, 같은 영속성컨텍스트에서 같은 멤버를 가져가때문
   }

}

