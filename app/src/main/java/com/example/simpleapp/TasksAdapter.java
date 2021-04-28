package com.example.simpleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleapp.database.Goods;
import com.example.simpleapp.database.Tasks;

import org.w3c.dom.Document;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder>{
    Context context;
    private List<Tasks> listTasks;
    protected void setData(List <Tasks> list) {
        this.listTasks= list;
        Collections.reverse(listTasks);
        notifyDataSetChanged();
    }
    public TasksAdapter(){};
//    public TasksAdapter(Context context, List<Tasks> listTasks) {
//        this.context = context;
//        this.listTasks = listTasks;
//    }
    @NonNull
    @Override
    public TasksAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.TaskViewHolder holder, int position) {
        Tasks tasks = listTasks.get(position);
        if (tasks == null) return;
        holder.cbContent.setText(tasks.getContent());
        holder.cbContent.setTag(listTasks.get(position));
        holder.cbContent.setChecked(tasks.getStatus());
        holder.tvDueDate.setText(tasks.getDueDate());

        //holder.cbContent.setOnCheckedChangeListener(null);

        //holder.cbContent.setChecked(listTasks.get(position).getStatus());

        holder.cbContent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listTasks.get(holder.getAdapterPosition()).setStatus(true);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listTasks != null) {
            return listTasks.size();
        }
        return 0;
    }
    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cbContent;
        private TextView tvDueDate;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            cbContent = itemView.findViewById(R.id.tv_list_check_box);
            tvDueDate = itemView.findViewById(R.id.tv_task_due_date);

        }

    }
//    interface TasksListener {
//        public void handleCheckChanged(boolean isChecked, DocumentSnapshot snapshot);
//    }

}
