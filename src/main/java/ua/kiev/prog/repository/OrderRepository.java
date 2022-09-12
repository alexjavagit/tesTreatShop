package ua.kiev.prog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ua.kiev.prog.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllBy(Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.id LIKE %:searchId%")
    Page<Order> findByOrderId(String searchId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.firstName LIKE %:searchName%")
    Page<Order> findByName(String searchName, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.email LIKE %:searchEmail%")
    Page<Order> findByEmail(String searchEmail, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.id LIKE %:searchId% AND o.firstName LIKE %:searchName%")
    Page<Order> findByOrderIdAndName(String searchId, String searchName, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.id LIKE %:searchId% AND o.email LIKE %:searchEmail%")
    Page<Order> findByOrderIdAndEmail(String searchId, String searchEmail, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.firstName LIKE %:searchName% AND o.email LIKE %:searchEmail%")
    Page<Order> findByNameAndEmail(String searchName, String searchEmail, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.id LIKE %:searchId% AND o.firstName LIKE %:searchName% AND o.email LIKE %:searchEmail%")
    Page<Order> findByOrderIdAndNameAndEmail(String searchId, String searchName, String searchEmail, Pageable pageable);
}
