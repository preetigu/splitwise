package com.application.controller;

import com.application.data.model.Expense;
import com.application.data.model.User;
import com.application.dto.ExpenseDto;
import com.application.service.ExpenseService;
import com.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping(path ="/expense")
    public String addExpense(@RequestBody ExpenseDto expenseDto) {
        return expenseService.addExpense(expenseDto);
    }

    @GetMapping(path ="/expense/{userId}")
    public Map<String, Double> getExpense(@PathVariable String userId) {
        return expenseService.getExpense(userId);
    }
}
