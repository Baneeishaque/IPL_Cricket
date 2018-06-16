package ndk.ipl_cricket.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Matches extends ActionBarActivity {

	private Vector ls;
	private ProgressDialog dialog;
	private DefaultHttpClient httpclient;
	private HttpPost httppost;
	private String response;
	private Thread t;
	private Vector lscode;
	final Context context = this;

	 String FILENAME = "ini";
		int s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.matches);
		
		try 
		{
			FileInputStream fos = openFileInput(FILENAME);
			try 
			{
				s = fos.read();
				fos.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		if (s != 1) 
		{
			   
			/* Alert Dialog Code Start*/ 	
	    	AlertDialog.Builder alert = new AlertDialog.Builder(context);
	    	
	    	alert.setTitle("Terms & Conditions"); //Set Alert dialog title here
	    	alert.setMessage(Html.fromHtml(getString(R.string.lisence)));
	    	/*alert.setMessage(""); //Message here*/


	    	alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					// TODO Auto-generated method stub
					 Matches.this.finish();
					
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
	    				e.printStackTrace();}
	    		} 
	    		catch (FileNotFoundException e) 
	    		{
	    		}

	    		dialog.cancel();
	    		
	    		/*Intent intent = new Intent(Matches.this,Matches.class);
	    		startActivity(intent);
	    		Matches.this.finish();*/

	    	  }
	    	});

	    	alert.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
	    	  public void onClick(DialogInterface dialog, int whichButton) {
	    	    // Canceled.
	    		  dialog.cancel();
	    		
	    		  Matches.this.finish();
	    	  }
	    	});
	    	AlertDialog alertDialog = alert.create();
	    	alertDialog.show();
	   /* Alert Dialog Code End*/ 
		} 
		else
		{
			ls=new Vector();
			lscode=new Vector();
			
			dialog = ProgressDialog.show(this, "", 
	                "Refreshing...", true);
			
			t=new Thread(new Runnable() {
				    public void run() {
				    	getmatches();					      
				    }
				  });
			t.start();	
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.listview,ls);
			final ListView listview=(ListView) findViewById(R.id.list);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new OnItemClickListener() {
		           

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Matches.this, Match_Details.class);
		                String matchtitle =  (String) parent.getItemAtPosition(position);
		                intent.putExtra("title", matchtitle);
		                intent.putExtra("id", lscode.get(position).toString());
		                startActivity(intent);
						
					}
		        });
		}

		
		
    	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.matches, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private void getmatches() {
		// TODO Auto-generated method stub
		try{			
			 
			httpclient=new DefaultHttpClient();
			httppost= new HttpPost(Globaldata.ip+"matches.php"); // make sure the url is correct.
						
		
			//Execute HTTP Post Request
			// edited by James from coderzheaven.. from here....
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			response = httpclient.execute(httppost, responseHandler);
			//System.out.println("Response : " + response); 
			
			runOnUiThread(new Runnable() {
			    public void run() {
			    	//Toast.makeText(Matches.this,"Response from PHP : " + response,Toast.LENGTH_LONG).show();
					dialog.dismiss();
			    }
			});
			
		
			
		}catch(Exception e){
			runOnUiThread(new Runnable() {
			    public void run() {
			    	Toast.makeText(Matches.this,"Try Again", Toast.LENGTH_SHORT).show();
			    	dialog.dismiss();
					 }
			});
			
			
		}
	
		
				
 		    		String p=response;
 		    		while(p.contains(":"))
 		    		{
 		    			int matchposition=p.indexOf(":");
 		    			final String matchtotal=p.subSequence(0,matchposition).toString();
 		    			final int matchcodeposition=matchtotal.indexOf("~");
 		    			final String matchcode=matchtotal.substring(matchcodeposition+1);
 		    			ls.add(matchtotal.substring(0, matchcodeposition));
 		    			lscode.add(matchcode);
 		    			
 		    			/*runOnUiThread(new Runnable() {
 		   			    public void run() {
 		   			    Toast.makeText(Matches.this,matchtotal, Toast.LENGTH_SHORT).show();
 		   			Toast.makeText(Matches.this,String.valueOf(matchcodeposition), Toast.LENGTH_SHORT).show();
 		   		 Toast.makeText(Matches.this,matchcode, Toast.LENGTH_SHORT).show();
		   			 
 		   					 }
 		   			});*/
 		    			
 		    			p=p.substring(matchposition+1);
 		    		}
 		    
 					
 		    	
		       	
		       					 		       
 		      
 		       	
		
	
		
	}
	
	
	
}
