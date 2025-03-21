package com.semicolon.africa.services;

import com.semicolon.africa.data.model.User;
import com.semicolon.africa.data.repository.UserRepos;
import com.semicolon.africa.dtos.request.CreateUserRequest;
import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.UpdateUserRequest;
import com.semicolon.africa.dtos.response.CreateUserResponse;
import com.semicolon.africa.dtos.response.DeleteUserResponse;
import com.semicolon.africa.dtos.response.LoginResponse;
import com.semicolon.africa.dtos.response.UpdateUserResponse;
import com.semicolon.africa.exception.EmailAlreadyExistException;
import com.semicolon.africa.exception.InvalidEmailException;
import com.semicolon.africa.exception.RegisterValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepos userRepository;
    @Override
    public CreateUserResponse signUp(CreateUserRequest createUserRequest) {
        EmailVerification(createUserRequest.getEmail());
        RegisterValidation(createUserRequest);
        phoneNumberValidation(createUserRequest.getPhoneNumber());
        User user = new User();
        user.setFirsName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setPassword(createUserRequest.getPassword());
        user.setEmail(createUserRequest.getEmail());
        user.setPhone(createUserRequest.getPhoneNumber());
        userRepository.save(user);
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setEmail(createUserRequest.getEmail());
        createUserResponse.setMessage("Successfully registered!");
        return createUserResponse;
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        User user = userRepository.findUserByEmail(updateUserRequest.getEmail());
        if (user != null) {
            user.setFirsName(updateUserRequest.getFirstName());
            user.setLastName(updateUserRequest.getLastName());
            user.setPassword(updateUserRequest.getPassword());
            user.setPhone(updateUserRequest.getPhone());
            userRepository.save(user);
        }
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setEmail(updateUserRequest.getEmail());
        updateUserResponse.setPhone(updateUserRequest.getPhone());
        updateUserResponse.setFirstName(updateUserRequest.getFirstName());
        updateUserResponse.setLastName(updateUserRequest.getLastName());
        updateUserResponse.setMessage("Successfully updated!");
        return updateUserResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = findByEmail(loginRequest.getEmail());
        if(user.getPassword().equals(loginRequest.getPassword())) {
            user.setLoggedIn(true);
            userRepository.save(user);
        }else{
            throw new InvalidEmailException("Invalid Credentials");
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Successfully logged in!");
        return loginResponse;
    }

    @Override
    public DeleteUserResponse deleteUser(String id) {
        userRepository.deleteById(id);
        DeleteUserResponse deleteUserResponse = new DeleteUserResponse();
        deleteUserResponse.setMessage("User successfully deleted!");
        return deleteUserResponse;
    }


    private void phoneNumberValidation(String phone) {
        if (phone.length() != 11) {
            throw new RegisterValidationException("Phone number must be 11 digits!");
        }
    }

    private void RegisterValidation(CreateUserRequest createUserRequest) {
        if (createUserRequest.getLastName().trim().isEmpty() ||
                createUserRequest.getPassword().trim().isEmpty() ||
                createUserRequest.getEmail().trim().isEmpty() ||
                createUserRequest.getPhoneNumber().trim().isEmpty() ||
                createUserRequest.getFirstName().trim().isEmpty()) {
            throw new RegisterValidationException("Wrong detail entered!");
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailAlreadyExistException(email + " does not exist"));

    }

    @Override
    public void userTask(User user) {
        userRepository.save(user);
    }


    public void EmailVerification(String email) {
        boolean existByEmail = userRepository.existsByEmail(email);
        if (existByEmail) throw new EmailAlreadyExistException("User already exist!");
        if (!email.contains("@") && !email.endsWith(".com")) {
            throw new InvalidEmailException("invalid email format");

        }



    }
}

