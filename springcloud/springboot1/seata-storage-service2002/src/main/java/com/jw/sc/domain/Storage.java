package com.jw.sc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    private Long id;
    private Long productId;
    private Integer total;
    private Integer residue;
    private Integer used;
}
