package bubtjobs.com.admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Murtuza on 3/15/2016.
 */
public class DataBaseManager {
    private DataBaseHelper helper;
    private QuestionSetMake questionSetMake;
    private SQLiteDatabase database;


    public DataBaseManager(Context context){
        helper=new DataBaseHelper(context);
    }

    private void open(){
        database=helper.getWritableDatabase();
    }

    private void close(){
        helper.close();
    }

    public String addQuestion(ArrayList<QuestionSetMake> questionSetMakeArrayList)
    {
        try {


            this.open();
            database.execSQL("DROP TABLE IF EXISTS " + DataBaseHelper.TABLE_QUESTION);

            database.execSQL(DataBaseHelper.CREATE_TABLE_QUESTION_SET);

            for (QuestionSetMake obj : questionSetMakeArrayList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DataBaseHelper.COL_QUESTION, obj.getQuestion());
                contentValues.put(DataBaseHelper.COL_OP1, obj.getOp1());
                contentValues.put(DataBaseHelper.COL_OP2, obj.getOp2());
                contentValues.put(DataBaseHelper.COL_OP3, obj.getOp3());
                contentValues.put(DataBaseHelper.COL_OP4, obj.getOp4());
                contentValues.put(DataBaseHelper.ANSWER, obj.getAnswer());
                long inserted = database.insert(DataBaseHelper.TABLE_QUESTION, null, contentValues);

            }
            this.close();
            return "yes";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }

    public boolean addAnswer(ArrayList<AnswerSetMaker> arrayList){
        try{
            this.open();
            database.execSQL("DROP TABLE IF EXISTS " + DataBaseHelper.TABLE_ANSWER);

            database.execSQL(DataBaseHelper.CREATE_TABLE_ANSWER);

            for(AnswerSetMaker obj:arrayList)
            {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DataBaseHelper.ANS_KEY,obj.getKey());
                contentValues.put(DataBaseHelper.ANS_VALUE, obj.getValue());

                long inserted = database.insert(DataBaseHelper.TABLE_ANSWER, null, contentValues);

            }


            this.close();
            return true;
        }
        catch (Exception e){
            return  false;
        }

    }
    public ArrayList<QuestionSetMake> getAllQuestion(){
        this.open();
        ArrayList<QuestionSetMake> questions=new ArrayList<>();
        String query="SELECT * FROM "+DataBaseHelper.TABLE_QUESTION;
        Cursor cursor=database.rawQuery(query, null);
        cursor.moveToFirst();


        if(cursor!=null && cursor.getCount()>0){
            for(int i=0;i<cursor.getCount();i++)
            {
                String question=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_QUESTION));
                String answer=cursor.getString(cursor.getColumnIndex(DataBaseHelper.ANSWER));
                String op1=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_OP1));
                String op2=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_OP2));
                String op3=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_OP3));
                String op4=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_OP4));
                String qusId=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_ID));
                questionSetMake=new QuestionSetMake(question,answer,op1,op2,op3,op4,qusId);
                questions.add(questionSetMake);
                cursor.moveToNext();
            }
        }
        this.close();
        return questions;
    }

    public String updateAnswer(String id,String answer){
        try {
            this.open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseHelper.ANS_VALUE,answer);

            int updated = database.update(DataBaseHelper.TABLE_ANSWER, contentValues, DataBaseHelper.COL_ID + " = " + Integer.parseInt(id), null);
            this.close();
            return "yes";
        }
        catch (Exception e)
        {
            return  e.toString();
        }
    }

    /****************************total result show per person *******************************************/

    public int result(){
        int cnt=0;
        try{
            this.open();

            String query="SELECT * FROM "+DataBaseHelper.TABLE_ANSWER;
            Cursor cursor=database.rawQuery(query, null);
            cursor.moveToFirst();

            if(cursor!=null && cursor.getCount()>0){
                for(int i=0;i<cursor.getCount();i++)
                {
                    String key=cursor.getString(cursor.getColumnIndex(DataBaseHelper.ANS_KEY));
                    String value=cursor.getString(cursor.getColumnIndex(DataBaseHelper.ANS_VALUE));
                    if(key.equals(value))
                        cnt++;
                        cursor.moveToNext();
                }
            }

            this.close();
            return cnt;
        }
        catch (Exception e)
        {
            return cnt;
        }
        //return  0;
    }

    /**************************** create account *******************************************/
    public String creatAccount(String name,String email,String password){
        try{
            this.open();

            // check email already use
            Cursor cursor1 = database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+DataBaseHelper.TABLE_STUDENTINFO+"'", null);
            if(cursor1!=null && cursor1.getCount()>0) {
                boolean flag = true;
                String sql = "select " + DataBaseHelper.EMAIL + " from " + DataBaseHelper.TABLE_STUDENTINFO +" where "+DataBaseHelper.USER_STATUS+ "= 'active' ";
                Cursor cursor = database.rawQuery(sql, null);
                cursor.moveToFirst();
                if (cursor != null && cursor.getCount() > 0) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        String retriveEmail = cursor.getString(cursor.getColumnIndex(DataBaseHelper.EMAIL));
                        if (retriveEmail.equals(email)) {
                            this.close();
                            return "email exist";
                        }
                        cursor.moveToNext();
                    }
                }
            }

            // is email is valid
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseHelper.NAME,name);
            contentValues.put(DataBaseHelper.EMAIL,email);
            contentValues.put(DataBaseHelper.PASSWORD,password);
            contentValues.put(DataBaseHelper.EXAM_STATUS,"0");
            contentValues.put(DataBaseHelper.MARKS, "0");
            contentValues.put(DataBaseHelper.NOTIFICATION, "0");
            contentValues.put(DataBaseHelper.USER_STATUS, "active");
            long inserted = database.insert(DataBaseHelper.TABLE_STUDENTINFO, null, contentValues);
            this.close();
            return "yes";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }
    /**************************** login *******************************************/
    public String login(String email,String password)
    {
        try {
            this.open();
            String sql = "select * from " + DataBaseHelper.TABLE_STUDENTINFO + " where " + DataBaseHelper.EMAIL + " = '" + email + "' and " + DataBaseHelper.PASSWORD + " = '" + password+"' and "+DataBaseHelper.USER_STATUS+" = 'active'";
            Cursor cursor = database.rawQuery(sql, null);
            cursor.moveToFirst();

            if (cursor != null && cursor.getCount() > 0) {
                String id=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_ID));
                this.close();
                return id;
            } else {
                this.close();
                return "no";
            }
        }
        catch (Exception e)
        {
            this.close();
            //return e.toString();
            return "error";
        }

    }

