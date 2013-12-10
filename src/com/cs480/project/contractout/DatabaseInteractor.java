package com.cs480.project.contractout;

public class DatabaseInteractor {
   public static String logIn(String args){
      if(args.equals("user_name=<danialr@gmail.com>;password=<adzq>"))
         return "<123456>, <danialr@gmail.com>, <adzq>, <Danial Racker>, <1234 Fake St>, <Springfield>, <Illinois>, <12334>, <9515551234>";
      else
         return "False";
   }
   
   public static String getData(String args){
      return "filler";
   }
   
   public static boolean insertData(String args){
      return true;
   }
   
   public static boolean updateData(String args){
      return true;
   }

   public static boolean initializer() {
      // TODO Auto-generated method stub
      return true;
   }
}
