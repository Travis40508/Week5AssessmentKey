package com.example.rodneytressler.week5assessmentkey;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rodneytressler on 12/19/17.
 */

@Dao
public interface DogDao {

    @Insert
    void insertDog(Dog dog);

    @android.arch.persistence.room.Query("SELECT * FROM dog")
    List<Dog> getAllDogs();

}
