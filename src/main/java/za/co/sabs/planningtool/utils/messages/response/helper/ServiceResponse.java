package za.co.sabs.planningtool.utils.messages.response.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> implements Serializable {
    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ServiceResponse<T> buildSuccessResponse(String message){
        this.status = "SUCCESS";
        this.message = message;
        this.data = null;
        return this;
    }

    public ServiceResponse<T> buildSuccessResponse(String message, final T data){
        this.status = "SUCCESS";
        this.message = message;
        this.data = data;
        return this;
    }

    public ServiceResponse<T> buildFailedResponse(String message){
        this.status = "FAILED";
        this.message = message;
        this.data = null;
        return this;
    }

    public ServiceResponse<T> buildFailedResponse(String message, final T data){
        this.status = "FAILED";
        this.message = message;
        this.data = data;
        return this;
    }
}

