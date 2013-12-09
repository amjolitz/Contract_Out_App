package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class InspectContractorActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_inspect_contractor);
      final Button backButton = (Button) findViewById(R.id.inspect_contractor_return_Button);
      final Button confirmButton = (Button) findViewById(R.id.inspect_contractor_confirm_button);
      final TextView contractorName = (TextView) findViewById(R.id.inspect_contractor_name);
      final TextView finishDate = (TextView) findViewById(R.id.inspect_finish_date);
      final RatingBar friendliness = (RatingBar) findViewById(R.id.inspectFriendlinessRating);
      final RatingBar quality = (RatingBar) findViewById(R.id.inspectQualityRating);
      final RatingBar timeliness = (RatingBar) findViewById(R.id.InspectTimelinessRating);
      Bundle extras = getIntent().getExtras();
      
      contractorName.setText(extras.getString("Contractor Name"));
      finishDate.setText(extras.getString("Price"));
      
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
            confirmJob();
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
   
   protected void confirmJob() {
      // TODO Auto-generated method stub
      
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
      getMenuInflater().inflate(R.menu.inspect_contractor, menu);
      return true;
   }

}
