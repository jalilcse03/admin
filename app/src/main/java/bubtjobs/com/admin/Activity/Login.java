package bubtjobs.com.admin.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bubtjobs.com.admin.Others.AlertDialogManager;
import bubtjobs.com.admin.DataBase.DataBaseManager;
import bubtjobs.com.admin.R;
import bubtjobs.com.admin.Others.SessionManager;

public class Login extends AppCompatActivity {
    EditText userEmail, userPassword;
    AlertDialogManager alertDialogManager;
    String isRegistrationSuccess="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        alertDialogManager=new AlertDialogManager();

        userEmail = (EditText) findViewById(R.id.userEmailEt);
        userPassword = (EditText) findViewById(R.id.userPasswordEt);

        isRegistrationSuccess =getIntent().getStringExtra("success");
        if(isRegistrationSuccess!=null && isRegistrationSuccess.equals("success"))
        {
            alertDialogManager.showAlertDialog(this, "Success....", "SignUp Successfully completed", true);
        }

    }

    public void creatAccount(View view) {
        startActivity(new Intent(this, SignUp.class));
    }

    public void loginBt(View view) {
        if (userEmail.getText().toString().length() > 0 && userPassword.getText().toString().length() > 0) {

            // admin login static part
            if (userEmail.getText().toString().equals("admin") && userPassword.getText().toString().equals("admin")) {
                    startActivity(new Intent(this,AdminHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            } else {

                DataBaseManager dataBaseManager = new DataBaseManager(this);

                String result = dataBaseManager.login(userEmail.getText().toString(), userPassword.getText().toString());
                if (result.equals("no") || result.equals("error")) {
                    alertDialogManager.showAlertDialog(this, "Login failed....", "Unauthorized User, Please Check your email and password", true);

                } else {

                    SessionManager sessionManager = new SessionManager(this);
                    sessionManager.createLoginSession(result);
                    startActivity(new Intent(this, StudentHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    // Toast.makeText(Login.this,sessionManager.getUserId(),Toast.LENGTH_SHORT).show();
                }

            }
        } else {
            Toast.makeText(Login.this, "Insert All Field", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset(View view) {
        boolean restult = new DataBaseManager(this).reset();
        Toast.makeText(this, " " + restult, Toast.LENGTH_LONG).show();
    }
}