package com.machula.repositories;

import com.machula.entities.ImageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, String> {
    List<ImageEntity> findAll();

    @Query(value = "select * from images i " +
            "where i.author like CONCAT('%',:searchTerm,'%') " +
            "or i.tags like CONCAT('%',:searchTerm,'%') " +
            "or i.camera like CONCAT('%',:searchTerm,'%')", nativeQuery = true)
    List<ImageEntity> findAllBySearchTerm(@Param("searchTerm") String searchTerm);

}
