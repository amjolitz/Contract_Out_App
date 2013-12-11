package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_register);
      final Button returnButton = (Button) findViewById(R.id.return_button_registration);
      final Button confirmButton = (Button) findViewById(R.id.confirm_button_registration);
      final EditText username = (EditText) findViewById(R.id.registration_email);
      final EditText password = (EditText) findViewById(R.id.regestration_password);
      final EditText confirmPassword = (EditText) findViewById(R.id.regestration_confirm_password);
      final EditText address = (EditText) findViewById(R.id.registration_street_address);
      final EditText address2 = (EditText) findViewById(R.id.registration_additional_street_info);
      final EditText city = (EditText) findViewById(R.id.registration_city);
      final EditText state = (EditText) findViewById(R.id.registration_state);
      final EditText zip = (EditText) findViewById(R.id.registration_zipcode);
      final EditText phone = (EditText) findViewById(R.id.registration_phone_number);           
      
//Logic for when the return button is pressed on the registration screen            
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
//Logic for when the confirm button is pressed on the registration screen            
      confirmButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(checkValidInfo(password, confirmPassword)){
               String usernameS, passwordS, addressS, address2S, cityS, stateS, zipS, phoneS;
               usernameS = username.getText().toString();
               passwordS = password.getText().toString();
               addressS = address.getText().toString();
               address2S = address2.getText().toString();
               cityS = city.getText().toString();
               stateS = state.getText().toString();
               zipS = zip.getText().toString();
               phoneS = phone.getText().toString();
               createNewAccount(usernameS, passwordS, addressS, address2S, cityS, stateS, zipS, phoneS);
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
            }else{
               displayErrorDialog();
               password.setText("");
               confirmPassword.setText("");
            }
            
         }
      });
   }
   

   private boolean checkValidInfo(EditText password, EditText confirmPassword) {
      String passwordS = password.getText().toString();
      String passwordConfirm = confirmPassword.getText().toString();
      if(passwordS.equals(passwordConfirm) && passwordS.length() != 0)
         return true;
      return false;
   }   
   
   protected void createNewAccount(String username, String password, String address, String address2, String city, 
                                   String state, String zip, String phone) {
      DatabaseInteractor.insertData("User;username=" + username + ";password=" + password + ";billing_address=" + address + ";billing_city=" + 
                                    city + ";billing_state=" + state + ";billing_zip=" + zip + ";user_phone=" + phone + "");
      
   }

   private void displayErrorDialog() {
      AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
      dialogBuilder.setTitle("Invalid Information");
      dialogBuilder.setMessage("Make sure that your password is the same in both fields.");
      dialogBuilder.setPositiveButton("OK", null);
      
      AlertDialog dialog = dialogBuilder.create();
      dialog.show();   
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
      getMenuInflater().inflate(R.menu.register, menu);
      return true;
   }

}
