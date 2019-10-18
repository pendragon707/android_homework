package ru.nonpenguin.dz_1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private static String ARRAY_KEY = "Numbers";
    private RecyclerView recyclerView;
    private MyDataAdapter myAdapter;
    private FragmentNavigator navigator;
    private int sizeOfList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
        {
            myAdapter = new MyDataAdapter(savedInstanceState.getIntegerArrayList(ARRAY_KEY));
        }
        else
        {
            myAdapter = new MyDataAdapter((new ArrayList<Integer>()));
            for (int i = 1; i <= 100; i++)
                myAdapter.mData.add(i);
        }

        if (getActivity() instanceof FragmentNavigator) {
            navigator = (FragmentNavigator) getActivity();
        }
    }

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        recyclerView = view.findViewById(R.id.list);
        int columns = getResources().getBoolean(R.bool.is_horizontal) ? 4 : 3;

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columns));
        recyclerView.setAdapter(myAdapter);

        Button button = view.findViewById(R.id.add_num);
        button.setOnClickListener(v -> myAdapter.addElement());
        myAdapter.setData(sizeOfList);

        return view;
    }

    public void onSaveInstanceState(@NonNull  Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(ARRAY_KEY, myAdapter.mData);
    }

    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {

        public ArrayList<Integer> mData;

        public MyDataAdapter(ArrayList<Integer> data) {
            mData = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            int data = mData.get(position), color;

            holder.num.setText(String.valueOf(data));
            color = (position % 2 == 0) ?
                    Color.RED :
                    Color.BLUE;
            holder.num.setTextColor(color);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        void setData(int size) {
            for(int i = getItemCount() + 1; i <= size; i++)
                mData.add(i);
            sizeOfList = size;
            notifyItemInserted(size);
        }
        void addElement() {
            int pos = getItemCount() + 1;
            mData.add(pos);
            sizeOfList++;
            notifyItemInserted(pos);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView num;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.item);

            num.setOnClickListener( v -> {
                int pos = getAdapterPosition() + 1;
                navigator.navigateToFragment(pos);
            });
        }
    }

}
