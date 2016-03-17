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
    Button resgistrationList_btn,notification_btn,question_btn,answer_btn,selectedList_btn,logout_btn,examStart_btn;

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
        answer_btn=(Button)findViewById(R.id.answer_btn);
        examStart_btn=(Button)findViewById(R.id.examStart_btn);
        selectedList_btn=(Button)findViewById(R.id.selectedList_btn);
        logout_btn=(Button)findViewById(R.id.logout_btn);

        sessionManager=new SessionManager(this);
        String text=sessionManager.getbuttonText();
        if(text!=null)
            examStart_btn.setText(text);

        manager=new DataBaseManager(this);



        resgistrationList_btn.setOnClickListener(this);
        notification_btn.setOnClickListener(this);
        question_btn.setOnClickListener(this);
        answer_btn.setOnClickListener(this);
        examStart_btn.setOnClickListener(this);
        selectedList_btn.setOnClickListener(this);
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

            case R.id.answer_btn:
                addAnswerSet();
                break;
            case R.id.examStart_btn:
               examStart();
                break;

            case R.id.selectedList_btn:
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

        questionSetMake=new QuestionSetMake("Which of the following is a legal identifier in java ?","2variable","#myvar","+@$var","$_myvar","$_myvar");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("Which of these is NOT valid keyword or reserved word in Java ?","default","#null","String","String","#null");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("Which operator will always evaluate all the operands ?","||","&&","?:","%","?:");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("What is my name?","jalilur","rahman","murtuza","ali","murtuza");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("What is your favorit color?","black","red","olive","pink","black");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("your best friend?","a","t","p","k","p");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("your best book?","quran","math","english","java","quran");
        questionSet.add(questionSetMake);

        questionSetMake=new QuestionSetMake("your hobby?","swing","codding","dancing","visiting","codding");
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

        Answer answer=new Answer("$_myvar","de");
        answersset.add(answer);
        answer=new Answer("#null","de");
        answersset.add(answer);
        answer=new Answer("?:","de");
        answersset.add(answer);
        answer=new Answer("murtuza","de");
        answersset.add(answer);
        answer=new Answer("black","de");
        answersset.add(answer);
        answer=new Answer("p","de");
        answersset.add(answer);
        answer=new Answer("quran","de");
        answersset.add(answer);
        answer=new Answer("codding","de");
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
        }
        else{
            sessionManager.buttonTextChage("Exam Start");
            examStart_btn.setText("Exam Start");
        }

    }
}
