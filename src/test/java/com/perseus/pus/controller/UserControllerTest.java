package com.perseus.pus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perseus.pus.dto.EmailDTO;
import com.perseus.pus.dto.PhoneNumberDTO;
import com.perseus.pus.dto.Response;
import com.perseus.pus.dto.UserDTO;
import com.perseus.pus.model.Email;
import com.perseus.pus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Test
    public void findById() throws Exception {
        UserDTO user= new UserDTO();
        user.setId(1L);
        user.setLastName("Pulishetti");
        user.setFirstName("Raghupathi");

        Response<UserDTO> r = new Response(true,"User Data retrieved successfully.", user);

        doReturn(r).when(userService).findById(user.getId());
        mockMvc.perform(get("/users/" + user.getId())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.firstName", is(user.getFirstName())));
    }

    @Test
    public void findUserByName() throws Exception{
        UserDTO user= new UserDTO();
        user.setId(1L);
        user.setLastName("Pulishetti");
        user.setFirstName("Raghupathi");

        Response<UserDTO> r = new Response(true,"User Data retrieved successfully.", user);

        doReturn(r).when(userService).findUserByName("raghupathi");
        mockMvc.perform(get("/users?name=raghupathi")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.lastName", is(user.getLastName())));
    }

    @Test
    public void saveUser() throws  Exception{
        UserDTO user= new UserDTO();
        user.setId(1L);
        user.setLastName("Pathi");
        user.setFirstName("Raghu");

        EmailDTO e = new EmailDTO();
        e.setMail("a@gmail.com");

        PhoneNumberDTO p = new PhoneNumberDTO();
        p.setNumber("1234567890");

        user.getEmails().add(e);
        user.getPhoneNumbers().add(p);


        Response<UserDTO> r = new Response(true,"User Data saved Successfully.", user);


        doReturn(r).when(userService).saveUser(user);
        mockMvc.perform(post("/users")
                .contentType("application/json").content(asJsonString(user)))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteUser()throws Exception{
        Response<?> r = new Response(true,"User data deleted successfully.", null);

        doReturn(r).when(userService).deleteUser(1L);
        mockMvc.perform(delete("/users/1")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").exists())
                .andExpect(jsonPath("$.success").value(true))
                .andDo(print());
    }

    @Test
    public void updateUser() throws  Exception{
        UserDTO user= new UserDTO();
        user.setId(1L);
        user.setLastName("Pathi");
        user.setFirstName("Raghu");

        EmailDTO e = new EmailDTO();
        e.setMail("a@gmail.com");

        PhoneNumberDTO p = new PhoneNumberDTO();
        p.setNumber("1234567890");

        user.getEmails().add(e);
        user.getPhoneNumbers().add(p);


        Response<UserDTO> r = new Response(true,"User Data saved Successfully.", user);


        doReturn(r).when(userService).updateContact(user,1L);
        mockMvc.perform(put("/users/1/contact")
                .contentType("application/json").content(asJsonString(user)))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
