package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
//        -> ApplicationContext를 닫아야 하는데 ApplicationContext에서는 제공하지 않는다.
//        그래서 ConfigurableApplicationContext 사용
//        ConfigurableApplicationContext가 AnnotationConfigApplicationContext보다 상위이기 때문에 부모는 자식을 담을 수 있다.
//        ConfigurableApplicationContext은 ApplicationContext를 상속 받아 구현함.

    }
    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // 첫 생성자 호출할 때는 당연히 url은 null이 맞음
            networkClient.setUrl("http://www.hello-spring.dev");
            return networkClient;
        }
    }
}