package space.example.taskapp.ui.onboard;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import space.example.taskapp.OnItemClickListener;
import space.example.taskapp.Prefs;
import space.example.taskapp.R;
import space.example.taskapp.ui.home.HomeFragment;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    public String[] titles = new String[]{"fast", "conveniently", "setting"};
    String[] description = new String[]{"You can quickly rewrite with another",
            "convenient to use in everyday life",
            "convenient use of settings"};
    private OnItemClickListener onItemClickListener;
    FragmentManager manager;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return titles.length;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDesc, tv_skip;
        Button btnStart;
        ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            btnStart = itemView.findViewById(R.id.btnStart);
            imgView = itemView.findViewById(R.id.imageView);
            tv_skip = itemView.findViewById(R.id.tvSkip);

            btnStart.setOnClickListener(v -> {
                onItemClickListener.onClick(getAdapterPosition());
            });

            tv_skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition() - 1);
                }
            });
        }

        public void bind(int position) {
            txtTitle.setText(titles[(position)]);
            txtDesc.setText(description[(position)]);
            switch (position) {
                case 0:
                    imgView.setImageResource(R.drawable.fast);
                    break;
                case 1:
                    imgView.setImageResource(R.drawable.onbic);
                    break;
                case 2:
                    imgView.setImageResource(R.drawable.settings);
                    break;
            }
            if (position == titles.length - 1) btnStart.setVisibility(View.VISIBLE);
            else btnStart.setVisibility(View.INVISIBLE);
            if (position == titles.length - 3) tv_skip.setVisibility(View.VISIBLE);
            else tv_skip.setVisibility(View.INVISIBLE);

        }
    }

}
