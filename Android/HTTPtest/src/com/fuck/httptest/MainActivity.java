package com.fuck.httptest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	static String str = "start";
	static String suck = "wait";
	class thread extends Thread {
		public void run()
		{
			
			try {
				Socket s = new Socket("140.113.246.249",50000);
				//DataInputStream in = new DataInputStream(s.getInputStream());
				//DataOutputStream out = new DataOutputStream(s.getOutputStream());
				InputStream in=s.getInputStream();
			    OutputStream out=s.getOutputStream();
				/*HttpURLConnection urlconn = (HttpURLConnection) new URL(url).openConnection();
				urlconn.setDoInput(true);
				urlconn.setDoOutput(true);
				urlconn.setRequestMethod("POST");
				urlconn.setUseCaches(false);
				urlconn.setRequestProperty("Content-type","application/x-www-form-urlencoded");
				DataOutputStream out = new DataOutputStream(urlconn.getOutputStream());*/
			     if(s.isConnected())
			    	 suck = "great success";
			     else
			    	 suck = "great failure";
			    byte[] sendstr = new byte[21];
				String str2 = "HttpClientSuck";
				System.arraycopy(str2.getBytes(), 0, sendstr, 0, str2.length());
				out.write(sendstr);
				byte[] rebyte = new byte[1024];
			    in.read(rebyte);
			    str = new String(new String(rebyte));
			    
		
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				str = "UnknownHost";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				str = "IOException";
			}
			//Toast.makeText(this, str, Toast.LENGTH_SHORT);
		}
		
	}
	public class getServerMessage
	{
		public String stringQuery(String url)
		{
			try
			{
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost method = new HttpPost(url);  
				HttpResponse response =	httpclient.execute(method);
				HttpEntity entity = response.getEntity();
				if(entity != null){
	                return EntityUtils.toString(entity);
	            }
	            else{
	                return "No string.";
	            }
			}
			catch(Exception e)
			{
				return "Network problem";
			}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView txt1 = (TextView)findViewById(R.id.txt1);
		TextView txt2 = (TextView)findViewById(R.id.txt2);
		Thread t = new thread();
		t.start();
		try
		{
			t.sleep(3000);
		}
		catch(InterruptedException e)
		{
		}
		txt1.setText(str);
		txt2.setText(suck);
		/*if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}*/
		//getServerMessage message = new getServerMessage();
		//String msg = message.stringQuery("140.113.246.247");
		//Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

