package com.example.listing.repasitory;

import com.example.listing.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepasitory extends JpaRepository<Category, Integer> {
}
