package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_register);
      final Button returnButton = (Button) findViewById(R.id.return_button_registration);
      final Button confirmButton = (Button) findViewById(R.id.confirm_button_registration);
//Logic for when the return button is pressed on the registration screen            
      returnButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
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
//Logic for when the confirm button is pressed on the registration screen            
      confirmButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            
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
      getMenuInflater().inflate(R.menu.register, menu);
      return true;
   }

}
