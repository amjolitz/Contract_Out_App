package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class LogInActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_log_in);
      final Button infoButton = (Button) findViewById(R.id.info_button_b);
      final Button logOutButton = (Button) findViewById(R.id.log_out_button);
      final Button accountInfoButton = (Button) findViewById(R.id.account_info_button);

// Logic for when the Info button is pressed on main screen      
      infoButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(1000);
                     Intent openInfoActivity = new Intent("android.intent.action.INFO");
                     openInfoActivity.putExtra("flag", 2);
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
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(1000);
                     Intent openMainActivity = new Intent("android.intent.action.START");
                     startActivity(openMainActivity);
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
                     sleep(1000);
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
   }

   @Override
   protected void onPause() {
      // TODO Auto-generated method stub
      super.onPause();
      finish();
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.log_in, menu);
      return true;
   }

}
