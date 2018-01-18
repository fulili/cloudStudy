package com.example.a.cloudstudy.fragments;

//import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.cloudstudy.R;
import com.example.a.cloudstudy.RegisterActivity;
import com.example.a.cloudstudy.entity.UserRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by jfl on 2018/1/17.
 */

public class FourthFragment extends Fragment {
    static EditText username,password;;
    Button login,register;
    TextView reultMsg;
    String _username,_password;
    String url = "http://10.73.133.19:8085/";
    private Novate novate;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private Map<String, String> headers = new HashMap<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login,container,false);

        username=(EditText) view.findViewById(R.id.text_userName);
        password=(EditText)view.findViewById(R.id.text_userPwd);
        login=(Button)view.findViewById(R.id.btn_login);
        register=(Button)view.findViewById(R.id.btn_register);
        reultMsg=(TextView)view.findViewById(R.id.resultMsg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perform();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),RegisterActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void perform() {
            parameters = new HashMap<>();
        /*start=0&count=5*/
//            parameters.put("start", "0");
//            parameters.put("count", "1");
            _username=username.getText().toString().trim();
            _password=password.getText().toString().trim();
            if (TextUtils.isEmpty(_username) || TextUtils.isEmpty(_password)) {
                Toast.makeText(getActivity(), "用户名与密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("username", _username);
            parameters.put("userpassword", _password);

            novate = new Novate.Builder(getActivity())
                    .addParameters(parameters)
                    .connectTimeout(10)
                    .baseUrl(url)
                    //.addApiManager(ApiManager.class)
                    .addLog(true)
                    .build();

            novate.post("loginxin", parameters, new BaseSubscriber<ResponseBody>() {
                @Override
                public void onError(Throwable e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(ResponseBody responseBody) {
                    try {
                        String jstr = new String(responseBody.bytes());
                        Type type = new TypeToken<UserRequest>() {
                        }.getType();

                        UserRequest response = new Gson().fromJson(jstr, type);
                        Toast.makeText(getActivity(), "登录成功"+response.toString(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
}
