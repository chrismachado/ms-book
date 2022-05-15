package com.ms.book.repository;

import com.ms.book.model.AuthorModel;
import com.ms.book.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {
    Optional<AuthorModel> findById(UUID id);
}
