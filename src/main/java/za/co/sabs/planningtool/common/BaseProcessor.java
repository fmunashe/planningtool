package za.co.sabs.planningtool.common;


import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;

public interface BaseProcessor<T, S> {

    ApiResponse<T> findAll(Integer pageNo, Integer pageSize);

    ApiResponse<T> findById(Long id);

    ApiResponse<T> save(S s);

    ApiResponse<T> update(Long id, T t);

    ApiResponse<T> deleteById(Long id);
}
