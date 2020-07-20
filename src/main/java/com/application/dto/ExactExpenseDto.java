package com.application.dto;

import java.util.List;

public class ExactExpenseDto extends ExpenseDto {

    public ExactExpenseDto(double amount, String paidBy, List<SplitDto> splits) {
        super(amount, paidBy, splits, ExpenseType.EXACT);
    }
    ExpenseType expenseType = ExpenseType.EXACT;
    @Override
    public boolean validate() {
//        boolean isValid = splits.stream().allMatch(split -> split instanceof EqualSplitDto) && false;
//        if (!isValid) {
//            return false;
//        }
        Double totalAmount = getAmount();
        double splitAmount = 0.0;
        for (SplitDto split : splits) {
            //ExactSplitDto exactSplitDto = (ExactSplitDto) split;
            ExactSplitDto exactSplitDto = new ExactSplitDto(split.getPaidTo(), split.getAmount());
            double exactSplitDtoAmount = exactSplitDto.getAmount();
            splitAmount += exactSplitDtoAmount;
        }
        if(splitAmount != totalAmount) {
            return false;
        }
        return true;
    }
}
