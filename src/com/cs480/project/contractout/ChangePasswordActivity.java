package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class ChangePasswordActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);      
      setContentView(R.layout.activity_change_password);
      final Button returnButtonChangePassword = (Button) findViewById(R.id.return_button_change_password);
      final Button confirmButtonChangePassword = (Button) findViewById(R.id.confirm_button_change_password);
      
      returnButtonChangePassword.setOnClickListener(new View.OnClickListener() {
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
   }
   
   

   @Override
   protected void onPause() {
      // TODO Auto-generated method stub
      super.onPause();
// Hide on screen keyboard      
      InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
      finish();
   }



   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.change_password, menu);
      return true;
   }

}
