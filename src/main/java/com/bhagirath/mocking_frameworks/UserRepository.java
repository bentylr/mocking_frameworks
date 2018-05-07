package com.bhagirath.mocking_frameworks;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User getDefaultUserDetails() {
        return User.UserBuilder.anUser().userId("12333342").name("John").mobileNumber("1236172812").address("1111 S King Dr").build();
    }
}