/**************************** data reset *******************************************/
    public boolean reset(){
        try{
            this.open();
            database.execSQL("DROP TABLE IF EXISTS " + DataBaseHelper.TABLE_STUDENTINFO);
            database.execSQL("DROP TABLE IF EXISTS " + DataBaseHelper.TABLE_ANSWER);
            database.execSQL("DROP TABLE IF EXISTS " + DataBaseHelper.TABLE_QUESTION);

            this.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

 /************************************* view student list admin **********************/

    public ArrayList<RegistrationListMaker> getRegistrationList(){
        RegistrationListMaker registrationListMaker =new RegistrationListMaker();
        ArrayList<RegistrationListMaker> list=new ArrayList<>();
        try {
            this.open();
            String sql = "select * from " + DataBaseHelper.TABLE_STUDENTINFO + " where " + DataBaseHelper.USER_STATUS + " = 'active' ";
            Cursor cursor = database.rawQuery(sql, null);

            cursor.moveToFirst();

            if (cursor != null && cursor.getCount() > 0) {
                for(int i=0;i<cursor.getCount();i++) {
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.NAME));
                    String email = cursor.getString(cursor.getColumnIndex(DataBaseHelper.EMAIL));
                    String id = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_ID));

                    registrationListMaker = new RegistrationListMaker(name, email, id);
                    list.add(registrationListMaker);
                    cursor.moveToNext();
                }
            }

            this.close();

            return list;

        }
        catch (Exception e){
          this.close();
            return  list;

        }
    }
    /**************************  message send  ************************************/

    public String  notificationSend(String text)
    {
        try{
            this.open();
            Cursor cursor1 = database.rawQuery("select * from "+DataBaseHelper.TABLE_STUDENTINFO, null);
            if(cursor1!=null && cursor1.getCount()>0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DataBaseHelper.MESSAGE_BODY, text);
                long inserted = database.insert(DataBaseHelper.TABLE_NOTIFICATION, null, contentValues);
                this.close();
                return "yes";
            }
            else
            {
               return  "NO Student";
            }
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }

    /*************************** notification status change **********************************/

    public boolean notificationStatusChange(){
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.NOTIFICATION,"1");
        int updated = database.update(DataBaseHelper.TABLE_STUDENTINFO, contentValues, DataBaseHelper.USER_STATUS + " = 'active'" , null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }

    /*************************************** check new notification **************/
    public String checkNewNotificatin(String id){
        try{
            this.open();
            String sql="select * from "+DataBaseHelper.TABLE_STUDENTINFO+ " where "+DataBaseHelper.COL_ID+" = "+id +" and  "+DataBaseHelper.NOTIFICATION+" = 1";
            Cursor cursor=database.rawQuery(sql,null);
            if(cursor!=null && cursor.getCount()>0)
            {
                this.close();
                return "yes";
            }
            else
            {
                this.close();
                return "no";
            }

        }
        catch (Exception e)
        {
           return  e.toString();
        }
    }
    /*************************************** get Notification **************/

    public ArrayList<String> getNotification(){

        try{
            this.open();
            ArrayList<String> list=new ArrayList<>();
            String sql="select * from "+DataBaseHelper.TABLE_NOTIFICATION +" ORDER BY  "+DataBaseHelper.COL_ID+ " DESC";
            Cursor cursor=database.rawQuery(sql,null);

            if(cursor!=null && cursor.getCount()>0)
            {
                cursor.moveToFirst();

                for(int i=0;i<cursor.getCount();i++)
                {
                    list.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.MESSAGE_BODY)));
                    cursor.moveToNext();
                }
            }
            this.close();
            return list;
        }
        catch (Exception e)
        {

        }
        return  null;
    }
    /************************************* studentUpdataNotificationStatus **************************/
    public boolean studentUpdataNotificationStatus(String id){
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.NOTIFICATION,"0");
        int updated = database.update(DataBaseHelper.TABLE_STUDENTINFO, contentValues, DataBaseHelper.COL_ID + " = "+id , null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }
    /************************************* Exam Status update **************************/
    public boolean studentExamStatusUpdate(String id){
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.EXAM_STATUS,"1");
        int updated = database.update(DataBaseHelper.TABLE_STUDENTINFO, contentValues, DataBaseHelper.COL_ID + " = "+id , null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }
    /************************************* result update **************************/
    public boolean studentResultUpdate(String id,String mark){
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.MARKS,mark);
        int updated = database.update(DataBaseHelper.TABLE_STUDENTINFO, contentValues, DataBaseHelper.COL_ID + " = "+id , null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }
    /***************************** check before exam attend ********************************************/
    public boolean checkExamStatus(String id)
    {
        this.open();

        String sql="select * from "+DataBaseHelper.TABLE_STUDENTINFO+ " where "+DataBaseHelper.COL_ID+" = "+id+" and "+DataBaseHelper.EXAM_STATUS+" = 0";

        Cursor cursor=database.rawQuery(sql, null);
        if(cursor!=null && cursor.getCount()>0)
        {
            this.close();
            return true;
        }
        return false;

    }

    /***************************** show result ********************************************/
    public String showStudentResult(String id)
    {
        this.open();

        String sql="select * from "+DataBaseHelper.TABLE_STUDENTINFO+ " where "+DataBaseHelper.COL_ID+" = "+id;

        Cursor cursor=database.rawQuery(sql,null);
        cursor.moveToFirst();

        if(cursor!=null && cursor.getCount()>0)
        {
            this.close();

            return cursor.getString(cursor.getColumnIndex(DataBaseHelper.MARKS));
        }
        return "0";

    }
    /***************************** resgistration cancel ********************************************/
    public boolean resgistrationCancel(String id){
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.USER_STATUS,"inactive");
        int updated = database.update(DataBaseHelper.TABLE_STUDENTINFO, contentValues, DataBaseHelper.COL_ID + " = "+id , null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }
}
