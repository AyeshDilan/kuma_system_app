package edu.iset.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    String firstName;
    String lastName;
    String shopName;
    String idNumber;
    String address;
    String contact1;
    String contact2;
    String email;
    String identificationCode;
}
