package com.example.test.service;

import com.example.test.entity.Student;
import com.example.test.entity.template.User;
import com.example.test.payload.StudentPayload;
import com.example.test.repository.RoleRepository;
import com.example.test.repository.StudentRepository;
import com.example.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final Logger logger= LoggerFactory.getLogger(StudentService.class);


    public boolean saveStudent(StudentPayload studentPayload)
    {
        Student student=new Student();
        student.setGroupName(studentPayload.getGroupName());
        student.setTeacherName(studentPayload.getTeacherName());

        User user=new User();
        user.setFullName(studentPayload.getFullname());
        user.setUsername(studentPayload.getUsername());
        user.setRoles(new ArrayList<>(Collections.singletonList(roleRepository.findByName("ROLE_STUDENT"))));
        user.setPassword(studentPayload.getPassword());
        userRepository.save(user);

        student.setUser(user);

        return studentRepository.save(student)!=null;
    }

    public List<Student> getAll()
    {
        return studentRepository.findAll();
    }

    public boolean editStudent(UUID id,StudentPayload studentPayload)
    {
        Student student=studentRepository.getById(id);
        student.setGroupName(studentPayload.getGroupName());
        student.setTeacherName(studentPayload.getTeacherName());

        User user=new User();
        user.setFullName(studentPayload.getFullname());
        user.setUsername(studentPayload.getUsername());
        user.setRoles(new ArrayList<>(Arrays.asList(roleRepository.findByName("ROLE_STUDENT"))));
        user.setPassword(studentPayload.getPassword());
        userRepository.save(user);

        student.setUser(user);

        return studentRepository.save(student)!=null;
    }

    public boolean delete(UUID id)
    {
        try {
            Student student=studentRepository.getById(id);
            studentRepository.delete(student);
            return true;
        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }

        return false;
    }
}
