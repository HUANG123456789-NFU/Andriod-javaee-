package com.cqjtu.studentsystemweb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StuAdapter extends BaseAdapter implements View.OnClickListener {
    private List <Student>stuDates;
    private Context stuContext;
    private InnerItemOnClickListener myListener;
    public StuAdapter(Context context,List<Student>list){
        this.stuContext=context;
        this.stuDates=list;
    }
    @Override
    public int getCount() {
        return stuDates.size();
    }

    @Override
    public Object getItem(int position) {
        return stuDates.get(position);
    }

    @Override
    public long getItemId(int position) {
        Student student=(Student)stuDates.get(position);
        return position;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View stuView=View.inflate(stuContext,R.layout.studentweb_item,null);
        TextView stuName=stuView.findViewById(R.id.student_name);
        TextView stuSex=stuView.findViewById(R.id.student_sex);
        TextView stuAge=stuView.findViewById(R.id.student_age);
        TextView stuId=stuView.findViewById(R.id.student_id);
        TextView stuMajor=stuView.findViewById(R.id.student_major);
        ImageView edit=stuView.findViewById(R.id.student_edit);
        ImageView delete=stuView.findViewById(R.id.student_delete);
        Student student=(Student)stuDates.get(i);
        stuName.setText(student.getName());
        stuSex.setText(student.getSex());
        stuAge.setText(student.getAge());
        stuId.setText(student.getId());
        stuMajor.setText(student.getMajor());
        edit.setImageResource(R.drawable.edit);
        delete.setImageResource(R.drawable.delete);
        edit.setOnClickListener(this);
        delete.setOnClickListener(this);
        edit.setTag(i);
        delete.setTag(i);
        return stuView;
    }
    interface InnerItemOnClickListener {
        abstract void itemClick(View view);
    }
    public void setOnInnerItemOnClickListener(InnerItemOnClickListener listener){
        this.myListener=listener;
    }
    @Override
    public void onClick(View view) {
        myListener.itemClick(view);
    }

}

