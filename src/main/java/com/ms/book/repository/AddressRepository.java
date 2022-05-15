package com.ms.book.repository;

import com.ms.book.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressModel, UUID> {
    Optional<AddressModel> findById(UUID uuid);
}
