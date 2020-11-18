package week4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShowName {
    
    public static void main(String[] args) {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student123 = (Student) context.getBean("student001");
        System.out.println(student123.toString());

    
        Klass class1 = context.getBean(Klass.class);
        System.out.println(class1.toString());

        School school=(School)context.getBean("school001");
        System.out.println(school.toString());

        System.out.println("   context.getBeanDefinitionNames() ===>> "+ String.join(",", context.getBeanDefinitionNames()));
        

    }
}
