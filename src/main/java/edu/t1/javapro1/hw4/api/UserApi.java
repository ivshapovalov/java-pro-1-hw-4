package edu.t1.javapro1.hw4.api;

import edu.t1.javapro1.hw4.dto.common.CommonRequest;
import edu.t1.javapro1.hw4.dto.common.CommonResponse;
import edu.t1.javapro1.hw4.dto.incoming.request.CreateUserRequest;
import edu.t1.javapro1.hw4.dto.incoming.response.UserResponse;
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

@Tag(name = "User api", description = "API for handle User requests")
@RequestMapping("/api/v1/user")
public interface UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CommonResponse<UserResponse> createUser(@RequestBody @Valid CommonRequest<CreateUserRequest> request);

    @DeleteMapping("/{userId}")
    ResponseEntity<CommonResponse<Void>> deleteUserById(@PathVariable("userId") Long userId);

    @GetMapping("/{userId}")
    CommonResponse<UserResponse> getUserById(@PathVariable("userId") Long userId);

    @GetMapping
    CommonResponse<List<UserResponse>> getAllUsers();

}
