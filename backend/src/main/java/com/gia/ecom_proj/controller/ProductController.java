package com.gia.ecom_proj.controller;


import com.gia.ecom_proj.model.Product;
import com.gia.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{prodId}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable int prodId){
        Product product = service.getProductById(prodId);
        byte[] imageData = product.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType()))
                .body(imageData);
    }

    @PutMapping("/product/{prodId}")
    public ResponseEntity<String> updateProduct(@PathVariable int prodId,
                                                @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile){

        Product updatedProd;
        try{
            updatedProd = service.updateProduct(prodId, product, imageFile);
        }catch(IOException e){
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }

        if (updatedProd != null){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int prodId){
        System.out.println("deleted");
       Product deleteProd = service.getProductById(prodId);
       if(deleteProd != null ){
           service.deleteProduct(prodId);
           return new ResponseEntity<>("Deleted", HttpStatus.OK);
       }
       return new ResponseEntity<>("Product not found.", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        System.out.println("Searching with " + keyword);
        List<Product> products = service.searchProduct(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
