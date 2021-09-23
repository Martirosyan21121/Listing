package com.example.listing.repasitory;

import com.example.listing.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListingRepasitory extends JpaRepository<Listing, Integer> {

    Optional<Listing> findListingByCategoryId(int id);



}
