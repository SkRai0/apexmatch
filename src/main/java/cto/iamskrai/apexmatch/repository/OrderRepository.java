package cto.iamskrai.apexmatch.repository;

import cto.iamskrai.apexmatch.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
