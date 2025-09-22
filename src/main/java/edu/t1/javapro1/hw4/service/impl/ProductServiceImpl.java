package edu.t1.javapro1.hw4.service.impl;

import edu.t1.javapro1.hw4.dto.incoming.request.CreateProductRequest;
import edu.t1.javapro1.hw4.dto.incoming.response.ProductResponse;
import edu.t1.javapro1.hw4.mapper.ProductMapper;
import edu.t1.javapro1.hw4.persistence.dao.ProductRepository;
import edu.t1.javapro1.hw4.persistence.model.Product;
import edu.t1.javapro1.hw4.persistence.model.User;
import edu.t1.javapro1.hw4.service.ProductService;
import edu.t1.javapro1.hw4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final UserService userService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        User user = userService.findUserById(createProductRequest.userId());
        Product product = productMapper.mapCreateProductRequestToProduct(createProductRequest, user);

        productRepository.save(product);

        return productMapper.mapProductToProductResponse(product);
    }

    @Override
    public ProductResponse findProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        return productMapper.mapProductToProductResponse(product);
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(productMapper::mapProductToProductResponse).toList();
    }

    @Override
    public List<ProductResponse> findAllProductsByUserId(Long userId) {
        List<Product> products = productRepository.findAllByUserId(userId);

        return products.stream().map(productMapper::mapProductToProductResponse).toList();
    }

    @Override
    public boolean deleteProductById(Long productId) {
        try {
            productRepository.deleteById(productId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
