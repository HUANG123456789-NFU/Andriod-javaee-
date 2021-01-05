package com.cqjtu.studentsystemweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class delite extends AppCompatActivity implements View.OnClickListener {

    private TextView id33;
    private EditText major33;
    private EditText age33;
    private RadioGroup sex33;
    private EditText name33;
    private Button exit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delite);
        id33=(TextView)findViewById(R.id.no33);
        name33=(EditText) findViewById(R.id.name33);
        sex33=(RadioGroup) findViewById(R.id.sex33);
        age33=(EditText) findViewById(R.id.age33);
        major33=(EditText)findViewById(R.id.major33);
        exit1=(Button)findViewById(R.id.exit1);
        exit1.setOnClickListener(this);
        name33.setKeyListener(null);
        age33.setKeyListener(null);
        major33.setKeyListener(null);
        View v = findViewById(R.id.back4);
        v.getBackground().setAlpha(230);
        Bundle bundle = getIntent().getExtras();
        List<Student> show=(ArrayList<Student>)bundle.get("show");
        String id=(String) show.get(0).getId();
        String name=(String) show.get(0).getName();
        String sex=(String) show.get(0).getSex();
        String age=(String) show.get(0).getAge();
        String major=(String)show.get(0).getMajor();
        id33.setText("学生学号:"+id);
        name33.setText("姓名:"+name);
        showRadio(sex33,sex);
        age33.setText("年龄:"+age);
        major33.setText("专业:"+major);
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
    public void showBox(LinearLayout linearLayout , List<String>v){
        int num=linearLayout.getChildCount();
        for(int i=0;i<num;i++){
            CheckBox checkBox=(CheckBox)linearLayout.getChildAt(i);
            checkBox.setChecked(false);
            for(String c:v){
                if(checkBox.getText().toString().equals(c)){
                    checkBox.setChecked(true);
                    break;
                }
            }
        }
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.exit1){
            finish();
        }
    }
}
