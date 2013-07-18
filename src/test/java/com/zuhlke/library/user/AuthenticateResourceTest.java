package com.zuhlke.library.user;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.zuhlke.library.domain.User;
import com.zuhlke.library.domain.UserBuilder;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticateResourceTest {

    AuthenticateResource resource = new AuthenticateResource();
    
    @Test
    public void shouldAuthenticateUser() throws Exception {
        User user = resource.authenticate(new UserBuilder().id(1L).email("nmo@zuhlke.com").build());
        assertThat(user.getEmail(), is("nmo@zuhlke.com"));
    }
    

}

