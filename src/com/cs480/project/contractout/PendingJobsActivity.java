package com.cs480.project.contractout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PendingJobsActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_pending_jobs);
      final Button backButton = (Button) findViewById(R.id.pending_jobs_return_button);
      final EditText list = (EditText) findViewById(R.id.Pending_Job_List);
      String userId;
      
//Get the text file
      File file = new File(this.getFilesDir(), "userAccountInfo.txt");

//Read text from file
      try {
         BufferedReader br = new BufferedReader(new FileReader(file));
         userId = br.readLine();
      }catch (IOException e) {
         e.printStackTrace();
         return;
      }
      
      String[][] jobList = DatabaseInteractor.getPending(userId);
   
//  Prints list of incomplete jobs (ones which have not reached its end date)
//  Format: JobID  JobStartDate   SelectedContractor
      list.append("ID\t\tStart Date\t\tContractor\n");
      for(int i=0; i<1; i++){
         String temp = jobList[i][4];
         if (temp.length() == 8)
            temp += " ";
         if (temp.length() == 9)
            temp += " ";
         list.append(jobList[i][0] + "\t\t"+ temp + "\t\t" + jobList[i][10] + "\n");
      }
      
// Logic for when the Log Out button is pressed
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
      getMenuInflater().inflate(R.menu.pending_jobs, menu);
      return true;
   }

}
