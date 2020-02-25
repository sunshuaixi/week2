package com.bawei.week2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.week2.R;
import com.bawei.week2.base.BaseActivity;
import com.bawei.week2.base.BasePresenter;
import com.bawei.week2.bean.BeanClass;
import com.bawei.week2.conteact.ILoginContract;
import com.bawei.week2.presenter.LoginPresenter;
import com.google.gson.Gson;

import java.util.HashMap;

public class Main2Activity extends BaseActivity implements ILoginContract.ILoginView {


    private EditText et2;
    private EditText et1;
    private TextView tv;
    private Button bt;

    @Override
    public BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getlayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        bt = findViewById(R.id.bt);
        tv = findViewById(R.id.tv);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
    }

    @Override
    protected void initData() {

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }
        });

        final String path="http://mobile.bwstudent.com/small/user/v1/login";
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et1.getText().toString();
                String pwd = et2.getText().toString();
                HashMap<String,String> map=new HashMap<>();
                map.put("phone",phone);
                map.put("pwd",pwd);
                BasePresenter presenter = getPresenter();
                if(presenter!=null&&presenter instanceof LoginPresenter){
                    ((LoginPresenter)presenter).doLogin(path,map);
                }


            }
        });
    }


    @Override
    public void onLoginSuccess(String str) {
        Gson gson = new Gson();
        BeanClass beanClass = gson.fromJson(str, BeanClass.class);
        String message = beanClass.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        if (message.equals("登录成功")) {
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onLoginFailure(String str) {

    }
}
