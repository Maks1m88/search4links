package ru.same.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.same.entities.Source;

import java.util.List;

public interface SourceRepository extends CrudRepository<Source, Long> {
    Iterable<Source> findAll(Pageable pageable);
}
