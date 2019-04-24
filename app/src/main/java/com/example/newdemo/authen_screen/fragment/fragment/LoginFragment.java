package com.example.newdemo.authen_screen.fragment.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.newdemo.R;
import com.example.newdemo.home_screen.HomeActivity;
import com.example.newdemo.json_models.request.LoginSendForm;
import com.example.newdemo.json_models.response.UserInfor;
import com.example.newdemo.network.RetrofitService;
import com.example.newdemo.network.RetrofitUtils;
import com.example.newdemo.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    EditText edtName;
    EditText edtPass;
    Button btnLogin;
    RetrofitService retrofitService;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        edtName = view.findViewById(R.id.edtName);
        edtPass = view.findViewById(R.id.edtPass);
        btnLogin = view.findViewById(R.id.btnLogin);

        retrofitService = RetrofitUtils.getInstance().createService(RetrofitService.class);

        addListener();
        return view;
    }

    public void addListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtName.getText().toString();
                String password = edtPass.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    Utils.showToast(getContext(), "Username or Password mustn't be empty!");
                } else 
                    login(username,password);
            }
        });
    }

    private void login(String username, String password) {
        LoginSendForm sendForm = new LoginSendForm(username, password);
        retrofitService.login(sendForm).enqueue(new Callback<UserInfor>() {
            @Override
            public void onResponse(Call<UserInfor> call, Response<UserInfor> response) {
                UserInfor userInfor = response.body();
                if(response.code()==200 && userInfor != null){
//                    Log.d("it", "Login successfully!" + userInfor.toString());
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                } else {
//                    Log.d("it", "Login code fail!");
                    Utils.showToast(getActivity(), "Username or Password is incorrect!");
                }
            }

            @Override
            public void onFailure(Call<UserInfor> call, Throwable t) {
                Log.d("it", "LOGIN FAIL!");
            }
        });
    }

}
