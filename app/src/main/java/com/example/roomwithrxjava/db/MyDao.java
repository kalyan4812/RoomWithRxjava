package com.example.roomwithrxjava.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
@Dao
public interface MyDao {
    @Insert
    Completable insertUser(User user);
    @Update
    Single<Integer> updateUser(User user); // Integer is num of effected.
    @Delete
    Single<Integer> deleteUser(User user);
    @Query("SELECT *  FROM Users WHERE uemail=:userEmail")
    Flowable<User> getUserByEmail(String  userEmail );
}
