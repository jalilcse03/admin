package bubtjobs.com.admin.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import bubtjobs.com.admin.Adapter.RegistrationAdapter;
import bubtjobs.com.admin.DataBase.DataBaseManager;
import bubtjobs.com.admin.R;
import bubtjobs.com.admin.Getter_Setter.RegistrationListMaker;

public class ShowRegistrationList extends AppCompatActivity {
    ListView registrationListView;
    RegistrationListMaker registrationListMaker;
    ArrayList<RegistrationListMaker> list;

    DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_resgistration_list);

        registrationListView=(ListView)findViewById(R.id.registrationListView);

        dataBaseManager=new DataBaseManager(this);
        list=new ArrayList<>();

       list=dataBaseManager.getRegistrationList();



        if(list!=null)
        {
            RegistrationAdapter adapter=new RegistrationAdapter(getApplicationContext(),list);
            registrationListView.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(ShowRegistrationList.this,"No Data Found",Toast.LENGTH_SHORT).show();
        }

    }

}
