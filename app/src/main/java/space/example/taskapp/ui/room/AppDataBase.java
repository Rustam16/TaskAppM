package space.example.taskapp.ui.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import space.example.taskapp.ui.model.Task;

@Database(entities = {Task.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract TaskDao taskDao();
}
