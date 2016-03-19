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

public class SignUp extends AppCompatActivity {

    EditText nameEt,userEmailEt,passwordEt,userConPasswordET;
    AlertDialogManager alertDialogManager;
   // DataBaseManager dataBaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       // dataBaseManager=new DataBaseManager(this);
        alertDialogManager=new AlertDialogManager();
        nameEt=(EditText)findViewById(R.id.userNameEt);
        userEmailEt=(EditText)findViewById(R.id.userEmailEt);
        passwordEt=(EditText)findViewById(R.id.userPasswordEt);
        userConPasswordET=(EditText)findViewById(R.id.userConPasswordET);

    }
    public void CreatAccount(View view){
        if(nameEt.getText().toString().length()>0 &&userEmailEt.getText().toString().length()>0 &&passwordEt.getText().toString().length()>0){

            if(passwordEt.getText().toString().equals(userConPasswordET.getText().toString())) {
                DataBaseManager dataBaseManager = new DataBaseManager(this);

                String restul = dataBaseManager.creatAccount(nameEt.getText().toString(), userEmailEt.getText().toString(), passwordEt.getText().toString());
                if (restul.equals("yes")) {
                    startActivity(new Intent(this, Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra("success","success"));
                } else if (restul.equals("email exist")) {
                    alertDialogManager.showAlertDialog(this, "Error....", "Email Already Exist.....", true);
                } else {
                    Toast.makeText(SignUp.this, restul, Toast.LENGTH_SHORT).show();
                }
            }
            else{
                alertDialogManager.showAlertDialog(this, "Error....", "Password and Confirm password must be same", true);
            }
        }
        else
        {
            Toast.makeText(SignUp.this,"Insert All Field",Toast.LENGTH_SHORT).show();
        }
    }
}