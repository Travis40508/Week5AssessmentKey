package com.example.rodneytressler.week5assessmentkey;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rodneytressler on 12/19/17.
 */

@Entity
public class Dog {

    @PrimaryKey(autoGenerate = true)
    int id;

    private String name;
    private String breed;
    private String age;

    @SerializedName("message")
    @Expose private String imageUrl;

    public Dog(String name, String breed, String age, String imageUrl) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getAge() {
        return age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
