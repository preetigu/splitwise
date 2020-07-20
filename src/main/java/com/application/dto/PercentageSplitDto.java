package com.application.dto;

import com.application.data.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PercentageSplitDto extends SplitDto {
    private double percentage;

    public PercentageSplitDto(String user, double percentage) {
        super(user);
        this.percentage = percentage;
    }
}
