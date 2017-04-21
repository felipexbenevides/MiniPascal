package minipascal.AnaliseSintatica;

import minipascal.AnaliseSintatica.Token;

public final class Scanner {

    private SourceFile sourceFile;
    private boolean debug;

    private char currentChar;
    private StringBuffer currentSpelling;
    private boolean currentlyScanningToken;

    //Construtor
    public Scanner(SourceFile source) {
        sourceFile = source;
        currentChar = sourceFile.getSource();
        //System.out.println(currentChar);
        debug = false;
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

// isOperator returns true iff the given character is an operator character.
    private boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/'
                || c == '=' || c == '<' || c == '>' || c == '\\'
                || c == '&' || c == '@' || c == '%' || c == '^'
                || c == '?');
    }

///////////////////////////////////////////////////////////////////////////////
    public void enableDebugging() {
        debug = true;
    }

    // takeIt appends the current character to the current token, and gets
    // the next character from the source program.
    private void takeIt() {
        if (currentlyScanningToken) {
            currentSpelling.append(currentChar);
        }
        currentChar = sourceFile.getSource();
        //escrever cada caracter do progrmaa
        //System.out.println(currentChar);
    }

    // scanSeparator skips a single separator.
    private void scanSeparator() {
        switch (currentChar) {
            case '!': {
                takeIt();
                while ((currentChar != SourceFile.EOL) && (currentChar != SourceFile.EOT)) {
                    takeIt();   
                }
                if (currentChar == SourceFile.EOL) {
                    takeIt();
                }
            }
            break;

            case ' ':
            case '\n':
            case '\r':
            case '\t':
                takeIt();
                break;
        }
    }

    private int scanToken() {

        switch (currentChar) {

            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
                takeIt();
                while (isLetter(currentChar) || isDigit(currentChar)) {
                    takeIt();
                }
                //System.out.println("[identifier]");
                return Token.IDENTIFIER;

            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                takeIt();
                while (isDigit(currentChar)) {
                    takeIt();
                }
                return Token.INTLIT;

            /*
    case '+':  case '-':  case '*': case '/':  case '=':
    case '<':  case '>':  case '\\':  case '&':  case '@':
    case '%':  case '^':  case '?':
      takeIt();
      while (isOperator(currentChar))
        takeIt();
      return Token.OPERATOR;
             */
            case '+':
            case '-':
                takeIt();
                return Token.OPAD;

            case '*':
            case '/':
                takeIt();
                return Token.OPMUL;

            case '<':
            case '>':
            case '=':
                takeIt();
                while (isOperator(currentChar)) {
                    takeIt();
                }
                return Token.OPREL;

            /*     
    case '\'':
      takeIt();
      takeIt(); // the quoted character
      if (currentChar == '\'') {
      	takeIt();
        return Token.CHARLITERAL;
      } else
        return Token.ERROR;
             */
            case '.':
                takeIt();
                if (currentChar == '.') {
                    takeIt();
                    return Token.DPERIOD;
                }
                return Token.PERIOD;

            case ':':
                takeIt();
                if (currentChar == '=') {
                    takeIt();
                    return Token.BECOMES;
                } else {
                    return Token.COLON;
                }

            case ';':
                takeIt();
                //System.out.println("[SEMICOLON]");
                return Token.SEMICOLON;

            case ',':
                takeIt();
                return Token.COMMA;

            /*
    case '~':
      takeIt();
      return Token.IS;
             */
            case '(':
                takeIt();
                return Token.LPAREN;

            case ')':
                takeIt();
                return Token.RPAREN;

            case '[':
                takeIt();
                return Token.LSQUA;

            case ']':
                takeIt();
                return Token.RSQUA;
            /*
    case '{':
      takeIt();
      return Token.LCURLY;

    case '}':
      takeIt();
      return Token.RCURLY;
             */
            case SourceFile.EOT:
                return Token.EMPTY;

            default:

                takeIt();
                return Token.ERROR;

        }
    }

    //public Token scan () {
    public Token scan() {
        Token tok;
        SourcePosition pos;
        int kind;
        currentlyScanningToken = false;
        while (currentChar == '!'
                || currentChar == ' '
                || currentChar == '\n'
                || currentChar == '\r'
                || currentChar == '\t') {
            scanSeparator();
        }

        currentlyScanningToken = true;
        currentSpelling = new StringBuffer("");
        pos = new SourcePosition();
        pos.start = sourceFile.getCurrentLine();

        kind = scanToken();

        pos.finish = sourceFile.getCurrentLine();
        //System.out.println("\n");
        //System.out.println(pos);
        
        tok = new Token(kind, currentSpelling.toString(),pos);
        //if (debug)
        System.out.print(tok.spelling + " ");
        //return tok;
        Data.tokenCount++;
        return tok;
    }

}
