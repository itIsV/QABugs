package com.example.qabugs.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter @Getter
@AllArgsConstructor
public class TestCaseDto {

    @JsonProperty("id_case")
    private Long idCase;
    private String description;
    private Boolean tested;
    private Boolean passed;
    private Integer number_of_tries;
    private String last_update;

}
