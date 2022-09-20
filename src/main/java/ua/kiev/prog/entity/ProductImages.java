package ua.kiev.prog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_images")
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] image;

    @Transient
    private String base64Image;

    public ProductImages(Product product, String name) {
        this.product = product;
        this.name = name;
    }

    public String getBase64Image() {
        return Base64.toBase64String(this.image);
    }
}
