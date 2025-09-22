package edu.t1.javapro1.hw4.service;

import edu.t1.javapro1.hw4.dto.incoming.request.CreateProductRequest;
import edu.t1.javapro1.hw4.dto.incoming.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAllProducts();

    List<ProductResponse> findAllProductsByUserId(Long userId);

    ProductResponse findProductById(Long productId);

    ProductResponse createProduct(CreateProductRequest createProductRequest);

    boolean deleteProductById(Long id);
}
