package esercitazione3;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {

  public static void main(String[] args) {
    String [] filesName = {"fail1.txt","fail2.txt","fail3.txt","fail4.txt","fail5.txt","fail6.txt","failure.txt",
        "input.txt", "succ1.txt","succ2.txt","succ3.txt","succ4.txt","succ5.txt","succ6.txt","test.txt"};
    boolean [] oracle = {false,false,false,false,false,false,false,true,true,true,true,true,true,true,true};
    try {
     // LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
      for (int i = 0; i < filesName.length; i++) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        System.out.println("carico il file: "+ filesName[i]+" output predetto dall'oracolo: "+oracle[i]);
        lexicalAnalyzer.initialize(filesName[i]);
        Parser parser = new Parser();
        System.out.println( "output reale: "+parser.Program());
      }
      
     /* lexicalAnalyzer.initialize("input.txt");
      Parser parser = new Parser();
      System.out.println( "output: "+parser.P());
     */
      
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
   
  }

}
