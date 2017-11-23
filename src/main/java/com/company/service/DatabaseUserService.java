package com.company.service;

import com.company.model.User;
import com.company.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("databaseUserService")
public class DatabaseUserService implements UserDetailsService{

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUserService.class);

    private UserRepository userRepository;

    @Autowired
    public DatabaseUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findOneByUsername(username);

        if(user == null){
            LOGGER.info(String.format("Username %s not found !", username));
            throw new UsernameNotFoundException(String.format("Username %s not found !", username));
        }

        LOGGER.info(String.format("Username %s found in database !", username));
        return user;
    }
}
