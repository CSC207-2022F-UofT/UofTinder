package com.group80.uoftinder.entities;

import androidx.annotation.NonNull;

public enum UserType {
    ACADEMIC("Academic"),
    FRIENDSHIP("Friendship"),
    ROMANTIC("Romantic"),
    TEST("Test");

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    @NonNull
    @Override
    public String toString() {
        return this.userType;
    }
}
