package com.cqjtu.studentsystemweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import static com.cqjtu.studentsystemweb.MainActivity.students;
import static com.cqjtu.studentsystemweb.studentList.adapter;

public class edit extends AppCompatActivity implements View.OnClickListener{
    private EditText name11;
    private AutoCompleteTextView major11;
    private TextView id11;
    private EditText age11;
    private RadioGroup sex11;
    private Button save;
    private Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        View v = findViewById(R.id.back1);
        v.getBackground().setAlpha(230);
        id11=(TextView)findViewById(R.id.no);
        name11=(EditText) findViewById(R.id.name1);
        sex11=(RadioGroup) findViewById(R.id.sex1);
        age11=(EditText) findViewById(R.id.age1);
        major11=(AutoCompleteTextView)findViewById(R.id.major1);
        quit=(Button)findViewById(R.id.exit);
        save=(Button)findViewById(R.id.save);
        String id=(String) students.get(k()).getId();
        String name=(String) students.get(k()).getName();
        String sex=(String) students.get(k()).getSex();
        String age=(String) students.get(k()).getAge();
        String major=(String)students.get(k()).getMajor();
        id11.setText(id);
        name11.setText(name);
        showRadio(sex11,sex);
        age11.setText(age);
        major11.setText(major);
        String major1[]={"计算机科学与技术","物联网工程","电子信息","电子通信","土木工程","工程造价","计算机软件","物联网系统"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this,R.layout.textview,major1);
        major11.setAdapter(arrayAdapter);
        quit.setOnClickListener(this);
        save.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.exit){
            finish();
        }else if (view.getId()==R.id.save){
            final String id=id11.getText().toString();
            students.get(k()).setId(id);
            final String name=name11.getText().toString();
            students.get(k()).setName(name);
            final String sex=getRadio(sex11);
            students.get(k()).setSex(sex);
            final String age=age11.getText().toString();
            students.get(k()).setAge(age);
            final String major=major11.getText().toString();
            students.get(k()).setMajor(major);
            new Thread() {
                @Override
                public void run() {
                    String url = HttpUtilsHttpURLConnection.BASE_URL + "/studentInsertServlet";
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("no",id);
                    params.put("name", name);
                    params.put("age", age);
                    params.put("major", major);
                    params.put("sex", sex);
                    String result = HttpUtilsHttpURLConnection.getContextByHttp(url, params);
                    System.out.println(result);
                }
            }.start();
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
            finish();
        }
    }
    public void showRadio(RadioGroup radioGroup, String v){
        int num=radioGroup.getChildCount();
        for(int i=0;i<num;i++){
            RadioButton radioButton=(RadioButton)radioGroup.getChildAt(i);
            String info=radioButton.getText().toString();
            if(info.equals(v)){
                radioButton.setChecked(true);
                break;
            }
        }
    }
    public int k(){
        Bundle bundle1 = getIntent().getExtras();
        int k=bundle1.getInt("p");
        return k;
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
