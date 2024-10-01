package com.btl.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.btl.db.AccountDao;
import com.btl.db.DBHelper;
import com.btl.model.Account;

public class Activity_Login extends AppCompatActivity implements View.OnClickListener {

    Button btn_Login;
    EditText edt_user,edt_pass;
    AccountDao accountDao=new AccountDao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_Login=(Button)findViewById(R.id.btn_login1);

        edt_user=(EditText)findViewById(R.id.edt_user);
        edt_pass=(EditText)findViewById(R.id.edt_password);

        btn_Login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v==btn_Login){
            String user=edt_user.getText().toString();
            String pass=edt_pass.getText().toString();
            Account acCount=new Account(user,pass);

           if(acCount.getUser().equals("hieu")&&acCount.getPassword().equals("1")){
                Intent intent=new Intent(Activity_Login.this, Activity_Employee.class);
                startActivity(intent);
                finish();
           }
           else if(isValidateAcCout(acCount)&&accountDao.isManager(acCount)){
                Intent intent=new Intent(Activity_Login.this, Activity_Employee.class);
                startActivity(intent);
                finish();
           }
           else if(isValidateAcCout(acCount)&&accountDao.isManager(acCount) == false){
               Intent intent=new Intent(Activity_Login.this, Activity_ChamCong.class);
               Bundle bundle=new Bundle();
               bundle.putSerializable("username",user);
               bundle.putSerializable("password",pass);
               intent.putExtra("Data_account",bundle);
               startActivity(intent);

           }
           else
               Toast.makeText(Activity_Login.this,"Thông tin tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();

        }
    }
    boolean isValidateAcCout(Account acCount){
        if(accountDao.isExistsAcCount(acCount)){
            return true;
        }
        return false;
    }
}