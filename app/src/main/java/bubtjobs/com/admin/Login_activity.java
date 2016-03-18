package bubtjobs.com.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Externalizable;

public class Login_activity extends AppCompatActivity {
    EditText userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        userEmail = (EditText) findViewById(R.id.userEmailEt);
        userPassword = (EditText) findViewById(R.id.userPasswordEt);
    }

    public void creatAccount(View view) {
        startActivity(new Intent(this, CreateAccount.class));
    }

    public void loginBt(View view) {
        if (userEmail.getText().toString().length() > 0 && userPassword.getText().toString().length() > 0) {

            // admin login static part
            if (userEmail.getText().toString().equals("admin") && userPassword.getText().toString().equals("admin")) {
                    startActivity(new Intent(this,admin_home.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            } else {

                DataBaseManager dataBaseManager = new DataBaseManager(this);

                String result = dataBaseManager.login(userEmail.getText().toString(), userPassword.getText().toString());
                if (result.equals("no") || result.equals("error")) {
                    Toast.makeText(Login_activity.this, result, Toast.LENGTH_SHORT).show();

                } else {

                    SessionManager sessionManager = new SessionManager(this);
                    sessionManager.createLoginSession(result);
                    startActivity(new Intent(this, StudentHome_activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    // Toast.makeText(Login_activity.this,sessionManager.getUserId(),Toast.LENGTH_SHORT).show();
                }

            }
        } else {
            Toast.makeText(Login_activity.this, "Insert All Field", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset(View view) {
        boolean restult = new DataBaseManager(this).reset();
        Toast.makeText(this, " " + restult, Toast.LENGTH_LONG).show();
    }
}
