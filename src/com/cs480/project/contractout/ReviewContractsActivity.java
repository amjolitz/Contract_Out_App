package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ReviewContractsActivity extends Activity {
   boolean destroyFlag;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_review_contracts);
      destroyFlag = false;
      final Button backButton = (Button) findViewById(R.id.review_contracts_return_button);
      final Button pendingButton = (Button) findViewById(R.id.review_pending_button);
      final Button completedButton = (Button) findViewById(R.id.review_completed_button);
      
// Logic for when the Log Out button is pressed
      backButton.setOnClickListener(new View.OnClickListener() {
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
      pendingButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.PENDINGJOBS");
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
      completedButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.COMPLETEDJOBS");
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
      getMenuInflater().inflate(R.menu.review_contracts, menu);
      return true;
   }

}
