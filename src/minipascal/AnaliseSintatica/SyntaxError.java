/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipascal.AnaliseSintatica;

class SyntaxError extends Exception {

  SyntaxError() {
    super();
  };

  SyntaxError (String s) {
    super(s);
  }

}
