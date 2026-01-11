package za.co.sabs.planningtool.common;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> findAll();

    Page<T> findAll(Integer pageNo, Integer pageSize);

    Optional<T> findById(Long id);

    T save(T t);

    void deleteById(Long id);
}
