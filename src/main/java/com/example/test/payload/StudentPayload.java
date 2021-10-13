package com.example.test.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentPayload {
    private String fullname;
    private String groupName;
    private String teacherName;
    private String password;
    private String username;
}
