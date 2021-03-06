package com.example.contractout;

import android.os.Bundle;
import android.app.Activity;
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
      
      infoButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            setContentView(R.layout.activity_info);
            final Button returnButton = (Button) findViewById(R.id.return_button);
            
            returnButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  createMainScreen();
               }
            });
         }
      });  
      
      logInButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            verifyLogIn(username.getText().toString(), password.getText().toString());
         }
      });
   }
 
// temporary method will likely be split into two methods later on   
   private void verifyLogIn(final String user, final String pass){
      setContentView(R.layout.activity_log_in);
      final Button infoButton = (Button) findViewById(R.id.info_button_b);
      final Button logOutButton = (Button) findViewById(R.id.log_out_button);
      
      infoButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            setContentView(R.layout.activity_info);
            final Button returnButton = (Button) findViewById(R.id.return_button);
            
            returnButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  verifyLogIn(user, pass);
               }
            });
         }
      }); 
      
      logOutButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            createMainScreen();
         }
      }); 
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}
