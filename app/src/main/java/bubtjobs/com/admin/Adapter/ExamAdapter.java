package bubtjobs.com.admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import bubtjobs.com.admin.DataBase.DataBaseManager;
import bubtjobs.com.admin.Getter_Setter.QuestionSetMake;
import bubtjobs.com.admin.R;

/**
 * Created by Murtuza on 3/15/2016.
 */
public class ExamAdapter extends ArrayAdapter<QuestionSetMake> {
    DataBaseManager dataBaseManager;

    private ArrayList<QuestionSetMake> questionList;
    private Context context;

    public ExamAdapter(Context context, ArrayList<QuestionSetMake> questionList) {
        super(context, R.layout.question_set_custom_row,questionList);
        this.context=context;
        this.questionList=questionList;
        dataBaseManager=new DataBaseManager(context);
    }

    static class ViewHolder{
        TextView qestionTv;
        TextView idTv;
        RadioButton option1,option2,option3,option4,option5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.question_set_custom_row,null);

            viewHolder=new ViewHolder();
            viewHolder.qestionTv=(TextView)convertView.findViewById(R.id.qestionTv);
            viewHolder.idTv=(TextView)convertView.findViewById(R.id.idTv);
            viewHolder.option1=(RadioButton)convertView.findViewById(R.id.op1);
            viewHolder.option2=(RadioButton)convertView.findViewById(R.id.op2);
            viewHolder.option3=(RadioButton)convertView.findViewById(R.id.op3);
            viewHolder.option4=(RadioButton)convertView.findViewById(R.id.op4);
            viewHolder.option5=(RadioButton)convertView.findViewById(R.id.op5);



            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }





        viewHolder.qestionTv.setText(questionList.get(position).getQuestion());
        viewHolder.idTv.setText(questionList.get(position).getQustionId());
        viewHolder.option1.setText(questionList.get(position).getOp1());
        viewHolder.option2.setText(questionList.get(position).getOp2());
        viewHolder.option3.setText(questionList.get(position).getOp3());
        viewHolder.option4.setText(questionList.get(position).getOp4());

        HashMap<String,String> hashMap=new HashMap<>();

        hashMap =dataBaseManager.answerRetrive(Integer.parseInt(viewHolder.idTv.getText().toString()));

        if(hashMap!=null)
        {
           // int index=Integer.parseInt(hashMap.get("index"));
            String index=hashMap.get("index");
            String value=hashMap.get("value");
          //  Toast.makeText(context,index+""+value,Toast.LENGTH_LONG).show();
            if(Integer.parseInt(index)-1==position)
            {
                if(viewHolder.option1.getText().toString().equals(value))
                {
                   // Toast.makeText(context,index+""+viewHolder.option1.getText().toString(),Toast.LENGTH_LONG).show();
                    viewHolder.option1.setChecked(true);
                    viewHolder.option2.setChecked(false);
                    viewHolder.option3.setChecked(false);
                    viewHolder.option4.setChecked(false);
                    viewHolder.option5.setChecked(false);
                }
                else if(viewHolder.option2.getText().toString().equals(value))
                {
                    viewHolder.option1.setChecked(false);
                    viewHolder.option2.setChecked(true);
                    viewHolder.option3.setChecked(false);
                    viewHolder.option4.setChecked(false);
                    viewHolder.option5.setChecked(false);
                }
                else if(viewHolder.option3.getText().toString().equals(value))
                {
                    viewHolder.option1.setChecked(false);
                    viewHolder.option2.setChecked(false);
                    viewHolder.option3.setChecked(true);
                    viewHolder.option4.setChecked(false);
                    viewHolder.option5.setChecked(false);
                }
                else if(viewHolder.option4.getText().toString().equals(value))
                {
                    viewHolder.option1.setChecked(false);
                    viewHolder.option2.setChecked(false);
                    viewHolder.option3.setChecked(false);
                    viewHolder.option4.setChecked(true);
                    viewHolder.option5.setChecked(false);
                }
                else{
                    viewHolder.option1.setChecked(false);
                    viewHolder.option2.setChecked(false);
                    viewHolder.option3.setChecked(false);
                    viewHolder.option4.setChecked(false);
                    viewHolder.option5.setChecked(true);
                }
            }
            else{
                viewHolder.option1.setChecked(false);
                viewHolder.option2.setChecked(false);
                viewHolder.option3.setChecked(false);
                viewHolder.option4.setChecked(false);
                viewHolder.option5.setChecked(true);
            }
        }
        else{
            viewHolder.option1.setChecked(false);
            viewHolder.option2.setChecked(false);
            viewHolder.option3.setChecked(false);
            viewHolder.option4.setChecked(false);
            viewHolder.option5.setChecked(true);
        }

        //String an=dataBaseManager.answerRetrive(position);


       // Toast.makeText(context,""+position,Toast.LENGTH_LONG).show();



/*
        if(position==0 &&  viewHolder.idTv.getText().toString().equals("1"))
        {
            viewHolder.option1.setChecked(true);
            viewHolder.option2.setChecked(false);
            viewHolder.option3.setChecked(false);
            viewHolder.option4.setChecked(false);
        }
        if(position==1 &&  viewHolder.idTv.getText().toString().equals("2"))
        {
            viewHolder.option1.setChecked(false);
            viewHolder.option2.setChecked(true);
            viewHolder.option3.setChecked(false);
            viewHolder.option4.setChecked(false);
        }
        if(position==2 &&  viewHolder.idTv.getText().toString().equals("3"))
        {
            viewHolder.option1.setChecked(false);
            viewHolder.option2.setChecked(false);
            viewHolder.option3.setChecked(true);
            viewHolder.option4.setChecked(false);
        }
        if(position==3 &&  viewHolder.idTv.getText().toString().equals("4"))
        {
            viewHolder.option1.setChecked(false);
            viewHolder.option2.setChecked(false);
            viewHolder.option3.setChecked(false);
            viewHolder.option4.setChecked(true);
        }
        if(position==4 &&  viewHolder.idTv.getText().toString().equals("5"))
        {
            viewHolder.option1.setChecked(true);
            viewHolder.option2.setChecked(false);
            viewHolder.option3.setChecked(false);
            viewHolder.option4.setChecked(false);
        }
        if(position==5 &&  viewHolder.idTv.getText().toString().equals("6"))
        {
            viewHolder.option1.setChecked(false);
            viewHolder.option2.setChecked(true);
            viewHolder.option3.setChecked(false);
            viewHolder.option4.setChecked(false);
        }
*/

        viewHolder.option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t =dataBaseManager.updateAnswer(viewHolder.idTv.getText().toString(),viewHolder.option1.getText().toString());
            }
        });
        viewHolder.option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t =dataBaseManager.updateAnswer(viewHolder.idTv.getText().toString(),viewHolder.option2.getText().toString());
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
