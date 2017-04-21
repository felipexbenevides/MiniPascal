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
    public String spelling;

    public Identifier(String s,SourcePosition thePosition) {
        super(thePosition);
        this.spelling = s;
        this.type = null;
        this.decl = null;
        
    }

    public Object visit(Visitor v, Object o) {
        return v;
    }

}
