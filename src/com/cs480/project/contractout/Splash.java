package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Splash extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      boolean success = DatabaseInteractor.initializer(getFilesDir().getAbsolutePath());
      setContentView(R.layout.activity_splash);
      final Button skipButton = (Button) findViewById(R.id.skipButton);
// Logic for when the screen is touched at the splash screen
      skipButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            try{
               Intent startActivity = new Intent("android.intent.action.START");
               startActivity(startActivity);
            }catch(Exception e){
               e.printStackTrace();
            }
         }
      });
      
      if(success){
         Thread timer = new Thread(){
            public void run(){
               try{
                  sleep(1500);
                  Intent openInfoActivity = new Intent("android.intent.action.START");
                  startActivity(openInfoActivity);
               }catch(Exception e){
                  e.printStackTrace();
               }
            }
         };
         timer.start(); 
      }
      else{
         AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
         dialogBuilder.setTitle("Database Error");
         dialogBuilder.setMessage("We are sorry the database is having issues connecting. This prevents this app from working so please try again at a later time.");
         dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {        
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();          
            }
         });
         
         AlertDialog dialog = dialogBuilder.create();
         dialog.show();
      }
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
      getMenuInflater().inflate(R.menu.splash, menu);
      return true;
   }

}
