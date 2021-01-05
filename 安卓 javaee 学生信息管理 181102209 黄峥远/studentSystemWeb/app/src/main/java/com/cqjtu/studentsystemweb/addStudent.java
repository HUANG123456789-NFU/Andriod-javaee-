package com.cqjtu.studentsystemweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import static com.cqjtu.studentsystemweb.MainActivity.students;

public class addStudent extends AppCompatActivity implements View.OnClickListener {
    private AutoCompleteTextView major1;
    public  EditText name1;
    private EditText age1;
    private Button addname1;
    private Button addmajor1;
    private RadioGroup sex1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        name1=(EditText) findViewById(R.id.name);
        major1=(AutoCompleteTextView) findViewById(R.id.major);
        age1=(EditText)findViewById(R.id.age);
        sex1=(RadioGroup)findViewById(R.id.sex);
        addname1=(Button)findViewById(R.id.addname);
        addmajor1=(Button)findViewById(R.id.addmajor);
        addname1.setOnClickListener(this);
        addmajor1.setOnClickListener(this);
        String major[]={"计算机科学与技术","物联网工程","电子信息","电子通信","土木工程","工程造价","计算机软件","物联网系统"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this,R.layout.textview,major);
        major1.setAdapter(arrayAdapter);
        View v = findViewById(R.id.back);
        v.getBackground().setAlpha(200);
    }
    private boolean isEmpty(String str) {
        return str.length()==0||null==str;
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.addname) {
            final String name22 = name1.getText().toString();
            final String sex22 = getRadio(sex1);
            final String age22 = age1.getText().toString();
            final String major22 = major1.getText().toString();
            if (isEmpty(name22)) {
                Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEmpty(age22)) {
                Toast.makeText(this, "年龄不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEmpty(sex22)) {
                Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isEmpty(major22)) {
                Toast.makeText(this, "专业不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/studentInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", name22);
                    params.put("age", age22);
                    params.put("major", major22);
                    params.put("sex", sex22);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    System.out.println(result);
                }
            }.start();
            Toast.makeText(this, "已经添加" + students.size() + "条记录", Toast.LENGTH_SHORT).show();
            name1.setText("");
            sex1.clearCheck();
            age1.setText("");
            major1.setText("");
            name1.setFocusable(true);
            name1.setFocusableInTouchMode(true);
            name1.requestFocus();
        } else if (view.getId() == R.id.addmajor) {
            intent.setClass(this, studentList.class);
            startActivity(intent);
        }
    }
    public String getRadio(RadioGroup radioGroup){
        String info="";
        int num=radioGroup.getChildCount();
        for(int i=0;i<num;i++){
            RadioButton rd=(RadioButton)radioGroup.getChildAt(i);
            if(rd.isChecked()){
                info=rd.getText().toString();
                break;
            }
        }
        return info;
    }
}
