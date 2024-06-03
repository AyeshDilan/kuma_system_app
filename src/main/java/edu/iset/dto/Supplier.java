package edu.iset.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {
    String nameOfCompany;
    String location;
    String contactNumber;
    String email;


}
