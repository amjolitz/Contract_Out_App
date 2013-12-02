package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class CreateJobActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_create_job);
      final Button returnButton = (Button) findViewById(R.id.createJobReturnButton);
      final Button confirmButton = (Button) findViewById(R.id.createJobConfirmButton);
      final Spinner jobType = (Spinner) findViewById(R.id.jobType);
      final EditText description = (EditText) findViewById(R.id.jobDescription);
      final EditText address = (EditText) findViewById(R.id.jobAddress);
      final Button fillAddressButton = (Button) findViewById(R.id.useBillingAddressButton);
      final EditText eairlyStartMonth = (EditText) findViewById(R.id.eairliestMonth);
      final EditText eairlyStartDay = (EditText) findViewById(R.id.eairliestDay);
      final EditText latestStartMonth = (EditText) findViewById(R.id.latestStartMonth);
      final EditText latestStartDay = (EditText) findViewById(R.id.latestStartDay);
      final EditText maxPrice = (EditText) findViewById(R.id.maxPrice);
      final RatingBar friendliness = (RatingBar) findViewById(R.id.friendlinessRating);
      final RatingBar quality = (RatingBar) findViewById(R.id.qualityRating);
      final RatingBar timeliness = (RatingBar) findViewById(R.id.timelinessRating);
      
// Logic for when the return button is pressed on the create job screen
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
// Logic for when the confirm button is pressed on the create job screen
      confirmButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            // Create Job
         }
      });
// Logic for when the Use Billing Address button is pressed on the create job screen
      fillAddressButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            address.setText("This is your billing address");
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
      getMenuInflater().inflate(R.menu.create_job, menu);
      return true;
   }

}
