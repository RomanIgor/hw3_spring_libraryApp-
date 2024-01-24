package org.example.hw3.repository;

import org.example.hw3.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepositorry extends JpaRepository<Reader,Long> {
}
