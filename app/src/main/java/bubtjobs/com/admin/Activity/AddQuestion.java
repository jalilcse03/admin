package bubtjobs.com.admin.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import bubtjobs.com.admin.R;

public class AddQuestion extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {
    EditText questionTitleET, optionET1, optionET2, optionET3, optionET4;
    RadioButton answer1, answer2, answer3, answer4;
    int answer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        setTitle("Admin");

        Toast.makeText(this,"This part is future recommendation",Toast.LENGTH_LONG).show();

        answer1 = (RadioButton) findViewById(R.id.answer1);
        answer2 = (RadioButton) findViewById(R.id.answer2);
        answer3 = (RadioButton) findViewById(R.id.answer3);
        answer4 = (RadioButton) findViewById(R.id.answer4);
        answer1.setOnCheckedChangeListener(this);
        answer2.setOnCheckedChangeListener(this);
        answer3.setOnCheckedChangeListener(this);
        answer4.setOnCheckedChangeListener(this);

        questionTitleET = (EditText) findViewById(R.id.questionTitle);
        optionET1 = (EditText) findViewById(R.id.option1);
        optionET2 = (EditText) findViewById(R.id.option2);
        optionET3 = (EditText) findViewById(R.id.option3);
        optionET4 = (EditText) findViewById(R.id.option4);

    }

    public void createQuestion(View view){

        Toast.makeText(this,"This part is future recommendation",Toast.LENGTH_LONG).show();
        String questionTitle = questionTitleET.getText().toString();
        String option1 = optionET1.getText().toString();
        String option2 = optionET2.getText().toString();
        String option3 = optionET3.getText().toString();
        String option4 = optionET4.getText().toString();

    }

    public void goBack(View view){
        finish();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            answer1.setChecked(false);
            answer2.setChecked(false);
            answer3.setChecked(false);
            answer4.setChecked(false);
            switch ( buttonView.getId() ) {
                case R.id.answer1:
                    answer1.setChecked(true);
                    answer = 1;
                    break;
                case R.id.answer2:
                    answer2.setChecked(true);
                    answer = 2;
                    break;
                case R.id.answer3:
                    answer3.setChecked(true);
                    answer = 3;
                    break;
                case R.id.answer4:
                    answer4.setChecked(true);
                    answer = 4;
                    break;
            }
        }
    }

    private String getAnswer(){
        String answerSt = "";
        switch( answer ) {
            case 1:
                answerSt = optionET1.getText().toString();
                break;
            case 2:
                answerSt = optionET2.getText().toString();
                break;
            case 3:
                answerSt = optionET3.getText().toString();
                break;
            case 4:
                answerSt = optionET4.getText().toString();
                break;
        }
        return answerSt;
    }
}

