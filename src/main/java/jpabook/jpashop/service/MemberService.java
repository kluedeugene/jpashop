package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional      //기본적으로 트랜잭션안에서 데이터 변경이 이루어져야한다. 그래서 Transcationl이 꼭필요
@RequiredArgsConstructor    //final있는 필드만 가지고 생성자 만들어준다.
public class MemberService {

    // 스프링이 빈에 등록되어있는 memberRepository 를 인젝션해준다.(필드인젝션)
    private final MemberRepository memberRepository;

//    //생성자가 하나만있다면 자동 Autowired
//    public MemberService(MemberRepository memberRepository) {       //생성자 인젝션. 생성이후 변경 안되고. 테스테 케이스도 용이.
//        this.memberRepository = memberRepository;
//    }

    //    @Autowired      //setter 주입. 테스트코드 용이. 단점: 런타임중 변경가능성이 있다.ㅏㅏ
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional(readOnly = false)        //기본값 false
    public Long join(Member member) {

        validateDuplicateMember(member);       //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //중복검사
    //동시에 같은요청이들어오면 문제가될것이다.(멀티스레드같은환경)따라서 db의 멤버의 name을 유니크 제약조건으로 잡는것을 추천
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true)     //readonly를 하면 jpa가 조회하는곳에서는 성능최적화가능.(더티체킹안하고..)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
    @Transactional(readOnly = true)     //readonly를 하면 jpa가 조회하는곳에서는 성능최적화가능.(더티체킹안하고..)
    public Member findMember(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}




