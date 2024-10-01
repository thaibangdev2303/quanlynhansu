package com.btl.quanlynhanvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.btl.Adapter.AccountAdapter;
import com.btl.db.AccountDao;
import com.btl.db.DBHelper;
import com.btl.model.Account;

import java.util.ArrayList;

public class Activity_Account extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ImageView imageView;
    TextView tv_user,tv_pass;
    ListView listView;
    EditText txtSearch;
    Context context;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add){
            Intent intent=new Intent(Activity_Account.this, Infor_Account.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.employee){
            Intent intent=new Intent(Activity_Account.this, Activity_Employee.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.phongban){
            Intent intent=new Intent(Activity_Account.this, Activity_PhongBan.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.bacluong){
            Intent intent=new Intent(Activity_Account.this, Activity_BacLuong.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.logout){
            Intent intent=new Intent(Activity_Account.this, Activity_Login.class);
            startActivity(intent);
            finish();

        }
        else if(item.getItemId()==R.id.bangluong){
            Intent intent=new Intent(Activity_Account.this, Activity_BangLuong.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    AccountAdapter arrayAdapter;
    ArrayList<Account> accounts=new ArrayList<Account>();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        listView=(ListView)findViewById(R.id.lv_acCount);
        txtSearch = findViewById(R.id.edt_search_account);
        AccountDao accountDao=new AccountDao(this);
        accounts=accountDao.getAllAcCount();
        arrayAdapter=new AccountAdapter(Activity_Account.this,R.layout.item_account,accounts);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    accounts=accountDao.getAllAccount(txtSearch.getText().toString());
                    arrayAdapter=new AccountAdapter(Activity_Account.this,R.layout.item_account,accounts);
                    listView.setAdapter(arrayAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle=new Bundle();
        Account acCount=new Account(accounts.get(position).getIdnv(),accounts.get(position).getUser(),accounts.get(position).getPassword());
        bundle.putSerializable("account",acCount);
        Intent intent=new Intent(Activity_Account.this, Infor_Account.class);
        intent.putExtra("data",bundle);
        startActivity(intent);
    }
}