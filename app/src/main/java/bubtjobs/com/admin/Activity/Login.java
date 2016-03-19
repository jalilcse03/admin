package bubtjobs.com.admin.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bubtjobs.com.admin.Others.AlertDialogManager;
import bubtjobs.com.admin.DataBase.DataBaseManager;
import bubtjobs.com.admin.Others.CommonFunction;
import bubtjobs.com.admin.R;
import bubtjobs.com.admin.Others.SessionManager;

public class Login extends AppCompatActivity {
    EditText userEmail, userPassword;
    AlertDialogManager alertDialogManager;
    String isRegistrationSuccess="";
    CommonFunction commonFunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        alertDialogManager=new AlertDialogManager();
        commonFunction=new CommonFunction();

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
        boolean isvalid=validation(view);
        if (isvalid) {

            // admin login static part
            if (userEmail.getText().toString().equals("admin@a.a") && userPassword.getText().toString().equals("admin")) {
                    startActivity(new Intent(this,AdminHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            } else {

                DataBaseManager dataBaseManager = new DataBaseManager(this);

                boolean islogin = dataBaseManager.login(userEmail.getText().toString(), userPassword.getText().toString());
                if (!islogin) {
                    alertDialogManager.showAlertDialog(this, "Login failed....", "Unauthorized User, Please Check your email and password", true);

                } else {

                    startActivity(new Intent(this, StudentHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }

            }
        }
    }

    public void reset(View view) {
        boolean restult = new DataBaseManager(this).reset();
        Toast.makeText(this, " " + restult, Toast.LENGTH_LONG).show();
    }

    public boolean validation(View view)
    {
        boolean temp=true;

        if(!commonFunction.isValidEmail(userEmail)){
            userEmail.setError("Please enter valid email");
            userEmail.requestFocus();
            temp=false;
        }

        if(!commonFunction.isEmpty(userPassword)){
            userPassword.setError("Please enter your Password");
            userPassword.requestFocus();
            temp=false;
        }
        if(!commonFunction.isEmpty(userEmail)){
            userEmail.setError("Please enter your Email");
            userEmail.requestFocus();
            temp=false;
        }
        if(temp)
            return  true;
        else
            return  false;
    }
}
