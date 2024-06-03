package edu.iset.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckedInvoice {
    private Long checkedInvoiceId;
    private Integer invoiceForApprovalId;
    private String invoiceNumber;
    private String invoicedDate;
    private String itemName;
    private String units;
    private Double quantity;
    private Double cost;
    private Double margin;
    private Double wholesalePrice;
    private Double subTotal;
    private String salesRef;
    private String customer;
    private String paymentType;
    private String remarks;
    private String lorryNumber;
    private Double taxTotal;
    private Double discount;
    private Double netTotal;
    private String approvedBy;
    private String approvedDate;
    private Boolean status;
}
