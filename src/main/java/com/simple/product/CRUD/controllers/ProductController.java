package com.simple.product.CRUD.controllers;

import com.simple.product.CRUD.domain.product.Product;
import com.simple.product.CRUD.domain.product.ProductRepository;
import com.simple.product.CRUD.domain.product.RequestProduct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {
        // Verificar se o ID foi fornecido
        if (data.id() == null) {
            return ResponseEntity.badRequest().body("ID não fornecido");
        }

        // Buscar o produto existente
        Optional<Product> optionalProduct = repository.findById(data.id());

        // Verificar se o produto existe
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Obter o produto existente do Optional
        Product product = optionalProduct.get();

        // Atualizar os campos
        product.setName(data.name());
        product.setPrice_in_cents(data.price_in_cents());

        // Salvar a entidade atualizada
        Product updatedProduct = repository.save(product);

        // Retornar resposta de sucesso
        return ResponseEntity.ok(updatedProduct);
    }

}
