package bubtjobs.com.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotificationSendAdmin extends AppCompatActivity {
    EditText message;
    CommonFunction function;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_activity);
        message=(EditText)findViewById(R.id.messageEt);
        function=new CommonFunction();
    }

    public void sendMessage(View view)
    {
        if(function.isEmpty(message))
        {
            DataBaseManager manager=new DataBaseManager(this);
            String result=manager.notificationSend(message.getText().toString());
            if(result.equals("yes"))
            {
                boolean b=manager.notificationStatusChange();
                Toast.makeText(this,"Notification Sent ",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,admin_home.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
            else
                Toast.makeText(this,result,Toast.LENGTH_LONG).show();
        }
        else{
            message.setError("Message Body Empty");
        }
    }
    public void adminHome(View view)
    {
        startActivity(new Intent(this,admin_home.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
