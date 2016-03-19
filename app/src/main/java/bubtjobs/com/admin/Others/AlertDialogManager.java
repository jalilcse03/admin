package bubtjobs.com.admin.Others;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogManager {
	
	public void showAlertDialog(Context context,String title,String message,boolean status){
		AlertDialog alertDialog=new AlertDialog.Builder(context).create();
		
		//setting dialog title
		alertDialog.setTitle(title);
		
		//setting dialog message
		alertDialog.setMessage(message);
		
		//setting dialog icon
		//alertDialog.setIcon((status)?R.drawable.success:R.drawable.fail);
		
		
		//setting ok button
		alertDialog.setButton("OK",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		//showing alert message
		alertDialog.show();
		
	}

}
