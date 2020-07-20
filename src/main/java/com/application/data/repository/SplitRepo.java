package com.application.data.repository;

import com.application.data.model.Split;
import com.application.data.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SplitRepo extends CrudRepository<Split, Integer> {
    List<Split> findByPaidTo(User paidTo);
}
