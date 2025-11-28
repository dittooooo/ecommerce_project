package com.gia.ecom_proj.service;

import com.gia.ecom_proj.model.Product;
import com.gia.ecom_proj.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

    public byte[] getImageById(int prodId) {
            Product product = repo.findById(prodId).orElse(null);
            return product.getImageData();

    }

    public Product updateProduct(int prodId, Product product, MultipartFile imageFile) throws IOException {


        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());

        return repo.save(product);

    }

    public void deleteProduct(int prodId){
        repo.deleteById(prodId);
    }

    public List<Product> searchProduct(String keyword) {
        return repo.searchProduct(keyword);
    }
}