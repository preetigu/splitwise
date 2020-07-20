package com.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SplitDto {
    public SplitDto(String paidTo) {
        this.paidTo = paidTo;
    }

    private String paidTo;

    private double amount;
    private double percentage;
}
