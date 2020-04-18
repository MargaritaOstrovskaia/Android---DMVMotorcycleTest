package com.ostrov.dmvmotorcycletest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RVTopicsAdapter extends RecyclerView.Adapter<RVTopicsAdapter.TopicViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Topic item);
    }
    private final ArrayList<Topic> topics;
    private final OnItemClickListener listener;

    RVTopicsAdapter(ArrayList<Topic> topics, OnItemClickListener listener) {
        this.topics = topics;
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, final int position) {
        holder.bind(topics.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    static class TopicViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tv;

        TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv_topic);
            tv = itemView.findViewById(R.id.text_view_topic);
        }

        void bind(final Topic item, final OnItemClickListener listener) {
            String text = String.format("%s (%d)", item.getTitle(), item.getCountOfQuestions());
            tv.setText(text);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
