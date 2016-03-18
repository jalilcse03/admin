package bubtjobs.com.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ExamDuration extends AppCompatActivity {
    EditText timeEd;
    SessionManager sessionManager;
    CommonFunction commonFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_duration);

        sessionManager=new SessionManager(this);
        commonFunction=new CommonFunction();
        timeEd=(EditText)findViewById(R.id.timeEt);
    }

    public void setTime(View view)
    {
       if(commonFunction.isEmpty(timeEd))
       {
           sessionManager.setExamDuration(timeEd.getText().toString());
           Toast.makeText(this,"Exam Duration Set Successfully ",Toast.LENGTH_LONG).show();
          startActivity(new Intent(this,admin_home.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
       }
        else{
           timeEd.setError("Field is Empty");
       }
    }
}
