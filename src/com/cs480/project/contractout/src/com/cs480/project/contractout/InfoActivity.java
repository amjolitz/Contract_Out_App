package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
// Receives integer extra under the variable 'flag'
public class InfoActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_info);
      final Button back = (Button) findViewById(R.id.return_button_info);
      final int flag = getIntent().getExtras().getInt("flag");
      
      back.setOnClickListener(new View.OnClickListener() {         
         @Override
         public void onClick(View v) {
            if(flag == 1){ // If the info screen was accessed from the main screen
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
            else if(flag == 2){ // If the info screen is accessed from the log in screen
               Thread timer = new Thread(){
                  public void run(){
                     try{
                        sleep(1000);
                        Intent openLogInActivity = new Intent("android.intent.action.LOGIN");
                        startActivity(openLogInActivity);
                     }catch(Exception e){
                        e.printStackTrace();
                     }
                  }
               };
               timer.start(); 
            }
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
      getMenuInflater().inflate(R.menu.info, menu);
      return true;
   }

}
