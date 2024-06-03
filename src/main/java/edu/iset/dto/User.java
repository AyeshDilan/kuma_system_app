package edu.iset.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    String firstName;
    String lastName;
    String position;
    String nicNumber;
    String email;
    String password;
    String contact;
}
