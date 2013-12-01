package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Splash extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
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
