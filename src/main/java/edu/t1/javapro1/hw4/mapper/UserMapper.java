package edu.t1.javapro1.hw4.mapper;

import edu.t1.javapro1.hw4.dto.incoming.request.CreateUserRequest;
import edu.t1.javapro1.hw4.dto.incoming.response.UserResponse;
import edu.t1.javapro1.hw4.persistence.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse mapUserToUserResponse(User user);

    User mapCreateUserRequestToUser(CreateUserRequest request);
}
