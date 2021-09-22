package com.example.listing.endpoint;

import com.example.listing.model.Category;
import com.example.listing.repasitory.CategoryRepasitory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryEndpoint {
    @Autowired
    private CategoryRepasitory categoryRepasitory;

    @GetMapping("/category")
    public List<Category> categories(){
        return categoryRepasitory.findAll();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") int id){
        Optional<Category> category = categoryRepasitory.findById(id);
        if (category.isEmpty()){
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity.ok(category.get());
    }
    @PostMapping("/category")
    public Category category(@RequestBody Category category){
        return categoryRepasitory.save(category);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> categoryResponseEntity(@PathVariable("id") int id, @RequestBody Category category){
        Optional<Category> category1 = categoryRepasitory.findById(id);
        if (category1.isEmpty()){
            return ResponseEntity
                    .notFound()
                    .build();
        }
        Category category2 = category1.get();
        category2.setName(category.getName());

        return ResponseEntity.ok().body(categoryRepasitory.save(category2));

    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable("id") int id){
        Optional<Category> category = categoryRepasitory.findById(id);
        if (category.isEmpty()){
            return ResponseEntity
                    .notFound()
                    .build();
        }
        categoryRepasitory.deleteById(id);
        return ResponseEntity
                .notFound()
                .build();

    }
}
