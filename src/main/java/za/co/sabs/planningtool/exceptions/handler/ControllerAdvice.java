package za.co.sabs.planningtool.exceptions.handler;

import io.swagger.v3.oas.annotations.Hidden;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import za.co.sabs.planningtool.exceptions.BusinessException;
import za.co.sabs.planningtool.exceptions.RecordExistsException;
import za.co.sabs.planningtool.exceptions.RecordNotFoundException;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.basic.GenericResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<@NonNull ApiResponse<String>> handleException(BusinessException exception) {
        ApiResponse<String> response = HelperResponse.buildApiResponse(null, null, false, 400, false, exception.getMessage(), null);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<@NonNull ApiResponse<String>> handleException(RecordNotFoundException exception) {
        ApiResponse<String> response = HelperResponse.buildApiResponse(null, null, false, 404, false, exception.getMessage(), null);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(RecordExistsException.class)
    public ResponseEntity<@NonNull ApiResponse<String>> handleException(RecordExistsException exception) {
        ApiResponse<String> response = HelperResponse.buildApiResponse(null, null, false, 400, false, exception.getMessage(), null);
        return ResponseEntity.ok(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NonNull GenericResponse<String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        GenericResponse<String> response = GenericResponse.<String>builder()
                .statusCode(400)
                .success(false)
                .message("Something went wrong")
                .responseDTOS(errors)
                .responseDTO(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
