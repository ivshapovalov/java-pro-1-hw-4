package edu.t1.javapro1.hw4.api.controller;

import edu.t1.javapro1.hw4.api.ProductApi;
import edu.t1.javapro1.hw4.dto.common.CommonRequest;
import edu.t1.javapro1.hw4.dto.common.CommonResponse;
import edu.t1.javapro1.hw4.dto.incoming.request.CreateProductRequest;
import edu.t1.javapro1.hw4.dto.incoming.response.ProductResponse;
import edu.t1.javapro1.hw4.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    public CommonResponse<ProductResponse> createProduct(@RequestBody @Valid CommonRequest<CreateProductRequest> request) {
        ProductResponse response = productService.createProduct(request.getBody());

        return CommonResponse.<ProductResponse>builder()
                .id(UUID.randomUUID())
                .body(response)
                .build();
    }

    @Override
    public ResponseEntity<CommonResponse<Void>> deleteProductById(Long productId) {
        boolean deleted = productService.deleteProductById(productId);
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status)
                .body(CommonResponse.<Void>builder()
                        .id(UUID.randomUUID())
                        .build());
    }

    @Override
    public CommonResponse<ProductResponse> getProductById(Long productId) {
        ProductResponse response = productService.findProductById(productId);

        return CommonResponse.<ProductResponse>builder()
                .id(UUID.randomUUID())
                .body(response)
                .build();
    }

    @Override
    public CommonResponse<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> responses = productService.findAllProducts();

        return CommonResponse.<List<ProductResponse>>builder()
                .id(UUID.randomUUID())
                .body(responses)
                .build();
    }

    @Override
    public CommonResponse<List<ProductResponse>> getAllProductsByUserId(Long userId) {
        List<ProductResponse> responses = productService.findAllProductsByUserId(userId);

        return CommonResponse.<List<ProductResponse>>builder()
                .id(UUID.randomUUID())
                .body(responses)
                .build();
    }
}
