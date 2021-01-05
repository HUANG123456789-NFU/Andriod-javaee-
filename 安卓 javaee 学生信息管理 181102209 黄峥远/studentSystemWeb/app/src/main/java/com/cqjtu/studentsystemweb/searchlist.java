package com.cqjtu.studentsystemweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import static com.cqjtu.studentsystemweb.studentList.find;

public class searchlist extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,StuAdapter.InnerItemOnClickListener{
    private ListView list;
    public static StuAdapter stuAdapter1;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);
        list=(ListView)findViewById(R.id.studentList1);
        back=(Button)findViewById(R.id.back);
        Bundle bundle = getIntent().getExtras();
        stuAdapter1=new StuAdapter(searchlist.this,find);
        list.setAdapter(stuAdapter1);
        stuAdapter1.setOnInnerItemOnClickListener(this);
        list.setOnItemClickListener(searchlist.this);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        if (view.getId()==R.id.back){
            finish();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }
    @Override
    public void itemClick(View view) {

    }
}
