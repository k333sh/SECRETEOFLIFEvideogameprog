
/**
 * SecretOfLife
 * //SECRET OF LIFE BY OYEWUSI ITEOLUWAKISI COMP1020 A03 7959523
 */
//all imported java functions to assist with the game 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.sound.sampled.Line;


public class SecretOfLife {
   static Maps[] mapArray;

   public static void main(String[] args) {
      openMap();// initialise the map array
      gameOutput();// call method that displays the user input options
      GameMethod();// call method that controls all the game functions

   }

   // method that displays the user input options
   public static void gameOutput() {
      System.out.println(" PICK AN OPTION NUMBER ");// initial print statement
      String[] options = new String[6];
      options[0] = "1- See all maps";
      options[1] = "2-Open map";
      options[2] = "3-Decrypt map";
      options[3] = "4-Write down secret";
      options[4] = "5-Send information to Captain Lila ";
      options[5] = "0 - Exit";

      for (int i = 0; i < options.length; i++) {
         System.out.println(options[i]);// print each string in options array
      }
   }

   // call method that controls all the game functions
   public static void GameMethod() {
      Scanner userInput = new Scanner(System.in);// create a new scanner
      int input = -1;// initailise input as -1 regardless of entry
      if (userInput.hasNextInt()) {
         input = userInput.nextInt();// only change input from -1 if its an integer
      } else { // else return invalid input and await input
         System.out.println("INVALID INPUT");
         userInput.next();
      }
      // request input whilst input is not 0
      while (input != 0) {
         if (input == 1) {
            showMaps(); // call showMaps
         } else if (input == 2) {
            System.out.println("SELECT A MAP"); // print this user prompt SELECT A MAP
            optionTwo(); // call optionTwo
         } else if (input == 3) {
            System.out.println("SELECT A  MAP TO DECRYPT"); // print this user prompt SELECT A MAP TO DECRYPT
            optionThree(); // call optionThree
         } else if (input == 4) {
            System.out.println("ENTER MAP SECRET"); // print this user prompt ENTER MAP SECRET
            int j = -1; // create a variable j and initialise to -1
            // scan through map array for any map that has been decrypted if any
            for (int i = 1; i <= mapArray.length; i++) {
               if (mapArray[i - 1].getmapEncryption() == true) {
                  j = i - 1; // copy that index to j
               }
            }
            if (j == -1) {
               System.out.println("DECRYPT A MAP");// if j remains at -1 ask user to get a map decrypted
            } else if (j != -1) {
               optionFour(j); // enter that index into optionFour

            }
         } else if (input == 5) {
            System.out.println("MESSAGE SENDING PROCESS HAS BEGUN ......");// create fun little statement to infomr user that the message is being sent
            optionFive(mapArray); // pass in mapArray into option Five

         }
         gameOutput(); // call game output
         if (userInput.hasNextInt()) {
            input = userInput.nextInt();// only change input from -1 if its an integer
         } else {
            System.out.println("INVALID INPUT");
            input = -1; // if the input is not an integer return -1 and continue function
            userInput.next();
         }

      }
      // if input is 0
      if (input == 0) {
         System.out.println("YOU HAVE QUIT THE GAME"); // inform user game has been quit
         userInput.close();// close the scanner
      }
   }

   private static void openMap() {
      try {

         FileReader mapp = new FileReader("folio.txt");// read folio.txt
         BufferedReader m = new BufferedReader(mapp); // create bufferreader for more accessibility to informtion read  from folio.txt

         String mappS = null; // create a null string
         mappS = m.readLine();// read folios first line into mappS
         Scanner scanner = new Scanner(mappS); // take the int read from mappS into scanner
         int numMaps = scanner.nextInt();
         mapArray = new Maps[numMaps];// create an array of that length
         // loop through the empty Maps array
         for (int i = 1; i <= numMaps; i++) {
            mappS = m.readLine(); // read the strings from folio
            String[] mappInfo = mappS.split(","); // split on commas
            mapArray[i - 1] = new Maps(mappInfo[0], Integer.parseInt(mappInfo[1]), mappInfo[2]); // add variables to the map array accordingly
         }
         // close both scanners
         mapp.close();
         scanner.close();

      } catch (IOException e) {
         // System.out.println("We have a problem: \n\t" + e.getMessage());
         System.out.println("GAME ERROR SECRET OF LIFE CANNOT BE FOUND");
      }

   }
   // function used when input 1 is pressed to display all maps 
   public static void showMaps() {
      //loop through map aray
      for (int i = 1; i <= mapArray.length; i++) {
         System.out.println(mapArray[i - 1]); //implement toString by printing out the maps in the mapArray 
      }
   }

