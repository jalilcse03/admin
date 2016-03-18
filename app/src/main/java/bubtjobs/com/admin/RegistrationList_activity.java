package bubtjobs.com.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrationList_activity extends AppCompatActivity {
    ListView registrationListView;
    RegistrationListMaker registrationListMaker;
    ArrayList<RegistrationListMaker> list;

    DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_list_activity);

        registrationListView=(ListView)findViewById(R.id.registrationListView);

        dataBaseManager=new DataBaseManager(this);
        list=new ArrayList<>();

       list=dataBaseManager.getRegistrationList();



        if(list!=null)
        {
            AdapterForRegistrationList adapter=new AdapterForRegistrationList(getApplicationContext(),list);
            registrationListView.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(RegistrationList_activity.this,"No Data Found",Toast.LENGTH_SHORT).show();
        }

    }

}
