package com.group80.uoftinder.create_account_use_case;

import com.group80.uoftinder.entities.User;

import java.util.List;

public interface CreateAccountInput {
    void createAccount(String email, String password1, String password2);


    void setBasicInfo(String name, String age, String identity, String type, User currentUser);

    void setAcademicInfo(User currentUser, int year, List<Integer> majors, int campus);

    void setFriendshipInfo(User currentUser, int year, List<Integer> majors, int campus,
                           List<Integer> interests, List<Integer> colours);

    void setRomanticInfo(User currentUser, int sexuality, List<Integer> majors, int campus,
                         List<Integer> interests, int distance, int relationship);
}