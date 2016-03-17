package bubtjobs.com.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class admin_home extends AppCompatActivity implements View.OnClickListener{
    Button resgistrationList_btn,notification_btn,question_btn,answer_btn,resultPublisBt,logout_btn,examStart_btn;

    DataBaseManager manager;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        init();


    }
    public void init(){
        resgistrationList_btn=(Button)findViewById(R.id.resgistrationList_btn);
        notification_btn=(Button)findViewById(R.id.notification_btn);
        question_btn=(Button)findViewById(R.id.question_btn);
        //answer_btn=(Button)findViewById(R.id.answer_btn);
        examStart_btn=(Button)findViewById(R.id.examStart_btn);
        resultPublisBt=(Button)findViewById(R.id.resultPublisBt);
        logout_btn=(Button)findViewById(R.id.logout_btn);

        sessionManager=new SessionManager(this);

        // exam start button
        String text=sessionManager.getbuttonText();
        if(text.equals(" "))
            ;
        else
            examStart_btn.setText(text);

        manager=new DataBaseManager(this);

        // result publish button

        if(sessionManager.getResult().equals("yes"))
        {
            resultPublisBt.setText("Stop Result Publish");
        }
        else{
            resultPublisBt.setText("Result Publish");
        }

        resgistrationList_btn.setOnClickListener(this);
        notification_btn.setOnClickListener(this);
        question_btn.setOnClickListener(this);
       // answer_btn.setOnClickListener(this);
        examStart_btn.setOnClickListener(this);
        resultPublisBt.setOnClickListener(this);
        logout_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.resgistrationList_btn:
                //Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,RegistrationList_activity.class));
                break;

            case R.id.notification_btn:
                startActivity(new Intent(this,Message_activity.class));
                break;

            case R.id.question_btn:
                addqustionSet();
                break;

//            case R.id.answer_btn:
//                addAnswerSet();
//                break;
            case R.id.examStart_btn:
                addqustionSet();
               examStart();
                break;

            case R.id.resultPublisBt:
                if(resultPublisBt.getText().toString().equals("Result Publish")) {
                    // exam stop
                    sessionManager.buttonTextChage("Exam Start");
                    examStart_btn.setText("Exam Start");

                    // result publish
                    sessionManager.setResult("yes");
                    resultPublisBt.setText("Stop Result Publish");
                }
                else{

                    sessionManager.setResult("no");
                    resultPublisBt.setText("Result Publish");
                }
                break;

            case R.id.logout_btn:
                startActivity(new Intent(this,Login_activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
        }
    }

    public void addqustionSet(){
        QuestionSetMake questionSetMake;
        questionSetMake=new QuestionSetMake();
        ArrayList<QuestionSetMake> questionSet=new ArrayList<>();

        questionSetMake=new QuestionSetMake("Which is a valid keyword in java?","interface","string","Float","unsigned ","interface");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("Which is a reserved word in the Java programming language?","Method","native","subclasses","reference ","native");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("What is the name of the method used to start a thread execution?","init()","start()","run()","resume()","start()");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("Which method registers a thread in a thread scheduler??","run()","construct()","start()","register()","start()");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("Which of these values can a boolean variable contain?","true & false","0 & 1","Any integer value","True","true & false");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("Which one is a valid declaration of a boolean?","boolean b1 = 1","boolean b2 = ‘false’","boolean b3 = false","boolean b4 = ‘true’","boolean b3 = false");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("Who is the founder of Android?","Andy Rubin","Mark","Heli","Robi","Andy Rubin");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("How many the life cycle methods of android activity?","7","6","5","9","7");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("How many types of intent?","3","6","2","1","2");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("What is ADB?","assistant Debug Bridge","assistant Debug Boat","android Degub boat","Android Debug Bridge","Android Debug Bridge");
        questionSet.add(questionSetMake);

        DataBaseManager manager=new DataBaseManager(this);

        String isinsert=manager.addQuestion(questionSet);
        if(isinsert.equals("yes"))
        Toast.makeText(this,"Question Set Successful",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,isinsert,Toast.LENGTH_SHORT).show();
    }

    public void addAnswerSet(){
        DataBaseManager manager=new DataBaseManager(this);
        ArrayList<Answer> answersset=new ArrayList<>();

        Answer answer=new Answer("interface","de");
        answersset.add(answer);
        answer=new Answer("native","de");
        answersset.add(answer);
        answer=new Answer("start()","de");
        answersset.add(answer);
        answer=new Answer("start()","de");
        answersset.add(answer);
        answer=new Answer("true & false","de");
        answersset.add(answer);
        answer=new Answer("boolean b3 = false","de");
        answersset.add(answer);
        answer=new Answer("Andy Rubin","de");
        answersset.add(answer);
        answer=new Answer("7","de");
        answersset.add(answer);
        answer=new Answer("2","de");
        answersset.add(answer);
        answer=new Answer("Android Debug Bridge","de");
        answersset.add(answer);
        boolean isanswerRest=manager.addAnswer(answersset);

        //  Toast.makeText(this,""+isanswerRest,Toast.LENGTH_SHORT).show();
        if(isanswerRest)
        {
            Toast.makeText(this,"Add Answer Set Successful",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"Answer Set Error",Toast.LENGTH_SHORT).show();
    }
    public void examStart(){
        if(examStart_btn.getText().toString().equals("Exam Start"))
        {
            sessionManager.buttonTextChage("Exam Stop");
            examStart_btn.setText("Exam Stop");

            sessionManager.setResult("no");
            resultPublisBt.setText("Result Publish");
        }
        else{
            sessionManager.buttonTextChage("Exam Start");
            examStart_btn.setText("Exam Start");
        }

    }
}
