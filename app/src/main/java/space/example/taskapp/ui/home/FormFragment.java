package space.example.taskapp.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

import space.example.taskapp.App;
import space.example.taskapp.R;
import space.example.taskapp.ui.model.Task;

public class FormFragment extends Fragment {
    private EditText editText;
    private Task task;
    private boolean isEdit = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Task task = (Task) result.getSerializable("keyToo");
                editText.setText(task.getTitle());
                isEdit = true;
            }
        });
        view.findViewById(R.id.btnSave).setOnClickListener(v -> {
            if (isEdit){
                save("keys","tast");
            }else{
                save("rk_task","title");
            }
        });
    }


    private void save(String key,String keyBundle) {
        String text = editText.getText().toString();
        Task task = new Task(text,System.currentTimeMillis());
        App.appDataBase.taskDao().insert(task);


        Date date = new Date();
        task = new Task(text,date.getTime());
        Bundle bundle = new Bundle();
        bundle.putSerializable(keyBundle,task);
        getParentFragmentManager().setFragmentResult(key, bundle);
        close();
    }
    private void close(){
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigateUp();
    }
}