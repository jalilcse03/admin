package bubtjobs.com.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    EditText nameEt,userEmailEt,passwordEt,userConPasswordET;
   // DataBaseManager dataBaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

       // dataBaseManager=new DataBaseManager(this);

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
                    Toast.makeText(CreateAccount.this, "Create Account Successfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, Login_activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                } else if (restul.equals("emial exist")) {
                    Toast.makeText(CreateAccount.this, "Email Already Exist.....", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateAccount.this, restul, Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(CreateAccount.this,"Password and Confirm password must be same",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(CreateAccount.this,"Insert All Field",Toast.LENGTH_SHORT).show();
        }
    }
}
