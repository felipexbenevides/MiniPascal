/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipascal.AnaliseSintatica;

/**
 *
 * @author Benevides
 */
public class Parser {

    private Scanner lexicalAnalyser;
    private Token currentToken;

    public Parser(Scanner lexer) {
        lexicalAnalyser = lexer;
    }

    void accept(int tokenExpected) {
        //  System.out.println("--------------------");      
        //System.out.println("{debug}[cKind]:");
        //  System.out.println(currentToken.kind +" - "+ tokenExpected);
        //System.out.println("{debug}[expected]:");
        //System.out.println(tokenExpected);
        //  System.out.println("--------------------");      

        if (currentToken.kind == tokenExpected) {
            //previousTokenPosition = currentToken.position;
            currentToken = lexicalAnalyser.scan();
        } else {
            System.out.println("error");
        }
    }

    void acceptIt() {
        //previousTokenPosition = currentToken.position;
        currentToken = lexicalAnalyser.scan();
    }

    /*
    *
    *<programa> ::= program <id> ; <corpo> .
    *
    **/
    public void parseProgram() {
        currentToken = lexicalAnalyser.scan();
        accept(Token.PROGRAM);
        parseIdentifier();
        accept(Token.SEMICOLON);
        parseCorpo();
        accept(Token.PERIOD);
    }

    public void parseIdentifier() {
        accept(Token.IDENTIFIER);
    }

    /*
    *
    *<corpo> ::= <declarações> <comando-composto>
    *
    **/
    public void parseCorpo() {
        parseDeclaracoes();
        //parseComandoComposto();
    }

    /*
    *<declarações> ::= <declaração> ; | <declarações> <declaração> ; | <vazio>
    **/
    public void parseDeclaracoes() {
        accept(Token.VAR);
        accept(Token.IDENTIFIER);
        accept(Token.COLON);
        accept(Token.INTEGER);
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
