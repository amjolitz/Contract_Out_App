package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class LogInActivity extends Activity {
   boolean destroyFlag;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);      
      setContentView(R.layout.activity_log_in);
      destroyFlag = false;
      final Button infoButton = (Button) findViewById(R.id.info_button_b);
      final Button logOutButton = (Button) findViewById(R.id.log_out_button);
      final Button accountInfoButton = (Button) findViewById(R.id.account_info_button);
      final Button createJobButton = (Button) findViewById(R.id.create_job_button);
      final Button reviewJobButton = (Button) findViewById(R.id.rate_contractor_button);

// Logic for when the Info button is pressed on main screen      
      infoButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openInfoActivity = new Intent("android.intent.action.INFO");
                     startActivity(openInfoActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start(); 
         }
      }); 

// Logic for when the Log Out button is pressed on the main screen
      logOutButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            destroyFlag = true;
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     onPause();
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start(); 
         }
      }); 
      
// Logic for when the Account Info button is pressed on the main screen
      accountInfoButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.ACCOUNTINFO");
                     startActivity(openAccountInfoActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start(); 
         }
      });
      
// Logic for when the Create Job button is pressed on the main screen
      createJobButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.CREATEJOB");
                     startActivity(openAccountInfoActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start(); 
         }
      });
// Logic for when the Review Job button is pressed on the main screen
      reviewJobButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.REVIEWCONTRACTS");
                     startActivity(openAccountInfoActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start(); 
         }
      });
   }

   @Override
   protected void onPause() {
      // TODO Auto-generated method stub
      super.onPause();
      if(destroyFlag)
         finish();
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.log_in, menu);
      return true;
   }

}
