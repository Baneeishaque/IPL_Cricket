package ndk.ipl_cricket.ui;


import java.io.FileInputStream;
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
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Launch extends Activity {

	
	View v;
	ProgressBar Pb;
	String FILENAME = "ini";
	int s=0;
	final Context context = this;
	int a[] = { 50, 100 }, i;
	Thread t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launch);
		
		Pb = (ProgressBar) findViewById(R.id.progressBar1);
		Pb.setProgress(0);
	
		
		t=new Thread(new Runnable() 
		{ 
			public void run() 
			{ 
				try 
				{
		  
					for(i=0;i<2;i++) 
					{
		  
						Thread.sleep(1000);
		  
						Pb.setProgress(a[i]); 
					} 
					//next(v); 
				} 
				catch(InterruptedException e) 
				{
					e.printStackTrace(); 
				} 
			} 
		});
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Toast.makeText(getApplicationContext(), "tread completed", Toast.LENGTH_LONG).show();

		try 
		{
			FileInputStream fos = openFileInput(FILENAME);
			try 
			{
				fos.read();
				fos.close();
			} 
			catch (IOException e) 
			{
				//e.printStackTrace();
			}

		} 
		
		catch (FileNotFoundException e) 
		{
			//e.printStackTrace();
			/* Alert Dialog Code Start*/ 	
	    	AlertDialog.Builder alert = new AlertDialog.Builder(context);
	    	
	    	alert.setTitle("Terms & Conditions"); //Set Alert dialog title here
	    	alert.setMessage(Html.fromHtml(getString(R.string.lisence)));
	    	alert.setMessage(""); //Message here*/


	    	alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					// TODO Auto-generated method stub
					if(s!=1)
					{
						Launch.this.finish();
					}
					
					
				}
			});
	    
	    	alert.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int whichButton) {
	    	 
	    		//Toast.makeText(getApplicationContext(), "tread completed", Toast.LENGTH_LONG).show();
	    		s=1;
	    		//Close the Activity when click OK.
	    		/*String FILE = "ini";
	    		
	    		try 
	    		{

	    			FileOutputStream fos = openFileOutput(FILE, Context.MODE_PRIVATE);
	    			try 
	    			{
	    				fos.write(s);
	    				fos.close();
	    			} 
	    			catch (IOException e){
	    				//e.printStackTrace();
	    				}
	    		} 
	    		catch (FileNotFoundException e) 
	    		{
	    		}*/

	    	
	    		
	    		Intent intent = new Intent(Launch.this,Matches.class);
	    		startActivity(intent);
	    		Launch.this.finish();

	    	  }
	    	});

	    	
	    	alert.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
	    	  public void onClick(DialogInterface dialog, int whichButton) {
	    	    // Canceled.
	    		dialog.cancel();
	    		
	    		  Launch.this.finish();
	    	  }
	    	});
	    	
	    	AlertDialog alertDialog = alert.create();
	    	alertDialog.show();
	    	/* Alert Dialog Code End*/ 
		}
	
	}

	  

	
	public void next(View v) {
		
		try 
		{
			FileInputStream fos = openFileInput(FILENAME);
			try 
			{
				fos.read();
				fos.close();
			} 
			catch (IOException e) 
			{
				//e.printStackTrace();
			}

		} 
		catch (FileNotFoundException e) 
		{
			//e.printStackTrace();
			/* Alert Dialog Code Start*/ 	
	    	AlertDialog.Builder alert = new AlertDialog.Builder(context);
	    	
	    	alert.setTitle("Terms & Conditions"); //Set Alert dialog title here
	    	alert.setMessage(Html.fromHtml(getString(R.string.lisence)));
	    	alert.setMessage(""); //Message here*/


	    	/*alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					// TODO Auto-generated method stub
					 Launch.this.finish();
					
				}
			});*/
	    
	    	alert.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int whichButton) {
	    	 
	    		//Close the Activity when click OK.
	    		/*String FILE = "ini";
	    		s=1;
	    		try 
	    		{

	    			FileOutputStream fos = openFileOutput(FILE, Context.MODE_PRIVATE);
	    			try 
	    			{
	    				fos.write(s);
	    				fos.close();
	    			} 
	    			catch (IOException e){
	    				//e.printStackTrace();
	    				}
	    		} 
	    		catch (FileNotFoundException e) 
	    		{
	    		}

	    	
	    		
	    		Intent intent = new Intent(Launch.this,Matches.class);
	    		startActivity(intent);
	    		Launch.this.finish();*/

	    	  }
	    	});

	    	alert.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
	    	  public void onClick(DialogInterface dialog, int whichButton) {
	    	    // Canceled.
	    		/*  dialog.cancel();
	    		
	    		  Launch.this.finish();*/
	    	  }
	    	});
	    	AlertDialog alertDialog = alert.create();
	    	alertDialog.show();
	   /* Alert Dialog Code End*/ 
		}
	
		/*if (s != 1) 
		{
			   
			/* Alert Dialog Code Start*/ 	
	    	/*AlertDialog.Builder alert = new AlertDialog.Builder(context);
	    	
	    	alert.setTitle("Terms & Conditions"); //Set Alert dialog title here
	    	alert.setMessage(Html.fromHtml(getString(R.string.lisence)));
	    	/*alert.setMessage(""); //Message here*/


	    	/*alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					// TODO Auto-generated method stub
					 Launch.this.finish();
					
				}
			});
	    
	    	alert.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int whichButton) {
	    	 
	    		//Close the Activity when click OK.
	    		String FILE = "ini";
	    		s=1;
	    		try 
	    		{

	    			FileOutputStream fos = openFileOutput(FILE, Context.MODE_PRIVATE);
	    			try 
	    			{
	    				fos.write(s);
	    				fos.close();
	    			} 
	    			catch (IOException e){
	    				//e.printStackTrace();
	    				}
	    		} 
	    		catch (FileNotFoundException e) 
	    		{
	    		}

	    	
	    		
	    		Intent intent = new Intent(Launch.this,Matches.class);
	    		startActivity(intent);
	    		Launch.this.finish();

	    	  }
	    	});

	    	alert.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
	    	  public void onClick(DialogInterface dialog, int whichButton) {
	    	    // Canceled.
	    		  dialog.cancel();
	    		
	    		  Launch.this.finish();
	    	  }
	    	});
	    	AlertDialog alertDialog = alert.create();
	    	alertDialog.show();
	   /* Alert Dialog Code End*/ 
		//} 
			/*Intent target = new Intent(this,Matches.class);
			
			startActivity(target);
			this.finish();*/
		
	}

}
