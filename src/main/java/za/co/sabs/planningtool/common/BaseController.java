package za.co.sabs.planningtool.common;

import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

public interface BaseController<T, R> {
    ResponseEntity<@NonNull ApiResponse<T>> getAll(
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    );

    ResponseEntity<@NonNull ApiResponse<T>> create(@RequestBody @Valid R payload);

    ResponseEntity<@NonNull ApiResponse<T>> update(@PathVariable("id") Long id, @RequestBody T payload);

    ResponseEntity<@NonNull ApiResponse<T>> findById(@PathVariable("id") Long id);

    ResponseEntity<@NonNull ApiResponse<T>> deleteById(@PathVariable("id") Long id);
}
