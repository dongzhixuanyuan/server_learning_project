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


//        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
//        obj.getMessage();
//        obj.setMessage1("hello world 2");
//
//        obj = ((HelloWorld) context.getBean("helloWorld"));
//        obj.getMessage();
//        context.getBean("goodBoy");

        TextEditor editor = ((TextEditor) context.getBean("textEditor"));
        editor.spellCheck();
        Student student = ((Student) context.getBean("student"));
        student.speak();
        student.write();
        JavaCollection collection = ((JavaCollection) context.getBean("javaCollection"));
        System.out.println(collection.getAddressList());
        System.out.println(collection.getAddressMap());
        System.out.println(collection.getAddressProp());
        System.out.println(collection.getAddressSet());
        context.registerShutdownHook();


    }

}
