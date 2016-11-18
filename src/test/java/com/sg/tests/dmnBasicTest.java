/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.tests;

import org.camunda.bpm.dmn.engine.benchmark.util.DecisionTableGenerator;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author lizergsav
 */
public class dmnBasicTest {
    
    public dmnBasicTest() {
    }

     @Test
     public void basicTest() {
         String template = "C:\\tmp\\dmn1.dmn";
         String output = "C:\\tmp\\dmn1-result.dmn";
         
         DecisionTableGenerator dt = new DecisionTableGenerator(template, output);
         dt.generateDmn("test", "test", 3, 2);
         
     
     }
}
