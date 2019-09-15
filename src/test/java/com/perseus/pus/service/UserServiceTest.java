package com.perseus.pus.service;

import com.perseus.pus.dto.EmailDTO;
import com.perseus.pus.dto.PhoneNumberDTO;
import com.perseus.pus.dto.Response;
import com.perseus.pus.dto.UserDTO;
import com.perseus.pus.model.Email;
import com.perseus.pus.model.PhoneNumber;
import com.perseus.pus.model.User;
import com.perseus.pus.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveUser() throws Exception{

        UserDTO user= new UserDTO();
        user.setLastName("Pathi");
        user.setFirstName("Raghu");

        EmailDTO e = new EmailDTO();
        e.setMail("a@gmail.com");

        PhoneNumberDTO p = new PhoneNumberDTO();
        p.setNumber("1234567890");

        user.getEmails().add(e);
        user.getPhoneNumbers().add(p);

        User u = new User();
        u.setId(1L);
        u.setLastName("Pathi");
        u.setFirstName("Raghu");

        Email edto = new Email();
        edto.setId(1L);
        edto.setMail("a@gmail.com");

        PhoneNumber pdto = new PhoneNumber();
        pdto.setId(1L);
        pdto.setNumber("1234567890");

        u.getEmails().add(edto);
        u.getPhoneNumbers().add(pdto);

        when(userRepository.save(any(User.class))).thenReturn(u);

        Response<?> resp = userService.saveUser(user);
        Assert.assertEquals(true,resp.isSuccess());


    }


    @Test
    public void findById() throws Exception{
        User u = new User();
        u.setId(1L);
        u.setLastName("Pathi");
        u.setFirstName("Raghu");

        Email edto = new Email();
        edto.setId(1L);
        edto.setMail("a@gmail.com");

        PhoneNumber pdto = new PhoneNumber();
        pdto.setId(1L);
        pdto.setNumber("1234567890");

        u.getEmails().add(edto);
        u.getPhoneNumbers().add(pdto);
        when(userRepository.findById(u.getId())).thenReturn(Optional.of(u));
        Response<?> resp = userService.findById(u.getId());
        Assert.assertEquals(true, resp.isSuccess());
        UserDTO dto = (UserDTO) resp.getData();

        Assert.assertEquals("Pathi",dto.getLastName());

    }


    @Test
    public void deleteUser() throws Exception{
        User u = new User();
        u.setId(1L);
        u.setLastName("Pathi");
        u.setFirstName("Raghu");

        Email edto = new Email();
        edto.setId(1L);
        edto.setMail("a@gmail.com");

        PhoneNumber pdto = new PhoneNumber();
        pdto.setId(1L);
        pdto.setNumber("1234567890");

        u.getEmails().add(edto);
        u.getPhoneNumbers().add(pdto);
        when(userRepository.findById(u.getId())).thenReturn(Optional.of(u));

        Response<UserDTO> resp = userService.deleteUser(1L);
        Assert.assertEquals(true,resp.isSuccess());
    }

    @Test
    public void updateContact() throws Exception{

        UserDTO user= new UserDTO();

        user.setLastName("Pathi");
        user.setFirstName("Raghu");

        EmailDTO e = new EmailDTO();
        e.setMail("a@gmail.com");

        PhoneNumberDTO p = new PhoneNumberDTO();
        p.setNumber("1234567890");

        user.getEmails().add(e);
        user.getPhoneNumbers().add(p);

        User u = new User();
        u.setId(1L);
        u.setLastName("Pathi");
        u.setFirstName("Raghu");

        Email edto = new Email();
        edto.setId(1L);
        edto.setMail("b@gmail.com");

        PhoneNumber pdto = new PhoneNumber();
        pdto.setId(1L);
        pdto.setNumber("1234567890");

        u.getEmails().add(edto);
        u.getPhoneNumbers().add(pdto);

        when(userRepository.save(any(User.class))).thenReturn(u);
        when(userRepository.findById(u.getId())).thenReturn(Optional.of(u));


        Response<UserDTO> resp = userService.updateContact(user,1L);
        Assert.assertEquals(true,resp.isSuccess());


    }
}
