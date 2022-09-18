package ua.kiev.prog.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_added")
    private Date dateAdded;

    @Column(name = "updated")
    private Date updated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private CustomUser customUser;


    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "shopOrder", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<OrderItems> orderItems = new ArrayList<>();

    @Column(name = "total")
    private BigDecimal total;

    public ShopOrder(String email, String firstName, String lastName, String phone,
                     String shippingAddress, OrderStatus status,
                     CustomUser customUser, Date dateAdded, Date updated) {
        this.customUser = customUser;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateAdded = dateAdded;
        this.updated = updated;
    }

}
