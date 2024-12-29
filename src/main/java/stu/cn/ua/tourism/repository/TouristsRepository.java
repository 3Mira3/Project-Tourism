package stu.cn.ua.tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stu.cn.ua.tourism.models.Tourists;

@Repository
public interface TouristsRepository extends JpaRepository<Tourists, Integer> {
}
