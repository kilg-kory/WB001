package ru.kilg.wb.services.auth;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;
import ru.kilg.wb.repositories.auth.AuthGroupRepository;
import ru.kilg.wb.repositories.auth.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserDetailsServiceImplUnitTest {

    private static final String STUB_USERNAME = "Sample";
    private static final String STUB_ROLE = "SAMPLE";

    @Mock
    UserRepository userRepository;

    @Mock
    AuthGroupRepository authGroupRepository;

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setUsername("Sample");

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(userRepository.findByUsername(eq("NoUser"))).thenReturn(null);

        List<AuthGroup> authGroups = new ArrayList<>();
        AuthGroup authGroup = new AuthGroup();
        authGroup.setUser(user);
        authGroup.setAuthGroup(STUB_ROLE);
        authGroups.add(authGroup);

        when(authGroupRepository.findByUser(any(User.class))).thenReturn(authGroups);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameThrowableIfNull()  {
        userDetailsService.loadUserByUsername("NoUser");

    }

    @Test
    public void loadUserByUsername() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("Sample");

        verify(authGroupRepository, times(1)).findByUser(any(User.class));
        verify(userRepository, times(1)).findByUsername(anyString());

        assertThat(userDetails.getUsername(), is(equalTo(STUB_USERNAME)));
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(STUB_ROLE)));

    }
}