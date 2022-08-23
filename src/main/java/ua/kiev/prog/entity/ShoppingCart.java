package ua.kiev.prog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

   @OneToOne
   @JoinColumn(name = "user_id")
   private CustomUser customUser;

   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name = "shoppingCart")
   private List<ShoppingCartProducts> shoppingCartProducts;

//   @ManyToMany
//   @JoinTable(name = "shopping_cart_products",
//           joinColumns = @JoinColumn(name = "shopping_cart_id"),
//           inverseJoinColumns = @JoinColumn(name= "product_id"),
//           )
//   private List<Product> products;


}
