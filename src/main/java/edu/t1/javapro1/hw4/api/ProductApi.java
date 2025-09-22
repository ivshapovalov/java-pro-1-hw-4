package edu.t1.javapro1.hw4.api;

import edu.t1.javapro1.hw4.dto.common.CommonRequest;
import edu.t1.javapro1.hw4.dto.common.CommonResponse;
import edu.t1.javapro1.hw4.dto.incoming.request.CreateProductRequest;
import edu.t1.javapro1.hw4.dto.incoming.response.ProductResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Tag(name = "Product api", description = "API for handle Product requests")
@RequestMapping("/api/v1")
public interface ProductApi {

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    CommonResponse<ProductResponse> createProduct(@RequestBody @Valid CommonRequest<CreateProductRequest> request);

    @DeleteMapping("/product/{productId}")
    ResponseEntity<CommonResponse<Void>> deleteProductById(@PathVariable("productId") Long productId);

    @GetMapping("/product/{productId}")
    CommonResponse<ProductResponse> getProductById(@PathVariable("productId") Long productId);

    @GetMapping("/product")
    CommonResponse<List<ProductResponse>> getAllProducts();

    @GetMapping("/user/{userId}/product")
    CommonResponse<List<ProductResponse>> getAllProductsByUserId(@PathVariable("userId") Long userId);

}
