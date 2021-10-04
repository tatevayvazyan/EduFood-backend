package am.mse.eduFood.repository;

import am.mse.eduFood.domain.Order;
import am.mse.eduFood.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to);
    List<Order> findByCreatedDateBetweenAndUser(LocalDateTime from, LocalDateTime to, User user);

}


