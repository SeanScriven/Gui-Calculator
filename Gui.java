import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Gui extends JFrame{

	private JTextField answerField;
	private JButton[] numButtons = new JButton[10];
	private JButton addButton, subButton, multButton, divButton, equalsButton, clearButton;
	private double d1, d2;
	private CalcFunction function;
	private JPanel contentPanel;
	
	public Gui(){
		super("Calculator");
		
		//buttons for 0 to 9
		for(int i = 0; i < 10; i++){
			numButtons[i] = new JButton(Integer.toString(i));
		}
		
		//set the text field where answer shows
		answerField = new JTextField();
		answerField.setPreferredSize(new Dimension(200,30));
		answerField.setEnabled(false);
		answerField.setEditable(false);
		
		//the calculation buttons
		addButton = new JButton("+");
		subButton = new JButton("-");
		multButton = new JButton("*");
		divButton = new JButton("/");
		equalsButton = new JButton("=");
		clearButton = new JButton("C");
		
		//set up the contetn panel and layout of calculator
		contentPanel = new JPanel();
		
		contentPanel.setBackground(Color.CYAN);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(answerField, BorderLayout.NORTH);
	
		//add number buttons to content panel
		for (int i = 0; i < 10; i++){
			contentPanel.add(numButtons[i]);
		}
	
		//add function buttons to content Panel
		contentPanel.add(addButton);
		contentPanel.add(subButton);
		contentPanel.add(multButton);
		contentPanel.add(divButton);
		contentPanel.add(equalsButton);
		contentPanel.add(clearButton);
		
		//set up action listeners for buttons
		NumButtonsAction[] numButtonActions = new NumButtonsAction[10];
	    for ( int i = 0; i < 10; i++ ) {
	        numButtonActions[i] = new NumButtonsAction(numButtons[i]);
	        numButtons[i].addActionListener(numButtonActions[i]);
	    }
		
	    EqualsButton equals = new EqualsButton();
        equalsButton.addActionListener(equals);

        ClearButton c = new ClearButton();
        clearButton.addActionListener(c);

        Calculation multiply = new Calculation(CalcFunction.MULTIPLICATION);
        multButton.addActionListener(multiply);

        Calculation divide = new Calculation(CalcFunction.DIVISION);
        divButton.addActionListener(divide);

        Calculation add = new Calculation(CalcFunction.ADDITION);
        addButton.addActionListener(add);

        Calculation subtract = new Calculation(CalcFunction.SUBTRACTION);
        subButton.addActionListener(subtract);

		this.setContentPane(contentPanel);
	}

	private class NumButtonsAction implements ActionListener{
		private String c;

	    public NumButtonsAction(JButton a) {
	        this.c = a.getText();
	    }

	    public void actionPerformed(ActionEvent e) {
	        if (!answerField.getText().equals("0.0")) {
	            answerField.setText(answerField.getText() + c);
	        } else {
	            answerField.setText("");
	            actionPerformed(e);
	        }
	    }
	}

	private class EqualsButton implements ActionListener {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        d2 = Double.parseDouble(answerField.getText());

	        if (function == CalcFunction.DIVISION) {
	            answerField.setText(Double.toString((Math.round((d1 / d2) * 100)) / 100));
	        } else if (function == CalcFunction.MULTIPLICATION) {
	            answerField.setText(Double.toString(d1 * d2));
	        } else if (function == CalcFunction.ADDITION) {
	            answerField.setText(Double.toString(d2 + d1));
	        } else if (function == CalcFunction.SUBTRACTION) {
	            answerField.setText(Double.toString(d1 - d2));
	        } else {
	            answerField.setText(String.valueOf(d1));
	        }
	            d1 = Double.parseDouble(answerField.getText());
	    }


	}
	
	private class ClearButton implements ActionListener {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        answerField.setText("");
	        d1 = 0;
	        d2 = 0;
	        function = CalcFunction.CLEAR;
	    }


	}

	public class Calculation implements ActionListener{
		CalcFunction calc;
		
		public Calculation(CalcFunction calc){
			this.calc = calc;
		}
		
		@Override
	    public void actionPerformed(ActionEvent e) {
	        if (d1 == 0) {
	            d1 = Double.parseDouble(answerField.getText());
	            answerField.setText("");
	        } else {
	            d2 = Double.parseDouble(answerField.getText());
	            answerField.setText("");
	        }
	        function = calc;
	    }
		
	}
	
}
