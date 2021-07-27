package hello.core.beanfind;
/**
 * 스프링 컨테이너를 이용하여 저장된 빈을 조회하는 기본 로직
 */

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


class ApplicationContextInfoTest {
    //스프링 컨테이너 가져오기
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name=" + beanDefinitionName + " object=" +
                    bean);
        }
    }


    //스프링이 내부에서 사용하는 빈은 제외하고, 내가 등록한 빈만 출력해보자.
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        //iter:: 리스트 반복문 자동생성
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =
                    ac.getBeanDefinition(beanDefinitionName);

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) { //만약 해당 빈이 직접 등록한 빈이라면
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name=" + beanDefinitionName + " object=" +
                        bean);
            }
        }
    }
}