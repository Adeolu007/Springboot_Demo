package com.second.springbootdemo.Service;

import com.second.springbootdemo.Entity.Product;
import com.second.springbootdemo.Repository.ProductRepository;
import com.second.springbootdemo.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
//    @Autowired
//            private ProductRepository productRepository;

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public ProductRequest addProduct (ProductRequest request){
        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setQuantity(request.getQuantity());
        newProduct.setSkuCode(request.getSkuCode());
        newProduct.setDescription(request.getDescription());
        newProduct.setCreatedAt(request.getLocalDateTime());

        Product addProduct = productRepository.save(newProduct);
        request.setName(addProduct.getName());
        request.setQuantity(addProduct.getQuantity());
        request.setSkuCode(addProduct.getSkuCode());
        request.setDescription(addProduct.getDescription());

        return request;

    }

    public List<ProductRequest> fetchAllProducts(){

         List<ProductRequest> products = new ArrayList<>();
        List<Product> allProducts = productRepository.findAll();

        for(Product pro:allProducts){
            ProductRequest request = new ProductRequest();
           request.setName(pro.getName());
            request.setQuantity(pro.getQuantity());
            request.setSkuCode(pro.getSkuCode());
            request.setDescription(pro.getDescription());

            products.add(request);
        }
        return products;

//        or
//                return allProducts.stream(),map(this::mapToDto).toList
    }

    public Optional<Product> fetchProductById(Long id){
        return productRepository.findById(id);
    }

//    public Product fetchProductByName(String productName){
//
//        boolean isProductExist = productRepository.existByName(productName);
//
//        if(!isProductExist){
//         return   productRepository.findByName(productName);
//        }else {
//            return null;
//        }
//
//    }

    public void deleteProduct(Long id){
  Product newProduct = productRepository.findById(id).orElseThrow();
  productRepository.deleteById(id);
    }

    public ProductRequest updateProduct(Long id, Product product){
        Product newProduct = new Product();
            newProduct = productRepository.findById(id).orElseThrow();
            newProduct.setName(product.getName());
            newProduct.setQuantity(product.getQuantity());
            newProduct.setSkuCode(product.getSkuCode());
            newProduct.setDescription(product.getDescription());
            newProduct.setCreatedAt(product.getCreatedAt());

            productRepository.save(newProduct);

            ProductRequest newRequest = new ProductRequest();
            newRequest.setName(newProduct.getName());
            newRequest.setQuantity(newProduct.getQuantity());
            newRequest.setSkuCode(newProduct.getSkuCode());
            newRequest.setDescription(newProduct.getDescription());
            return newRequest;
    }

    public Product getByDate (LocalDateTime createdAt){
        Product newProduct = new Product();
      newProduct =  productRepository.findByCreatedAt(createdAt);
        newProduct.getName();
        newProduct.getQuantity();
        newProduct.getSkuCode();
        newProduct.getDescription();
        newProduct.getCreatedAt();
        return newProduct;
    }

    public Product fetchProductsByName (String productName){
        boolean isProductExist = productRepository.existsByName(productName);
        if(isProductExist){
            return productRepository.findByName(productName);
        }else{
            return null;
        }
    }
}
