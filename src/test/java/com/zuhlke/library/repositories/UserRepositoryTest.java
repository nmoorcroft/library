package com.zuhlke.library.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.zuhlke.library.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/data-context.xml")
public class UserRepositoryTest {

    @Inject UserRepository userRepository;
    @Inject TransactionTemplate transactionTemplate;
    
    @Test
    public void shouldUserFindByEmail() throws Exception {
        User user = transactionTemplate.execute(new TransactionCallback<User>() {
            @Override
            public User doInTransaction(TransactionStatus status) {
                return userRepository.findByEmail("nmo@zuhlke.com");
            }
        });
        
        assertNotNull(user);
        assertEquals("Neil M", user.getName());
        
    }
    
}
