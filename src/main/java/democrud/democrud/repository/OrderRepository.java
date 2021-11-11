package democrud.democrud.repository;

import democrud.democrud.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findOrderById(Long id);

    @Transactional
    void deleteOrderById(Long id);
}
