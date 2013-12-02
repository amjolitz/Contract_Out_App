package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class AccountInfoActivity extends Activity {
   Button returnButton;
   Boolean destroyFlag;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      destroyFlag = false;
      setContentView(R.layout.activity_account_info);
      final Button returnButton = (Button) findViewById(R.id.return_button_account_info);
      final Button updateButton = (Button) findViewById(R.id.update_account_info_button);
      final Button changePassButton = (Button) findViewById(R.id.change_password_button);
//Logic for when the Return button is pressed on the Account Info page            
      returnButton.setOnClickListener(new View.OnClickListener() {
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
//Logic for when the Change Password button is pressed on the Account Info page
      changePassButton.setOnClickListener(new View.OnClickListener() {         
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
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
   
   protected void onPause() {
      // TODO Auto-generated method stub
      super.onPause();
// Hide on screen keyboard      
      InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
      if(destroyFlag)
         finish();
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.account_info, menu);
      return true;
   }

}
