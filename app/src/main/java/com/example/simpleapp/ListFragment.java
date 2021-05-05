package com.example.simpleapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.example.simpleapp.database.Goods;
import com.example.simpleapp.database.GoodsDatabase;
import com.example.simpleapp.database.Tasks;
import com.example.simpleapp.database.TasksDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    private BottomNavigationView topListNavigationView;
    private NavController navControllerList;
    private View view;
    private RecyclerView rcvTasks;
    private TasksAdapter tasksAdapter;
    private List<Tasks> listTasks;
    private FloatingActionButton btnAddTask;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Object ViewHolder;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);

        rcvTasks = view.findViewById(R.id.rcv_tasks);
        btnAddTask = view.findViewById(R.id.todo_add_button);
        tasksAdapter = new TasksAdapter();
        listTasks = new ArrayList<>();
        listTasks = TasksDatabase.getInstance(getActivity()).tasksDAO().getListTasks();

        tasksAdapter.setData(listTasks);
        tasksAdapter.setContext(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvTasks.setLayoutManager(linearLayoutManager);
        rcvTasks.setAdapter(tasksAdapter);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AddNewTask addNewTask = new AddNewTask();
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                startActivity(intent);
            }
        });
        //addTasks();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(tasksAdapter != null){
            tasksAdapter.setData(TasksDatabase.getInstance(getContext()).tasksDAO().getListTasks());
            tasksAdapter.notifyDataSetChanged();
        }
    }

    private void addTasks() {
        Tasks tasks = new Tasks("Làm bài tập Kiến Trúc máy tính", "17/5/2021", false);
        Tasks tasks1 = new Tasks("Làm lại căn cước công dân", "20/5/2021", false);
        TasksDatabase.getInstance(getActivity()).tasksDAO().insertTasks(tasks);
        TasksDatabase.getInstance(getActivity()).tasksDAO().insertTasks(tasks1);
        listTasks = TasksDatabase.getInstance(getActivity()).tasksDAO().getListTasks();
        tasksAdapter.setData(listTasks);
    }
}