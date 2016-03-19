package bubtjobs.com.admin.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import bubtjobs.com.admin.Adapter.NotificationAdapter;
import bubtjobs.com.admin.DataBase.DataBaseManager;
import bubtjobs.com.admin.Others.SessionManager;
import bubtjobs.com.admin.R;

public class StudentNotification extends AppCompatActivity {
    ListView listView;
    ArrayList<String> list;
    DataBaseManager manager;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_notification);
        listView=(ListView)findViewById(R.id.listView);

        manager=new DataBaseManager(this);
        sessionManager=new SessionManager(this);

        setTitle(sessionManager.getUserName()+" Inbox");

        list=new ArrayList<>();
        list=manager.getNotification();

        if(list!=null)
        {
            NotificationAdapter adapter=new NotificationAdapter(this,list);
            listView.setAdapter(adapter);
        }

    }
}
