package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompletedJobsActivity extends Activity {
   boolean destroyFlag;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_completed_jobs);
      destroyFlag = false;
      final Button backButton = (Button) findViewById(R.id.completed_jobs_return_button);
      final Button nextButton = (Button) findViewById(R.id.next_3_jobs_button);
      final Button job1RateButton = (Button) findViewById(R.id.rate_job_1_button);
      final Button job2RateButton = (Button) findViewById(R.id.rate_job_2_button);
      final Button job3RateButton = (Button) findViewById(R.id.rate_job_3_button);
      final TextView job1 = (TextView) findViewById(R.id.Job1);
      final TextView job2 = (TextView) findViewById(R.id.Job2);
      final TextView job3 = (TextView) findViewById(R.id.Job3);
      
// Logic for when the Return button is pressed
      backButton.setOnClickListener(new View.OnClickListener() {
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
// Logic for when the Rate Job1 button is pressed
      job1RateButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.RATECONTRACTOR");
                     openAccountInfoActivity.putExtra("Contractor Name", job1.getText());
                     openAccountInfoActivity.putExtra("End Date", "12/21/2013");
                     startActivity(openAccountInfoActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start(); 
         }
      });  
// Logic for when the Rate Job2 button is pressed
      job2RateButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.RATECONTRACTOR");
                     openAccountInfoActivity.putExtra("Contractor Name", job2.getText());
                     openAccountInfoActivity.putExtra("End Date", "12/22/2013");
                     startActivity(openAccountInfoActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start(); 
         }
      });     
// Logic for when the Rate Job3 button is pressed
      job3RateButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.RATECONTRACTOR");
                     openAccountInfoActivity.putExtra("Contractor Name", job3.getText());
                     openAccountInfoActivity.putExtra("End Date", "12/23/2013");
                     startActivity(openAccountInfoActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start(); 
         }
      });     
   }

   @Override
   protected void onPause() {
      // TODO Auto-generated method stub
      super.onPause();
      if(destroyFlag)
         finish();
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.completed_jobs, menu);
      return true;
   }

}
