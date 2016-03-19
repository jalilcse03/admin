package bubtjobs.com.admin.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bubtjobs.com.admin.R;

public class ExamRule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_rule);
    }
    public void backOperation(View view)
    {
        startActivity(new Intent(this,StudentHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
