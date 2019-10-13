package ru.nonpenguin.dz_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyDataAdapter myAdapter;
    private int SizeOfTheList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int columns = getResources().getBoolean(R.bool.is_horizontal) ? 4 : 3;

        RecyclerView recyclerView = findViewById(R.id.list);

        myAdapter = new MyDataAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        recyclerView.setAdapter(myAdapter);

              if(getResources().getBoolean(R.bool.is_horizontal))
              {
                  Toast toast1 = Toast.makeText(getApplicationContext(), "horizontal", Toast.LENGTH_SHORT);
                          toast1.setGravity(Gravity.TOP, 0, 0);
                          toast1.show();
              }
              else
              {
                  Toast toast2 = Toast.makeText(getApplicationContext(), "vertical", Toast.LENGTH_SHORT);
                  toast2.setGravity(Gravity.TOP, 0, 0);
                  toast2.show();
              }

    }

    public void onButtonClick(View view)
    {
        myAdapter.addNum();
    }

    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {

        List<Integer> mData;

        MyDataAdapter() {
            mData = new ArrayList<>();
        }

        public MyDataAdapter(List<Integer> data) {
            mData = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("ListActivity", "onCreateViewHolder " + viewType);
                    View view = LayoutInflater
                            .from(parent.getContext())
                            .inflate(R.layout.list_item, parent, false);
                    return new MyViewHolder(view);
            }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            int data = mData.get(position), color;

            holder.num.setText(String.valueOf(data));
            color = (position%2 == 0) ?
                    Color.BLUE :
                    Color.RED;
            holder.num.setTextColor(color);
            Log.d("ListActivity", "onBindViewHolder " + position);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        void addNum() {
            int pos = getItemCount() + 1;
            mData.add(pos);
            SizeOfTheList++;
            notifyDataSetChanged();
            for(int i = getItemCount() + 1; i <= mData.size(); i++)
                mData.add(i);
            SizeOfTheList = mData.size();
            notifyDataSetChanged();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView num;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.item);
            num.setOnClickListener(view -> {
                int pos = getAdapterPosition() + 1;
                int currentTextColor = num.getCurrentTextColor();
            });
        }
    }

}
