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
import android.widget.ViewFlipper;

import com.example.newdemo.R;
import com.example.newdemo.dbcontext.RealmContext;
import com.example.newdemo.home_screen.HomeActivity;
import com.example.newdemo.json_models.request.LoginSendForm;
import com.example.newdemo.json_models.response.UserInfor;
import com.example.newdemo.network.RetrofitService;
import com.example.newdemo.network.RetrofitUtils;
import com.example.newdemo.utils.Utils;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    EditText edtName;
    EditText edtPass;
    Button btnLogin;
    RetrofitService retrofitService;
    ViewFlipper viewFlipper;

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
        viewFlipper = view.findViewById(R.id.view_flipper);

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
        viewFlipper.setDisplayedChild(1);
        LoginSendForm sendForm = new LoginSendForm(username, password);

        retrofitService.login(sendForm).enqueue(new Callback<UserInfor>() {
            @Override
            public void onResponse(Call<UserInfor> call, Response<UserInfor> response) {
                UserInfor userInfor = response.body();

                if(response.code()==200 && userInfor != null){
                    Log.d("it23", "Login successfully!" + userInfor.toString());
                    RealmContext.getInstance().addUser(userInfor);

                    goToHome();
                } else {
                    Log.d("it23", "Login code fail!");
                    Utils.showToast(getActivity(), "Username or Password is incorrect!");
                }
                viewFlipper.setDisplayedChild(0);
            }

            @Override
            public void onFailure(Call<UserInfor> call, Throwable t) {
                Log.d("it23", "No Internet!");
                Utils.showToast(getActivity(), "Login fail!");
                viewFlipper.setDisplayedChild(0);
            }
        });
    }

    private void goToHome() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
