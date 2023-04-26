package hello.core.sigleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefullServiceTest {

    @Test
    void statefullServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefullService statefullService1 = ac.getBean("statefullService",StatefullService.class);
        StatefullService statefullService2 = ac.getBean("statefullService",StatefullService.class);

        statefullService1.order("UserA",1000);
        statefullService2.order("UserB",2000);

        int price = statefullService1.getPrice();
        System.out.println("price = " + price);
        // statefullService1 ,statefullService2 싱글톤이라서 참조값이 같다 따라서 price = 2000
        // 공유 필드는 항상 무상태로 설계해야한다!

        Assertions.assertThat(price).isEqualTo(2000);

    }

    static class TestConfig{
        @Bean
        public StatefullService statefullService(){
            return new StatefullService();
        }

    }

}