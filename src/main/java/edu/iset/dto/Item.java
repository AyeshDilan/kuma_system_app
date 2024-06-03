package edu.iset.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    Long id;
    String itemName;
    String itemCode;
    String category;
    String supplierName;
    String unit;
    Integer stock;
    Double cost;

}
