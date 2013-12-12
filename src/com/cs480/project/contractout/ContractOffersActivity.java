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
   final int REQUEST_EXIT = 11111;
   String key1, key2, key3, contractorList[][];

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
      final Bundle extras = getIntent().getExtras();
      
      final int listLength;
      
      contractorList = DatabaseInteractor.getData("JobMatches;job_id=" + extras.getString("Job ID"));
      listLength = contractorList.length;
      
      if(listLength > 1){
         contractor1.setText(contractorList[1][5]);
         key1 = contractorList[1][0];
      }
      if(listLength > 2){
         contractor2.setText(contractorList[2][5]);
         key2 = contractorList[2][0];
      }
      if(listLength > 3){
         contractor2.setText(contractorList[3][5]);
         key3 = contractorList[3][0];
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
      
      inspect1Button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
 //           if(!contractor1.getText().toString().equals("N/A")){
               Thread timer = new Thread(){
                  public void run(){
                     try{
                        sleep(100);
                        Intent openAccountInfoActivity = new Intent("android.intent.action.INSPECTCONTRACTOR");
                        openAccountInfoActivity.putExtra("Contractor Name", contractor1.getText());
                        String priceString = "$50";//DatabaseInteractor.getData("Price Ranges;price_range_id=" + extras.getString("price"))[1][1];
                        // Placeholder Code will require Revision
                        openAccountInfoActivity.putExtra("Price", priceString);
                        openAccountInfoActivity.putExtra("key", extras.getString("Job ID"));
//                        openAccountInfoActivity.putExtra("id", key1);
                        // End Placholder Code
                        startActivityForResult(openAccountInfoActivity, REQUEST_EXIT);
                     }catch(Exception e){
                        e.printStackTrace();
                     }
                  }
               };
               timer.start(); 
            }
//         }
      });  

      inspect2Button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(!contractor2.getText().toString().equals("N/A")){
               Thread timer = new Thread(){
                  public void run(){
                     try{
                        sleep(100);
                        Intent openAccountInfoActivity = new Intent("android.intent.action.INSPECTCONTRACTOR");
                        openAccountInfoActivity.putExtra("Contractor Name", contractor2.getText());
                        String priceString = DatabaseInteractor.getData("Price Ranges;price_range_id=" + extras.getString("price"))[1][1];
                        // Placeholder Code will require Revision
                        openAccountInfoActivity.putExtra("Price", priceString);
                        openAccountInfoActivity.putExtra("key", extras.getString("Job ID"));
                        openAccountInfoActivity.putExtra("id", key2);
                        // End Placholder Code
                        startActivityForResult(openAccountInfoActivity, REQUEST_EXIT);
                     }catch(Exception e){
                        e.printStackTrace();
                     }
                  }
               };
               timer.start(); 
            }
         }
      });     

      inspect3Button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(!contractor3.getText().toString().equals("N/A")){
               Thread timer = new Thread(){
                  public void run(){
                     try{
                        sleep(100);
                        Intent openAccountInfoActivity = new Intent("android.intent.action.INSPECTCONTRACTOR");
                        openAccountInfoActivity.putExtra("Contractor Name", contractor3.getText());
                        String priceString = DatabaseInteractor.getData("Price Ranges;price_range_id=" + extras.getString("price"))[1][1];
                        // Placeholder Code will require Revision
                        openAccountInfoActivity.putExtra("Price", priceString);
                        openAccountInfoActivity.putExtra("key", extras.getString("Job ID"));
                        openAccountInfoActivity.putExtra("id", key3);
                        // End Placholder Code
                        startActivityForResult(openAccountInfoActivity, REQUEST_EXIT);
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
      super.onPause();
      if(destroyFlag)
         finish();
   }
   
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {

       if (requestCode == REQUEST_EXIT) {
            if (resultCode == RESULT_OK) {
               this.finish();

            }
        }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.contract_offers, menu);
      return true;
   }

}
