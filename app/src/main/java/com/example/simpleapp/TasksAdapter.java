package com.example.simpleapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import com.example.simpleapp.database.Goods;
import com.example.simpleapp.database.Tasks;
import com.example.simpleapp.database.TasksDatabase;

import org.w3c.dom.Document;

import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder>{
    Context context;
    private List<Tasks> listTasks;
    public void setContext(Context context){
        this.context = context;
    }
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

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.TaskViewHolder holder, int position) {
        Tasks tasks = listTasks.get(position);
        if (tasks == null) return;
        holder.cbContent.setTag(listTasks.get(position));
        holder.cbContent.setText(tasks.getContent());
        holder.cbContent.setChecked(tasks.getStatus());
        holder.tvDueDate.setText(tasks.getDueDate());
        holder.tvIdTask.setText(tasks.getId()+"");
        int tg = numberDaysToDueDate(tasks.getDueDate());
        if (tasks.getStatus()) {
            holder.tvNumberDayToDueDate.setText("Đã hoàn thành");
            holder.tvNumberDayToDueDate.setTextColor(Color.parseColor("#238C2A"));
        } else {
            if (tg >= 0) {
                if (tg <= 2) {
                    holder.tvNumberDayToDueDate.setText("Sắp hết hạn " + "(còn" + tg + " ngày)");
                    holder.tvNumberDayToDueDate.setTextColor(Color.parseColor("#C81D1E"));
                } else {
                    holder.tvNumberDayToDueDate.setText("Còn " + tg + " ngày");
                    holder.tvNumberDayToDueDate.setTextColor(Color.parseColor("#238C2A"));
                }
            } else {
                holder.tvNumberDayToDueDate.setText("Đã quá hạn");
                holder.tvNumberDayToDueDate.setTextColor(Color.parseColor("#FF000000"));
            }
        }

        holder.cbContent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                listTasks.get(position).setStatus(isChecked);
                TasksDatabase.getInstance(context).tasksDAO().Update(listTasks.get(position));
                Log.e("TaskAdapter",""+isChecked);
//                notifyDataSetChanged();
                if (tasks.getStatus()) {
                    holder.tvNumberDayToDueDate.setText("Đã hoàn thành");
                    holder.tvNumberDayToDueDate.setTextColor(Color.parseColor("#238C2A"));
                } else {
                    if (tg >= 0) {
                        if (tg <= 2) {
                            holder.tvNumberDayToDueDate.setText("Sắp hết hạn " + "(" + tg + " ngày)");
                            holder.tvNumberDayToDueDate.setTextColor(Color.parseColor("#C81D1E"));
                        } else {
                            holder.tvNumberDayToDueDate.setText("Còn " + tg + " ngày");
                            holder.tvNumberDayToDueDate.setTextColor(Color.parseColor("#238C2A"));
                        }
                    } else {
                        holder.tvNumberDayToDueDate.setText("Đã quá hạn");
                        holder.tvNumberDayToDueDate.setTextColor(Color.parseColor("#FF000000"));
                    }
                }
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
        private TextView tvIdTask, tvNumberDayToDueDate;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            cbContent = itemView.findViewById(R.id.tv_list_check_box);
            tvDueDate = itemView.findViewById(R.id.tv_task_due_date);
            tvIdTask = itemView.findViewById(R.id.tv_id_task);
            tvNumberDayToDueDate = itemView.findViewById(R.id.tv_task_number_date_to_due_date);
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int numberDaysToDueDate (String dueDateThisTask) {
        String thisDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        int[] daysMonth = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int tg1 = 0;
        for (int i=1; i<getMonth(thisDate); i++) {
            tg1 += daysMonth[i];
        }
        tg1 += getDay(thisDate);
        int tg2 = 0;
        for (int i=1; i<getMonth(dueDateThisTask); i++) {
            tg2 += daysMonth[i];
        }
        tg2 += getDay(dueDateThisTask);
        return tg2 - tg1;
    }
    public int getDay(String s) {
        return (s.charAt(0)-'0')*10 + (s.charAt(1)-'0');
    }
    public int getMonth(String s) {
        return (s.charAt(3)-'0')*10 + (s.charAt(4)-'0');
    }
}
