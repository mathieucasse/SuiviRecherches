package ch.matfly.suivirecherches.service;

import ch.matfly.suivirecherches.dao.UserRepo;
import ch.matfly.suivirecherches.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired private UserRepo userRepo;

    private User user;

    public User createUser(String email){
        this.user = new User();
        user.setEmail(email);
        return userRepo.saveAndFlush(user);
    }

    public User createUser(User user){
        this.user = user;
        return userRepo.saveAndFlush(user);
    }

    public User getUser(){
        return this.user;
    }

    public User getUserByEmail(String email) {
        this.user = userRepo.findOneByEmail(email);
        if(null == user){
            throw new UsernameNotFoundException(String.format("The useremail %s doesn't exist", email));
        }
        return this.user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        this.user = userRepo.findOneByEmail(email);
        if(null == user){
            throw new UsernameNotFoundException(String.format("The useremail %s doesn't exist", email));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), authorities);
    }

}
