package ru.antoncharov.messenger.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private String name;
}
