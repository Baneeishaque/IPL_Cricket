package ndk.ipl_cricket.ui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;

public class Lisence extends Activity {

	WebView  v;
	// this context will use when we work with Alert Dialog
				final Context context = this;
				int s = 1;
				
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lisence);
		
		
		
		/* Alert Dialog Code Start*/ 	
    	AlertDialog.Builder alert = new AlertDialog.Builder(context);
    	
    	alert.setTitle("Terms & Conditions"); //Set Alert dialog title here
    	alert.setMessage(Html.fromHtml(getString(R.string.lisence)));
    	/*alert.setMessage(""); //Message here*/


    	alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				// TODO Auto-generated method stub
				 Lisence.this.finish();
				
			}
		});
    
    	alert.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    	 
    		//Close the Activity when click OK.
    		String FILE = "ini";
    		try 
    		{

    			FileOutputStream fos = openFileOutput(FILE, Context.MODE_PRIVATE);
    			try 
    			{
    				fos.write(s);
    				fos.close();
    			} 
    			catch (IOException e){
    				e.printStackTrace();}
    		} 
    		catch (FileNotFoundException e) 
    		{
    		}

    		Intent intent = new Intent(Lisence.this,Matches.class);
    		startActivity(intent);
    		Lisence.this.finish();

    	  }
    	});

    	alert.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
    	  public void onClick(DialogInterface dialog, int whichButton) {
    	    // Canceled.
    		  dialog.cancel();
    		
    		  Lisence.this.finish();
    	  }
    	});
    	AlertDialog alertDialog = alert.create();
    	alertDialog.show();
   /* Alert Dialog Code End*/ 
	}

	
	@Override
	public void onBackPressed() 
	{
		Lisence.this.finish();
	}
	
	
	
	
	
	
}
