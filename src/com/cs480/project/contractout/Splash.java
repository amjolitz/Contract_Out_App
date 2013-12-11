package com.cs480.project.contractout;

import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Splash extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      boolean success = DatabaseInteractor.initializer();
      try {
         FileOutputStream outputStream = openFileOutput("userTable.txt", 0);
         outputStream.write("<123456>, <danialr@gmail.com>, <adzq>, <Danial Racker>, <1234 Fake St>, <Springfield>, <Illinois>, <12334>, <9515551234>\n".getBytes());
         outputStream.write("<111111>, <tester@hotmail.com>, <123>, <Tester Man>, <3625 Weekley Street>, <San Antonio>, <California>, <78258>, <5555551234>\n".getBytes());
         outputStream.close();
         outputStream = openFileOutput("jobTable.txt", 0);
         outputStream.write("<1><123 Sesame St><Los Angeles><9001><12/1/2013><12/1/2013><My kitchen sink won't drain.><20><10><123456><305>\n".getBytes());
         outputStream.write("<2><2827 Summit Street><Shelton><12345><1/2/2014><2/6/2015><description><40><20><123456><630>\n".getBytes());
         outputStream.write("<6><347 Goldleaf Lane><Norwalk><99205><1/6/2014><2/18/2015><description><120><60><111111><208>\n".getBytes());
         outputStream.write("<10><694 Wood Duck Drive><Richmond><45701><1/10/2014><3/2/2015><description><200><100><111111><106>\n".getBytes());
         outputStream.close();
         outputStream = openFileOutput("contractorTable.txt", 0);
         outputStream.write("<1> <awesomeCo> <pa$$w0rd> <Davis George>   <Awesome Construction Company>  <3105551230>  <We provide services for electrical and plumbing.>\n".getBytes());
         outputStream.write("<2>   <Jamathand>   <iemahKi6Aev> <Frances>  <Cut Above>   <9182399293>  <Electronic equipment repairer>\n".getBytes());
         outputStream.write("<3>   <Theausted1931>  <iwuoFeeN0Th> <Leonard>  <Veramons> <2544872113>  <Career counselor>\n".getBytes());
         outputStream.write("<4>   <Duerse65> <ro5ohhaeGae> <Ronald>   <Crandall's Fine Furniture>  <7188645174>  <Statistical assistant>\n".getBytes());
         outputStream.close();
         outputStream = openFileOutput("averageRatingTable.txt", 0);
         outputStream.write("".getBytes());
         outputStream.write("".getBytes());
         outputStream.write("".getBytes());
         outputStream.write("".getBytes());
         outputStream.write("".getBytes());
         outputStream.write("".getBytes());
         outputStream.write("".getBytes());
         outputStream.write("".getBytes());
         outputStream.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
      setContentView(R.layout.activity_splash);
      final Button skipButton = (Button) findViewById(R.id.skipButton);
// Logic for when the screen is touched at the splash screen
      skipButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            try{
               Intent startActivity = new Intent("android.intent.action.START");
               startActivity(startActivity);
            }catch(Exception e){
               e.printStackTrace();
            }
         }
      });
      
      if(success){
         Thread timer = new Thread(){
            public void run(){
               try{
                  sleep(1500);
                  Intent openInfoActivity = new Intent("android.intent.action.START");
                  startActivity(openInfoActivity);
               }catch(Exception e){
                  e.printStackTrace();
               }
            }
         };
         timer.start(); 
      }
      else{
         AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
         dialogBuilder.setTitle("Database Error");
         dialogBuilder.setMessage("We are sorry the database is having issues connecting. This prevents this app from working so please try again at a later time.");
         dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {        
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();          
            }
         });
         
         AlertDialog dialog = dialogBuilder.create();
         dialog.show();
      }
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
      getMenuInflater().inflate(R.menu.splash, menu);
      return true;
   }

}
