package ru.kilg.wb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kilg.wb.services.RetService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetServiceTest {

    @Autowired
    RetService retService;

    @Test
    public void getRet() {
        String ret = retService.getRet();
        assertEquals("Return", ret);
    }
}