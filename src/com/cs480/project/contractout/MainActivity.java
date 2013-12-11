package com.cs480.project.contractout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cs480.project.contractout.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
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
                     sleep(100);
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
      String userInfo;
      try {
         DatabaseInteractor db = new DatabaseInteractor();
         userInfo = db.logIn(user.getText().toString(), pass.getText().toString());
      } catch (IOException e1) {
         e1.printStackTrace();
//         System.exit(1);
         return;
      }
      
      if(!userInfo.equals("False")){
         try {
            String[] stringArray = new String[9];
            Pattern p = Pattern.compile("\\<(.*?)\\>");
            Matcher m = p.matcher(userInfo);
            int i = 0;
            while(m.find())
            {
                stringArray[i] = m.group(1);
                i++;
            }        
            FileOutputStream outputStream = openFileOutput("userAccountInfo.txt", Context.MODE_PRIVATE);
            for(i=0; i<9; i++)
               outputStream.write((stringArray[i]+"\n").getBytes());
            outputStream.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
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
         displayErrorDialog();
         EditText temp = (EditText) findViewById(R.id.password);
         temp.setText("");
      }
   }

   private void displayErrorDialog() {
      AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
      dialogBuilder.setTitle("Wrong Password");
      dialogBuilder.setMessage("The username/password combo was incorrect");
      dialogBuilder.setPositiveButton("OK", null);
      
      AlertDialog dialog = dialogBuilder.create();
      dialog.show();
   }

   @Override
   protected void onPause() {
      // TODO Auto-generated method stub
      super.onPause();
// Hide on screen keyboard      
      InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
      
      EditText username = (EditText) findViewById(R.id.username);
      EditText password = (EditText) findViewById(R.id.password);
      FileOutputStream outputStream;
      String temp = username.getText().toString() + "\n" + password.getText().toString();
      
      if(((CheckBox) findViewById(R.id.remember_user)).isChecked()){
         try {
            outputStream = openFileOutput("userInfo.txt", Context.MODE_PRIVATE);
            outputStream.write(temp.getBytes());
            outputStream.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
      }
      else{
         // Delete userInfo.txt
         try {
            File dir = getFilesDir();
            File file = new File(dir, "userInfo.txt");
            file.delete();
          } catch (Exception e) {}
      }
      username.setText("");
      password.setText("");
   }
   
   @Override
   protected void onResume() {
      // TODO Auto-generated method stub
      super.onResume();
      //Get the text file
      File file = new File(this.getFilesDir(), "userInfo.txt");

      //Read text from file
      try {
         BufferedReader br = new BufferedReader(new FileReader(file));
         String line;

         if ((line = br.readLine()) != null) {
            ((EditText) findViewById(R.id.username)).setText(line);
         }
         if ((line = br.readLine()) != null) {
            ((EditText) findViewById(R.id.password)).setText(line);
         }
         ((CheckBox) findViewById(R.id.remember_user)).setChecked(true);
      }
      catch (IOException e) {}
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}
