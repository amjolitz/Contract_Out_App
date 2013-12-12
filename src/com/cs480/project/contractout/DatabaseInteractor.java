package com.cs480.project.contractout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.res.*;

public class DatabaseInteractor extends Activity {
   // Globals
   AssetManager assetMan;
   
//   AssetFileDescriptor file;
//   SQLiteDatabase db;
	
   /*
    * Checks username and password against the database to ensure that there is a matching 
    * entry.
    */
   public static String logIn(String username, String password) throws IOException {
    //Get the text file
      File file = new File(MyApplication.getAppContext().getFilesDir(), "userTable.txt");

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
         return readFile("contractorTable.txt");
      }
      
      return temp;
   }
   
   public static int insertData(String args) {
      return 0;
   }
   
   public static int updateData(String args) {
      return 0;
   }

   public static boolean initializer() {
//      Context context = new Context();
//      assetMan = context.getAssets();
//      String fileName = "user_data.db";
//      file = assetMan.openFd(fileName); 
//      db = SQLiteDatabase.openOrCreateDatabase(file, null);
      // @todo Check AssetManager.list() for all expected file names and creat all missing ones*/
      return true;
   }
   
   
/***************************            HELPERS           *****************************/

   /*
    * Searches the asset manager for fileName, opens it, reads its contents, and converts 
    * them into a 2-d array of Strings.
    */
   private static String[][] readFile(String fileName, int rowSize) {
      File file = new File(MyApplication.getAppContext().getFilesDir(), fileName);
	  ArrayList<String[]> table = new ArrayList<String[]>();
      try {
         BufferedReader br = new BufferedReader(new FileReader(file));
         String line;
         while ((line = br.readLine()) != null) {
            String[] row = new String[rowSize];
            Pattern pat = Pattern.compile("\\<(.*?)\\>");
            Matcher match = pat.matcher(line);
            int element = 0;
            // Fill a row
			while(match.find()) {
                row[element] = match.group(1);
                element++;
            }
            // Add a row to the virtual table
			table.add(row);
         }
      } catch(IOException e){}
	  String[][] data = new String[table.size()][rowSize];
	  return table.toArray(data);
   }
}
