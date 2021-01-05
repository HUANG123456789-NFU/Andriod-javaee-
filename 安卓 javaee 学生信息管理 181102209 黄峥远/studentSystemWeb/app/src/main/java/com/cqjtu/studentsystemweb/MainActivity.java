package com.cqjtu.studentsystemweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences loginPreference;
    private EditText userName1;
    private EditText password1;
    private CheckBox remember;
    private Button login;
    public static ArrayList<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = findViewById(R.id.back3);
        v.getBackground().setAlpha(210);
        userName1 = (EditText) findViewById(R.id.userName);
        password1 = (EditText) findViewById(R.id.password);
        remember = (CheckBox) findViewById(R.id.remember);
        login = (Button) findViewById(R.id.login);
        loginPreference = getSharedPreferences("login", MODE_PRIVATE);
        ///要通过loginPreference去记录三个参数（checked，userName，password）
        boolean cheched = loginPreference.getBoolean("checked", false);
        if (cheched) {
            Map<String, Object> m = readLogin();
            if (m != null) {
                userName1.setText((CharSequence) m.get("userName"));
                password1.setText((CharSequence) m.get("password"));
                remember.setChecked(cheched);
            }
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configLoginInfo(remember.isChecked());
                login();
            }
        });
    }
    public void configLoginInfo(boolean checked) {
        SharedPreferences.Editor editor = loginPreference.edit();
        editor.putBoolean("checked", remember.isChecked());
        if (checked) {
            editor.putString("userName", userName1.getText().toString());
            editor.putString("password", password1.getText().toString());
        } else {
            editor.remove("userName").remove("password");
        }
        editor.commit();
    }
    /**
     * 读登录信息
     *
     * @return
     */
    public Map<String, Object> readLogin() {
        Map<String, Object> m = new HashMap<>();
        String userName = loginPreference.getString("userName", "");
        String password = loginPreference.getString("password", "");
        m.put("userName", userName);
        m.put("password", password);
        return m;
    }
    private void login() {
        new Thread() {
            @Override
            public void run() {
                System.out.println("登录成功");
                Map<String, String> params = new HashMap<String, String>();
                String userName = userName1.getText().toString();
                String password = password1.getText().toString();
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/login";
                params.put("password", password);
                params.put("userName", userName);
                String result = HttpUtilsHttpURLConnection.getContextByHttp(url,params);
                    ///返回JSON
                Message msg = new Message();
                msg.what = 0x11;
                Bundle data = new Bundle();
                data.putString("result", result);
                msg.setData(data);
                handler.sendMessage(msg);
            }
            Handler handler=new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what==0x11){
                        Bundle data = msg.getData();
                        String key = data.getString("result");//得到json返回的json
                        System.out.println(key);
                        try {
                            JSONObject json= new JSONObject(key);
                            int code = Integer.parseInt(json.getString("code"));
                            System.out.println(code);
                            if (code==1) {
                                Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent();
                                intent.setClass(MainActivity.this,studentList.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "用户名或密码错误！", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
    }

}
