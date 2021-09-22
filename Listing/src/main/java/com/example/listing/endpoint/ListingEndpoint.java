package com.example.listing.endpoint;

import com.example.listing.model.Listing;
import com.example.listing.repasitory.ListingRepasitory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ListingEndpoint {

    @Autowired
    private ListingRepasitory listingRepasitory;

    @GetMapping("/listing")
    public List<Listing> listings(){
        return listingRepasitory.findAll();
    }

    @GetMapping("listing/{id}")
    public ResponseEntity<Listing> listingResponseEntity(@PathVariable("id") int id){
        Optional<Listing> listing = listingRepasitory.findById(id);
        if (listing.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listing.get());
    }
    
    @PostMapping("/listing")
    public Listing listing(@RequestBody Listing listing){
        return listingRepasitory.save(listing);
    }

    @PutMapping("/listing/{id}")
    public ResponseEntity<Listing> listingResponseEntity(@PathVariable("id") int id, @RequestBody Listing listing){
        Optional<Listing> listing1 = listingRepasitory.findById(id);
        if (listing1.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Listing listing2 = listing1.get();
        listing2.setCategory(listing.getCategory());
        listing2.setDescription(listing.getDescription());
        listing2.setTitle(listing.getTitle());
        listing2.setPrice(listing.getPrice());
        listing2.setCategoryId(listing.getCategoryId());
        listing2.setUserId(listing.getUserId());

        return ResponseEntity.ok().body(listingRepasitory.save(listing));
    }

    @DeleteMapping("/listing/{id}")
    public ResponseEntity<Listing> deleteById(@PathVariable("id") int id){
        Optional<Listing> listing = listingRepasitory.findById(id);
        if (listing.isEmpty()){
            ResponseEntity.notFound().build();
        }
        listingRepasitory.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
