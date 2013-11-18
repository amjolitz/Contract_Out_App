package com.cs480.project.contractout;

import com.cs480.project.contractout.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState); 
      createMainScreen(); 
   }

   private void createMainScreen() {
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
                     sleep(1000);
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
            verifyLogIn(username.getText().toString(), password.getText().toString());
         }
      });
// Logic for when the register button is pressed on start screen      
      registerButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(1000);
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
   private void verifyLogIn(final String user, final String pass){
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

   @Override
   protected void onPause() {
      // TODO Auto-generated method stub
      super.onPause();
      finish();
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}
