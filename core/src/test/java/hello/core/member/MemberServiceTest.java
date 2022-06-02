package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberSerivce memberSerivce;
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberSerivce = appConfig.memberSerivce();
    }
//    MemberSerivce memberSerivce = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);
        // when
        memberSerivce.join(member);
        Member findMember = memberSerivce.findMember(1L);
        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}