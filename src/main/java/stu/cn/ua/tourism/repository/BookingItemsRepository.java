package stu.cn.ua.tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stu.cn.ua.tourism.models.BookingItems;

@Repository
public interface BookingItemsRepository extends JpaRepository<BookingItems, Integer> {
}
