/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.tests;

import org.camunda.bpm.dmn.engine.errors.WrongDMNNameException;
import org.camunda.bpm.dmn.engine.util.DecisionTableGenerator;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

/**
 *
 * @author lizergsav
 */
public class dmnBasicTest {
    
    public dmnBasicTest() {
    }

     @Test
     public void basicTest() throws FileNotFoundException, WrongDMNNameException {
         String template = "C:\\tmp\\dmn1.dmn";
         String output = "C:\\tmp\\dmn1-result.dmn";
         int inputNumber = 2;         
         int rowNumber = 10;
         
         DecisionTableGenerator dt = new DecisionTableGenerator(template, output);
         String[][] inputVariables = new String[rowNumber][2];
         String[] outputVariable = new String[rowNumber];
         
         for (int i = 0; i < rowNumber;i++){
        	 
        	inputVariables[i][0] = "FirstRow".concat("-"+i);
        	inputVariables[i][1] = "SecondRow".concat("-"+i);
        	outputVariable[i] = "OutputRow".concat("-"+i);
        	 
         }
         dt.generateDmn("test", "test", rowNumber, inputNumber, inputVariables, outputVariable);
         
     
     }
}
