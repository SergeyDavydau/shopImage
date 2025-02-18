package com.shop.shopimage.service;

import com.shop.shopimage.model.Role;
import com.shop.shopimage.model.User;
import com.shop.shopimage.repo.RoleRepo;
import com.shop.shopimage.repo.UserRepo;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepo.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

//        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        user.setRoles(Collections.singleton(new Role( 1L, "ROLE_ADMIN")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepo.findById(userId).isPresent()) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    @EventListener
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public   void setDefaultAdmin(ContextRefreshedEvent event) {
        User user = new User();
//        user.setId(1L);
        user.setUsername("admin");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        user.setRoles(Collections.singleton(role));
        userRepo.save(user);

        User saveuser = userRepo.findByUsername("admin");
        System.out.printf("saveuser = " + saveuser.getUsername());
    }
}