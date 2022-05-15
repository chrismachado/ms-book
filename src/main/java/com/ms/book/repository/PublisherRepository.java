package com.ms.book.repository;

import com.ms.book.model.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublisherModel, UUID> {

    Optional<PublisherModel> findById(UUID uuid);
}
