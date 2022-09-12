package ua.kiev.prog.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal qty;
    private BigDecimal price;

    private String size;

    public OrderItems(Order order, Product product, BigDecimal qty, BigDecimal price, String size) {
        this.order = order;
        this.product = product;
        this.qty = qty;
        this.price = price;
        this.size = size;
    }
}
