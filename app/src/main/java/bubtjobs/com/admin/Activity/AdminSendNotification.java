package bubtjobs.com.admin.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bubtjobs.com.admin.Others.CommonFunction;
import bubtjobs.com.admin.DataBase.DataBaseManager;
import bubtjobs.com.admin.R;

public class AdminSendNotification extends AppCompatActivity {
    EditText message;
    CommonFunction function;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_send_notification);
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
                startActivity(new Intent(this,AdminHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
        startActivity(new Intent(this,AdminHome.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
