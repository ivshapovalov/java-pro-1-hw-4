package edu.t1.javapro1.hw4.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import edu.t1.javapro1.hw4.dto.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse<?> handleException(Exception ex) {
        log.error("Непредвиденное исключение: " + ex.getMessage(), ex);

        return CommonResponse.builder()
                .id(UUID.randomUUID())
                .errorMessage("Непредвиденное исключение: " + ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        List<ValidationError> validationErrors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(error -> {
                    return ValidationError.builder()
                            .field(error.getField())
                            .message(error.getDefaultMessage())
                            .build();
                })
                .toList();

        log.warn("Ошибка валидации: {}", validationErrors, ex);

        return CommonResponse.builder()
                .id(UUID.randomUUID())
                .errorMessage("Ошибка валидации")
                .validationErrors(validationErrors)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public CommonResponse<?> handleConstraintViolation(
            HandlerMethodValidationException ex) {

        List<ValidationError> validationErrors = ex.getParameterValidationResults().stream()
                .flatMap((parameter) -> {
                    String paramName = parameter.getMethodParameter().getParameterName();
                    return parameter.getResolvableErrors().stream()
                            .map(error -> ValidationError.builder()
                                    .field(paramName)
                                    .message(error.getDefaultMessage())
                                    .build());
                }).toList();

        log.warn("Ошибка валидации: {}", validationErrors, ex);

        return CommonResponse.builder()
                .id(UUID.randomUUID())
                .errorMessage("Ошибка валидации")
                .validationErrors(validationErrors)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResponse<?> handleConstraintViolation(
            HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException ife) {
            String path = ife.getPath().stream()
                    .map(reference -> (reference.getFieldName() == null) ? String.valueOf(reference.getIndex()) : reference.getFieldName())
                    .collect(Collectors.joining("."));

            String message = "Ошибка валидации, указан некорректный формат поля '%s'".formatted(path);

            log.warn(message, ex);

            return CommonResponse.builder()
                    .id(UUID.randomUUID())
                    .errorMessage(message)
                    .build();
        }

        return handleException(ex);
    }
}
