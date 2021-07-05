package com.mercateo.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
@AllArgsConstructor
public class Item {
    private Integer itemNo;
    private BigDecimal itemWeight;
    private Integer itemCost;
}