   // optionTwo function if user selects Option "2"
   public static void optionTwo() {
      // to avoid exceptions keep in try-catch
      try {
         FileReader mapp = new FileReader("folio.txt");// open file folio.txt
         BufferedReader m = new BufferedReader(mapp); // read the file
         String mappS = null; // new string mapps set to null
         mappS = m.readLine();// mappS is set to the value read from bufferReader m
         Scanner scanner = new Scanner(mappS);// new scanner used to take in value from folio line 1
         int numMaps = scanner.nextInt();// int NumMaps is number we got from the scanner
         // Maps[] mapArray = new Maps[numMaps]; // create a new map array

         Scanner two = new Scanner(System.in);// Scanner used in option 2 declared
         String mapN = two.nextLine();// mapN being map Name inputed by user

         String line;// Line string used to read bufferReader info
         boolean notfound = true; // create a boolean that checks if the mapselected has not been found

         // Loop through array
         for (int i = 0; i < mapArray.length; i++) {
            // if input given matches a specific map name or Id
            if (mapN.equalsIgnoreCase(mapArray[i].getmapName()) || mapN.equalsIgnoreCase(mapArray[i].mapId)) {
               notfound = false;// the map has been found
               FileReader file = new FileReader(mapArray[i].getmapId());// read the file with that mapName or Id
               BufferedReader Mp = new BufferedReader(file);
               System.out.println("SELECTED MAP:"); // print this text prior to the selcted map
               // while the reader is not reading in a null value
               while ((line = Mp.readLine()) != null) {
                  System.out.println("\t" + line);// Display the map selected
               }
            }

         }
         // if the map was not found
         if (notfound) {
            System.out.println("MAP WAS NOT FOUND"); // display text
         }
      } catch (IOException e) {
         System.out.println("GAME ERROR SECRET OF LIFE CANNOT BE FOUND");// print this fun error message

      }

   }

   public static void optionThree() {
      // try catcg resource
      try {
         FileReader folio = new FileReader("folio.txt"); // open file folio.txt
         BufferedReader br = new BufferedReader(folio); // read the file
         String line = null; // create the null string
         line = br.readLine(); // read folio into line
         int numMaps = Integer.parseInt(line); // convert the string from the first line of folio to an int
         // System.out.println(numMaps);//print out that number
         br.close();// close scanner

         Scanner input = new Scanner(System.in); // new scanner input
         String mapName = input.nextLine(); // read the input to string mapName
         boolean found = false; // create a boolean that checks if the mapselected has not been found

         // Loop through array
         for (int i = 0; i < mapArray.length; i++) {
            // if input given matches a specific map name or Id
            if (mapName.equalsIgnoreCase(mapArray[i].getmapName()) || mapName.equalsIgnoreCase(mapArray[i].mapId)) {
               mapArray[i].setEncryption(true); // since its decrypted set map decryption to true
               found = true;
               FileReader filereader = new FileReader(mapArray[i].getmapId()); // read the selected mapid name and thus the file name into filereader                                                             
               BufferedReader buffer = new BufferedReader(filereader); // read filereader into buffer
               String decryptedMap = ""; // empty string decrypted map used to assist decryption 
               String mapUse = ""; // empty string mapUse that we return 

               do {
                  //read the constituents of buffer into decrypted map
                  decryptedMap = buffer.readLine();
                  //if not empty 
                  if (decryptedMap != null) {
                     //create an array  that stores all the elements of the file without commas or spaces 
                     String[] arrayTwo = decryptedMap.split("[,\\s]+");
                     //loop through the arrayTwo 
                     for (int d = 0; d < arrayTwo.length; d++) {
                        //if the character to be converted is not an empty string 
                        if (!arrayTwo[d].isEmpty()) {
                           mapUse = mapUse + (char) Integer.parseInt(arrayTwo[d].trim()); //add to mapUse 
                        }
                     }
                  }
               } while (decryptedMap != null); // whilst decryptedmap is not null

               System.out.println("DECRYPTED MAP:");// print out  "DECRYPTED MAP:" then the decrypted text 

               System.out.println(mapUse);
            }

         }

         if (!found) {
            System.out.println("MAP NOT FOUND"); // display text
         }
      } catch (IOException e) {
         System.out.println("GAME ERROR SECRET OF LIFE CANNOT BE FOUND"); //print fun error message 
      }

   }

   //optionFour  that takes in an integer  from our game option 
   private static void optionFour(int a) {
      Scanner scanner = new Scanner(System.in); ///new scanner 
     
      //if the scanner has a line to read 
      if (scanner.hasNextLine() == true) {
         String x = scanner.nextLine(); //store the sceret 
         mapArray[a].setSecret(x);// convert the secrret of the map in the index of mapArray to that string 
      }
   }
   
   //option Five that receives an array of maps 
   private static void optionFive(Maps[] a) {
      // try resources 
      try {
         FileWriter fileWriter = new FileWriter("secret.txt"); // create file secret.txt 
         PrintWriter writer = new PrintWriter(fileWriter); // write into filewriter
         String Secret = null; // create string secret and set to null 
         String[] arrayUsed = new String[mapArray.length]; // create a new string array  arrayUsed
         //loop true map array a 
         for (int i = 0; i < a.length; i++) {
            //if the map is decrypted and secret has been entered 
            if (mapArray[i].getmapEncryption() == true && !mapArray[i].getmapSecret().equals("")) {
               arrayUsed[i] = mapArray[i].getmapSecret(); // copy that into a string in the array
               Secret = arrayUsed[i]; // make secret be the collected string 
            }
         }
         //check if any secret is available to be sent by checking if secret is still null 
         if (Secret == null) {
            System.out.println("NO SECRET TO SEND"); // display error mesage
         } else {
            //loop through the array used 
            for (int i = 0; i < arrayUsed.length; i++) {
               writer.println(arrayUsed[i]); // write those secrets into the secret.txt file 
            }
            System.out.println("MESSAGE ..... HAS BEEN SUCCESSFULLY SENT TO CAPTAIN LILA"); // inform the user of a successful transplant 

         }
         writer.close();// close writer

      } catch (Exception e) {
         System.out.println("WE HAVE A PROBLEM IN THE SECRET OF LIFE : \n\t" + e.getMessage());  //IF EXCEPTION OCCURS CATCH AND PRINT WE HAVE A PROBLEM IN THE SECRET OF LIFE :and the exception 

      }

   }

}
