package testdemo.springcore1;

import com.jw.springcore.test1.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring5 {
    @Test
    public void testAdd(){
        //加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        //获取对象
        User user = context.getBean("user", User.class);

        user.add();
        System.out.println( user.getClass());
        System.out.println( user);


    }
}
