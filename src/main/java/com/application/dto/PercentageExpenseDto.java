package com.application.dto;

import java.util.List;

public class PercentageExpenseDto extends ExpenseDto {
    public PercentageExpenseDto(double amount, String paidBy, List<SplitDto> splits) {
        super(amount, paidBy, splits, ExpenseType.PERCENT);
    }

    @Override
    public boolean validate() {
//        for (SplitDto split : getSplits()) {
//            if (!(split instanceof PercentageSplitDto)) {
//                return false;
//            }
//        }

        double totalPercent = 100;
        double sumSplitPercent = 0;
        for (SplitDto split : getSplits()) {
            //PercentageSplitDto exactSplit = (PercentageSplitDto) split;
            PercentageSplitDto exactSplit = new PercentageSplitDto(split.getPaidTo(), split.getPercentage());

            sumSplitPercent += exactSplit.getPercentage();
        }

        if (totalPercent != sumSplitPercent) {
            return false;
        }

        return true;
    }
}
