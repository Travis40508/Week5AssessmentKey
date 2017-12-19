package com.example.rodneytressler.week5assessmentkey;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by rodneytressler on 12/19/17.
 */

@Database(version = 1, entities = {Dog.class})
public abstract class DogDatabase extends RoomDatabase {

    private static DogDatabase INSTANCE;

    abstract public DogDao dogDao();

    public static DogDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DogDatabase.class, "dog-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
