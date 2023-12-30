package com.glsi.xpress.Repository;

import com.glsi.xpress.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Book, Long>{
}
