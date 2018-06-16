package ndk.ipl_cricket.ui;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Match_Details extends ActionBarActivity {

	private String stitle;
	private String id;
	TextView title,score,target;
	private ProgressDialog dialog;
	private Thread t;
	private DefaultHttpClient httpclient;
	private HttpPost httppost;
	private String response;
	private ArrayList<NameValuePair> nameValuePairs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match_details);
		Bundle extras =getIntent().getExtras();
		if(extras!=null){
			
			stitle=extras.getString("title");
			id=extras.getString("id");
		}
		title=(TextView)findViewById(R.id.title);
		score=(TextView)findViewById(R.id.score);
		target=(TextView)findViewById(R.id.target);
		title.setText(stitle);
		
		dialog = ProgressDialog.show(this, "", 
                "Refreshing...", true);
		
		t=new Thread(new Runnable() {
			    public void run() {
			    	getscore();					      
			    }
			  });
		t.start();	
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.match__details, menu);
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
	
	private void getscore() {
		// TODO Auto-generated method stub
		try{			
			 
			httpclient=new DefaultHttpClient();
			httppost= new HttpPost(Globaldata.ip+"details.php"); // make sure the url is correct.
			//add your data
			nameValuePairs = new ArrayList<NameValuePair>(1);
			// Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar, 
			nameValuePairs.add(new BasicNameValuePair("id",id));  // $Edittext_value = $_POST['Edittext_value'];
		
			
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					
		
			//Execute HTTP Post Request
			// edited by James from coderzheaven.. from here....
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			response = httpclient.execute(httppost, responseHandler);
			//System.out.println("Response : " + response); 
			
			runOnUiThread(new Runnable() {
			    public void run() {
			    	//Toast.makeText(Match_Details.this,"Response from PHP : " + response,Toast.LENGTH_LONG).show();
					dialog.dismiss();
			    }
			});
			
		
			
		}
		catch(Exception e)
		{
			runOnUiThread(new Runnable() {
			    public void run() {
			    	Toast.makeText(Match_Details.this,"Try Again", Toast.LENGTH_SHORT).show();
			    	dialog.dismiss();
					 }
			});
			
			
		}
	
		
				
 		    		String p=response;
 		    		int divident=p.indexOf(":");
 		    			final String matchscore=p.subSequence(0,divident).toString();
 		    			
 		    			final String matchtarget=p.substring(divident+1);
 		    			
 		    			score.setText(matchscore);
 		    			target.setText(matchtarget.substring((matchtarget.indexOf("v")+1)));
 		    			
 		    			/*runOnUiThread(new Runnable() {
 		   			    public void run() {
 		   			    Toast.makeText(Matches.this,matchtotal, Toast.LENGTH_SHORT).show();
 		   			Toast.makeText(Matches.this,String.valueOf(matchcodeposition), Toast.LENGTH_SHORT).show();
 		   		 Toast.makeText(Matches.this,matchcode, Toast.LENGTH_SHORT).show();
		   			 
 		   					 }
 		   			});*/
 		    			
 		    		
	}
}
