package com.example.rodneytressler.week5assessmentkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddDogActivity extends AppCompatActivity {


    private String baseUrl = "https://dog.ceo";

    private Retrofit retrofit;

    private DogApi dogApi;

    private DogDatabase dogDatabase;

    @BindView(R.id.input_age)
    protected EditText inputAge;

    @BindView(R.id.input_breed)
    protected EditText inputBreed;

    @BindView(R.id.input_name)
    protected EditText inputName;

    @OnClick(R.id.button_save_dog)
    protected void onSaveClicked(View view) {
        if(inputAge.length() > 0 && inputBreed.length() > 0 && inputName.length() > 0) {
            getDogImage(inputBreed.getText().toString());
        } else {
            toastInputError();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);
        ButterKnife.bind(this);
        buildRetrofit();
        buildDatabase();
    }

    private void buildDatabase() {
        dogDatabase = DogDatabase.getDatabase(this);
    }

    private void buildRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dogApi = retrofit.create(DogApi.class);
    }

    private void toastInputError() {
        Toast.makeText(this, "Please input all fields first!", Toast.LENGTH_SHORT).show();
    }

    private void getDogImage(String dogBreed) {
        dogApi.getDogImage(dogBreed).enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                if(response.isSuccessful()) {
                    String name = inputName.getText().toString();
                    String breed = inputBreed.getText().toString();
                    String age = inputAge.getText().toString() + " years old";
                    String dogImage = response.body().getImageUrl();

                    Dog dog = new Dog(name, breed, age, dogImage);
                    dogDatabase.dogDao().insertDog(dog);

                    Toast.makeText(AddDogActivity.this, "Dog Saved!", Toast.LENGTH_SHORT).show();

                    inputAge.setText("");
                    inputBreed.setText("");
                    inputName.setText("");

                    inputBreed.requestFocus();
                } else {
                    Toast.makeText(AddDogActivity.this, "Something went wrong. Do you have an Internet connection?", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                Toast.makeText(AddDogActivity.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
