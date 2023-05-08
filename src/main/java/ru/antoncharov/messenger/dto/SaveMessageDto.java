package ru.antoncharov.messenger.dto;

import java.util.List;

public record SaveMessageDto(List<String> recipients, String content) {
}
