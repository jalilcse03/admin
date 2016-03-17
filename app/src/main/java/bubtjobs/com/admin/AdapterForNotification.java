package bubtjobs.com.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Murtuza on 3/17/2016.
 */
public class AdapterForNotification extends ArrayAdapter<String> {

    private ArrayList<String> list;
    private Context context;

    public AdapterForNotification(Context context, ArrayList<String> list) {
        super(context, R.layout.notification_customs_rom, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        TextView text;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.notification_customs_rom, null);

            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.text.setText(list.get(position));

        return convertView;
    }
}
