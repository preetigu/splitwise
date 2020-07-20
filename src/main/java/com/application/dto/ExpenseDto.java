package com.application.dto;

import com.application.data.model.Expense;
import com.application.data.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {
    double amount;
    String paidBy;
    List<SplitDto> splits;
    ExpenseType expenseType;

    public boolean validate() {return true;};
}
