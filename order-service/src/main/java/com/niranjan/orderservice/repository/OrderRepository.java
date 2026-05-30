package com.niranjan.orderservice.repository;

import com.niranjan.orderservice.entity.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderUser, Long> {
}