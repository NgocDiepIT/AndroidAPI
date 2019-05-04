package com.example.newdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newdemo.R;
import com.example.newdemo.json_models.response.Status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.lang.String.valueOf;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyViewHolder> {
    ArrayList<Status> statusList;
    public StatusAdapter(ArrayList<Status> statusList) {
        this.statusList = statusList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_status,
                viewGroup, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bindView(statusList.get(i));
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvContent, tvUsername, tvDatetime, tvCountLike, tvCountComment;
        TextView tvLike;
        ImageView ivLike;
        CircleImageView ivAva;
        Context context;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvDatetime = itemView.findViewById(R.id.tv_dateTime);
            tvCountLike = itemView.findViewById(R.id.tv_countLike);
            tvCountComment = itemView.findViewById(R.id.tv_countComment);
            ivLike = itemView.findViewById(R.id.iv_like);
            tvLike = itemView.findViewById(R.id.tv_like);
            ivAva = itemView.findViewById(R.id.iv_ava);
        }

        private void bindView(Status status){

            Glide.with(context).load(status.getAuthorAvatar()).into(ivAva);
            tvContent.setText(status.getContent());
            tvUsername.setText(status.getAuthor());

//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            Date date = new Date();
//            tvDatetime.setText(dateFormat.format(date));
            tvDatetime.setText(status.getCreateDate());

            tvCountLike.setText(valueOf(status.getNumberLike()));
            tvCountComment.setText(valueOf(status.getNumberComment()));

            if(status.isLike()){
                ivLike.setBackground(context.getResources().getDrawable(R.drawable.ic_liked));
                tvLike.setTextColor(context.getResources().getColor(R.color.red));
            }
            else {
                ivLike.setBackground(context.getResources().getDrawable(R.drawable.ic_like));
                tvLike.setTextColor(context.getResources().getColor(R.color.black));
            }
        }
    }
}
