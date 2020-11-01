package com.poc.search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "photoRepository")
public interface PhotoRepository extends JpaRepository<Photos, String> {

}