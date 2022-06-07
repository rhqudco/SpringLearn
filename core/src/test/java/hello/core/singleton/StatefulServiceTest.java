package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // Thread A : A 사용자 10000원 주문
        statefulService1.order("userA", 10000); // -> int userAPrice = statefulService1.order("userA", 10000);
        // Thread B : B 사용자 10000원 주문
        statefulService1.order("userB", 20000); // -> int userBPrice = statefulService1.order("userB", 20000);

        // Thread A : 사용자 A 주문 금액 조회
        int price = statefulService1.getPrice();
        // 예상헀던 결과는? price = 10000
        // 하지만 출력값은? price = 20000
        // A가 주문했던 10000원이 있었으나, B가 다시 주문해서 20000만원으로 바뀜
        // 객체의 상태가 유지되기 때문!
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}