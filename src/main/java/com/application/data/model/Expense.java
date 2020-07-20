package com.application.data.model;

import com.application.dto.ExpenseType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    @NotNull
    User paidBy;

    double amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="expense_id")
    List<Split> splits;

    @Enumerated(EnumType.STRING)
    ExpenseType expenseType;
}
