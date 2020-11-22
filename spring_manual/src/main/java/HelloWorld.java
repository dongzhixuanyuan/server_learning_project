import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author liudong (735106520@qq.com)
 * @description 文件描述
 * @date 2020/11/8 4:26 下午
 */
public class HelloWorld implements InitializingBean, DisposableBean {
    private String message1;

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    private String message2;

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public void getMessage() {
        System.out.println("Your Message : " + message1);
    }

    public void initMethod() {
        System.out.println("HelloWorld initMethod");
    }

    public void destroyMethod() {
        System.out.println("HelloWorld destroyMethod");

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("HelloWorld generate");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("HelloWorld destroy");
    }

}
