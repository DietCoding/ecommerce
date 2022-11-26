package com.example.catalogservice.service;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/* Service를 상속받은 ServiceImpl
   @Service 를 이용해 서비스 로직을 처리하는 것을 알려준다
   여기에서 비즈니스 로직 처리를 한다
   UserDto에 랜덤 ID를 추가하고, UserEntity로 변환한다
   이후 이를 DB에 저장하도록 Repository로 전달
   return 할 때는 UserDto로 값을 다시 변환해서 보내도록 한다
 */
@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {

    CatalogRepository catalogRepository;
    Environment env;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository,
                              Environment env){
        this.catalogRepository = catalogRepository;
        this.env = env;
    }
    @Override
    public Iterable<CatalogEntity> getAllCatalogs(){
        return catalogRepository.findAll(); //조건없이 전체 데이터 반환
    }

    @Override
    public CatalogEntity findByCatalogId(String catalogId){
        CatalogEntity catalogEntity = catalogRepository.findByProductId(catalogId);
        catalogEntity.setViews(catalogEntity.getViews() + 1);
        catalogRepository.save(catalogEntity);
        return catalogRepository.findByProductId(catalogId);
    }

}


