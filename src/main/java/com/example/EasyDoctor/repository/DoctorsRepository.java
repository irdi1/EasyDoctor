package com.example.EasyDoctor.repository;

import com.example.EasyDoctor.model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, Long> {

    @Query(value = "SELECT p FROM Doctors p WHERE p.specialization LIKE %:keyword%")
   public List<Doctors> findAll(String keyword);


}
