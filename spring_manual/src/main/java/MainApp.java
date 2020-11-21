import annotation.AutoWiredTest;
import annotation.Cat;
import annotation.Dog;
import db.StudentJDBCTemplate;
import event.CustomEventPublisher;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

//        TextEditor editor = ((TextEditor) context.getBean("textEditor"));
//        editor.spellCheck();
//        Student student = ((Student) context.getBean("student"));
//        student.speak();
//        student.write();
//        JavaCollection collection = ((JavaCollection) context.getBean("javaCollection"));
//        System.out.println(collection.getAddressList());
//        System.out.println(collection.getAddressMap());
//        System.out.println(collection.getAddressProp());
//        System.out.println(collection.getAddressSet());
//        context.registerShutdownHook();

        AutoWiredTest autowired = ((AutoWiredTest) context.getBean("autowired"));
        Dog dog = (Dog) context.getBean("dog");
        dog.testTalk();
        Cat cat = (Cat)context.getBean("cat");
        System.out.println(cat.getAge());
//        System.out.println(dog.getTalk());
        System.out.println(autowired.getAge());

        context.registerShutdownHook();

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(HelloWorldConfig.class);
        annotationConfigApplicationContext.register(ZoneIDFactoryBean.class);
        annotationConfigApplicationContext.refresh();
        annotationConfigApplicationContext.start();
//        annotation.Dog bean = annotationConfigApplicationContext.getBean(annotation.Dog.class);
        annotationConfigApplicationContext.getBean("bigDog");
        ZoneID bean1 = annotationConfigApplicationContext.getBean(ZoneID.class);
        System.out.println(bean1.toString());
//        Sxystem.out.println(bean);
        CustomEventPublisher bean = annotationConfigApplicationContext.getBean(CustomEventPublisher.class);
        bean.publish();
        bean.publish();

        StudentJDBCTemplate studentjdbctemplate = ((StudentJDBCTemplate) context.getBean("studentjdbctemplate"));
        studentjdbctemplate.create("张三",90);
        studentjdbctemplate.create("李斯",89);
        System.out.println(studentjdbctemplate.listStudents());





//        annotationConfigApplicationContext.stop();

    }

}
