package com.example.jankovic.test.data.model;

/**
 * Created by wurfl_v on 01/11/16.
 */

public class User {

    public static final String TAG = User.class.getSimpleName();
    public static final String TABLE = "User";
    // Labels Table Columns names
    public static final String KEY_UserId = "userId";
    public static final String KEY_FirstName = "first_name";
    public static final String KEY_LastName = "last_name";
    public static final String KEY_Email = "email";
    public static final String KEY_Password = "password";


    private int userId;
    private String first_name;
    private String last_name;
    private String email;
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getFirstName() {
        return first_name;
    }

    public void set_FirstName(String first_name) {
        this.first_name = first_name;
    }


    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

