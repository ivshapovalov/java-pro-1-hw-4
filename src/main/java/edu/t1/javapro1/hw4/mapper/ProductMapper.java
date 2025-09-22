package edu.t1.javapro1.hw4.mapper;

import edu.t1.javapro1.hw4.dto.incoming.request.CreateProductRequest;
import edu.t1.javapro1.hw4.dto.incoming.response.ProductResponse;
import edu.t1.javapro1.hw4.persistence.model.Product;
import edu.t1.javapro1.hw4.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "product.user.id", target = "userId")
    ProductResponse mapProductToProductResponse(Product product);

    @Mapping(source = "user", target = "user")
    @Mapping(target = "id", ignore = true)
    Product mapCreateProductRequestToProduct(CreateProductRequest request, User user);
}
