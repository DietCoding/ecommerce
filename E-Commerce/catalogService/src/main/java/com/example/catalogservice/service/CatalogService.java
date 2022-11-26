package com.example.catalogservice.service;

import com.example.catalogservice.dto.CatalogDto;
import com.example.catalogservice.jpa.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs(); //반복적인(Iterable) 데이터에서. DB에서 바로 가져올꺼기 때문에.  UserEntity 가공할꺼면 UserDto로 쓸수 있따.

    CatalogEntity findByCatalogId(String catalogId);
};

