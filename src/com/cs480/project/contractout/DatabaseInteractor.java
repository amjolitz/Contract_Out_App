package com.cs480.project.contractout;

import java.io.IOException;

import android.app.Activity;
import android.content.res.*;
import android.content.*;
//import android.database.Cursor;
//import android.database.sqlite.*;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;

public class DatabaseInteractor extends Activity {
   
   // Globals
   static String PATH;
   static AssetManager assetMan;
//   AssetFileDescriptor file;
//   SQLiteDatabase db;
	
   /*
    * Checks username and password against the database to ensure that there is a matching 
    * entry.
    */
   public static String logIn(String username, String password) throws IOException {
	  String[][] users = readFile("TSV_Users.txt");
	  String[][] contractors = readFile("TSV_Contractors.txt");
	  String[][] jobs = readFile("TSV_Jobs.txt");
	  String[][] Zips = readFile("TSV_Zips.txt");
	  
	  // Dump file into array (see Alex?)
	  // Search array for matching username and password
	  // return "False" or string in Alex's format with <>'s
	  return "False";
   }
   
   public static String[][] getData(String args){
      String[][] temp = new String[3][];
      if(args.equals("Jobs;creator=<123456>;end_date=")){
         temp[1] = ("1;123 Sesame St;Los Angeles;9001;12/1/2013;12/1/2013;My kitchen sink won't drain.;20;10;123456;305").split(";");
         temp[2] = ("2;2827 Summit Street;Shelton;12345;1/2/2014;2/6/2015;description;40;20;123456;630").split(";");
      }else{
         temp = new String[1][1];
         temp[0][0] = "9999";
      }
      
      return temp;
   }
   
   public static int insertData(String args) {
      return 0;
   }
   
   public static int updateData(String args) {
      return 0;
   }

   public static boolean initializer(String filepath, Context con) {
      PATH = filepath;
      assetMan = con.getAssets();
//      String fileName = "user_data.db";
//      file = assetMan.openFd(fileName); 
//      db = SQLiteDatabase.openOrCreateDatabase(file, null);
      // @todo Check AssetManager.list() for all expected file names and creat all missing ones
      return true;
   }
   
   
/***************************            HELPERS           *****************************/
   
   /*
    * Searches the asset manager for fileName, opens it, reads its contents, and converts 
    * them into a 2-d array of Strings.
    */
   // @throws IOException
   private static String[][] readFile(String fileName) throws IOException {
	  AssetFileDescriptor file = assetMan.openFd(fileName); 
	  AutoCloseInputStream input = new AssetFileDescriptor.AutoCloseInputStream(file);
	  byte[] buffer = new byte[10000000];
	  input.read(buffer);
	  String fileData = new String(buffer, "UTF-8");
	  String[] data_1d = fileData.split("\r\n");
	  String[][] data = new String[data_1d.length][15];
	  for (int i=0; i<data_1d.length; i++)
	     data[i] = data_1d[i].split("\t");
	  return data;
   }
}
