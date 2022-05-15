package com.ms.book.repository;

import com.ms.book.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {
    Optional<BookModel> findById(UUID uuid);
    @Query(value = "select * from tb_book b where b.author_id = UNHEX(?1)", nativeQuery = true)
    List<BookModel> findByAuthorId(String uuid);

    //TODO: findByPublisherId(String uuid)

}
