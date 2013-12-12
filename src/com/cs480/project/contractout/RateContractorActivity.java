package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class RateContractorActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_rate_contractor);
      final Button backButton = (Button) findViewById(R.id.rate_contractor_return_Button);
      final Button confirmButton = (Button) findViewById(R.id.rate_contractor_confirm_button);
      final TextView contractorName = (TextView) findViewById(R.id.contractor_name);
      final TextView finishDate = (TextView) findViewById(R.id.finish_date);
      final RatingBar friendliness = (RatingBar) findViewById(R.id.friendlinessRatingRateContractor);
      final RatingBar quality = (RatingBar) findViewById(R.id.qualityRatingRateContractor);
      final RatingBar timeliness = (RatingBar) findViewById(R.id.timelinessRatingRateContractor);
      Bundle extras = getIntent().getExtras();
      
      contractorName.setText(extras.getString("Contractor Name"));
      finishDate.setText(extras.getString("End Date"));
      
// Logic for when the Return button is pressed
      backButton.setOnClickListener(new View.OnClickListener() {
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
// Logic for when the Confirm button is pressed
      confirmButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
 //           uploadRating(friendliness.getRating(), quality.getRating(), timeliness.getRating());
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
          
   }

   protected void uploadRating(float friendliness, float quality, float timeliness) {
      Bundle extras = getIntent().getExtras();
      String key = extras.getString("key");
      String key2 = extras.getString("jobKey");
      
      DatabaseInteractor.updateData("Contractor_Ratings;contractor=" + key + ";job=" + key2 + ";rating_friendliness=" + 
                                     friendliness + ";rating_quality=" + quality + ";rating_timeliness=" + timeliness + "");      
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
      getMenuInflater().inflate(R.menu.rate_contractor, menu);
      return true;
   }

}
