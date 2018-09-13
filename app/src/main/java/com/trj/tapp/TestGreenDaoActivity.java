package com.trj.tapp;

import android.os.Bundle;
import android.view.View;

import com.trj.tlib.activity.BaseTitleActivity;
import com.trj.tlib.db.StudentDaoOpe;
import com.trj.tlib.db.daobean.Student;
import com.trj.tlib.uils.Logger;

import java.util.ArrayList;
import java.util.List;

public class TestGreenDaoActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_green_dao);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试DreenDao数据库框架");
        initData();
    }

    private List<Student> studentList = new ArrayList<>();

    private void initData() {
        Student student = null;
        for (int i = 0; i < 10; i++) {
            student = new Student();
            student.setId((long) (i+1));
            student.setName("张三" + (i + 10));
            student.setAge(30);
            studentList.add(student);
        }
    }

    public void addBtn(View view){
        StudentDaoOpe.insertData(context, studentList);
    }


    public void addDelete(View view) {
//        删除全部
//        StudentDaoOpe.deleteAllData(context);
        //根据对象删除
//        StudentDaoOpe.deleteData(context,new Student());
        StudentDaoOpe.deleteByKeyData(context, 2);
    }

    public void addUpdate(View view) {
        Student student = new Student();
        student.setId((long) 10);
        student.setName("王五");
        student.setAge(22);
        StudentDaoOpe.updateData(context,student);
    }

    public void addFind(View view) {
        List<Student> list = StudentDaoOpe.queryAll(context);
        Logger.t("---" + gson.toJson(list));
    }
}
