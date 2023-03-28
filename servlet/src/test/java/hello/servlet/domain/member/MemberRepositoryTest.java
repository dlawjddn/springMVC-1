package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member("dlawjddn", 24);
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(savedMember).isEqualTo(findMember);

    }
    @Test
    public void findAll(){
        Member member1 = new Member("dlawjddn", 24);
        Member member2 = new Member("rkdgPdnjs", 24);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1, member2);
    }
}
