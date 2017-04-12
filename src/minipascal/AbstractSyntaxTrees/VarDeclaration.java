/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipascal.AbstractSyntaxTrees;

import minipascal.AnaliseSintatica.SourcePosition;

/**
 *
 * @author Benevides
 */
public class VarDeclaration extends AST {

    public String Type;
    public String Identifier;

    public VarDeclaration(String Type, String Identifier, SourcePosition thePosition) {
        super(thePosition);
        this.Type = Type;
        this.Identifier = Identifier;
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return v;
    }

}
