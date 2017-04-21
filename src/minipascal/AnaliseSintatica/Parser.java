/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipascal.AnaliseSintatica;

import minipascal.AbstractSyntaxTrees.*;

/**
 *
 * @author Benevides
 */
public class Parser {

    private Scanner lexicalAnalyser;
    private Token currentToken;
    private SourcePosition previousTokenPosition;

    public Parser(Scanner lexer) {
        lexicalAnalyser = lexer;
        previousTokenPosition = new SourcePosition();
        //Me traz o primeiro token
        currentToken = lexicalAnalyser.scan();

    }

    void accept(int tokenExpected) {
        //  System.out.println("--------------------");      
        //System.out.println("{debug}[cKind]:");
        //  System.out.println(currentToken.kind +" - "+ tokenExpected);
        //System.out.println("{debug}[expected]:");
        //System.out.println(tokenExpected);
        //  System.out.println("--------------------");      

        if (currentToken.kind == tokenExpected) {
            previousTokenPosition = currentToken.position;
            currentToken = lexicalAnalyser.scan();
        } else {
            System.out.println("error");
        }
    }

    void acceptIt() {
        previousTokenPosition = currentToken.position;
        currentToken = lexicalAnalyser.scan();
    }
// start records the position of the start of a phrase.
// This is defined to be the position of the first
// character of the first token of the phrase.

  void start(SourcePosition position) {
    position.start = currentToken.position.start;
  }

// finish records the position of the end of a phrase.
// This is defined to be the position of the last
// character of the last token of the phrase.

  void finish(SourcePosition position) {
    position.finish = previousTokenPosition.finish;
  }

    

    /*
    *AST - cada parser deve instanciar um objeto extend AST passando os
    *os objetos retornados pelos parses internos
    *
    *Cada 'bloco' de instruções tem um start e finish para ter a posicao
    *
    *Cada Token tem um previousTokenPosition = currentTokenPosition para
    *ter a posicao do token
    *
    *<programa> ::= program <id> ; <corpo> .
    *
    **/ 
    public Program parseProgram() {
        Program programAST = null;
        
        previousTokenPosition.start = 0;
        previousTokenPosition.finish = 0;
        
        // 2 e 3              
        //LOGICA GRAMATICA
        //<programa> ::= program <id> ; <corpo> .
        accept(Token.PROGRAM);
        Identifier iAST = parseIdentifier();
        accept(Token.SEMICOLON);
        Body bAST = parseBody();
        accept(Token.PERIOD);
        
        //4
        /*
        *       ________Program______
        *      |                    |
        *   Identifier            Body  
        **/
        programAST = new Program(iAST, bAST, previousTokenPosition);
        
        //5
        return programAST;
    }

    public Identifier parseIdentifier() {
        //1
        Identifier I = null;
    
        previousTokenPosition = currentToken.position;
        
        // 2 e 3
        //get spelling
        String s = currentToken.spelling;
        accept(Token.IDENTIFIER);

        /*4
        *       __________I__________
        *      |          |         |
        *  spelling      Type     decl
        **/        
        I = new Identifier(s, previousTokenPosition);
        
        //5
        return I;
    }

    /*
    *
    *<corpo> ::= <declarações> <comando-composto>
    *
    **/
    public Body parseBody() {
        //1
        Body B = null;
        SourcePosition bodyPos = new SourcePosition();
        start(bodyPos);

        VarDeclaration dAST = parseDeclaration();
        //ComandoComposto cAST = parseComandoComposto();
        finish(bodyPos);

        B = new Body(dAST, bodyPos);

        return B;
    }

    /*
    *<declarações> ::= <declaração> ; | <declarações> <declaração> ; | <vazio>
    **/
    public VarDeclaration parseDeclaration() {
        VarDeclaration D = null;
        String tAST = null;
        Identifier iAST = null;
        SourcePosition declarationPos = new SourcePosition();
        start(declarationPos);
        accept(Token.VAR);
               
        accept(Token.IDENTIFIER);
        accept(Token.COLON);
        
        accept(Token.INTEGER);
        finish(declarationPos);
        
        /*
        *       _____D_____
        *      |          |
        * Identifier    Type
        **/
        D = new VarDeclaration(iAST, tAST, declarationPos);
        return D;
    }

    

    /*
    * <comando-composto> ::= begin <lista-de-comandos> end
    **/
    public void parseComandoComposto() {
        accept(Token.BEGIN);
        parseListaComandos();
        accept(Token.END);
    }

    /*
    *<lista-de-comandos> ::= ε | (<comando> ;)
    *
    **/
    public void parseListaComandos() {
        parseComando();
    }

    /*
    * <comando-composto> ::= begin <lista-de-comandos> end
    **/
    public void parseComando() {
        parseAtribuicao();
    }

    /*
    *<atribuição> ::= <variável> := <expressão>
    *
    **/
    public void parseAtribuicao() {
        accept(Token.IDENTIFIER);
        accept(Token.BECOMES);
        accept(Token.INTLIT);
        accept(Token.SEMICOLON);
    }

}
