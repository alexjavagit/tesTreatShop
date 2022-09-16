package ua.kiev.prog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kiev.prog.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllBy(Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.id = :searchId")
    Page<Order> findByOrderId(Long searchId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.firstName LIKE %:searchName%")
    Page<Order> findByName(String searchName, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.email LIKE %:searchEmail%")
    Page<Order> findByEmail(String searchEmail, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.id = :searchId AND o.firstName LIKE %:searchName%")
    Page<Order> findByOrderIdAndName(Long searchId, String searchName, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.id = :searchId AND o.email LIKE %:searchEmail%")
    Page<Order> findByOrderIdAndEmail(Long searchId, String searchEmail, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.firstName LIKE %:searchName% AND o.email LIKE %:searchEmail%")
    Page<Order> findByNameAndEmail(String searchName, String searchEmail, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.id = :searchId AND o.firstName LIKE %:searchName% AND o.email LIKE %:searchEmail%")
    Page<Order> findByOrderIdAndNameAndEmail(Long searchId, String searchName, String searchEmail, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.customUser.id = :userId")
    Page<Order> findByUserId(Long userId, Pageable pageable);
}
