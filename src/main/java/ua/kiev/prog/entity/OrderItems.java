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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private ShopOrder shopOrder;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal qty;
    private BigDecimal price;

    private String size;

    public OrderItems(ShopOrder shopOrder, Product product, BigDecimal qty, BigDecimal price, String size) {
        this.shopOrder = shopOrder;
        this.product = product;
        this.qty = qty;
        this.price = price;
        this.size = size;
    }
}
