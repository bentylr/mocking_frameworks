package com.bhagirath.mocking_frameworks;

import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(String id) throws IOException {
        Response response = Request.Get(getHostName()+"/getUser").execute();
        if (response.returnResponse().getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            return userRepository.getDefaultUserDetails();
        }
        return null;
    }

    protected String getHostName() {
        return "www.google.com";
    }
}
