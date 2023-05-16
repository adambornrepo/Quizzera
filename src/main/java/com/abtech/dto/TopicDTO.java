package com.abtech.dto;

import com.abtech.domain.Topic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {

    private Integer id;

    @Size(max = 50, message = "Topic name must be max {max} characters")
    @NotBlank(message = "Topic name cannot be blank")
    private String name;

    private Boolean inUse;

    public TopicDTO(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
        this.inUse = topic.getInUse();
    }
}
