package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class AccountInfoActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_account_info);
      final Button returnButton = (Button) findViewById(R.id.return_button_account_info);
      final Button updateButton = (Button) findViewById(R.id.update_account_info_button);
      final Button changePassButton = (Button) findViewById(R.id.change_password_button);
//Logic for when the Return button is pressed on the Account Info page            
      returnButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(200);
                     Intent openLogInActivity = new Intent("android.intent.action.LOGIN");
                     startActivity(openLogInActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start();
         }
      });
//Logic for when the Change Password button is pressed on the Account Info page
      changePassButton.setOnClickListener(new View.OnClickListener() {         
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(200);
                     Intent openChangePasswordActivity = new Intent("android.intent.action.CHANGEPASSWORD");
                     startActivity(openChangePasswordActivity);
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
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.account_info, menu);
      return true;
   }

}
