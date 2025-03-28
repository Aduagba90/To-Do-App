package com.semicolon.africa.services;

import com.semicolon.africa.data.model.User;
import com.semicolon.africa.data.repository.UserRepos;
import com.semicolon.africa.dtos.request.CreateUserRequest;
import com.semicolon.africa.dtos.request.DeleteUserRequest;
import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.UpdateUserRequest;
import com.semicolon.africa.dtos.response.CreateUserResponse;
import com.semicolon.africa.dtos.response.DeleteUserResponse;
import com.semicolon.africa.dtos.response.LoginResponse;
import com.semicolon.africa.dtos.response.UpdateUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserRepos userRepos;
    @Autowired
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepos.deleteAll();
    }

    @Test
    public void checkIfUserCanRegisterUser() {
        CreateUserResponse createUserResponse = registerUse();
        assertNotNull(createUserResponse);
        assertThat(createUserResponse.getMessage()).isEqualTo("Successfully registered!");
    }

    private CreateUserResponse registerUse() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("Idris");
        createUserRequest.setLastName("Abubakr");
        createUserRequest.setEmail("idris98@gmail.com");
        createUserRequest.setPassword("Abubakr");
        createUserRequest.setPhoneNumber("O8032504941");
        CreateUserResponse createUserResponse = userService.signUp(createUserRequest);
        return createUserResponse;
    }

    @Test
    public void testIfUserExists() {
        CreateUserResponse createUserResponse = registerUse();
        assertThat(createUserResponse);


    }

    @Test
    public void testIfUserCanBeUpdated(){
        CreateUserResponse response = registerUse();
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setFirstName("Zakariyya");
        updateUserRequest.setLastName("Musa");
        updateUserRequest.setEmail(response.getEmail());
        updateUserRequest.setPassword("Zakariyya");
        updateUserRequest.setPhone("O8032504941");
        UpdateUserResponse updateUserResponse = userService.updateUser(updateUserRequest);
        updateUserResponse.setMessage("Successfully updated!");
        assertThat(updateUserResponse.getMessage()).isEqualTo("Successfully updated!");

    }

    @Test
    public void checkIfUserCanLogIn(){
        registerUse();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("idris98@gmail.com");
        loginRequest.setPassword("Abubakr");
        LoginResponse loginResponse = userService.login(loginRequest);
        loginResponse.setMessage("Successfully logged in!");
    }

    @Test
    public void testIfUserCanDelete(){
        CreateUserResponse response = registerUse();
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setId(response.getId());
        DeleteUserResponse deleteUserResponse = userService.deleteUser("66d7a3ff58090c40371e1110");
        assertThat(deleteUserResponse.getMessage()).isEqualTo("User successfully deleted!");
    }

    @Test
    public void testCanFindUserByEmail(){

        assertTrue(userRepos.existsByEmail("lukman"));

    }
    @Test
    public void testThatUserCanSignUp(){
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName("akin");
        createUserRequest.setLastName("oye");
        createUserRequest.setEmail("ayu@email");
        createUserRequest.setPhoneNumber("08109643956");
        createUserRequest.setPassword("6666");
        CreateUserResponse createUserResponse = userService.signUp(createUserRequest);
        assertEquals(1, 1);
        assertThat(createUserResponse.getEmail()).isEqualTo("ayu@email");
    }
    @Test
    public void testThatFindByEmail(){
        User user = new User();
        user.setEmail("Taye@gmail.com");
        userRepos.save(user);
        boolean exists = userRepos.existsByEmail("Taye@gmail.com");
        assertTrue(exists);
    }


}



