package com.pawan.websockets.services;

import com.pawan.websockets.dao.UserRepository;
import com.pawan.websockets.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        String pass=user.getPassword();
        if(user!=null){
            var springUser= org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                    .password(pass)
                    .roles(user.getRole())
                    .build();
            return springUser;
        }
        return null;
    }
}
