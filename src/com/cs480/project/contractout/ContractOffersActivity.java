package com.cs480.project.contractout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContractOffersActivity extends Activity {
   boolean destroyFlag;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_contract_offers);
      destroyFlag = false;
      final Button backButton = (Button) findViewById(R.id.avalible_contractors_return_button);
      final Button inspect1Button = (Button) findViewById(R.id.inspect_contractor_1_button);
      final Button inspect2Button = (Button) findViewById(R.id.inspect_contractor_2_button);
      final Button inspect3Button = (Button) findViewById(R.id.inspect_contractor_3_button);
      final TextView contractor1 = (TextView) findViewById(R.id.Contractor1);
      final TextView contractor2 = (TextView) findViewById(R.id.Contractor2);
      final TextView contractor3 = (TextView) findViewById(R.id.Contractor3);
      
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
      
      inspect1Button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.INSPECTCONTRACTOR");
                     openAccountInfoActivity.putExtra("Contractor Name", contractor1.getText());
// Placeholder Code will require Revision
                     openAccountInfoActivity.putExtra("Price", "100");
                     openAccountInfoActivity.putExtra("key", "placeholder");
// End Placholder Code
                     startActivity(openAccountInfoActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start(); 
         }
      });  

      inspect2Button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.INSPECTCONTRACTOR");
                     openAccountInfoActivity.putExtra("Contractor Name", contractor2.getText());
// Placeholder Code will require Revision
                     openAccountInfoActivity.putExtra("Price", "200");
                     openAccountInfoActivity.putExtra("key", "placeholder");
// End Placholder Code
                     startActivity(openAccountInfoActivity);
                  }catch(Exception e){
                     e.printStackTrace();
                  }
               }
            };
            timer.start(); 
         }
      });     

      inspect3Button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread timer = new Thread(){
               public void run(){
                  try{
                     sleep(100);
                     Intent openAccountInfoActivity = new Intent("android.intent.action.INSPECTCONTRACTOR");
                     openAccountInfoActivity.putExtra("Contractor Name", contractor3.getText());
// Placeholder Code will require Revision
                     openAccountInfoActivity.putExtra("Price", "300");
                     openAccountInfoActivity.putExtra("key", "placeholder");
// End Placholder Code
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
      getMenuInflater().inflate(R.menu.contract_offers, menu);
      return true;
   }

}
