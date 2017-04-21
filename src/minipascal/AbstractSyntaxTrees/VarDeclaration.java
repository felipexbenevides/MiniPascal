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

    public String T;
    public Identifier I;

    public VarDeclaration(Identifier iAST, String tAST, SourcePosition thePosition) {
        super(thePosition);
        this.I = iAST;       
        this.T = tAST;
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return v;
    }

}
