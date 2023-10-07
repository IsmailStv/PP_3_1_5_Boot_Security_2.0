package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;

@Component
public class Init {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Init(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void doInit() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        User user = new User();
        user.setAge(22);
        user.setEmail("admin@mail.com");
        user.setFirstName("renya");
        user.setLastName("krokodilov");
        user.setPassword(passwordEncoder.encode("adminasdfggfg"));
        user.getRoles().add(roleRepository.findRoleByRole("ROLE_ADMIN"));
        userRepository.save(user);

        user = new User();
        user.setAge(33);
        user.setEmail("user@mail.com");
        user.setFirstName("ez");
        user.setLastName("strelkov");
        user.setPassword(passwordEncoder.encode("userasdfasdf"));
        user.getRoles().add(roleRepository.findRoleByRole("ROLE_USER"));
        userRepository.save(user);
    }
}
