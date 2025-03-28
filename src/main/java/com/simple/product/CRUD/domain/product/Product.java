package com.simple.product.CRUD.domain.product;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Request;

@Table(name="product")
@Entity(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer price_in_cents;

    public Product(RequestProduct requestProduct) {
        this.name = requestProduct.name();
        this.price_in_cents = requestProduct.price_in_cents();
    }

}
