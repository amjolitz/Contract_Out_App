package com.cs480.project.contractout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompletedJobsActivity extends Activity {
   boolean destroyFlag;
   int index;
   String userInfo, endDate1, endDate2, endDate3, key1, key2, key3, jobKey1, jobKey2, jobKey3, jobsList[][];

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
      
      final int listLength;
      
//Get the text file
      File file = new File(this.getFilesDir(), "userAccountInfo.txt");

//Read text from file
      try {
         BufferedReader br = new BufferedReader(new FileReader(file));
         userInfo = br.readLine();  // dispose of user id
      }
      catch (IOException e) {
         e.printStackTrace();
         return;
      }
      
      jobsList = DatabaseInteractor.getData("UnratedJobs;user_id=" + userInfo + "");
      listLength = jobsList.length;
      index = 1;
      
      if(listLength > index){
         endDate1 = jobsList[index][5];
         key1 = jobsList[index][10];
         jobKey1 = jobsList[index][0];
         String temp = DatabaseInteractor.getData("Contractors;contractor_id=" + key1 + "")[0][4];
         job1.setText(temp);
      }
      if(listLength > index+1){
         endDate2 = jobsList[index+1][5];
         key2 = jobsList[index+1][10];
         jobKey2 = jobsList[index+1][0];
         String temp = DatabaseInteractor.getData("Contractors;contractor_id=" + key2 + "")[0][4];
         job2.setText(temp);
      }
      if(listLength > index+2){
         endDate3 = jobsList[index+2][5];
         key3 = jobsList[index+2][10];
         jobKey3 = jobsList[index+2][0];
         String temp = DatabaseInteractor.getData("Contractors;contractor_id=" + key3 + "")[0][4];
         job3.setText(temp);
      }
      
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
// Logic for when the Next 3 button is pressed
      nextButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(listLength > index+3){
               index += 3;
               endDate1 = jobsList[index][5];
               key1 = jobsList[index][10];
               jobKey1 = jobsList[index][0];
               String temp = DatabaseInteractor.getData("Contractors;contractor_id=<" + key1 + ">")[0][4];
               job1.setText(temp);
               if(listLength > index+1){
                  endDate2 = jobsList[index+1][5];
                  key2 = jobsList[index+1][10];
                  jobKey2 = jobsList[index+1][0];
                  temp = DatabaseInteractor.getData("Contractors;contractor_id=<" + key2 + ">")[0][4];
                  job1.setText(temp);
               }
               if(listLength > index+2){
                  endDate3 = jobsList[index+2][5];
                  key3 = jobsList[index+2][10];
                  jobKey3 = jobsList[index+2][0];
                  temp = DatabaseInteractor.getData("Contractors;contractor_id=<" + key3 + ">")[0][4];
                  job1.setText(temp);
               }
            }
            else{
               index = 1;
               if(listLength > index){
                  endDate1 = jobsList[index][5];
                  key1 = jobsList[index][10];
                  jobKey1 = jobsList[index][0];
                  String temp = DatabaseInteractor.getData("Contractors;contractor_id=<" + key1 + ">")[0][4];
                  job1.setText(temp);
               }
               if(listLength > index+1){
                  endDate2 = jobsList[index+1][5];
                  key2 = jobsList[index+1][10];
                  jobKey2 = jobsList[index+1][0];
                  String temp = DatabaseInteractor.getData("Contractors;contractor_id=<" + key2 + ">")[0][4];
                  job2.setText(temp);
               }
               if(listLength > index+2){
                  endDate3 = jobsList[index+2][5];
                  key3 = jobsList[index+2][10];
                  jobKey3 = jobsList[index+2][0];
                  String temp = DatabaseInteractor.getData("Contractors;contractor_id=<" + key3 + ">")[0][4];
                  job3.setText(temp);
               }
            }
         }
      }); 
// Logic for when the Rate Job1 button is pressed
      job1RateButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(!(job1.getText().toString().equals("N/A"))){
               Thread timer = new Thread(){
                  public void run(){
                     try{
                        sleep(100);
                        Intent openAccountInfoActivity = new Intent("android.intent.action.RATECONTRACTOR");
                        openAccountInfoActivity.putExtra("Contractor Name", job1.getText());
                        // Placeholder Code will require Revision
                        openAccountInfoActivity.putExtra("End Date", endDate1);
                        openAccountInfoActivity.putExtra("key", key1);
                        openAccountInfoActivity.putExtra("jobKey", jobKey1);
                        // End Placholder Code
                        startActivity(openAccountInfoActivity);
                     }catch(Exception e){
                        e.printStackTrace();
                     }
                  }
               };
               timer.start();
            }
         }
      });  
      // Logic for when the Rate Job2 button is pressed
      job2RateButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(!(job2.getText().toString().equals("N/A"))){
               Thread timer = new Thread(){
                  public void run(){
                     try{
                        sleep(100);
                        Intent openAccountInfoActivity = new Intent("android.intent.action.RATECONTRACTOR");
                        openAccountInfoActivity.putExtra("Contractor Name", job2.getText());
                        // Placeholder Code will require Revision
                        openAccountInfoActivity.putExtra("End Date", endDate2);
                        openAccountInfoActivity.putExtra("key", key2);
                        openAccountInfoActivity.putExtra("jobKey", jobKey2);
                        // End Placholder Code
                        startActivity(openAccountInfoActivity);
                     }catch(Exception e){
                        e.printStackTrace();
                     }
                  }
               };
               timer.start(); 
            }
         }
      });     
      // Logic for when the Rate Job3 button is pressed
      job3RateButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(!(job3.getText().toString().equals("N/A"))){
               Thread timer = new Thread(){
                  public void run(){
                     try{
                        sleep(100);
                        Intent openAccountInfoActivity = new Intent("android.intent.action.RATECONTRACTOR");
                        openAccountInfoActivity.putExtra("Contractor Name", job3.getText());
                        // Placeholder Code will require Revision
                        openAccountInfoActivity.putExtra("End Date", endDate3);
                        openAccountInfoActivity.putExtra("key", key3);
                        openAccountInfoActivity.putExtra("jobKey", jobKey3);
                        // End Placholder Code
                        startActivity(openAccountInfoActivity);
                     }catch(Exception e){
                        e.printStackTrace();
                     }
                  }
               };
               timer.start(); 
            }
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
