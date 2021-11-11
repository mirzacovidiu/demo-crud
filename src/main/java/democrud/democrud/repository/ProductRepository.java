package democrud.democrud.repository;

import democrud.democrud.enums.CategoryType;
import democrud.democrud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
        Optional<Product> findProductById(Long id);
        List<Product> findProductsByCategoryType(CategoryType categoryType);


        @Transactional
        void deleteProductById(Long id);
}
