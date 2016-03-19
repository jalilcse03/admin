package bubtjobs.com.admin.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bubtjobs.com.admin.Others.CommonFunction;
import bubtjobs.com.admin.R;
import bubtjobs.com.admin.Others.SessionManager;

public class SetExamDuration extends AppCompatActivity {
    EditText timeEd;
    SessionManager sessionManager;
    CommonFunction commonFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_exam_duration);

        setTitle("Admin");

        sessionManager=new SessionManager(this);
        commonFunction=new CommonFunction();
        timeEd=(EditText)findViewById(R.id.timeEt);
    }

    public void setTime(View view)
    {
       if(commonFunction.isEmpty(timeEd))
       {
           sessionManager.setExamDuration(timeEd.getText().toString());
           Toast.makeText(this,"ExamPage Duration Set Successfully ",Toast.LENGTH_LONG).show();
          startActivity(new Intent(this,AdminHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
       }
        else{
           timeEd.setError("Field is Empty");
       }
    }
}
