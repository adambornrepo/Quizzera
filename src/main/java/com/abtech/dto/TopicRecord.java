package com.abtech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicRecord(
        @Size(max = 50, message = "Topic name must be max {max} characters")
        @NotBlank(message = "Topic name cannot be blank")
        String name,
        Boolean inUse
) {
}
