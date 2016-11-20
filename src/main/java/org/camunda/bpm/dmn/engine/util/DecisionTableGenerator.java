/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.dmn.engine.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.camunda.bpm.dmn.engine.errors.WrongDMNNameException;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.camunda.bpm.model.dmn.instance.Decision;
import org.camunda.bpm.model.dmn.instance.DecisionTable;
import org.camunda.bpm.model.dmn.instance.InputEntry;
import org.camunda.bpm.model.dmn.instance.OutputEntry;
import org.camunda.bpm.model.dmn.instance.Rule;
import org.camunda.bpm.model.dmn.instance.Text;

/**
 * Generate DMN decision tables with a given number of rules.
 *
 * @author Philipp Ossler
 */
public class DecisionTableGenerator {

  private String template; // "/org/camunda/bpm/dmn/engine/benchmark/templates/";
  private String output; // "src/main/resources/org/camunda/bpm/dmn/engine/benchmark/";

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public DecisionTableGenerator(String template, String output) {
        this.template = template;
        this.output = output;
    }
    
    public void generateDmn(String dmnName, String decisionId, long numberOfRules, long numberOfInputs, String[][] input, String[] output) throws FileNotFoundException, WrongDMNNameException {
    InputStream inputStream = new FileInputStream(this.getTemplate());
    DmnModelInstance dmnModelInstance = Dmn.readModelFromStream(inputStream);

    // set id of the decision
    Decision decision = dmnModelInstance.getModelElementById(dmnName);

    if (decision != null){
    	decision.setId(decisionId);
    } else {
    	throw new WrongDMNNameException(decisionId);
    }
       
    // add the rules
    DecisionTable decisionTable = dmnModelInstance.getModelElementById("decisionTable");

    for (int i = 0; i < numberOfRules; i++) {

      Rule rule = createRule(dmnModelInstance, numberOfInputs, input[i], output[i]);
      decisionTable.getRules().add(rule);
    }

    // write the dmn file
    File dmnFile = new File(this.getOutput());
    Dmn.writeModelToFile(dmnFile, dmnModelInstance);

    System.out.println("generate dmn file: " + dmnFile.getAbsolutePath());
  }

  private Rule createRule(DmnModelInstance dmnModelInstance, double numberOfInputs, String[] input, String output) {
	    Rule rule = dmnModelInstance.newInstance(Rule.class);
	
	    for (int i = 0; i < numberOfInputs; i++) {
	      InputEntry inputEntry = createInputEntry(dmnModelInstance, "> " + input[i]);
	      rule.getInputEntries().add(inputEntry);
	    }
	      OutputEntry outputEntry = createOutputEntry(dmnModelInstance, output);
	      rule.getOutputEntries().add(outputEntry);

    return rule;
  }

  private InputEntry createInputEntry(DmnModelInstance dmnModelInstance, String expression) {
    Text text = dmnModelInstance.newInstance(Text.class);
    text.setTextContent(expression);

    InputEntry inputEntry = dmnModelInstance.newInstance(InputEntry.class);
    inputEntry.setText(text);
    return inputEntry;
  }

  private OutputEntry createOutputEntry(DmnModelInstance dmnModelInstance, String expression) {
    Text text = dmnModelInstance.newInstance(Text.class);
    text.setTextContent(expression);

    OutputEntry outputEntry = dmnModelInstance.newInstance(OutputEntry.class);
    outputEntry.setText(text);
    return outputEntry;
  }

}
