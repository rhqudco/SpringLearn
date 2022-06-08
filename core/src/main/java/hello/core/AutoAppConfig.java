package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(
        basePackages = "hello.core",
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// (excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))는 원래 AppConfig를 제외하기 위함
public class AutoAppConfig {

}
