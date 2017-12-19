package com.example.rodneytressler.week5assessmentkey;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rodneytressler on 12/19/17.
 */

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {

    private final Context context;
    private List<Dog> dogList;

    public DogAdapter(List<Dog> dogList, Context context) {
        this.dogList = dogList;
        this.context = context;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);

        return new DogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {
        holder.bindDog(dogList.get(position));
    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_dog)
        protected ImageView dogImage;

        @BindView(R.id.text_breed)
        protected TextView dogBreed;

        @BindView(R.id.text_name)
        protected TextView dogName;

        @BindView(R.id.text_age)
        protected TextView dogAge;

        public DogViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindDog(Dog dog) {
            dogBreed.setText(dog.getBreed());
            dogName.setText(dog.getName());
            dogAge.setText(dog.getAge());

            Picasso.with(context).load(dog.getImageUrl()).into(dogImage);
        }
    }
}
