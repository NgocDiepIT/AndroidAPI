package com.example.newdemo.authen_screen.fragment.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.newdemo.R;
import com.example.newdemo.adapter.StatusAdapter;
import com.example.newdemo.dbcontext.RealmContext;
import com.example.newdemo.json_models.request.CreateStatusSendForm;
import com.example.newdemo.json_models.response.Status;
import com.example.newdemo.json_models.response.UserInfor;
import com.example.newdemo.network.RetrofitService;
import com.example.newdemo.network.RetrofitUtils;
import com.example.newdemo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewFeedsFragment extends Fragment {
    private RetrofitService retrofitService;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    TextView tvPost;
    UserInfor user;

    CircleImageView newfeesAvatar, dialogAvatar;
    EditText edtPost;

    StatusAdapter statusAdapter;
    ArrayList<Status> statusList;

    public NewFeedsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_feeds, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        addListener();

        user = RealmContext.getInstance().getUser();
        if (user != null) {
            Glide.with(getActivity()).load(user.getAvatar()).into(newfeesAvatar);
            getAllPost(user.getUserId());
        }
    }

    private void addListener() {
        tvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.layout_dialog_post, null);
                builder.setView(dialogView);
                builder.setCancelable(false);

                dialogAvatar = dialogView.findViewById(R.id.dialog_ava);
                edtPost = dialogView.findViewById(R.id.edt_post);

                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String content = edtPost.getText().toString();
                        if(content.isEmpty()){
                            Utils.showToast(getActivity(), "You didn't input content to post!");
                        } else {
                            createPost(content);
                        }
                    }
                });
                builder.setNegativeButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            private void createPost(String content) {
                CreateStatusSendForm sendForm = new CreateStatusSendForm(user.getUserId(), content);
                retrofitService.createPost(sendForm).enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                        Status status = response.body();
                        if(response.code() == 200 && status != null){
                            statusList.add(0, status);
                            statusAdapter.notifyDataSetChanged();
                            edtPost.setText("");
                        } else {
                            Utils.showToast(getActivity(), "Post fail!");
                        }
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                        Utils.showToast(getActivity(), "No internet!");
                    }
                });
            }
        });
    }

    private void init(View view) {
        retrofitService = RetrofitUtils.getInstance().createService(RetrofitService.class);
        viewFlipper = view.findViewById(R.id.flipper_new_feeds);
        tvPost = view.findViewById(R.id.tv_post);
        recyclerView = view.findViewById(R.id.rv_status);
        newfeesAvatar = view.findViewById(R.id.newfeeds_ava);


        statusList = new ArrayList<>();
        statusAdapter = new StatusAdapter(statusList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(statusAdapter);
    }

    private void getAllPost(String userId) {
        viewFlipper.setDisplayedChild(0);
        retrofitService.getAllPost(userId).enqueue(new Callback<List<Status>>() {
            @Override
            public void onResponse(Call<List<Status>> call, Response<List<Status>> response) {
                ArrayList<Status> statuses = (ArrayList<Status>) response.body();
                if (response.code() == 200 && statusList != null) {
                    statusList.clear();
                    statusList.addAll(statuses);
                    statusAdapter.notifyDataSetChanged();
                    viewFlipper.setDisplayedChild(2);
                    for (Status status : statusList) {
                        Log.d("stt", "Thanh cong!" + status.toString());
                    }
                }
                else {
                    viewFlipper.setDisplayedChild(1);
                }
            }

            @Override
            public void onFailure(Call<List<Status>> call, Throwable t) {
                viewFlipper.setDisplayedChild(1);
                Utils.showToast(getContext(), "No Internet!");
                Log.d("stt", "That bai!");
            }
        });

    }
}