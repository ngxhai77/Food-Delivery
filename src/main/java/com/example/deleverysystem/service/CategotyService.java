package com.example.deleverysystem.service;

import com.example.deleverysystem.repository.CategoryRepository;
import com.example.deleverysystem.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategotyService {

    public final CategoryRepository categotyRepository;

    public CategotyService(CategoryRepository categotyRepository) {
        this.categotyRepository = categotyRepository;
    }

    public List<Category> findAll(){
        return categotyRepository.findAll();
    }

}
