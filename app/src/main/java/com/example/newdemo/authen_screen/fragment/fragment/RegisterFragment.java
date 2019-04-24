package com.example.newdemo.authen_screen.fragment.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.newdemo.R;
import com.example.newdemo.utils.Utils;

public class RegisterFragment extends Fragment {
    EditText edtName;
    EditText edtPass;
    EditText re_edtPass;
    EditText edtFullname;
    EditText edtAddress;
    EditText edtPhone;
    Button btnRegister;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register, container, false);

        edtName = view.findViewById(R.id.edtName);
        edtPass = view.findViewById(R.id.edtPass);
        re_edtPass = view.findViewById(R.id.re_edtPass);
        edtFullname = view.findViewById(R.id.edtFullname);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtPhone = view.findViewById(R.id.edtPhone);
        btnRegister = view.findViewById(R.id.btnRegister);

        addListener();

        return view;
    }

    public void addListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtName.getText().toString();
                String password = edtPass.getText().toString();
                String re_pass = re_edtPass.getText().toString();
                String fullname = edtFullname.getText().toString();
                String address = edtAddress.getText().toString();
                String phone = edtPhone.getText().toString();
                if(username.isEmpty()){
                    Utils.showToast(getContext(), "Username mustn't be empty!");
                } else if(password.isEmpty()){
                    Utils.showToast(getContext(), "Password mustn't be empty!");
                } else if(!re_pass.equals(password)){
                    Utils.showToast(getContext(), "Password doesn't match!");
                } else if(fullname.isEmpty()){
                    Utils.showToast(getContext(), "Fullname mustn't be empty!");
                } else if(address.isEmpty()){
                    Utils.showToast(getContext(), "Address mustn't be empty!");
                } else if(phone.isEmpty()){
                    Utils.showToast(getContext(), "Telephone number mustn't be empty!");
                } else {
                    register(username, password, fullname, address, phone);
                }
            }
        });
    }

    private void register(String username, String password, String fullname, String address, String phone) {
    }
}
