/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Thomas-Gaming
 */
class EndOfGameException extends Exception
{
      //Parameterless Constructor
      public EndOfGameException() {}

      //Constructor that accepts a message
      public EndOfGameException(String message)
      {
         super(message);
      }
 }