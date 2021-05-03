package space.example.taskapp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import space.example.taskapp.App;
import space.example.taskapp.OnItemClickListener;
import space.example.taskapp.R;
import space.example.taskapp.ui.model.Task;

public class HomeFragment extends Fragment {
    TaskAdapter adapter;
    private LinearLayout containerTxtTitle;
    private int positionTo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TaskAdapter(getContext());
        List<Task> list = App.appDataBase.taskDao().getAll();
        adapter.addList(list);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.fab).setOnClickListener(v -> {
            openForm();
        });
        adapter.setonClick(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                alertDialogClick(position);
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onClick(int position) {
                positionTo = position;
                Bundle bundle = new Bundle();
                bundle.putSerializable("keyToo", adapter.getList().get(position));
                getParentFragmentManager().setFragmentResult("key", bundle);
                openForm();
            }
        });
        initList(view);
        getParentFragmentManager().setFragmentResultListener("rk_task", getViewLifecycleOwner(), (requestKey, result) -> {
            Task title = (Task) result.getSerializable("title");
            Log.e("Home", "title = " + title);
            adapter.addItem(title);
            containerTxtTitle = view.findViewById(R.id.containerTxtTile);
        });
        getParentFragmentManager().setFragmentResultListener("keys", getViewLifecycleOwner(), (requestKey, result) -> {
            Task title = (Task) result.getSerializable("tast");
            adapter.getList().set(positionTo, title);
            adapter.notifyDataSetChanged();
        });


    }

    private void initList(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);

    }

    private void openForm() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.formFragment);

    }

    private void alertDialogClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("DELETE" + containerTxtTitle + " ?");
        builder.setMessage("Are you sure you want to delete " + containerTxtTitle + " ?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.getList().remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("cancel", null);
        builder.create();
        builder.show();
    }

}