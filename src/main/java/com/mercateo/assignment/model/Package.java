package com.mercateo.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class Package {
    private BigDecimal packageWeigthLimit;
    private List<Item> packageItems = new ArrayList<>();
}
