package com.example.roomwithrxjava.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey
    @ColumnInfo(name = "uemail")
    @NonNull
    private String useremail;
    @ColumnInfo(name = "uname")
    private String username;
    @ColumnInfo(name = "upassword")
    private String userpassword;

    public User() {
    }

    public User(String useremail, String username, String userpassword) {
        this.useremail = useremail;
        this.username = username;
        this.userpassword = userpassword;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
