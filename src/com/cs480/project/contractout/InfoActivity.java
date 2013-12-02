package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_info);
      final Button back = (Button) findViewById(R.id.return_button_info);
      
      back.setOnClickListener(new View.OnClickListener() {         
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
      getMenuInflater().inflate(R.menu.info, menu);
      return true;
   }

}
