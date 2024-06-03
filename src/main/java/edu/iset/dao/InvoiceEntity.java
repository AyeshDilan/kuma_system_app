package edu.iset.dao;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceForApprovalId;

    private String itemName;
    private String units;
    private String invoiceNumber;
    private Double cost;
    private Double margin;
    private Double sellingPrice;
    private Double quantity;
    private Double inStock;
    private Double itemsValue;
    @Column(name = "`currentDate`")
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
