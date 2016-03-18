package bubtjobs.com.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NotificationReceiveStudent extends AppCompatActivity {
    ListView listView;
    ArrayList<String> list;
    DataBaseManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_activity);
        listView=(ListView)findViewById(R.id.listView);

        manager=new DataBaseManager(this);


        list=new ArrayList<>();
        list=manager.getNotification();

        if(list!=null)
        {
            AdapterForNotification adapter=new AdapterForNotification(this,list);
            listView.setAdapter(adapter);
        }

    }
}
