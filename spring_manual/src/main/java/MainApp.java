import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author liudong (liudong@rd.netease.com)
 * @description 文件描述
 * @date 2020/11/8 4:26 下午
 */
public class MainApp {

    public static void main(String[] args) {

        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("Beans.xml"));

//        String resource = MainApp.class.getClassLoader().getResource("Beans.xml").getPath();
//        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext(resource);

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");


        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
        obj.setMessage1("hello world 2");

        obj = ((HelloWorld) context.getBean("helloWorld"));
        obj.getMessage();
        context.getBean("goodBoy");


        context.registerShutdownHook();


    }

}
