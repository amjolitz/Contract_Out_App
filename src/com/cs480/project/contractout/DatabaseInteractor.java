package com.cs480.project.contractout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.res.*;
import android.content.*;

public class DatabaseInteractor extends Activity {
   
   public DatabaseInteractor() {
      super();
   }


   // Globals
   static String PATH;
   AssetManager assetMan;
   
//   AssetFileDescriptor file;
//   SQLiteDatabase db;
	
   /*
    * Checks username and password against the database to ensure that there is a matching 
    * entry.
    */
   public String logIn(String username, String password) throws IOException {
/*	  String[][] users = readFile("TSV_Users.txt");
	  String[][] contractors = readFile("TSV_Contractors.txt");
	  String[][] jobs = readFile("TSV_Jobs.txt");
	  String[][] Zips = readFile("TSV_Zips.txt");*/	 
    //Get the text file
      File file = new File(this.getFilesDir(), "userTable.txt");

      //Read text from file
      try {
         BufferedReader br = new BufferedReader(new FileReader(file));
         String line;
         while ((line = br.readLine()) != null) {
            String[] stringArray = new String[9];
            Pattern p = Pattern.compile("\\<(.*?)\\>");
            Matcher m = p.matcher(line);
            int i = 0;
            while(m.find())
            {
                stringArray[i] = m.group(1);
                i++;
            }
            if(username.equals(stringArray[1]) && password.equals(stringArray[2])){
               return line;
            }
         }
      }catch(IOException e){}
/*
      catch (IOException e) {}
      if(username.equals("danialr@gmail.com") && password.equals("adzq"))
         return "<123456>, <danialr@gmail.com>, <adzq>, <Danial Racker>, <1234 Fake St>, <Springfield>, <Illinois>, <12334>, <9515551234>";
      else if(username.equals("tester@hotmail.com") && password.equals("123"))
         return "<111111>, <tester@hotmail.com>, <123>, <Tester Man>, <3625 Weekley Street>, <San Antonio>, <California>, <78258>, <5555551234>";
*/      
      return "False";

	  // Dump file into array (see Alex?)
	  // Search array for matching username and password
	  // return "False" or string in Alex's format with <>'s     
   }
   
   public static String[][] getData(String args){
      String[][] temp = new String[3][];
      if(args.equals("Jobs;creator=<123456>;end_date=")){
         temp[1] = ("1;123 Sesame St;Los Angeles;9001;12/1/2013;12/1/2013;My kitchen sink won't drain.;20;10;123456;305").split(";");
         temp[2] = ("2;2827 Summit Street;Shelton;12345;1/2/2014;2/6/2015;description;40;20;123456;630").split(";");
      }else if (args.equals("Jobs;creator=<11111>;end_date=")){
         temp[1] = ("6;347 Goldleaf Lane;Norwalk;99205;1/6/2014;2/18/2015;description;120;60;111111;208").split(";");
         temp[2] = ("10;694 Wood Duck Drive;Richmond;45701;1/10/2014;3/2/2015;description;200;100;111111;106").split(";");
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

   public boolean initializer(String filepath) {
      PATH = filepath;
     
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
//      Context context = new Context();
//      assetMan = context.getAssets();
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
   private static String[][] readFile(String fileName) throws IOException{
//	  AssetFileDescriptor file = assetMan.openFd(fileName); 
//	  AutoCloseInputStream input = new AssetFileDescriptor.AutoCloseInputStream(file);
	  InputStream input = MyApplication.getAppContext().getResources().openRawResource(R.raw.tsv_users); 
	  byte[] buffer = new byte[100000];
	  input.read(buffer);
	  String fileData = new String(buffer, "UTF-8");
	  String[] data_1d = fileData.split("\r\n");
	  String[][] data = new String[data_1d.length][15];
	  for (int i=0; i<data_1d.length; i++)
	     data[i] = data_1d[i].split("\t");

	  return data;
   }
}
