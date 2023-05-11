package com.second.springbootdemo.Controller;

import com.second.springbootdemo.Entity.Product;
import com.second.springbootdemo.Service.ProductService;
import com.second.springbootdemo.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductRequest saveProduct (@RequestBody ProductRequest request){
        return productService.addProduct(request);
    }

    @GetMapping("/all")
    public List<ProductRequest> fetchAllItems(){
        return productService.fetchAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> fetchProductById(@PathVariable(value = "id") Long id){
        return productService.fetchProductById(id);
    }

//    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(Long id){
        return "The product has been successfully deleted";
    }
@PutMapping("/{id}")
    public ResponseEntity<ProductRequest> updateAll(@PathVariable("id") Long id, @RequestBody Product product){
        return new ResponseEntity<>(productService.updateProduct(id,product), HttpStatus.OK);

    }

    @GetMapping("/get")
    public Product findByDate(@RequestParam("created") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdAt){
        return productService.getByDate(createdAt);
}

@GetMapping("/name")
    public Product fetchProductByName(@RequestParam(name = "name", required = true) String productName){
        return productService.fetchProductsByName(productName);
}
}
