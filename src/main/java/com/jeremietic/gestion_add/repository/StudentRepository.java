package com.jeremietic.gestion_add.repository;

import com.jeremietic.gestion_add.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository  extends JpaRepository<Student, Long> {


   Optional<Student> findByEmail(String email);
}
