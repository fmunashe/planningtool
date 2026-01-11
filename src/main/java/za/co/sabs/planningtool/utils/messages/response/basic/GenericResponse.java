package za.co.sabs.planningtool.utils.messages.response.basic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse<T> {
    private int statusCode;
    private String message;
    private boolean success;
    private T responseDTO;
    private List<T> responseDTOS;
}
