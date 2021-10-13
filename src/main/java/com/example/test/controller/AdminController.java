package com.example.test.controller;

import com.example.test.payload.StudentPayload;
import com.example.test.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
  private final StudentService studentService;

  @PostMapping("/save-student")
  public ResponseEntity<?> saveStudent(@RequestBody StudentPayload studentPayload) {
    return studentService.saveStudent(studentPayload) ? ResponseEntity.ok("saqlandi")
        : new ResponseEntity("Xatolik", HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/get-student")
  public ResponseEntity<?> getStudents() {
    return ResponseEntity.ok(studentService.getAll());
  }

  @PutMapping("/edit-student/{id}")
  public ResponseEntity<?> editStudent(@PathVariable UUID id, @RequestBody StudentPayload studentPayload) {
    return studentService.editStudent(id, studentPayload) ? ResponseEntity.ok("edited success")
        : new ResponseEntity("error", HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/delete-student/{id}")
  public int deleteStudent(@PathVariable UUID id) {
    return studentService.delete(id) ? 200 : 409;
  }

}
