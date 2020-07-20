package com.application.dto;

import com.application.data.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExactSplitDto extends SplitDto {
    double amount;

    public ExactSplitDto(String user, double amount) {
        super(user);
        this.amount = amount;
    }
}
