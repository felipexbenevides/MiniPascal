/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
**/
package minipascal.AbstractSyntaxTrees;

import minipascal.AnaliseSintatica.SourcePosition;

/**
 *
 * @author Benevides
 */
public class Identifier extends AST {

    public String type;
    public AST decl;

    public Identifier(SourcePosition thePosition) {
        super(thePosition);
    }

    public Object visit(Visitor v, Object o) {
        return v;
    }

}
