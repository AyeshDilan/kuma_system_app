package edu.iset.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {
    private Long invoiceForApprovalId;
    private String itemName;
    private String units;
    private String invoiceNumber;
    private Double  cost;
    private Double margin;
    private Double sellingPrice;
    private Double quantity;
    private Double inStock;
    private Double itemsValue;
    private String currentDate;
    private String selectedRef;
    private String selectedCustomer;
    private String paymentType;
    private String selectedRemarksType;
    private String selectedAssignTo;
    private String selectedAssignName;
    private String lorryNumber;
    private Double taxTotal;
    private Double discount;
    private Double netTotalOfInvoice;

}
