package com.cs480.project.contractout;

import com.cs480.project.contractout.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState); 
      createMainScreen(); 
   }

   private void createMainScreen(){
      setContentView(R.layout.activity_main);
      final Button infoButton = (Button) findViewById(R.id.info_button);
      final Button logInButton = (Button) findViewById(R.id.log_in_button);
      final Button registerButton = (Button) findViewById(R.id.register_button);
      final EditText username = (EditText) findViewById(R.id.username);
      final EditText password = (EditText) findViewById(R.id.password);
      
// Logic for when the info button is pressed on the start screen
      infoButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openInfoActivity = new Intent("android.intent.action.INFO");
                     openInfoActivity.putExtra("flag", 1);
                     startActivity(openInfoActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start();            
         }
      });  
// Logic for when the log in button is pressed on the start screen      
      logInButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            verifyLogIn(username, password);
         }
      });
// Logic for when the register button is pressed on start screen      
      registerButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(200);
                     Intent openRegisterActivity = new Intent("android.intent.action.REGISTER");
                     startActivity(openRegisterActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start();
         }
      }); 
   }
 
   // temporary method will likely be split into two methods later on   
   private void verifyLogIn(EditText user, EditText pass){
      if(checkPassword(user.getText().toString(), pass.getText().toString())){
         Thread timer = new Thread(){
            public void run(){
               try{
                  sleep(100);
                  Intent openLogInActivity = new Intent("android.intent.action.LOGIN");
                  startActivity(openLogInActivity);
               }catch(Exception e){
                  e.printStackTrace();
               }
            }
         };
         timer.start();
      }
      else{
         EditText temp = (EditText) findViewById(R.id.username);
         temp.setText("Not Valid Password");
         temp = (EditText) findViewById(R.id.password);
         temp.setText("");
      }
   }

   private boolean checkPassword(String user, String pass){
      if(  true  )
         return true;
      else
         return false;
   }

   @Override
   protected void onPause() {
      // TODO Auto-generated method stub
      super.onPause();
// Hide on screen keyboard      
      InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
      
      ((EditText) findViewById(R.id.username)).setText("");
      ((EditText) findViewById(R.id.password)).setText("");
      if(((CheckBox) findViewById(R.id.remember_user)).isChecked()){
//
//  Logic for remembering the username and password of the user if box is checked
//
      }
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}
