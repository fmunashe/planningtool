package za.co.sabs.planningtool.utils;

import lombok.Data;

@Data
public class PageableHelper {

    public static int cleanPageNumber(int pageNumber) {
        if (pageNumber <= 0) {
            pageNumber = Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER);
        }
        return pageNumber;
    }

    public static  int cleanPageSize(int pageSize) {
        if (pageSize <= 0) {
            pageSize = Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE);
        }
        return pageSize;
    }
}
