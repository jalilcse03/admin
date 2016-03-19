package bubtjobs.com.admin.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import bubtjobs.com.admin.Others.AlertDialogManager;
import bubtjobs.com.admin.Getter_Setter.AnswerSetMaker;
import bubtjobs.com.admin.DataBase.DataBaseManager;
import bubtjobs.com.admin.R;
import bubtjobs.com.admin.Others.SessionManager;

public class StudentHome extends AppCompatActivity implements View.OnClickListener{
    Button notificationBt,examBt,examRuleBt,resultBt,registrationCancleBt,LogoutBt;
    DataBaseManager dataBaseManager;
    SessionManager sessionManager;
    AlertDialogManager alertDialogManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        dataBaseManager=new DataBaseManager(this);
        sessionManager=new SessionManager(this);
        alertDialogManager=new AlertDialogManager();

        setTitle(sessionManager.getUserName());


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
                startActivity(new Intent(this, StudentNotification.class));

            break;
            case R.id.examBt:
                boolean isExamStatusUpdate=dataBaseManager.studentExamStatusUpdate(sessionManager.getUserId());
                if(isExamStatusUpdate)
                {
                    answerReset();
                    startActivity(new Intent(this,ExamPage.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                }

                break;
            case R.id.examRuleBt:
                startActivity(new Intent(this, ExamRule.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.resultBt:
                showResult();
                break;
            case R.id.registrationCancleBt:
                dataBaseManager=new DataBaseManager(this);
                boolean isRegistrationCancel=dataBaseManager.resgistrationCancel(sessionManager.getUserId());
                if(isRegistrationCancel)
                {
                    Toast.makeText(this,"SignUp Cancel Sucessful.....",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this,Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                else{
                    Toast.makeText(this,"SignUp Cancel fail.....",Toast.LENGTH_LONG).show();
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
       // Toast.makeText(this,""+mark,Toast.LENGTH_SHORT).show();
        if(Integer.parseInt(mark)>=6)
        alertDialogManager.showAlertDialog(this, "Result....", "You got: "+mark+"\n Status: pass", true);
        else{
            alertDialogManager.showAlertDialog(this, "Result....", "You got: "+mark+"\nStatus: fail. At least 6 marks need to pass", true);
        }
    }

    public void refreshOperation(View view)
    {
        //Toast.makeText(this,"ii",Toast.LENGTH_LONG).show();
        startActivity(new Intent(StudentHome.this, StudentHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
