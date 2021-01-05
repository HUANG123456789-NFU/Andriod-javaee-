package com.cqjtu.studentsystemweb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cqjtu.studentsystemweb.MainActivity.students;

public class studentList extends AppCompatActivity implements StuAdapter.InnerItemOnClickListener
        , AdapterView.OnItemClickListener, View.OnClickListener{
    private ListView listView;
    private Button add;
    public static StuAdapter adapter;
    private ImageButton search;
    private EditText find1;
    public static List<Student>find;
    private List<Student>show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        listView = findViewById(R.id.listView);
        add = (Button) findViewById(R.id.addStudent);
        add.setOnClickListener(this);
        find1=(EditText)findViewById(R.id.find);
        search=(ImageButton)findViewById(R.id.search);
        search.setOnClickListener(this);
        students.clear();
        if (find!=null) {
            find.clear();
        }
        new Thread() {
            @Override
            public void run() {
                String url = HttpUtilsHttpURLConnection.BASE_URL + "/studentQuery";
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", "1");
                params.put("key", "");
                String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                ///返回JSON
                Message msg = new Message();
                msg.what = 0x11;
                Bundle data = new Bundle();
                data.putString("result", result);
                System.out.println(result);
                msg.setData(data);
                handler.sendMessage(msg);
            }
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x11) {
                        Bundle data = msg.getData();
                        String key = data.getString("result");//得到json返回的json
                        try {
                            JSONObject json = new JSONObject(key);
                            JSONArray jsonArray = new JSONArray(json.get("students").toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                String id = jsonObject.get("no").toString();
                                String name = (String) jsonObject.get("name");
                                String sex = (String) jsonObject.get("sex");
                                String age = jsonObject.get("age").toString();
                                String major = (String) jsonObject.get("major");
                                Student s = new Student(id, name, sex, age, major);
                                students.add(s);
                            }
                            adapter = new StuAdapter(studentList.this, students);
                            listView.setAdapter(adapter);
                            adapter.setOnInnerItemOnClickListener(studentList.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
        listView.setOnItemClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        if (view.getId()==R.id.addStudent){
            intent.setClass(studentList.this,addStudent.class);
            startActivity(intent);
        }else if(view.getId()==R.id.search){
            final String key=find1.getText().toString();
            if (isEmpty(key)) {
                Toast.makeText(this,"查找条件不能为空",Toast.LENGTH_SHORT).show();
                return;
            }
            find=new ArrayList<Student>();
            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/studentQuery";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("flag", "1");
                    params.put("key",key);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    ///返回JSON
                    Message msg = new Message();
                    msg.what = 0x11;
                    Bundle data = new Bundle();
                    data.putString("result", result);
                    System.out.println(result);
                    msg.setData(data);
                    handler.sendMessage(msg);
                }
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 0x11) {
                            Bundle data = msg.getData();
                            String key = data.getString("result");//得到json返回的json
                            try {
                                JSONObject json = new JSONObject(key);
                                JSONArray jsonArray = new JSONArray(json.get("students").toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                    String id = jsonObject.get("no").toString();
                                    String name = (String) jsonObject.get("name");
                                    String sex = (String) jsonObject.get("sex");
                                    String age = jsonObject.get("age").toString();
                                    String major = (String) jsonObject.get("major");
                                    Student s = new Student(id, name, sex, age, major);
                                    find.add(s);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
            }.start();
            intent.setClass(studentList.this,searchlist.class);
            intent.putExtra("find",(Serializable)find);
            startActivity(intent);
        }
    }
    private boolean isEmpty(String str) {
        return str.length()==0||null==str;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        show=new ArrayList<Student>();
        Intent intent=new Intent();
        String id=students.get(i).getId();
        String name=students.get(i).getName();
        String sex=students.get(i).getSex();
        String age=students.get(i).getAge();
        String major=students.get(i).getMajor();
        Student student=new Student(id,name,sex,age,major);
        show.add(student);
        intent.setClass(studentList.this,delite.class);
        intent.putExtra("show",(Serializable)show);
        startActivity(intent);
    }
    @Override
    public void itemClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.student_edit:
                int p=(Integer) view.getTag();
                intent.setClass(this,edit.class);
                sentI(intent,p);
                startActivity(intent);
                break;
            case R.id.student_delete:
                final int k=(Integer)view.getTag();
                new AlertDialog.Builder( this )
                        .setIcon( R.drawable.student1)
                        .setTitle( "删除对话框" )
                        .setMessage( "确定删除学生:"+students.get(k).getName()+"？" )
                        .setNegativeButton( "取消",null )
                        .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String no=students.get(k).getId();
                                new Thread() {
                                    @Override
                                    public void run() {
                                        String url = HttpUtilsHttpURLConnection.BASE_URL + "/studentDelServlet";
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("no", no);
                                        String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                                        System.out.println(result);
                                    }
                                }.start();
                                students.remove(k);
                                Toast.makeText(studentList.this,"删除成功",Toast.LENGTH_SHORT).show();
                                adapter.notifyDataSetChanged();
                            }
                        } ).show();
                break;
        }
    }
    public void sentI(Intent intent,int p){
        intent.putExtra("p",p);
    }
}
