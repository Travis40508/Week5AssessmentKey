package com.example.rodneytressler.week5assessmentkey;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rodneytressler on 12/19/17.
 */

public interface DogApi {
    @GET("/api/breed/{breed}/images/random")
    Call<Dog> getDogImage(@Path("breed") String breed);
}
