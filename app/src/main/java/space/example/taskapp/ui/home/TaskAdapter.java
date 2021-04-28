package space.example.taskapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import space.example.taskapp.OnItemClickListener;
import space.example.taskapp.R;
import space.example.taskapp.ui.model.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ArrayList<Task> list;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private OnItemClickListener clickListener;

    public TaskAdapter(Context cOntext) {
        this.list = new ArrayList<>();
        this.context = cOntext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder  holder, int position) {
        holder.bind(list.get(position));
    }

    public void setonClick(OnItemClickListener listener) {
        clickListener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void addItem(Task title) {
        list.add(0, title);
        notifyItemInserted(list.size() - 1);
    }

    public ArrayList<Task> getlist() {
        return list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout containerTxtTitle;
        private TextView textTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            containerTxtTitle = itemView.findViewById(R.id.containerTxtTile);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onClick(getAdapterPosition());
                    return false;
                }
            });

        }

        public void bind(Task task) {
            textTitle.setText(task.getTitle());
        }
    }

    public void addList(List<Task> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
