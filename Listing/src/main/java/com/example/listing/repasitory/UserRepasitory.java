package com.example.listing.repasitory;

import com.example.listing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepasitory extends JpaRepository<User, Integer> {
}
