package com.perseus.pus.service;

import com.perseus.pus.dto.Response;
import com.perseus.pus.dto.UserContactDataDTO;
import com.perseus.pus.dto.UserDTO;
import com.perseus.pus.model.User;

public interface UserService {
    Response<UserDTO> saveUser(UserDTO user);

    Response<UserDTO> findById(Long userId);

    Response<UserDTO> deleteUser(Long userId);

    Response<UserDTO> findUserByName(String name);

    Response<UserDTO> updateContact(UserDTO userDTO, Long userId);
}
