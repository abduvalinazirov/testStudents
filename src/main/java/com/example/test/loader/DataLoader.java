package com.example.test.loader;



import com.example.test.entity.Admin;
import com.example.test.entity.Role;
import com.example.test.entity.template.User;
import com.example.test.repository.AdminRepository;
import com.example.test.repository.RoleRepository;
import com.example.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
        @Value("${spring.jpa.hibernate.ddl-auto}")
        private String init;

        private final RoleRepository roleRepository;
        private final AdminRepository adminRepository;
        private final PasswordEncoder passwordEncoder;
        private final UserRepository userRepository;

    @Override
    public void run(String... args) {


        try {
            if (init.equalsIgnoreCase("create"))
            {
                Role roleUser=new Role();
                roleUser.setId(1L);
                roleUser.setName("ROLE_USER");

                Role roleAdmin=new Role();
                roleAdmin.setId(2L);
                roleAdmin.setName("ROLE_ADMIN");

                Role roleStudent=new Role();
                roleStudent.setId(3L);
                roleStudent.setName("ROLE_STUDENT");

                List<Role> roleList=new ArrayList<>(Arrays.asList(roleUser,roleAdmin,roleStudent));
                roleList=roleRepository.saveAll(roleList);

                Admin admin=new Admin();
                User user=new User();

                user.setRoles(roleList);
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("1111"));
                user.setFullName("Abduvali Nazirov");
                userRepository.save(user);

                admin.setUser(user);

                admin.setEmail("admin@gmail.com");
                admin.setPhoneNumber("+998977777777");

                adminRepository.save(admin);
            }


        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
