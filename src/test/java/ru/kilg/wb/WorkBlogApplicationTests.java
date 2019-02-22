package ru.kilg.wb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkBlogApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void applicationContextTest() {
        WorkBlogApplication.main(new String[] {});
    }
}
