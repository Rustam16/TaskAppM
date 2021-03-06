package space.example.taskapp.ui.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import space.example.taskapp.ui.model.Task;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    void insert(Task task);
    @Delete
    void delete(Task task);

    @Query("SELECT * FROM task ORDER BY title ASC")
    List<Task> sortAll();
}
