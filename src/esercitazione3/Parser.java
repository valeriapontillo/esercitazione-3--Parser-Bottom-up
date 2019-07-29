package esercitazione3;

public class Parser {
  LexicalAnalyzer lexicalAnalyzer;
  public Token next; 
  String lessema;
  
  public Parser() throws Exception {
    lexicalAnalyzer = new LexicalAnalyzer();
    next = lexicalAnalyzer.nextToken();
    lessema = "";
  }
  
  public boolean Program() throws Exception {
    return Statement() && ProgramFirst();
  }
  
  public boolean ProgramFirst() throws Exception {
    if(next==null)
      return true;
    lessema=next.getAttribute();
    if (lessema!=null) {
      if(lessema.equalsIgnoreCase(";")) {
        try {
         next= lexicalAnalyzer.nextToken(); 
        } catch (Exception e) {
        return false;
        }
        try {
          if(Statement() && ProgramFirst())
            return true;
        } catch (Exception e) {
          return false;     //ho trovato ; ma non ho trovato nulla dopo e la stringa non può finire con un ;
        }
      }

    }
    return true;
  }
  
  public boolean Statement() throws Exception {
    lessema=next.getName();
    if (lessema.equals("IF")) { //qui deve andare IF expr THEN Statement
      next=lexicalAnalyzer.nextToken();
      lessema=next.getName();
      if(Expression()) {
        if(lessema.equals("THEN")) {
          next=lexicalAnalyzer.nextToken();
          lessema=next.getName();
          return Statement();   //Qui finisce il primo Statement<--IF expr THEN Statement
        }
      }
    }
    else if(lessema.equals("ID")) { //qui invece ID ASSIGN expr
      next=lexicalAnalyzer.nextToken();
      lessema=next.getName();
      if(lessema.equals("ASSIGN")) {
        next=lexicalAnalyzer.nextToken();
        lessema=next.getName();
        return Expression();    //qui finisce statement<--ID ASSIGN Expression
      }
    }
    return false;
  }
  
  public boolean Expression() throws Exception {
    
    return Terms() && ExpressionFirst();
  }
  
  public boolean Terms() throws Exception {
    if(lessema.equals("ID") || lessema.equals("NUMBER")) { 
      try{next=lexicalAnalyzer.nextToken();
      lessema=next.getName();
      return true;
      }catch (Exception e) {
        return true;
      }
    }
      return false;
  }
  
  public boolean ExpressionFirst() throws Exception {
    if(lessema.equals("RELOP")) {
      next=lexicalAnalyzer.nextToken();
      lessema=next.getName();
      return Terms();
    }
    return true;
  }
  

}
