package com.example.userservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface CatalogCountRepository extends CrudRepository<CatalogCount, Long> {
    CatalogCount findByCatalogId(int catalogId);
}
}
