package com.cs480.project.contractout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePasswordActivity extends Activity {
   private String oldPassword;
   private String[] userInfo = new String[9];

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);      
      setContentView(R.layout.activity_change_password);
      final Button returnButton = (Button) findViewById(R.id.return_button_change_password);
      final Button confirmButton = (Button) findViewById(R.id.confirm_button_change_password);
      final TextView username = (TextView) findViewById(R.id.email_change_password);
      final EditText oldPass = (EditText) findViewById(R.id.old_password);
      final EditText newPass = (EditText) findViewById(R.id.new_password);
      final EditText confirmNewPass = (EditText) findViewById(R.id.confirm_new_password);
      
//Get the text file
      File file = new File(this.getFilesDir(), "userAccountInfo.txt");

//Read text from file
      try {
         BufferedReader br = new BufferedReader(new FileReader(file));
         userInfo[0] = br.readLine();
         username.setText(br.readLine());
         userInfo[1] = username.getText().toString();
         userInfo[2] = oldPassword = br.readLine();
         userInfo[3] = br.readLine();
         userInfo[4] = br.readLine();
         userInfo[5] = br.readLine();
         userInfo[6] = br.readLine();
         userInfo[7] = br.readLine();
         userInfo[8] = br.readLine();

      }
      catch (IOException e) {}
      
      returnButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
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
      
      confirmButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            verifyPasswordChange(oldPass, newPass, confirmNewPass); 
         }
      });
   }
   
   protected void verifyPasswordChange(EditText oldPass, EditText newPass, EditText confirmNewPass) {
      if(!newPass.getText().toString().equals(confirmNewPass.getText().toString()) ||
         newPass.getText().toString().length() == 0 || !oldPass.getText().toString().equals(oldPassword)){
         displayErrorDialog();
         oldPass.setText("");
         newPass.setText("");
         confirmNewPass.setText("");
         return;
      }
      updatePassword(newPass);
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

   private void updatePassword(EditText newPass) {
      String newPassword = newPass.getText().toString();
      userInfo[2] = newPassword;
      try{
         FileOutputStream outputStream = openFileOutput("userAccountInfo.txt", Context.MODE_PRIVATE);
         for(int i=0; i<9; i++)
            outputStream.write((userInfo[i]+"\n").getBytes());
         outputStream.close();
      }catch(IOException e){
         e.printStackTrace();
      }
      DatabaseInteractor.updateData("User;id=<" + userInfo[0] + ">;password=<" + userInfo[2] + ">");

   }

   private void displayErrorDialog() {
      AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
      dialogBuilder.setTitle("Invalid Information");
      dialogBuilder.setMessage("Make sure that your old password is correct and your new one is the same in both fields.");
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
      finish();
   }



   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.change_password, menu);
      return true;
   }

}
