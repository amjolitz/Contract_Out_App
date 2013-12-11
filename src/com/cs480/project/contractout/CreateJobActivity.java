package com.cs480.project.contractout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

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
   Boolean destroyFlag;
   String[] userInfo = new String[9];
   String priceId;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_create_job);
      final Button returnButton = (Button) findViewById(R.id.createJobReturnButton);
      final Button confirmButton = (Button) findViewById(R.id.createJobConfirmButton);
      final Spinner jobType = (Spinner) findViewById(R.id.jobType);
      final EditText description = (EditText) findViewById(R.id.jobDescription);
      final EditText address = (EditText) findViewById(R.id.jobAddress);
      final EditText city = (EditText) findViewById(R.id.jobCity);
      final EditText zip = (EditText) findViewById(R.id.jobZip);
      final Button fillAddressButton = (Button) findViewById(R.id.useBillingAddressButton);
      final EditText eairlyStartMonth = (EditText) findViewById(R.id.eairliestMonth);
      final EditText eairlyStartDay = (EditText) findViewById(R.id.eairliestDay);
      final EditText latestStartMonth = (EditText) findViewById(R.id.latestStartMonth);
      final EditText latestStartDay = (EditText) findViewById(R.id.latestStartDay);
      final Spinner maxPrice = (Spinner) findViewById(R.id.maxPriceSpinner);
      final RatingBar friendliness = (RatingBar) findViewById(R.id.friendlinessRating);
      final RatingBar quality = (RatingBar) findViewById(R.id.qualityRating);
      final RatingBar timeliness = (RatingBar) findViewById(R.id.timelinessRating);
      
      destroyFlag = false;
      
      description.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
      description.setSingleLine(true);
      description.setLines(6); // desired number of lines
      description.setHorizontallyScrolling(false);
      description.setImeOptions(EditorInfo.IME_ACTION_DONE);
      
      nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
      
//Get the text file
      File file = new File(this.getFilesDir(), "userAccountInfo.txt");

//Read text from file
      try {
         BufferedReader br = new BufferedReader(new FileReader(file));
         userInfo[0] = br.readLine();  // user id
         userInfo[1] = br.readLine();  // username
         userInfo[2] = br.readLine();  // user password
         userInfo[3] = br.readLine(); // user full name
         userInfo[4] = br.readLine(); // user address
         userInfo[5] = br.readLine(); // user city
         userInfo[6] = br.readLine(); // user state
         userInfo[7] = br.readLine(); // user zip
         userInfo[8] = br.readLine(); // user phone

      }
      catch (IOException e) {}
      
// Logic for when the return button is pressed on the create job screen
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
// Logic for when the confirm button is pressed on the create job screen
      confirmButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            destroyFlag = true;
            postJob(jobType, description, address, eairlyStartMonth, eairlyStartDay,
                    latestStartMonth, latestStartDay, city, zip, maxPrice, friendliness, quality, timeliness);
         }
      });
// Logic for when the Use Billing Address button is pressed on the create job screen
      fillAddressButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            address.setText(userInfo[4]);
            city.setText(userInfo[5]);
            zip.setText(userInfo[7]);
         }
      });
   } 
   
   protected void postJob(Spinner jobType, EditText description, EditText address, 
                          EditText eairlyStartMonth, EditText eairlyStartDay, EditText latestStartMonth, 
                          EditText latestStartDay, EditText cityField, EditText zipField, 
                          Spinner maxPrice, RatingBar friendliness, RatingBar quality, RatingBar timeliness) {
      String type, desc, add, city, zip, startMonth, startDay, lateStartMonth, lateStartDay, price, friend, qual, time;
      
      try{
         type = jobType.getItemAtPosition(jobType.getLastVisiblePosition()).toString();
         desc = description.getText().toString();
         add = address.getText().toString();
         city = cityField.getText().toString();
         zip = zipField.getText().toString();
         startMonth = eairlyStartMonth.getText().toString();
         startDay = eairlyStartDay.getText().toString();
         lateStartMonth = latestStartMonth.getText().toString();
         lateStartDay = latestStartMonth.getText().toString();
         price = jobType.getItemAtPosition(jobType.getLastVisiblePosition()).toString();
         friend = Float.toString(friendliness.getRating());
         qual = Float.toString(quality.getRating());
         time = Float.toString(timeliness.getRating());
      }catch(Exception e){
         AlertDialog.Builder errorBuilder = new AlertDialog.Builder(this);
         errorBuilder.setTitle("Job not posted");
         errorBuilder.setMessage("Please check that all required fields are filled");
         errorBuilder.setPositiveButton("OK", null);

         AlertDialog errorDialog = errorBuilder.create();
         errorDialog.show();
         return;
      }
      
      final int uniqueId = postJob(type, desc, add, city, zip, startMonth, startDay, lateStartMonth, lateStartDay, price, friend, qual, time);
      
      Handler handler = new Handler();
      handler.postDelayed(
          new Runnable() {
              public void run() {
                notifyUser(uniqueId);
              }
          }, 10000);
      
      onPause();
   }
   
   private int postJob(String type, String desc, String add, String city, String zip, String startMonth, String startDay, 
                       String lateStartMonth, String lateStartDay, String price, String friend, String qual, String time) {
//Get the text file
      File file = new File(this.getFilesDir(), "userAccountInfo.txt");
      String userId;

//Read text from file
      try {
         BufferedReader br = new BufferedReader(new FileReader(file));
         userId = br.readLine();  // dispose of user id
      }
      catch (IOException e) {
         e.printStackTrace();
         return 0;
      }
      
      String jobType = DatabaseInteractor.getData("Job_Types;job_type_name=" + type + "")[0][0];
      String priceRange = DatabaseInteractor.getData("Price_Ranges;price_range_text=" + price + "")[0][0];   
      
      int jobId = DatabaseInteractor.insertData("Jobs;job_address=" + add + ";job_city=" + city + ";job_zip=" + zip + 
                                    ";job_start_date=" +  startMonth + "/" + startDay + "/" + Calendar.getInstance().get(Calendar.YEAR) + 
                                    ";job_description=" + desc + ";job_zip=" + zip + ";creator=" + userId + 
                                    ";job_type=" + jobType + ";price_range=" + priceRange + "");
      
      String temp = DatabaseInteractor.getData("Jobs;job_address=" + add + ";job_city=" + city + ";job_zip=" + zip + 
            ";job_start_date=" +  startMonth + "/" + startDay + "/" + Calendar.getInstance().get(Calendar.YEAR) + 
            ";job_description=" + desc + ";job_zip=" + zip + ";creator=" + userId + 
            ";job_type=" + jobType + ";price_range=" + priceRange + "")[0][0];

      priceId = priceRange;

      int tempInt = Integer.parseInt(temp);
      return tempInt;
   }

   private void notifyUser(int uniqueId)
   {
      Intent intent = new Intent(this, ContractOffersActivity.class);
      intent.putExtra("Job ID", uniqueId);
      intent.putExtra("Price", priceId);
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
      if(destroyFlag)
         finish();
   }



   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.create_job, menu);
      return true;
   }

}
