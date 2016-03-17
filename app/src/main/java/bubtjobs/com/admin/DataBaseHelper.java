package bubtjobs.com.admin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Murtuza on 3/15/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION=1;
    static final String DATABSE_NAME="bitm_registration";

    // table name
    static final String TABLE_QUESTION="question_set";
    static final String TABLE_ANSWER="answer_set";
    static final String TABLE_STUDENTINFO="student_info";
    static final String TABLE_NOTIFICATION="notification_info";


    /****************************** variable *************************/
    static final String COL_ID="id";
    static final String COL_QUESTION="question";
    static final String COL_OP1="option1";
    static final String COL_OP2="option2";
    static final String COL_OP3="option3";
    static final String COL_OP4="option4";
    static final String ANSWER="answer";
    //answer table
    static final String ANS_ID="id";
    static final String ANS_KEY="key";
    static final String ANS_VALUE="value";
    // student info
    static final String NAME="name";
    static final String  EMAIL="email";
    static final String  PASSWORD="password";
    static final String  MARKS="marks";
    static final String  EXAM_STATUS="exam_status";
    static final String  EXAM_DATE="exam_date";
    static final String  NOTIFICATION="notification";
    static final String  USER_STATUS="user_status";
    // message
    static final String MESSAGE_BODY="message";

    // table qustion set
    static final  String CREATE_TABLE_QUESTION_SET=" CREATE TABLE "+TABLE_QUESTION+" ( "+COL_ID+" INTEGER PRIMARY KEY,"+
            COL_QUESTION+" TEXT,"+COL_OP1+" TEXT,"+COL_OP2+" TEXT,"+COL_OP3+" TEXT,"+COL_OP4+" TEXT,"+ANSWER+" TEXT )";

    // table answer set
   static final String CREATE_TABLE_ANSWER=" CREATE TABLE "+TABLE_ANSWER+" ( "+COL_ID+" INTEGER PRIMARY KEY,"+
            ANS_KEY+" TEXT,"+ANS_VALUE+" TEXT )";

    // table student info
    static final String CREATE_TABLE_SUDENTINFO=" CREATE TABLE "+TABLE_STUDENTINFO+" ( "+COL_ID+" INTEGER PRIMARY KEY,"+
            NAME+" TEXT,"+ EMAIL+" TEXT,"+ PASSWORD+" TEXT,"+ MARKS+" TEXT,"+ EXAM_STATUS+" TEXT," + EXAM_DATE+" TEXT," + NOTIFICATION+" TEXT,"+USER_STATUS+" TEXT )";
// table message
static final String CREATE_TABLE_NOTIFICATION=" CREATE TABLE "+TABLE_NOTIFICATION+" ( "+COL_ID+" INTEGER PRIMARY KEY,"+MESSAGE_BODY+" TEXT )";

    public DataBaseHelper(Context context) {
        super(context,DATABSE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUESTION_SET);
        db.execSQL(CREATE_TABLE_SUDENTINFO);
        db.execSQL(CREATE_TABLE_ANSWER);
        db.execSQL(CREATE_TABLE_NOTIFICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}

