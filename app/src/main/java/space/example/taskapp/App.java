package space.example.taskapp;

import android.app.Application;

import androidx.room.Room;

import space.example.taskapp.ui.room.AppDataBase;

public class App  extends Application {
    public static AppDataBase appDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase = Room.
                databaseBuilder(this,AppDataBase.class,"database")
                .allowMainThreadQueries()
                .build();
    }
}
