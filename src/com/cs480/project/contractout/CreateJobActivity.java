package com.cs480.project.contractout;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class CreateJobActivity extends Activity {
   
   NotificationManager nm;

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
      
      description.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
      description.setSingleLine(true);
      description.setLines(6); // desired number of lines
      description.setHorizontallyScrolling(false);
      description.setImeOptions(EditorInfo.IME_ACTION_DONE);
      
      nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
      
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
            postJob(jobType, description, address, eairlyStartMonth, eairlyStartDay,
                    latestStartMonth, latestStartDay, maxPrice, friendliness, quality, timeliness);
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
   
   protected void postJob(Spinner jobType, EditText description, EditText address, 
                          EditText eairlyStartMonth, EditText eairlyStartDay, EditText latestStartMonth, 
                          EditText latestStartDay, EditText maxPrice, RatingBar friendliness, 
                          RatingBar quality, RatingBar timeliness) {
      String type, desc, add;
      int startMonth, startDay, lateStartMonth, lateStartDay;
      double price, friend, qual, time;
      try{
         type = jobType.getItemAtPosition(jobType.getLastVisiblePosition()).toString();
         desc = description.getText().toString();
         add = address.getText().toString();
         startMonth = Integer.valueOf(eairlyStartMonth.getText().toString());
         startDay = Integer.valueOf(eairlyStartDay.getText().toString());
         lateStartMonth = Integer.valueOf(latestStartMonth.getText().toString());
         lateStartDay = Integer.valueOf(latestStartMonth.getText().toString());
         price = Double.valueOf(maxPrice.getText().toString());
         friend = friendliness.getRating();
         qual = quality.getRating();
         time = timeliness.getRating();
      }catch(Exception e){
         AlertDialog.Builder errorBuilder = new AlertDialog.Builder(this);
         errorBuilder.setTitle("Job not posted");
         errorBuilder.setMessage("Please check that all required fields are filled");
         errorBuilder.setPositiveButton("OK", null);

         AlertDialog errorDialog = errorBuilder.create();
         errorDialog.show();
         return;
      }
      
      final int uniqueId =  (int) (startMonth + startDay + lateStartMonth + lateStartDay + price);
      Handler handler = new Handler();
      handler.postDelayed(
          new Runnable() {
              public void run() {
                notifyUser(uniqueId);
              }
          }, 10000);
      
      description.setText(type + add + startMonth + startDay + lateStartMonth + lateStartDay + "\n" +
                          price + friend + qual + time);
   }
   
   private void notifyUser(int uniqueId)
   {
      Intent intent = new Intent(this, ContractOffersActivity.class);
      intent.putExtra("Job ID", uniqueId);
      PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
      String body = "This is a notification of pending contract offers";
      String title = "Contract Out Notification";
      Notification n = new Notification(R.drawable.ic_launcher, body, System.currentTimeMillis());
      n.setLatestEventInfo(this, title, body, pi);
      n.defaults = Notification.DEFAULT_ALL;
      nm.notify(uniqueId, n);
   }

   @Override
   protected void onPause() {
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
