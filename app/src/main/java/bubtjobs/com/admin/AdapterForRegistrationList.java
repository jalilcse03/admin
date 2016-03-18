package bubtjobs.com.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mobile App Develop on 16-3-16.
 */
public class AdapterForRegistrationList extends ArrayAdapter<RegistrationListMaker> {

    private ArrayList<RegistrationListMaker> registrationListMaker;
    private Context context;

    public AdapterForRegistrationList(Context context, ArrayList<RegistrationListMaker> registrationListMaker) {
        super(context, R.layout.custom_row_student, registrationListMaker);
        this.context = context;
        this.registrationListMaker = registrationListMaker;
    }

    static class ViewHolder {
        TextView nameTv;
        TextView emailTv;
        TextView nameId;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_row_student, null);

            viewHolder = new ViewHolder();
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.nameTv);
            viewHolder.emailTv = (TextView) convertView.findViewById(R.id.emailTv);
            viewHolder.nameId = (TextView) convertView.findViewById(R.id.nameId);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.nameTv.setText(registrationListMaker.get(position).getUserName());
        viewHolder.emailTv.setText(registrationListMaker.get(position).getUserEmail());
        viewHolder.nameId.setText(registrationListMaker.get(position).getUserId());

        return convertView;
    }
}
