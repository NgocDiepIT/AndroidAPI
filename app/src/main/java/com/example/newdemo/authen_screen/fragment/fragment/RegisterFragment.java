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
import com.example.newdemo.json_models.request.RegisterSendForm;
import com.example.newdemo.json_models.response.UserInfor;
import com.example.newdemo.network.RetrofitService;
import com.example.newdemo.network.RetrofitUtils;
import com.example.newdemo.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    EditText edtName;
    EditText edtPass;
    EditText re_edtPass;
    EditText edtFullName;
    EditText edtAddress;
    EditText edtPhone;
    Button btnRegister;
    ViewFlipper viewFlipper;

    RetrofitService retrofitService;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register, container, false);

        init(view);
        addListener();

        return view;
    }

    private void init(View view) {
        edtName = view.findViewById(R.id.edtName);
        edtPass = view.findViewById(R.id.edtPass);
        re_edtPass = view.findViewById(R.id.re_edtPass);
        edtFullName = view.findViewById(R.id.edtFullName);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtPhone = view.findViewById(R.id.edtPhone);
        btnRegister = view.findViewById(R.id.btnRegister);
        viewFlipper = view.findViewById(R.id.view_flipper);

        retrofitService = RetrofitUtils.getInstance().createService(RetrofitService.class);
    }

    public void addListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtName.getText().toString();
                String password = edtPass.getText().toString();
                String re_pass = re_edtPass.getText().toString();
                String fullName = edtFullName.getText().toString();
                String address = edtAddress.getText().toString();
                String phone = edtPhone.getText().toString();
                if(username.isEmpty()){
                    Utils.showToast(getContext(), "Username mustn't be empty!");
                } else if(password.isEmpty()){
                    Utils.showToast(getContext(), "Password mustn't be empty!");
                } else if(!re_pass.equals(password)){
                    Utils.showToast(getContext(), "Password doesn't match!");
                } else if(fullName.isEmpty()){
                    Utils.showToast(getContext(), "Fullname mustn't be empty!");
                } else if(address.isEmpty()){
                    Utils.showToast(getContext(), "Address mustn't be empty!");
                } else if(phone.isEmpty()){
                    Utils.showToast(getContext(), "Telephone number mustn't be empty!");
                } else {
                    register(username, password, fullName, address, phone);
                }
            }
        });
    }

    private void register(String username, String password, String fullName, String address, String phone) {
        viewFlipper.setDisplayedChild(1);
        RegisterSendForm registerSendForm = new RegisterSendForm(username, password, fullName, address, phone);


        retrofitService.register(registerSendForm).enqueue(new Callback<UserInfor>(){
            @Override
            public void onResponse(Call<UserInfor> call, Response<UserInfor> response) {

                UserInfor userInfor = response.body();

                if(response.code()==200 && userInfor != null){
                    Log.d("it23", "Register successfully!" + userInfor.toString());
                    Utils.showToast(getActivity(), "Register successfully!");
                    RealmContext.getInstance().addUser(userInfor);
                    goToHome();
                } else {
                    Log.d("it23", "Register code fail!");
                    Utils.showToast(getActivity(), "This account already exists!");
                }
                viewFlipper.setDisplayedChild(0);
            }

            @Override
            public void onFailure(Call<UserInfor> call, Throwable t) {
                Utils.showToast(getActivity(), "Register fail!");
                Log.d("it23", "Register fail!");
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
