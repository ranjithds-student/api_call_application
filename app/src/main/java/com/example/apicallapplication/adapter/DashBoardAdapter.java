package com.example.apicallapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apicallapplication.R;
import com.example.apicallapplication.model.PostResponse;

import java.util.ArrayList;
import java.util.List;

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder> {

    private List<PostResponse> responseList;

    public DashBoardAdapter() {
        this.responseList = new ArrayList<>();
    }

    public DashBoardAdapter(List<PostResponse> responseList) {
        if (responseList != null) {
            this.responseList = responseList;
        } else {
            this.responseList = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public DashBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.post_single_item,
                        parent, false);
        return new DashBoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardViewHolder holder, int position) {
//        PostResponse response = new PostResponse();
//        holder.userIdTV
        if (responseList != null && !responseList.isEmpty()) {

            PostResponse response = responseList.get(position);
            holder.userIdTV.setText("user id : " + String.valueOf(response.getUserId()));
            holder.bodyTV.setText("body : " + response.getBody());
            holder.idTV.setText("user id :" + String.valueOf(response.getId()));
            holder.titleTV.setText("user title :" + response.getTitle());


        }
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    static class DashBoardViewHolder extends RecyclerView.ViewHolder {
        TextView userIdTV, idTV, titleTV, bodyTV;

        public DashBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            userIdTV = itemView.findViewById(R.id.userIdTV);
            idTV = itemView.findViewById(R.id.idTV);
            titleTV = itemView.findViewById(R.id.titleTV);
            bodyTV = itemView.findViewById(R.id.bodyTV);
        }
    }
}
