package edu.t1.javapro1.hw4.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.t1.javapro1.hw4.exception.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

    private UUID id;
    private T body;

    private String errorMessage;
    private List<ValidationError> validationErrors;
}
