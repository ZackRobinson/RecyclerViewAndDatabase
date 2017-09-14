package com.example.zack.recyclerviewanddatabase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapterTag";
    /**
     * Created by Zack on 9/14/2017.
     */
    List<Actor> actorList = new ArrayList<>();
    Context context;
    public RecyclerViewAdapter(List<Actor> actorList, Context context) {
        this.actorList = actorList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvActorName;
        private TextView tvActorAge;
        private TextView tvActorGender;
        private TextView tvActorHeight;

        public ViewHolder(View itemView) {
            super(itemView);
            tvActorName = (TextView) itemView.findViewById(R.id.tvActorName);
            tvActorAge = (TextView) itemView.findViewById(R.id.tvActorAge);
            tvActorGender = (TextView) itemView.findViewById(R.id.tvActorGender);
            tvActorHeight = (TextView) itemView.findViewById(R.id.tvActorHeight);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_item, parent, false);
        Log.d(TAG, "onCreateViewHolder: ");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Actor actor = actorList.get(position);
        holder.tvActorName.setText(actor.getName());
        holder.tvActorAge.setText(String.valueOf(actor.getAge()));
        holder.tvActorGender.setText(actor.getGender());
        holder.tvActorHeight.setText(String.valueOf(actor.getHeight()));

        Toast.makeText(context, "RecyclerViewAdapter is calling onBindViewHolder", Toast.LENGTH_SHORT).show();

        Log.d(TAG, "onBindViewHolder: ");
        // somewhere here do a thing with animation

    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }
}
