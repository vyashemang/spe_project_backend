package com.example.spe_project;

import com.example.spe_project.model.Announcement;
import com.example.spe_project.model.Role;
import com.example.spe_project.model.RoleName;
import com.example.spe_project.model.User;
import com.example.spe_project.repository.RoleRepository;
import com.example.spe_project.repository.UserRepository;
import com.example.spe_project.service.AnnouncementService;


import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@Sql({"/data.sql"})
class SpeProjectApplicationTests {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository  roleRepository;

    @Autowired
    AnnouncementService announcementService;

    User user;

    @BeforeEach
    public void setUp() {
        user = new User("test1", "test1", "test_user@test.com",
                encoder.encode("test123"));

        Set<Role> roles = new HashSet<>();

        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));;

        roles.add(adminRole);
        user.setRoles(roles);

        userRepository.save(user);
    }

    @AfterEach
    public void deleteRes(){
        userRepository.delete(user);
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void validUsername(){
        String name = "test1";
        Assertions.assertEquals(true, userRepository.existsByUsername(name));
    }

    @Test
    public void invalidUsername(){
        String name = "test_user_invalid";
        Assertions.assertEquals(false,userRepository.existsByUsername(name));
    }

    @Test
    public void validPassword(){

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken("test1", "test123"));
            assert true;
        }
        catch (BadCredentialsException e){
            assert false;
        }

    }

    @Test
    public void invalidPassword(){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken("test1", "test12345"));
            assert false;
        }
        catch (BadCredentialsException e){
            assert true;
        }
    }

    @Test
    public void validSignup(){

        try{
            User user = new User("test1 user", "test1_user", "test1_user@test.com", encoder.encode("password"));
            Set<Role> roles = new HashSet<>();

            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

            user.setRoles(roles);
            userRepository.save(user);
            roles.add(userRole);

            assert true;
        }
        catch (RuntimeException e){
            assert false;
        }

    }

    @Test
    public void invalidSignup(){
        try{
            User user = new User("test1 user", "test1_user", "test1_user@test.com", encoder.encode("password"));
            Set<Role> roles = new HashSet<>();

            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

            assert true;
        }
        catch (RuntimeException e){
            assert false;
        }
    }



    Announcement announcement;

    @Test
    public void addAnnouncement(){
        announcement = new Announcement("test_announcement", "test_announcement_description", new Date());
        announcementService.addAnnouncement(announcement);
    }

    @Test
    public void getAnnouncements(){
        assert announcementService.getAnnouncements() != null;
    }

    @Test
    public void deleteAnnouncement(){
        announcementService.deleteAnnouncement(10L);
    }
}
