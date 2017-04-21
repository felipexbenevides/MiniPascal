package minipascal.AbstractSyntaxTrees;

//import Triangle.CodeGenerator.RuntimeEntity;
import minipascal.AnaliseSintatica.SourcePosition;

public abstract class AST {
    
  public SourcePosition	position;
  //public RuntimeEntity  entity;
  public AST (SourcePosition thePosition) {
    position = thePosition;
    //entity = null;
  }

  public SourcePosition getPosition() {
    return position;
  }

  public abstract Object visit(Visitor v, Object o);

  
}
