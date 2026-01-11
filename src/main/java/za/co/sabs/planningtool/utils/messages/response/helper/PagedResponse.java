package za.co.sabs.planningtool.utils.messages.response.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> implements Serializable {
    private int statusCode;
    private String message;
    private boolean success;
    private boolean isPaged;
    private T responseDTO;
    private List<T> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private boolean next;
    private boolean previous;
}
