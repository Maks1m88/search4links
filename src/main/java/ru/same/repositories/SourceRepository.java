package ru.same.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.same.entities.Source;

public interface SourceRepository extends CrudRepository<Source, Long> {
}
