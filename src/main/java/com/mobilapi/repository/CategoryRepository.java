package com.mobilapi.repository;

import com.mobilapi.domain.category.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByGuid(String guid);

}
