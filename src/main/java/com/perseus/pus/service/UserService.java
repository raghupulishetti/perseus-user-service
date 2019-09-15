package com.perseus.pus.service;

import com.perseus.pus.dto.Response;
import com.perseus.pus.dto.UserContactDataDTO;
import com.perseus.pus.dto.UserDTO;
import com.perseus.pus.model.User;

public interface UserService {
    Response<?> saveUser(UserDTO user);

    Response<?> findById(Long userId);

    Response<?> deleteUser(Long userId);

    Response<?> findUserByName(String name);

    Response<?> updateContact(UserDTO userDTO, Long userId);
}
