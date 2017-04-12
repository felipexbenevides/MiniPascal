/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipascal;


import minipascal.AnaliseSintatica.SourceFile;
import minipascal.AnaliseSintatica.Scanner;
import minipascal.AnaliseSintatica.Parser;


/**
 *
 * @author Benevides
 */
public class MiniPascal {

    /**
     * @param args the command line arguments
     */
    
    //nome do programa objeto
    static String objectName = "obj.tam";
    private static Scanner scanner;
    private static Parser parser;
    //private static Program theAST;


    static boolean compileProgram (String sourceName, String objectName,
                                   boolean showingAST, boolean showingTable) {

        System.out.println("-_-_-_-_-_-_-_-_-_-" +
                           "LFC Compiler v0.1(Java)" +
                           "-_-_-_-_-_-_-_-_-_-");
        System.out.println("Programa fonte : " + sourceName);
        System.out.println("Programa objeto: " + objectName);
        System.out.println("-------------------------------------------------------------");


        System.out.println("Análise Sintática");
        
        //carrega o arquivo em source
        SourceFile source = new SourceFile(sourceName);

        if (source == null) {
            System.out.println("Não foi possível acessar o arquivo: " + sourceName);
            System.exit(1);
        }
        scanner  = new Scanner(source);
        parser   = new Parser(scanner);
        parser.parseProgram();
        
/*      
        reporter = new ErrorReporter();
        parser   = new Parser(scanner, reporter);
        checker  = new Checker(reporter);
        encoder  = new Encoder(reporter);
        drawer   = new Drawer();

        // scanner.enableDebugging();
        theAST = parser.parseProgram();				// 1st pass
        if (reporter.numErrors == 0) {
            //if (showingAST) {
            //    drawer.draw(theAST);
            //}
            System.out.println ("Contextual Analysis ...");
            checker.check(theAST);				// 2nd pass
            if (showingAST) {
                drawer.draw(theAST);
            }
            if (reporter.numErrors == 0) {
                System.out.println("Code Generation ...");
                encoder.encodeRun(theAST, showingTable);	// 3rd pass
            }
        }

	boolean successful = (reporter.numErrors == 0);
        if (successful) {
            encoder.saveObjectProgram(objectName);
            System.out.println("Compilation was successful.");
        } else {
            System.out.println("Compilation was unsuccessful.");
        }
        
*/
        return true;
    }
  public boolean parseProgram() {

   // Program programAST = null;

    //previousTokenPosition.start = 0;
    //previousTokenPosition.finish = 0;
    //currentToken = lexicalAnalyser.scan();


      //Command cAST = parseCommand();
      //parseCommand();
      //programAST = new Program(cAST, previousTokenPosition);
      //if (currentToken.kind != Token.EOT) {
       // syntacticError("\"%\" not expected after end of program",
        //  currentToken.spelling);
      //}

    return true;
  }    
    
    public static void main(String[] args) {
        boolean compiledOK;
        //verifica a conformidade do argumento, path do programa fonte
        if (args.length != 1) {
            System.out.println("[erro]Argumento inválido");
            System.exit(1);
        }
        
        //sourceName recebe endereço do programa a ser compilado
        String sourceName = args[0];
        
        //compila o programa P, path sourceName
        compiledOK = compileProgram(sourceName, objectName, false, false);
        if(compiledOK){
            System.out.println("OK");
        }
    }
    
}
