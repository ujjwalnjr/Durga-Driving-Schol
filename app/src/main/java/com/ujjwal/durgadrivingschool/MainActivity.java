package com.ujjwal.durgadrivingschool;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ujjwal.durgadrivingschool.Adapter.ViewPagerAdapter;
import com.ujjwal.durgadrivingschool.Fragment.LoginFragment;
import com.ujjwal.durgadrivingschool.Fragment.SignupFragment;

public class MainActivity extends AppCompatActivity {
    EditText un, pw;
    Button login;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Boolean isLogin;
    ProgressDialog progressDialog;

    ViewPager viewPager;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Durga Driving Center")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("oh yeah!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(), "Login");
        adapter.addFragment(new SignupFragment(), "Register");
        viewPager.setAdapter(adapter);

        preferences = getSharedPreferences("LOGINREF", MODE_PRIVATE);
        editor = preferences.edit();
        isLogin = preferences.getBoolean("LOGIN_INFO", false);
        if (isLogin == true) {
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
        }
        un = findViewById(R.id.et_un);
        pw = findViewById(R.id.et_pw);


//        login = findViewById(R.id.btn_login);
//
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login();
//            }
//        });
    }

    public void progressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Durga Driving Center");
        progressDialog.setMessage("Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public void login() {
        String username = un.getText().toString();
        String password = pw.getText().toString();
        if (username.isEmpty()) {
            un.setError("Username cannot be null");
        } else if (password.isEmpty()) {
            pw.setError("Password cannot be null");
        } else if (username.equals("admin") && password.equals("admin")) {
            editor.putBoolean("LOGIN_INFO", true);
            editor.commit();
//            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
            progressDialog();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 3000);
        }
    }
}
