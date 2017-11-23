package com.company.bootstrap;


import com.company.model.User;
import com.company.repository.UserRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SampleDataInitializer implements ApplicationContextAware {

    private UserRepository userRepository;

    @Autowired
    public SampleDataInitializer(UserRepository userRepository1) {
        this.userRepository = userRepository1;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        createSampleUser();
    }

    private void createSampleUser() {
        String username = "login";
        String password = "password";

        if (userRepository.findOneByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(new BCryptPasswordEncoder().encode(password));

            userRepository.saveAndFlush(user);
        }
    }
}
