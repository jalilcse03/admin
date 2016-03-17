package bubtjobs.com.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Murtuza on 3/15/2016.
 */
public class AdapterForExam extends ArrayAdapter<QuestionSetMake> {
    DataBaseManager dataBaseManager;

    private ArrayList<QuestionSetMake> questionList;
    private Context context;

   private int mSelectedVariation=0;

    public AdapterForExam(Context context, ArrayList<QuestionSetMake> questionList) {
        super(context, R.layout.single_row,questionList);
        this.context=context;
        this.questionList=questionList;
        dataBaseManager=new DataBaseManager(context);
    }

    static class ViewHolder{
        TextView qestionTv;
        TextView idTv;
        RadioButton option1,option2,option3,option4;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.single_row,null);

            viewHolder=new ViewHolder();
            viewHolder.qestionTv=(TextView)convertView.findViewById(R.id.qestionTv);
            viewHolder.idTv=(TextView)convertView.findViewById(R.id.idTv);
            viewHolder.option1=(RadioButton)convertView.findViewById(R.id.op1);
            viewHolder.option2=(RadioButton)convertView.findViewById(R.id.op2);
            viewHolder.option3=(RadioButton)convertView.findViewById(R.id.op3);
            viewHolder.option4=(RadioButton)convertView.findViewById(R.id.op4);


            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }


        if(position==mSelectedVariation) viewHolder.option1.setChecked(true);
        else viewHolder.option1.setChecked(false);




        viewHolder.option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String t =dataBaseManager.updateAnswer(viewHolder.idTv.getText().toString(),viewHolder.option1.getText().toString());
                mSelectedVariation = position;
                AdapterForExam.this.notifyDataSetChanged();
            }
        });



        viewHolder.option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String t =dataBaseManager.updateAnswer(viewHolder.idTv.getText().toString(),viewHolder.option2.getText().toString());
               // Toast.makeText(context,""+viewHolder.option2.getText().toString()+"ID: "+viewHolder.idTv.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t =dataBaseManager.updateAnswer(viewHolder.idTv.getText().toString(),viewHolder.option3.getText().toString());

            }
        });
        viewHolder.option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t =dataBaseManager.updateAnswer(viewHolder.idTv.getText().toString(),viewHolder.option4.getText().toString());

            }
        });



        return convertView;
    }
}
