package com.perseus.pus.service;

import com.perseus.pus.dto.*;
import com.perseus.pus.model.Email;
import com.perseus.pus.model.PhoneNumber;
import com.perseus.pus.model.User;
import com.perseus.pus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Response<?> saveUser(UserDTO userDTO) {
        User user = mapUserDTOToUserEntity(userDTO, userDTO.getId());
        user = userRepository.save(user);
        return new Response<>(true, "User data saved successfully.", mapEntityToDTO(user));
    }

    @Override
    public Response<?> findById(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new Response<>(true, "User with id " + userId + " retrieved successfully.", mapEntityToDTO(user.get()));
        } else {
            return new Response<>(true, "User with id " + userId + " not found.", null);
        }


    }

    @Override
    public Response<?> deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new Response<>(true, "User with id " + userId + " Deleted Successfully.", null);
        } else {
            return new Response<>(false, "User with id " + userId + " Not found.", null);
        }

    }

    @Override
    public Response<?> findUserByName(String name) {
        if (name != null && name.trim().length() == 0)
            return new Response<>(false, "Name must contain characters.", null);
        String[] names = name.trim().split(" ");
        String firstName = names[0].trim();

        User u = new User();
        u.setFirstName(firstName);
        if (names.length > 1 && names[1].trim().length() > 0)
            u.setLastName(names[1].trim());


        //  u.setId(id);
        Optional<User> user = userRepository.findOne(Example.of(u));
        if (user.isPresent()) {
            return new Response<>(true, "User with name " + name + " retrieved successfully.", mapEntityToDTO(user.get()));
        } else {
            return new Response<>(true, "User with name " + name + " not found.", null);
        }
    }

    @Override
    public Response<?> updateContact(UserDTO userDTO, Long userId) {
        User user = mapUserDTOToUserEntity(userDTO, userId);
        if (user == null)
            return new Response<>(false, "User with id " + userId + " Not found.", null);

        user = userRepository.save(user);
        return new Response<>(true, "User Contact data update successfully..", null);
    }

    private User mapUserDTOToUserEntity(UserDTO userDTO, Long userId) {
        User user;
        if (userId != null && userId > 0) {
            if (userRepository.findById(userId).isPresent()) {
                user = userRepository.findById(userId).get();
            } else
                return null;

        } else
            user = new User();

        user.setId(userId);
        if (userDTO.getLastName() != null)
            user.setLastName(userDTO.getLastName());
        if (userDTO.getFirstName() != null)
            user.setFirstName(userDTO.getFirstName());

        userDTO.getEmails().forEach(e -> {
            Email email = new Email();
            email.setId(e.getId());
            email.setMail(e.getMail());
            user.addEmail(email);
        });

        userDTO.getPhoneNumbers().forEach(p -> {
            PhoneNumber phone = new PhoneNumber();
            phone.setId(p.getId());
            phone.setNumber(p.getNumber());
            user.addPhoneNumber(phone);
        });
        return user;

    }

    private UserDTO mapEntityToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        user.getEmails().forEach(e -> {
            EmailDTO emailDTO = new EmailDTO();
            emailDTO.setId(e.getId());
            emailDTO.setMail(e.getMail());
            dto.getEmails().add(emailDTO);
        });

        user.getPhoneNumbers().forEach(p -> {
            PhoneNumberDTO pdto = new PhoneNumberDTO();
            pdto.setId(p.getId());
            pdto.setNumber(p.getNumber());
            dto.getPhoneNumbers().add(pdto);
        });
        return dto;
    }
}
