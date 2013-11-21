package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Splash extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_splash);
      Thread timer = new Thread(){
         public void run(){
            try{
               sleep(5000);
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
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.splash, menu);
      return true;
   }

}
