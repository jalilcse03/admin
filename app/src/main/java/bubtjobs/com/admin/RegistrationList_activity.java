package bubtjobs.com.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrationList_activity extends AppCompatActivity {
    ListView registrationListView;
    RegistrationList registrationList;
    ArrayList<RegistrationList> list;

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
    public void retrivetData(){
        registrationList=new RegistrationList("jalilur","jalil_cse@yahoo.com","1");
        list.add(registrationList);
        registrationList=new RegistrationList("murtuza","murtuza@gmail.com","2");
        list.add(registrationList);
        registrationList=new RegistrationList("ali","ali@yahoo.com","3");
        list.add(registrationList);
        registrationList=new RegistrationList("rakib","rakib@hot.com","4");
        list.add(registrationList);
        registrationList=new RegistrationList("mahabub","mahabub@gmail.com","5");
        list.add(registrationList);
        registrationList=new RegistrationList("mahi","mahi@yahoo.com","6");
        list.add(registrationList);
        registrationList=new RegistrationList("mithun","mithun@yahoo.com","7");
        list.add(registrationList);
        registrationList=new RegistrationList("tonmoy","tonmoy@gmail.com","8");
        list.add(registrationList);registrationList=new RegistrationList("arafat","arafat@yahoo.com","8");
        list.add(registrationList);
        registrationList=new RegistrationList("tarikul","tarikul@yahoo.com","10");
        list.add(registrationList);
        registrationList=new RegistrationList("nowrin","nowrin@yahoo.com","11");
        list.add(registrationList);
        registrationList=new RegistrationList("anik","anik@gmail.com","12");
        list.add(registrationList);
        registrationList=new RegistrationList("bitm","bitm@hotmail.com","13");
        list.add(registrationList);

    }

}
