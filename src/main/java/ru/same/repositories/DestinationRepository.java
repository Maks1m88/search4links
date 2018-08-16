package ru.same.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.same.entities.Destination;

public interface DestinationRepository extends CrudRepository<Destination, String> {
}
