package edu.iset.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CheckedInvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
