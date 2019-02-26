package ru.kilg.wb.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerIntTest {


    @Autowired
    private MainController mainController;

    @Spy
    Model model;

    @Test
    public void getUserPage() {
        String template = mainController.getUserPage();
        assertThat(template, is(equalTo("user")));
    }

    @Test
    public void getLoginPage() {
        String template = mainController.getLoginPage(model);
        assertThat(template, is(equalTo("login")));

    }

    @Test
    public void getLogoutPage() {
        String template = mainController.getLogoutPage(model);
        assertThat(template, is(equalTo("logout")));

    }

    @Test
    public void getIndexPage() {
        String template = mainController.getIndexPage(model);
        verify(model, times(1)).addAttribute(eq("title"), any());
        verify(model, times(1)).addAttribute(eq("usercomm"), any());
        assertThat(template, is(equalTo("index")));
    }
}