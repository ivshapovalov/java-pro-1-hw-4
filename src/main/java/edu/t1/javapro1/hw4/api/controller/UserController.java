package edu.t1.javapro1.hw4.api.controller;

import edu.t1.javapro1.hw4.api.UserApi;
import edu.t1.javapro1.hw4.dto.common.CommonRequest;
import edu.t1.javapro1.hw4.dto.common.CommonResponse;
import edu.t1.javapro1.hw4.dto.incoming.request.CreateUserRequest;
import edu.t1.javapro1.hw4.dto.incoming.response.UserResponse;
import edu.t1.javapro1.hw4.service.UserService;
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
public class UserController implements UserApi {

    private final UserService userService;

    public CommonResponse<UserResponse> createUser(@RequestBody @Valid CommonRequest<CreateUserRequest> request) {
        UserResponse response = userService.createUser(request.getBody());

        return CommonResponse.<UserResponse>builder()
                .id(UUID.randomUUID())
                .body(response)
                .build();
    }

    @Override
    public ResponseEntity<CommonResponse<Void>> deleteUserById(Long userId) {
        boolean deleted = userService.deleteUserById(userId);
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status)
                .body(CommonResponse.<Void>builder()
                        .id(UUID.randomUUID())
                        .build());
    }

    @Override
    public CommonResponse<UserResponse> getUserById(Long userId) {
        UserResponse response = userService.findUserResponseById(userId);

        return CommonResponse.<UserResponse>builder()
                .id(UUID.randomUUID())
                .body(response)
                .build();
    }

    @Override
    public CommonResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> responses = userService.findAllUsers();

        return CommonResponse.<List<UserResponse>>builder()
                .id(UUID.randomUUID())
                .body(responses)
                .build();
    }
}
