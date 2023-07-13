package com.jw.sc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;
    private Long userId;
    private BigDecimal total;
    private BigDecimal residue;
    private BigDecimal used;
}
