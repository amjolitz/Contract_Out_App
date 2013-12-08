package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class ChangePasswordActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);      
      setContentView(R.layout.activity_change_password);
      final Button returnButton = (Button) findViewById(R.id.return_button_change_password);
      final Button confirmButton = (Button) findViewById(R.id.confirm_button_change_password);
      final EditText oldPass = (EditText) findViewById(R.id.old_password);
      final EditText newPass = (EditText) findViewById(R.id.new_password);
      final EditText confirmNewPass = (EditText) findViewById(R.id.confirm_new_password);
      
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
         newPass.getText().toString().length() == 0){
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
