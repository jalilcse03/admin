package bubtjobs.com.admin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentHome_activity extends AppCompatActivity implements View.OnClickListener{
    Button notificationBt,examBt,examRuleBt,resultBt,registrationCancleBt,LogoutBt;
    DataBaseManager dataBaseManager;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_activity);

        dataBaseManager=new DataBaseManager(this);
        sessionManager=new SessionManager(this);

        notificationBt=(Button)findViewById(R.id.notificationBt);
        examBt=(Button)findViewById(R.id.examBt);
        examRuleBt=(Button)findViewById(R.id.examRuleBt);
        resultBt=(Button)findViewById(R.id.resultBt);
        registrationCancleBt=(Button)findViewById(R.id.registrationCancleBt);
        LogoutBt=(Button)findViewById(R.id.LogoutBt);

        String result=dataBaseManager.checkNewNotificatin(sessionManager.getUserId());
        if(result.equals("yes"))
        {
            notificationBt.setText("One New Notification");
            notificationBt.setBackgroundColor(Color.GREEN);
        }
        //Toast.makeText(this,sessionManager.getUserId(),Toast.LENGTH_LONG).show();


        notificationBt.setOnClickListener(this);
        examBt.setOnClickListener(this);
        examRuleBt.setOnClickListener(this);
        resultBt.setOnClickListener(this);
        registrationCancleBt.setOnClickListener(this);
        LogoutBt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.notificationBt:
                dataBaseManager.studentUpdataNotificationStatus(sessionManager.getUserId());
                startActivity(new Intent(this, Notification_activity.class));

            break;
            case R.id.examBt:
                String result=sessionManager.getbuttonText();
                Toast.makeText(this,result,Toast.LENGTH_LONG).show();
                break;
            case R.id.examRuleBt:
                break;
            case R.id.resultBt:
                break;
            case R.id.registrationCancleBt:
                break;
            case R.id.LogoutBt:
                SessionManager sessionManager=new SessionManager(this);
                sessionManager.logoutUser();
                break;
        }
    }
}
