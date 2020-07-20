package com.application.service;

import com.application.data.model.Expense;
import com.application.data.model.Split;
import com.application.data.model.User;
import com.application.data.repository.ExpenseRepo;
import com.application.data.repository.SplitRepo;
import com.application.data.repository.UserRepo;
import com.application.dto.EqualExpenseDto;
import com.application.dto.ExactExpenseDto;
import com.application.dto.ExpenseDto;
import com.application.dto.ExpenseType;
import com.application.dto.PercentageExpenseDto;
import com.application.dto.PercentageSplitDto;
import com.application.dto.SplitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SplitRepo splitRepo;

    public String addExpense(ExpenseDto expenseDto) {
        try {
            ExpenseType expenseType = expenseDto.getExpenseType();
            double amount = expenseDto.getAmount();
            String paidBy = expenseDto.getPaidBy();
            List<SplitDto> splits = expenseDto.getSplits();
            // TODO: Add validation for input params;

            ExpenseDto expenseDto1 = expenseDto;

            System.out.println(expenseType);
            switch (expenseType) {
                case EXACT:
                    expenseDto1 = new ExactExpenseDto(amount, paidBy, splits);
                    break;
                case PERCENT:
                    for (SplitDto split : splits) {
                        //PercentageSplitDto percentSplit = (PercentageSplitDto) split;
                        PercentageSplitDto percentSplit = new PercentageSplitDto(split.getPaidTo(), split.getPercentage());
                        split.setAmount((amount * percentSplit.getPercentage()) / 100.0);
                    }
                    expenseDto1 = new PercentageExpenseDto(amount, paidBy, splits);
                    break;
                case EQUAL:
                    int totalSplits = splits.size();
                    double splitAmount = ((double) Math.round(amount * 100 / totalSplits)) / 100.0;
                    for (SplitDto split : splits) {
                        split.setAmount(splitAmount);
                    }
                    splits.get(0).setAmount(splitAmount + (amount - splitAmount * totalSplits));
                    expenseDto1 = new EqualExpenseDto(amount, paidBy, splits);
                    System.out.println(expenseDto1);
                    break;
                default:
                    break;
            }
            if(!expenseDto1.validate()) {
                return "The input is not a valid one, please check the input";
            }
            Expense expense = createModelExpense(expenseDto1);
            System.out.println(expense.toString());
            expenseRepo.save(expense);
            } catch(Exception e){
                e.printStackTrace();
                return "Failed";
            }
        return "Success";
    }

    private Expense createModelExpense(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setAmount(expenseDto.getAmount());
        expense.setExpenseType(expenseDto.getExpenseType());
        User paidBy = userRepo.getUserByPhone(expenseDto.getPaidBy());
        expense.setPaidBy(paidBy);
        expense.setSplits(createModelSplits(expenseDto.getSplits(), paidBy));
        return expense;
    }

    private List<Split> createModelSplits(List<SplitDto> splitsList, User paidBy) {
        return splitsList.stream().map(splitDto -> {
            Split split = new Split();
            split.setAmount(splitDto.getAmount());
            split.setPaidBy(paidBy);
            split.setPaidTo(userRepo.getUserByPhone(splitDto.getPaidTo()));
            return split;
        }).collect(Collectors.toList());
    }

    public Map<String, Double> getExpense(String phone) {
        User user = userRepo.getUserByPhone(phone);
        List<Expense> expenses = expenseRepo.findByPaidBy(user);
        Map<String, Double> balances = new HashMap<>();
        for(Expense expense : expenses) {
            List<Split> splits = expense.getSplits();
            for (Split split : splits) {
                String paidTo = split.getPaidTo().getPhone();
                if (!balances.containsKey(paidTo)) {
                    balances.put(paidTo, 0.0);
                }
                balances.put(paidTo, balances.get(paidTo) + split.getAmount());
            }
        }
        List<Split> splits = splitRepo.findByPaidTo(user);
        splits.forEach(split -> {
            String paidBy = split.getPaidBy().getPhone();
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0.0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        });

        return balances;
    }
}