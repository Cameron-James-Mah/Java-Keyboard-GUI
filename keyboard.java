package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboard implements KeyListener, ActionListener {
	boolean caps = false; //bool variable to track upper/lower case
	//Creating frame elements
	JFrame frame = new JFrame();
    JPanel keyboard = new JPanel();
    JTextArea text;
    JLabel instructions1;
    JLabel instructions2;
    JButton spaceBtn = new JButton();
    //JButton arrays to hold the JButtons to be referenced on key/mouse clicks/wherever necessary
    private JButton[] btns1 = new JButton[14];
    private JButton[] btns2 = new JButton[14];
    private JButton[] btns3 = new JButton[13];
    private JButton[] btns4 = new JButton[12];
    private JButton[] btns5 = new JButton[4];
    //2d string array, will be used when creating keyboard
    static final String[][] key = {
            {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "+", "Backspace"},
            {"Tab", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\"},
            {"Caps", "a", "s", "d", "f", "g", "h", "j", "k", "l", ":", "'", "Enter"},
            {"Shift", "z", "x", "c", "v", "b", "n", "m", ",", ".", "/", "\u2191"},
            {"\u2190", "\u2193", "\u2192"}
        };
    
    public keyboard() {
    	spaceBtn.addActionListener(this);
    	spaceBtn.setActionCommand("Space");
    	//The JPanel keyRow will be used to create the keyboard row by row. 
    	JPanel keyRow;
    	//Using GridBagContraints to format my keyboard
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        keyboard.setLayout(new GridBagLayout());
        //Creating text at top describing program and adding to the keyboard layout
        gbc.gridy = 0;
        //Adding text to top to describe program and making my text area
    	instructions1 = new JLabel("Type some text using your keyboard. The keys you press will be highlighted and the text will be displayed.");
        keyboard.add(instructions1, gbc);
        gbc.gridy = 1;
        instructions2 = new JLabel("Note: Clicking the buttons with your mouse will not perform any action.");
        keyboard.add(instructions2, gbc);
        gbc.gridy = 2;
        text = new JTextArea(12, 60);
        text.addKeyListener(this);
        keyboard.add(text, gbc);
            
        //Adding keys to grid row by row
        
        for (int row = 0; row < key.length; row++) {
        	//Create new JPanel every iteration that will hold the next row of keys
            keyRow = new JPanel(new GridBagLayout());

            gbc.gridy = row+3;
            
            for (int col = 0; col < key[row].length; col++) {
            	/*Adding keys column by column to keyRow
            	adding ActionListener for mouse clicks for each button created
            	adding every new Jbutton to respective Jbutton arrays depending on row so I can reference these buttons later on via JButton arrays
                */
            	if(row == 0) {
            		btns1[col] = new JButton(key[row][col]);
            		btns1[col].addActionListener(this);
            		btns1[col].setActionCommand(btns1[col].getText());
            		keyRow.add(btns1[col]);
            	}
            	else if(row == 1) {
            		btns2[col] = new JButton(key[row][col]);
            		btns2[col].addActionListener(this);
            		btns2[col].setActionCommand(btns2[col].getText());
            		keyRow.add(btns2[col]);
            	}
            	else if(row == 2) {
            		btns3[col] = new JButton(key[row][col]);
            		btns3[col].addActionListener(this);
            		btns3[col].setActionCommand(btns3[col].getText());
            		keyRow.add(btns3[col]);
            	}
            	else if(row == 3) {
            		btns4[col] = new JButton(key[row][col]);
            		btns4[col].addActionListener(this);
            		btns4[col].setActionCommand(btns4[col].getText());
            		keyRow.add(btns4[col]);
            	}
            	
            	else if(row == 4) {
            		btns5[col] = new JButton(key[row][col]);
            		btns5[col].addActionListener(this);
            		btns5[col].setActionCommand(btns5[col].getText());
            		//keyRow.add(btns5[col]);
            	}
            	
                
            }
            
            if(row==4) {
            	//The final row with space and arrow keys(did it this way because I have to add padding)
                spaceBtn.setPreferredSize(new Dimension(290, 25));
                gbc.ipadx = 260;
                keyRow.add(spaceBtn);
                for(int i = 0; i < 3; i++) {
                	keyRow.add(btns5[i]);
                }
                keyboard.add(keyRow, gbc);
            }
            else {
            	//Add row after looping through every column and adding all elements
            	keyboard.add(keyRow, gbc);
            }    
        }
        
        //Adding bottom row(spacebar and arrows)
        
        //Adding elements to frame and setting frame 
        frame.add(keyboard);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700, 400);
        frame.setVisible(true);
        frame.setTitle("Typing Tutor");
    }


	public static void main(String[] args) {
        keyboard board = new keyboard();
    }

	//on key press listeners
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		//Casting key from int to char
		char c = (char)e.getKeyCode();
		//Casting key from char to string
		String cc = String.valueOf(c);
		//bool variable to know if a non alphabetical key was pressed and if so will not change case of cc later on
		boolean specialKey = false;
		
		/*
		 * First I will check if keys like backspace, shift, caps, etc is clicked. 
		 * If Any of these keys are clicked then I set my cc string value to corresponding string value and it will be found as I loop through all buttons
		 * Then I check alphabet/numbers/special characters
		 */
		
		//Checking if certain keys are pressed and setting string value accordingly to match button text so that it can be found in search
		if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
			cc = "Backspace";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_SHIFT) {
			cc = "Shift";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_CAPS_LOCK) {
			//Toggling keyboard gui with upper and lower case alphabetical letters
			cc = "Caps";
			specialKey = true;
			if (caps == true) {
				for(int i = 1; i < 14; i++) {					
					btns2[i].setText(btns2[i].getText().toLowerCase());	
				}
				
				for(int i = 1; i < 12; i++) {
					btns3[i].setText(btns3[i].getText().toLowerCase());	
				}
				
				for(int i = 1; i < 12; i++) {
					btns4[i].setText(btns4[i].getText().toLowerCase());	
				}
				caps = false;
			}
			else {
				for(int i = 1; i < 14; i++) {					
					btns2[i].setText(btns2[i].getText().toUpperCase());	
				}
				
				for(int i = 1; i < 12; i++) {
					btns3[i].setText(btns3[i].getText().toUpperCase());	
				}
				
				for(int i = 1; i < 12; i++) {
					btns4[i].setText(btns4[i].getText().toUpperCase());	
				}
				caps = true;
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			cc = "Enter";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_TAB) {
			cc = "Tab";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP) {
			cc = "\u2191";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			cc = "\u2190";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			cc = "\u2193";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			cc = "\u2192";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			spaceBtn.setBackground(Color.GRAY);
		}
		
		
		//I do this so my button text will match the key code
		if(caps == false && specialKey == false) {
			cc = cc.toLowerCase();
		}
		//Looping through all buttons to see which key was pressed	
		for(int i = 0; i < 14; i++) {
			if(cc.equals(btns1[i].getText())) {
				btns1[i].setBackground(Color.GRAY);
			}
		}
		
		for(int i = 0; i < 14; i++) {
			if(cc.equals(btns2[i].getText())) {
				btns2[i].setBackground(Color.GRAY);
			}
		}
		
		for(int i = 0; i < 13; i++) {
			if(cc.equals(btns3[i].getText())) {
				btns3[i].setBackground(Color.GRAY);
			}
		}
		
		for(int i = 0; i < 12; i++) {
			if(cc.equals(btns4[i].getText())) {
				btns4[i].setBackground(Color.GRAY);
			}
		}
		
		for(int i = 0; i < 3; i++) {
			if(cc.equals(btns5[i].getText())) {
				btns5[i].setBackground(Color.GRAY);
			}
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		//Same code used in keyPressed function as it does the same thing, just different background color
		char c = (char)e.getKeyCode();
		String cc = String.valueOf(c);
		//bool variable to know if a non alphabetical key was pressed and if so will not change case of cc later on
		boolean specialKey = false;
		
		/*
		 * First I will check if keys like backspace, shift, caps, etc is clicked. 
		 * If Any of these keys are clicked then I set my cc string value to corresponding string value and it will be found as I loop through all buttons
		 * Then I check alphabet/numbers/special characters
		 */
		
		//Checking if certain keys are pressed and setting string value accordingly to match button text so it can be found in search
		if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
			cc = "Backspace";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_SHIFT) {
			cc = "Shift";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_CAPS_LOCK) {
			cc = "Caps";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			cc = "Enter";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_TAB) {
			cc = "Tab";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP) {
			cc = "\u2191";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			cc = "\u2190";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			cc = "\u2193";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			cc = "\u2192";
			specialKey = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			spaceBtn.setBackground(null);
		}
		//I do this so my button text will match the key code
		if(caps == false && specialKey == false) {
			cc = cc.toLowerCase();
		}
		//Looping through all buttons to see which key was released
		for(int i = 0; i < 14; i++) {
			if(cc.equals(btns1[i].getText())) {
				btns1[i].setBackground(null);
			}
		}
		
		for(int i = 0; i < 14; i++) {
			if(cc.equals(btns2[i].getText())) {
				btns2[i].setBackground(null);
			}
		}
		
		for(int i = 0; i < 13; i++) {
			if(cc.equals(btns3[i].getText())) {
				btns3[i].setBackground(null);
			}
		}
		
		for(int i = 0; i < 12; i++) {
			if(cc.equals(btns4[i].getText())) {
				btns4[i].setBackground(null);
			}
		}
		
		for(int i = 0; i < 3; i++) {
			if(cc.equals(btns5[i].getText())) {
				btns5[i].setBackground(null);
			}
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//Making string cc equal to getActionCommand which by default will always be lowercase
		String cc = e.getActionCommand();
		//System.out.println(cc);
		/*
		 * On button click function 
		 * First I check if the key clicked was a special character
		 * In the case where it was a special key(backspace, caps lock, etc) then I do the function accordingly
		 * In the case where it wasn't a special key I just append to the textarea
		 */
		
		
		if(e.getActionCommand()=="Backspace") {
			text.setText (text.getText ().substring (0, text.getText ().length () - 1));		
		}
		else if(e.getActionCommand()=="Shift") {
					
		}
		else if(e.getActionCommand()=="Caps") {
			
			//Toggling keyboard gui with upper and lower case alphabetical letters
			if (caps == true) {
				for(int i = 1; i < 14; i++) {					
					btns2[i].setText(btns2[i].getText().toLowerCase());	
				}
				
				for(int i = 1; i < 12; i++) {
					btns3[i].setText(btns3[i].getText().toLowerCase());	
				}
				
				for(int i = 1; i < 12; i++) {
					btns4[i].setText(btns4[i].getText().toLowerCase());	
				}
				caps = false;
			}
			else {
				for(int i = 1; i < 14; i++) {					
					btns2[i].setText(btns2[i].getText().toUpperCase());	
				}
				
				for(int i = 1; i < 12; i++) {
					btns3[i].setText(btns3[i].getText().toUpperCase());	
				}
				
				for(int i = 1; i < 12; i++) {
					btns4[i].setText(btns4[i].getText().toUpperCase());	
				}
				caps = true;
			}
		}
		else if(e.getActionCommand()=="Enter") {
			text.append("\n");		
		}
		else if(e.getActionCommand()=="Tab") {
			text.append("\t");		
		}
		else if(e.getActionCommand()=="\u2191") {
					
		}
		else if(e.getActionCommand()=="\u2190") {
					
		}
		else if(e.getActionCommand()=="\u2193") {
					
		}
		else if(e.getActionCommand()=="\u2192") {
					
		}
		else if(e.getActionCommand()=="Space") {
			text.append(" ");
		}
		else {
		//Changing cc to uppercase when caps lock is selected
		if(caps == true) {
			cc=cc.toUpperCase();
		}
		
		//Looping through all buttons to see which key was pressed and appending accordingly
		for(int i = 0; i < 14; i++) {
			if(cc.equals(btns1[i].getText())) {
				text.append(btns1[i].getText());
				
			}
		}
		
		for(int i = 0; i < 14; i++) {
			if(cc.equals(btns2[i].getText())) {
				text.append(btns2[i].getText());
			}
		}
		
		for(int i = 0; i < 13; i++) {
			if(cc.equals(btns3[i].getText())) {
				text.append(btns3[i].getText());
			}
		}
		
		for(int i = 0; i < 12; i++) {
			if(cc.equals(btns4[i].getText())) {
				text.append(btns4[i].getText());
			}
		}
		
		for(int i = 0; i < 3; i++) {
			if(cc.equals(btns5[i].getText())) {
				text.append(btns5[i].getText());
			}
		}

		
	}
	}
	}
