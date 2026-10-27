package com.tripplanner.dev.config;

import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tripplanner.dev.domain.User;
import com.tripplanner.dev.domain.UserRepository;

@Service 
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User curuser = repository.findByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("User not found: " + username));
       
        return new org.springframework.security.core.userdetails.User(
            curuser.getUsername(),
            curuser.getPasswordHash(),
            AuthorityUtils.createAuthorityList(curuser.getRole()));
    }
}
