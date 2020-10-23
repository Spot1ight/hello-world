package org.example.helloWorld.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hello {

    private Long id;
    private String fullName;
    private String position;
    private String note1;
    private String note2;
    private String note3;




}
