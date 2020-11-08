import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;


class HelloProcessor implements BeanPostProcessor, Ordered {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeforeInitialization : " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AfterInitialization : " + beanName);
        return bean;
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
