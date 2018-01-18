package com.example.a.cloudstudy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a.cloudstudy.entity.UserRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

public class RegisterActivity extends Activity {
    EditText username,password;
    Button register;
    RadioGroup userType;
    String _username,_password,_userType;

    String url = "http://10.73.133.19:8085/";
    private Novate novate;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private Map<String, String> headers = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=(EditText) findViewById(R.id.editText_username);
        password=(EditText)findViewById(R.id.editText_password);
        userType=(RadioGroup)findViewById(R.id.userType);
        register=(Button)findViewById(R.id.button_regist);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                perform();
            }
        });
    }
    private void perform() {

        parameters = new HashMap<>();
        /*start=0&count=5*/
//        parameters.put("start", "0");
//        parameters.put("count", "1");
        _username=username.getText().toString().trim();
        _password=password.getText().toString().trim();
        _userType=((RadioButton)RegisterActivity.this.findViewById(userType.getCheckedRadioButtonId())).getText().toString();

        if (TextUtils.isEmpty(_username) || TextUtils.isEmpty(_password)) {
            Toast.makeText(RegisterActivity.this, "用户名与密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", encodeHeadInfo(_username));
        parameters.put("userpassword", encodeHeadInfo(_password));
        parameters.put("usertype",encodeHeadInfo(_userType));

        novate = new Novate.Builder(this)
                .addParameters(parameters)
                .connectTimeout(10)
                .baseUrl(url)
                //.addApiManager(ApiManager.class)
                .addLog(true)
                .build();

        novate.post("register", parameters, new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String jstr = new String(responseBody.bytes());
                    Type type = new TypeToken<UserRequest>() {
                    }.getType();

                    UserRequest response = new Gson().fromJson(jstr, type);
                    Toast.makeText(RegisterActivity.this, "注册成功"+response.toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

//    private String getOsDisplay() {
//        String osStr = StringUtil.removeSpace(android.os.Build.DISPLAY);
//        if (osStr.length() < osStr.getBytes().length) {
//            try {
//                return URLEncoder.encode(StringUtil.removeSpace(android.os.Build.DISPLAY), "UTF-8");
//            } catch (Exception e) {
//                e.printStackTrace();
//                return "";
//            }
//        } else {
//            return osStr;
//        }
//    }
    private static String encodeHeadInfo( String headInfo ) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, length = headInfo.length(); i < length; i++) {
            char c = headInfo.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                stringBuffer.append( String.format ("\\u%04x", (int)c) );
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1 && resultCode==2 && data!=null){
            Bundle bundle=data.getExtras();
//            int imageId=bundle.getInt("imageId");
//            imageview1.setImageResource(imageId);
        }
    }
}
