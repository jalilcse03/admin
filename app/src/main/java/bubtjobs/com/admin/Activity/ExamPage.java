package bubtjobs.com.admin.Activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import bubtjobs.com.admin.Adapter.ExamAdapter;
import bubtjobs.com.admin.DataBase.DataBaseManager;
import bubtjobs.com.admin.Getter_Setter.QuestionSetMake;
import bubtjobs.com.admin.R;
import bubtjobs.com.admin.Others.SessionManager;

public class ExamPage extends AppCompatActivity {

    DataBaseManager dataBaseManager;
    SessionManager sessionManager;
    ListView listView;
    TextView timeTv;
    ImageButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_page);

        timeTv = (TextView) findViewById(R.id.time);
        back_btn = (ImageButton) findViewById(R.id.back_btn);
        listView = (ListView) findViewById(R.id.listView);

        //listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        ArrayList<QuestionSetMake> questions = new ArrayList<>();

        dataBaseManager = new DataBaseManager(this);
        sessionManager = new SessionManager(this);

        questions = dataBaseManager.getAllQuestion();
        if (questions != null) {
            ExamAdapter adapter = new ExamAdapter(getApplicationContext(), questions);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(ExamPage.this, "Error", Toast.LENGTH_SHORT).show();
        }


        final CounterClass timer = new CounterClass((int) sessionManager.getExamDuration(), 1000);
        timer.start();


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                timeTv.setText("finish: ");
                int result = dataBaseManager.result();
                dataBaseManager.studentResultUpdate(sessionManager.getUserId(), "" + result);
                startActivity(new Intent(ExamPage.this, StudentHome.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NowApi")

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("NowApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;

            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),

                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
            );
            timeTv.setText(hms);

        }

        @Override
        public void onFinish() {
            timeTv.setText("finish: ");
            int result = dataBaseManager.result();
            dataBaseManager.studentResultUpdate(sessionManager.getUserId(), "" + result);
            startActivity(new Intent(ExamPage.this, StudentHome.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

        }
    }

}
