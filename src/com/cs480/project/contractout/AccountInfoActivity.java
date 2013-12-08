package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class AccountInfoActivity extends Activity {
   Boolean destroyFlag;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      destroyFlag = false;
      setContentView(R.layout.activity_account_info);
      final Button returnButton = (Button) findViewById(R.id.return_button_account_info);
      final Button updateButton = (Button) findViewById(R.id.update_account_info_button);
      final Button changePassButton = (Button) findViewById(R.id.change_password_button);
      final EditText address = (EditText) findViewById(R.id.street_address);
      final EditText address2 = (EditText) findViewById(R.id.additional_street_info);
      final EditText city = (EditText) findViewById(R.id.city);
      final EditText state = (EditText) findViewById(R.id.state);
      final EditText zip = (EditText) findViewById(R.id.zipcode);
      final EditText phone = (EditText) findViewById(R.id.phone_number); 
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
//Logic for when the Update button is pressed on the Account Info page            
      updateButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            destroyFlag = true;
            updateInfo(address, address2, city, state, zip, phone);
         }
      });
   }
   
   protected void updateInfo(EditText address, EditText address2, EditText city, 
                             EditText state, EditText zip, EditText phone) {
      String addressS = address.getText().toString();
      String address2S = address2.getText().toString();
      String cityS = city.getText().toString();
      String stateS = state.getText().toString();
      String zipS = zip.getText().toString();
      String phoneS = phone.getText().toString();
      if(addressS.length() == 0 || cityS.length() == 0 || 
         stateS.length() == 0 || zipS.length() == 0 || phoneS.length() == 0){
         displayError();
         return;
      }
// Insert Logic for Updating user information at the database level
//
//
//
//
// ------------------------------------------------------------------      
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

   private void displayError() {
      AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
      dialogBuilder.setTitle("Invalid Information");
      dialogBuilder.setMessage("All fields other than additional info must be filled");
      dialogBuilder.setPositiveButton("OK", null);
      
      AlertDialog dialog = dialogBuilder.create();
      dialog.show(); 
      
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
