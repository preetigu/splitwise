package com.application.data.repository;

import com.application.data.model.Expense;
import com.application.data.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExpenseRepo extends CrudRepository<Expense, Integer> {
    List<Expense> findByPaidBy(User paidBy);
}
