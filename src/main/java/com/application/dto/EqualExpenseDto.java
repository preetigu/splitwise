package com.application.dto;

import com.application.data.model.User;

import java.util.List;

public class EqualExpenseDto extends ExpenseDto {

    public EqualExpenseDto(double amount, String paidBy, List<SplitDto> splits) {
        super(amount, paidBy, splits, ExpenseType.EQUAL);
    }
    @Override
    public boolean validate() {
//        for (SplitDto split : splits) {
//            if (!(split instanceof EqualSplitDto)) {
//                return false;
//            }
//        }
        return true;
    }
}
