package ru.kilg.wb.repositories.auth;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:test-dataset.xml")
public class AuthGroupRepositoryDBTest {

    @Autowired
    AuthGroupRepository authGroupRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUsername() {

        User user = userRepository.findByUsername("admin");

        List<AuthGroup> authGroups = authGroupRepository.findByUser(user);
        assertThat(authGroups, hasSize(2));

        User verifyUser = new User();
        verifyUser.setId(1L);
        verifyUser.setUsername("admin");
        verifyUser.setPassword("admin");

//        User user2 = new User();
//        user2.setId(1);
//        user2.setUsername("user");
//        user2.setPassword("user");

        AuthGroup authGroup = new AuthGroup();
        authGroup.setId(1);
        authGroup.setUser(verifyUser);
        authGroup.setAuthGroup("ADMIN");

        AuthGroup authGroup2 = new AuthGroup();
        authGroup2.setId(2);
        authGroup2.setUser(verifyUser);
        authGroup2.setAuthGroup("USER");
        assertThat(authGroups, containsInAnyOrder(authGroup, authGroup2));
    }
}