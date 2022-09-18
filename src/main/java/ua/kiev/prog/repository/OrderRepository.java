package ua.kiev.prog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kiev.prog.entity.ShopOrder;


public interface OrderRepository extends JpaRepository<ShopOrder, Long> {

    Page<ShopOrder> findAllBy(Pageable pageable);

    @Query("SELECT o FROM ShopOrder o WHERE o.id = :searchId")
    Page<ShopOrder> findByOrderId(Long searchId, Pageable pageable);

    @Query("SELECT o FROM ShopOrder o WHERE o.firstName LIKE %:searchName%")
    Page<ShopOrder> findByName(String searchName, Pageable pageable);

    @Query("SELECT o FROM ShopOrder o WHERE o.email LIKE %:searchEmail%")
    Page<ShopOrder> findByEmail(String searchEmail, Pageable pageable);

    @Query("SELECT o FROM ShopOrder o WHERE o.id = :searchId AND o.firstName LIKE %:searchName%")
    Page<ShopOrder> findByOrderIdAndName(Long searchId, String searchName, Pageable pageable);

    @Query("SELECT o FROM ShopOrder o WHERE o.id = :searchId AND o.email LIKE %:searchEmail%")
    Page<ShopOrder> findByOrderIdAndEmail(Long searchId, String searchEmail, Pageable pageable);

    @Query("SELECT o FROM ShopOrder o WHERE o.firstName LIKE %:searchName% AND o.email LIKE %:searchEmail%")
    Page<ShopOrder> findByNameAndEmail(String searchName, String searchEmail, Pageable pageable);

    @Query("SELECT o FROM ShopOrder o WHERE o.id = :searchId AND o.firstName LIKE %:searchName% AND o.email LIKE %:searchEmail%")
    Page<ShopOrder> findByOrderIdAndNameAndEmail(Long searchId, String searchName, String searchEmail, Pageable pageable);

    @Query("SELECT o FROM ShopOrder o WHERE o.customUser.id = :userId")
    Page<ShopOrder> findByUserId(Long userId, Pageable pageable);
}
