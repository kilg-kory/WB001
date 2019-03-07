package ru.kilg.wb.services.auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kilg.wb.domain.auth.AuthGroup;
import ru.kilg.wb.domain.auth.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserDetailsImplTest {


    private static final String STUB_PASSWORD = "12345";
    private static final String STUB_USERNAME = "Sample Samplovich";
    public static final String STUB_ROLE = "ADMIN";
    private static final String ROLE_PREFIX = "ROLE_";
    @Mock
    User user;

    @Mock
    List<AuthGroup> authGroups;

    private UserDetails userDetails;

    @Before
    public void setUp() throws Exception {
        userDetails = new UserDetailsImpl(user, authGroups);
        when(user.getPassword()).thenReturn(STUB_PASSWORD);
        when(user.getUsername()).thenReturn(STUB_USERNAME);
    }

    @Test
    public void getAuthorities() {
        userDetails = new UserDetailsImpl(user, null);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertThat(authorities, is(equalTo(Collections.EMPTY_LIST)));



        List<AuthGroup> authGroups = new ArrayList<>();
        AuthGroup authGroup = new AuthGroup();
        authGroup.setUser(user);
        authGroup.setAuthGroup(STUB_ROLE);
        authGroups.add(authGroup);

        userDetails = new UserDetailsImpl(user, authGroups);
        authorities = userDetails.getAuthorities();
        assertEquals(authorities.size(), 1);
        assertTrue(authorities.contains(new SimpleGrantedAuthority(ROLE_PREFIX + STUB_ROLE)));
    }

    @Test
    public void getPassword() {
        //it's just mock user. Real user from db in int-tests will have hex password.
        String password = userDetails.getPassword();
        assertThat(password, is(equalTo(STUB_PASSWORD)));
    }

    @Test
    public void getUsername() {
        String username = userDetails.getUsername();
        assertThat(username, is(equalTo(STUB_USERNAME)));
    }

    @Test
    public void isAccountNonExpired() {
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLocked() {
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    public void isCredentialsNonExpired() {
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    public void isEnabled() {
        assertTrue(userDetails.isEnabled());
    }
}