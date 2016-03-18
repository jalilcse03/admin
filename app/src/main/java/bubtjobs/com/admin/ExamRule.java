package bubtjobs.com.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExamRule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_rule);
    }
    public void backOperation(View view)
    {
        startActivity(new Intent(this,StudentHome_activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
