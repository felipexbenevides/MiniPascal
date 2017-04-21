
package minipascal.AnaliseSintatica;


final class Token extends Object {

  protected int kind;
  protected String spelling;
  protected SourcePosition position;

  public Token(int kind, String spelling, SourcePosition position) {
    if (kind == Token.IDENTIFIER) {
      int currentKind = firstReservedWord;
      boolean searching = true;
      while (searching) {
        //System.out.println(currentKind + "-" +tokenTable[currentKind] +" - "+ spelling);
        boolean comparison = tokenTable[currentKind].equals(spelling);
        if (comparison) {
          this.kind = currentKind;
          searching = false;
          //System.out.println("=0  " + currentKind);
        } else if (currentKind == lastReservedWord) {
          this.kind = Token.IDENTIFIER;
          searching = false;
        } else {
          currentKind ++;
          //System.out.println("INC");
        }
      }
    } else{
      this.kind = kind;    
    }
    this.spelling = spelling;
    this.position = position;

  }

  public static String spell (int kind) {
    return tokenTable[kind];
  }

  public String toString() {
    return "Kind=" + kind + ", spelling=" + spelling;
   //   ", position=" + position;
  }

  // Token classes...

public static final int
    IDENTIFIER = 0,
    INTLIT = 1,
    INTFLOAT = 2,
    OPAD = 3,
    OPMUL = 4,
    OPREL = 5,
        
    TRUE = 6,
    FALSE = 7,
    PROGRAM = 8,
    VAR = 9,
    BEGIN = 10,
    END = 11,
    IF = 12,
    THEN = 13,
    ELSE = 14,
    FUNCTION = 15,
    PROCEDURE = 16,
    WHILE = 17,
    DO = 18,
    ARRAY = 19,
    OF = 20,
    INTEGER = 21,
    REAL = 22,
    BOOLEAN = 23,  
    OR = 24,
    AND = 25,
        
    LPAREN = 26,
    RPAREN = 27,
    LSQUA = 28,
    RSQUA = 29,
    COLON = 30,
    BECOMES = 31,
    SEMICOLON = 32,
    PERIOD = 33,
    COMMA = 34,
    DPERIOD  = 36,
    EMPTY  = 37,
    ERROR  = 38;

  private static String[] tokenTable = new String[] {
    "<id>",
    "<int-lit>",
    "<int-float>",
    "<op-ad>",
    "<op-mul>",
    "<op-rel>",
    "true",
    "false",
    "program",
    "var",
    "begin",
    "end",
    "if",
    "then",
    "else",
    "function",
    "procedure",
    "while",
    "do",
    "array",
    "of",
    "integer",
    "real",
    "boolean",
    "or",
    "and",
    "(",
    ")",
    "[",
    "]",
    ":",
    ":=",
    ";",
    ".",
    ",",
    "..",
    "",
    "<error>"
  };

  private final static int	firstReservedWord = Token.TRUE,
  				lastReservedWord  = Token.AND;

}
