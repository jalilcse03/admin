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

        examBt.setEnabled(false);



        // notification
        String result=dataBaseManager.checkNewNotificatin(sessionManager.getUserId());
        if(result.equals("yes"))
        {
            notificationBt.setText("One New Notification");
            notificationBt.setBackgroundColor(Color.GREEN);
        }
        //Toast.makeText(this,sessionManager.getUserId(),Toast.LENGTH_LONG).show();

        // exam
        String isExamStart=sessionManager.getbuttonText();
        if(isExamStart.equals("Exam Stop") && dataBaseManager.checkExamStatus(sessionManager.getUserId()))
        {
            examBt.setEnabled(true);
        }

        // result
        if(sessionManager.getResult().equals("yes"))
        {
            resultBt.setEnabled(true);
        }
        else
        {
            resultBt.setEnabled(false);
        }

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
                boolean isExamStatusUpdate=dataBaseManager.studentExamStatusUpdate(sessionManager.getUserId());
                if(isExamStatusUpdate)
                {
                    answerReset();
                    startActivity(new Intent(this,Exam.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                }

                break;
            case R.id.examRuleBt:
                break;
            case R.id.resultBt:
                showResult();
                break;
            case R.id.registrationCancleBt:
                dataBaseManager=new DataBaseManager(this);
                boolean isRegistrationCancel=dataBaseManager.resgistrationCancel(sessionManager.getUserId());
                if(isRegistrationCancel)
                {
                    Toast.makeText(this,"Registration Cancel Sucessful.....",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this,Login_activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                else{
                    Toast.makeText(this,"Registration Cancel fail.....",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.LogoutBt:
                SessionManager sessionManager=new SessionManager(this);
                sessionManager.logoutUser();
                break;
        }
    }

    public void answerReset(){
        DataBaseManager manager=new DataBaseManager(this);
        ArrayList<AnswerSetMaker> answersset=new ArrayList<>();

        AnswerSetMaker answerSetMaker =new AnswerSetMaker("interface","de");
        answersset.add(answerSetMaker);
        answerSetMaker =new AnswerSetMaker("native","de");
        answersset.add(answerSetMaker);
        answerSetMaker =new AnswerSetMaker("start()","de");
        answersset.add(answerSetMaker);
        answerSetMaker =new AnswerSetMaker("start()","de");
        answersset.add(answerSetMaker);
        answerSetMaker =new AnswerSetMaker("true & false","de");
        answersset.add(answerSetMaker);
        answerSetMaker =new AnswerSetMaker("boolean b3 = false","de");
        answersset.add(answerSetMaker);
        answerSetMaker =new AnswerSetMaker("Andy Rubin","de");
        answersset.add(answerSetMaker);
        answerSetMaker =new AnswerSetMaker("7","de");
        answersset.add(answerSetMaker);
        answerSetMaker =new AnswerSetMaker("2","de");
        answersset.add(answerSetMaker);
        answerSetMaker =new AnswerSetMaker("Android Debug Bridge","de");
        answersset.add(answerSetMaker);
        boolean isanswerRest=manager.addAnswer(answersset);

    }


    public void showResult(){
        String mark=dataBaseManager.showStudentResult(sessionManager.getUserId());
        Toast.makeText(this,""+mark,Toast.LENGTH_SHORT).show();
    }
}
