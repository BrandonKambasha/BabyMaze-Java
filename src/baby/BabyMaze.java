package baby;

//Java libraries imported for usage 
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Timer;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;


public class BabyMaze extends JFrame implements ActionListener {
	
	
	//Creation of Jpanels, Jlabels, Jtextfields and Jbuttons on the GUI
	JPanel maze_panel;
	JPanel right_panel;
	JPanel bottom_panel;
	
	JLabel timer_label;
	JTextField hours;
	JLabel colon1;
	JTextField minutes;
	JLabel colon2;
	JTextField seconds;
	
	JLabel options;
	JLabel square;
	JLabel direction;
	JTextField options_textfield;
	JTextField square_textfield;
	JTextField direction_textfield;
	
	JButton exit_button;
	JButton vertical;
	JButton horizontal;
	JButton up_button;
	JButton down_button;
	JButton right_button;
	JButton left_button;
	JButton compass_label;
	
	JButton act_button;
	JButton run_button;
	JButton reset_button;
	JLabel speed_label;
	JSlider speed_slider;
	
	//Timer function
	//(Salatian.2020)
	private javax.swing.Timer timer;
	private int nTimeDelay = 50;
	private int ticks = 0;
	
	//Integers used in the GUI
	int babyY=9;// babyY is Y-direction
	int babyX=0;// babyX is X-direction
	int timer_count=1;
	int up = 1;
	int down = 2;
	int right= 3;
	int left= 4;
	int movement=up;
	int run=0;
	
	//integers for the 2 layouts
	int h_layout=0;
	int v_layout=1;
	int layout=v_layout;//(Cross.2020)
	
	//calculation of the square number
	int squares =babyX*13+babyY;
	String sqr = String.valueOf(squares);
	
	// array for images such as the brick and white images to be placed on buttons in the maze panel
	//(Cross.2020)
	JButton[][] image_array= new JButton[13][16];

	
	//vertical array setup of the maze 0= white space & 1= brick & 2= baby
	int[][] vertical_array = {{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1}, 
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			{0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,1},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1},
			{0,1,0,1,0,0,0,1,0,1,0,1,0,1,0,0},
			{0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1},
			{0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1},
			{2,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			{0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			};

	//horizontal array 1= brick & 0= white space & 2 = baby
	int[][] horizontal_array= {{0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
				}; 

	
	//Creating the main frame and its attributes such as size
	public static void main (String[] args) {
			BabyMaze frame = new BabyMaze();
			frame.setSize(825,585);
			frame.createGUI();
			frame.setVisible(true);
			frame.setResizable(false);//(resizable.2020) 
			frame.setIconImage(new ImageIcon("images/greenfoot.png").getImage()); // Changing the application icon (Javapoint.2020)
		}
	
	// action performed for all the buttons on the GUI    
		public void actionPerformed(ActionEvent event) { 
			  
			if(event.getSource()==reset_button)
				reset_method();
				
			  
			if(event.getSource()==vertical)
				vertical_method();
			
			if(event.getSource()==horizontal)
				horizontal_method();
			
			if(event.getSource()==up_button)
				up_method();
				
			
			if(event.getSource()==down_button)
				down_method();
				
			
			if(event.getSource()==right_button)
				right_method();
				
			
			if(event.getSource()==left_button)
				left_method();
				
			
			if(event.getSource()==act_button)
				act_method();
				
				
			if(event.getSource()==run_button)
				run_method();
				
			
			//Timer count for the digital timer				
			if(event.getSource()==timer)
			{
			hours.setText(String.format("%02d", (ticks*(nTimeDelay)/1000) / 3600));
			minutes.setText(String.format("%02d", (ticks*(nTimeDelay)/1000) / 60));
			seconds.setText(String.format("%02d", (ticks*(nTimeDelay)/1000) % 60));
			ticks++;
			}
			
			
			}
		
	// Creation of the GUI code block
	private void createGUI() {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			Container window = getContentPane();
			window.setLayout(new BorderLayout());
			
						
			//Panel to contain the Maze and the Maze panel configurations
			maze_panel = new JPanel(new GridLayout(13,16));
			maze_panel.setPreferredSize(new Dimension(650,400));
			maze_panel.setBackground(Color.white);
			
			//Creating a black line border for the maze panel
			Border border2 = BorderFactory.createLineBorder(Color.BLACK);
			maze_panel.setBorder(border2);
			window.add(maze_panel, BorderLayout.WEST);
			
			//The panel on the right with the controls as well as the DIgital Timer
			right_panel = new JPanel();
			right_panel.setLayout(new GridLayout(10,3));
			Border border1 = BorderFactory.createLineBorder(Color.BLACK);
			right_panel.setBorder(border1);
			window.add(right_panel,BorderLayout.EAST);
			
			//All the Labels and textfields disolayed on the right panel
			timer_label = new JLabel("           DIGITAL TIMER");
			timer_label.setLayout(new FlowLayout());
			timer_label.setAlignmentX(CENTER_ALIGNMENT);
			right_panel.add(timer_label);
			
			JPanel time = new JPanel();
			time.setLayout(new FlowLayout());
			time.setAlignmentY(CENTER_ALIGNMENT);
			right_panel.add(time);
			
			hours = new JTextField("00");
			time.add(hours);
			hours.setBackground(Color.white);
		
			colon1 = new JLabel(":");
			time.add(colon1);
			
			minutes = new JTextField("00");
			time.add(minutes);
			minutes.setBackground(Color.white);
			
			colon2 = new JLabel(":");
			time.add(colon2);
			
			seconds = new JTextField("00");
			time.add(seconds);
			seconds.setBackground(Color.white);
			
			JPanel options_panel = new JPanel();
			options_panel.setLayout(new FlowLayout());
			right_panel.add(options_panel);
			
			options = new JLabel("Options:     ");
			options.setAlignmentX(LEFT_ALIGNMENT);
			options_panel.add(options);
			
			options_textfield = new JTextField("  Vertical  ");
			options_textfield.setAlignmentX(RIGHT_ALIGNMENT);
			options_panel.add(options_textfield);
			
			JPanel square_panel =new JPanel();
			square_panel.setLayout(new FlowLayout());
			right_panel.add(square_panel);
			
			square = new JLabel("Square:    ");
			square.setAlignmentX(CENTER_ALIGNMENT);
			square_panel.add(square);
					
			square_textfield = new JTextField(sqr);
			square_textfield.setAlignmentX(CENTER_ALIGNMENT);
			square_panel.add(square_textfield);
			
			JPanel direction_panel =new JPanel();
			direction_panel.setLayout(new FlowLayout());
			right_panel.add(direction_panel);
			
			direction = new JLabel("Direction:   ");
			direction.setAlignmentX(CENTER_ALIGNMENT);
			direction_panel.add(direction);
			
			direction_textfield = new JTextField(" N ");
			direction_textfield.setAlignmentX(CENTER_ALIGNMENT);
			direction_panel.add(direction_textfield);
								
			//button pad to contain movement buttons
			JPanel button_pad = new JPanel();
			button_pad.setLayout(new GridLayout(3,3));
			button_pad.setPreferredSize(new Dimension(100,100));
			
			up_button = new JButton("^");
			up_button.addActionListener(this);
			up_button.setBorder(BorderFactory.createLineBorder(Color.black));
					
			down_button = new JButton("v");
			down_button.addActionListener(this);
			down_button.setBorder(BorderFactory.createLineBorder(Color.black)); //border around buttons
			
			left_button = new JButton("<");
			left_button.addActionListener(this);
			left_button.setBorder(BorderFactory.createLineBorder(Color.black));//border around buttons
					
			right_button = new JButton(">");
			right_button.addActionListener(this);
			right_button.setBorder(BorderFactory.createLineBorder(Color.black));//border around buttons
					
			JButton blank1_button = new JButton();
			blank1_button.setBorder(BorderFactory.createLineBorder(Color.black));//border around buttons
			
			JButton blank2_button = new JButton();
			blank2_button.setBorder(BorderFactory.createLineBorder(Color.black));//border around buttons
			
			JButton blank3_button = new JButton();
			blank3_button.setBorder(BorderFactory.createLineBorder(Color.black));//border around buttons
			
			JButton blank4_button = new JButton();
			blank4_button.setBorder(BorderFactory.createBevelBorder(7));
			blank4_button.setBorder(BorderFactory.createLineBorder(Color.black));//border around buttons
			
			
			JButton pic = new JButton("", new ImageIcon("images/baby1.png"));
			
			//adding the 9 JButtons to the button pad
			button_pad.add(blank1_button);
			button_pad.add(up_button);
			button_pad.add(blank2_button);
			button_pad.add(left_button);
			button_pad.add(pic);
			button_pad.add(right_button);
			button_pad.add(blank3_button);
			button_pad.add(down_button);
			button_pad.add(blank4_button);		
			
			right_panel.add(button_pad, BorderLayout.EAST);
			
			compass_label= new JButton(new ImageIcon("images/north.jpg"));
			compass_label.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4)); // Removing the border on the compass image
			compass_label.setContentAreaFilled(false);
			right_panel.add(compass_label);			
			
			vertical = new JButton ("Vertical");
			vertical.setAlignmentX(CENTER_ALIGNMENT);
			vertical.addActionListener(this);
			right_panel.add(vertical);
			
			horizontal = new JButton("Horizontal");
			horizontal.setAlignmentX(CENTER_ALIGNMENT);
			horizontal.addActionListener(this);
			right_panel.add(horizontal);
			
			exit_button = new JButton("EXIT");
			exit_button.addActionListener(new ActionListener() 
				{
				public void actionPerformed(ActionEvent e)    // The action system.exit closes the application when clicked
				{System.exit(0);}
				});
			right_panel.add(exit_button);
			
			
			//THe panel at the bottom with the JLabels, JButtons and Jslider
			bottom_panel = new JPanel();
			bottom_panel.setLayout(new FlowLayout());
			
			//THe buttons on the bottom panel as well as the slider
			Icon icon = new ImageIcon("images/step.png");
			act_button = new JButton("Act", icon);
			act_button.addActionListener(this);
			bottom_panel.add(act_button);
			window.add(bottom_panel, BorderLayout.SOUTH);
			
			Icon icon2 = new ImageIcon("images/run.png");
			run_button = new JButton("RUN", icon2);
			run_button.addActionListener(this);
			bottom_panel.add(run_button);
			
			Icon icon3 = new ImageIcon("images/reset.png");
			reset_button = new JButton("RESET", icon3);
			reset_button.addActionListener(this);
			bottom_panel.add(reset_button);
			
			// A space on the speed label was added to allow the Speed slider to be separated from the buttons
			speed_label =new JLabel("                                          Speed:");
			bottom_panel.add(speed_label);
			
			speed_slider = new JSlider(JSlider.HORIZONTAL,0,4,0); //Creates a horizontal JSlider with 4 ticks
			speed_slider.setMajorTickSpacing(1); //Creates a stage each 1 interval
			speed_slider.setPaintTicks(true); // Allows the ticks to be visible
			bottom_panel.add(speed_slider);
			
			
			//coding for the maze panel
			//Placing the images into icons			
			Icon baby = new ImageIcon("images/baby2.png");
			Icon brick = new ImageIcon("images/bricks2.jpg");
			Icon white = new ImageIcon("images/white32x32.jpg");
		
			// for loop to add images to the Image array layout (Cross.2020)	
			for(int i=0;i<13;i++)
			for(int j=0;j<16;j++) 
				{
					if (vertical_array[i][j]==1) //All 1's in the vertical array are replaced with brick icons
						{
						JButton brick_button = new JButton(brick);
						brick_button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4)); //removes button border
						brick_button.setContentAreaFilled(false);
						image_array[i][j]= brick_button;
						}
						
					else if(vertical_array[i][j]==0) // White icons placed on all 0's
						{
						JButton white_button = new JButton(white);
						white_button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4)); //removes button border
						white_button.setContentAreaFilled(false);
						image_array[i][j]= white_button;
						}
					
					else 
						{ 
						JButton baby_button = new JButton (baby);// Baby icon placed on 2 in array
						baby_button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4)); //removes button border
						baby_button.setContentAreaFilled(false);
						image_array[i][j] =baby_button;
						}
				}
			
			for(int i=0;i<13; i++)
			for(int j=0; j<16; j++)
			
			maze_panel.add(image_array[i][j]);
			window.add(maze_panel);
			
			
		}
		
		//Method codes for the all buttons in the GUI
		
				//All icons used by the buttons
				Icon baby = new ImageIcon("images/baby2.png");
				Icon brick = new ImageIcon("images/bricks2.jpg");
				Icon white = new ImageIcon("images/white32x32.jpg");
				Icon north = new ImageIcon("images/north.jpg");
				Icon south = new ImageIcon("images/south.jpg");
				Icon west = new ImageIcon("images/west.jpg");
				Icon east = new ImageIcon ("images/east.jpg");

	//THe vertical method code block
	private void vertical_method() 
		{
		// Adding all images involved with buttons to icons
		options_textfield.setText("  Vertical  ");
		square_textfield.setText("  "+(babyX*13)+(babyY)+"  ");
		
		//Places the baby at coordinate 0 ~ x-direction and 9 ~ y-direction
		babyY = 9;
		babyX = 0;
		
		//places baby icon at position(0,9)
		image_array[babyY][babyX].setIcon(baby);
		
		//Sets the layout to Vertical layout
		layout=v_layout;
		
		//resets the movement of the babay to start by going up
		movement=up;
			
			//For loop to place vertical layout on the maze			
			for(int i=0;i<13;i++)
			for(int j=0;j<16;j++) 
				{
				//places brick icon on all 1's
				if (vertical_array[i][j]==1)
					{
					image_array[i][j].setIcon(brick);
					}
				// places white on all 0's in array	
				else if(vertical_array[i][j]==0) 
					{
					image_array[i][j].setIcon(white);
					}
				//places baby icon on 2's on array
				else 
					{ 
					image_array[i][j].setIcon(baby);
					}
				}
			timer.restart();		
			ticks=0;			
		}
	
	//code to set layout grid in horizontal orientation
	private void horizontal_method() 
		{
			options_textfield.setText("  Horizontal  ");
			square_textfield.setText("  "+babyY*13+babyX+"  ");
					
			//Places the baby at coordinate 7 ~ x-direction and 0 ~ y-direction
			babyY = 0;
			babyX = 7;
			
			timer_count=1;
					
			//Places the baby icon on the set coordinates(7,0)		
			image_array[babyY][babyX].setIcon(baby);
				
			// Changes the layout to Horizontal layout	
			layout=h_layout;
					
				//For loop to Create Horizontal Maze layout			
				for(int i=0;i<13;i++)
				for(int j=0;j<16;j++) 
					{
						// Sets all 1's to bricks
						if (horizontal_array[i][j]==1)
							{
							image_array[i][j].setIcon(brick);
							}
						// sets all 0's to white spaces		
						else if(horizontal_array[i][j]==0) 
							{
							image_array[i][j].setIcon(white);
							}
						//Baby icon placed on the 2 in the horizontal array	
						else 
							{
							image_array[i][j].setIcon(baby);
							}
					}
			timer.restart();		
			ticks=0;
		}
				
				
	// method for the up button on the button pad
	private void up_method() 
  		{
		// firstly checks whether layout is vertical layout to perform a vertical movement
		if(layout==v_layout) 
			{
			//out of bounds check for upward direction
			if((babyY-1)!=-1) 
				{
											
					if(vertical_array[babyY-1][babyX]==0) //Checks if top space is a white space(0) 
						{
						image_array[babyY][babyX].setIcon(white);//if true the current position is replaced with white image 
						image_array[babyY-=1][babyX].setIcon(baby);//The upward position(north direction) is replaced with a baby image
						}
							
					
					else if(vertical_array[babyY-1][babyX]==2) //Checks if one step up(north direction) is a baby(2)
						{
						image_array[babyY][babyX].setIcon(white); //if true the current position is replaced with white image 
						image_array[babyY-=1][babyX].setIcon(baby); //The upward position(north direction) is replaced with a baby image
						}
							
				else {
					image_array[babyY][babyX].setIcon(baby); //baby does not move and remains in the same position
					  }
				
		}	
			
			//if the layout is horizontal layout
			else {
				if((babyY-1)!=-1) 
					{
						if(horizontal_array[babyY-1][babyX]==0) //Checks if top space is a white space(0)
							{
							image_array[babyY][babyX].setIcon(white);//if true the current position is replaced with white image 
							image_array[babyY-=1][babyX].setIcon(baby);//The upward position(north direction) is replaced with a baby image
							}
						
						else if(horizontal_array[babyY-1][babyX]==2) //Checks if one step up(north direction) is a baby(2)
							{
							// Moves the baby up
							image_array[babyY][babyX].setIcon(white);
							image_array[babyY-=1][babyX].setIcon(baby);
							}
						
						else // Does not move baby
							{
							image_array[babyY][babyX].setIcon(baby);
							}
						
					}
					
				
				}
						//Updates for right panel each time button is pressed
						compass_label.setIcon(north); 		 //sets north compass image
						direction_textfield.setText(" N ");  //sets text N in the textfield
						int squares =babyY*13+babyX;		//value of square position
						String sqr = String.valueOf(squares);
						square_textfield.setText(sqr); 	 // sets the value os squares in the textfield
						
						if(timer_count==1) 
							{
							timer = new javax.swing.Timer(50, this);
							timer.start();
							}
						timer_count++;
			}
	}		
					
	private void down_method() 
		{
		if(layout==v_layout) 
			{
				//out of bounds check for down direction
				if((babyY+1)!=13) 
					{
					//This function does a check to see if one step down is a white space					
					if(vertical_array[babyY+1][babyX]==0) 
						{
						//moves the baby down
						image_array[babyY][babyX].setIcon(white);
						image_array[babyY+=1][babyX].setIcon(baby);
						}
					//Allows to pass through a 2 in the array		
					else if(vertical_array[babyY+1][babyX]==2) 
						{
						image_array[babyY][babyX].setIcon(white);
						image_array[babyY+=1][babyX].setIcon(baby);
						}							
							
					else 
						{
						image_array[babyY][babyX].setIcon(baby);
						}
					}
				if(timer_count==1) 
						{
						timer = new javax.swing.Timer(50, this);
						timer.start();
						
						}
					
						timer_count++;	
			}
		else 
			{
			if((babyY+1)!=13) 
				{ // Executes similar down movement code only for horizontal layout						
				if(horizontal_array[babyY+1][babyX]==0) 
					{
					image_array[babyY][babyX].setIcon(white);
					image_array[babyY+=1][babyX].setIcon(baby);
					}
					
				else if(horizontal_array[babyY+1][babyX]==2) 
					{
					image_array[babyY][babyX].setIcon(white);
					image_array[babyY+=1][babyX].setIcon(baby);
					}							
					
				else 
					{
					image_array[babyY][babyX].setIcon(baby);
					}
					
				}
				
				if(timer_count==1) 
						{
						timer = new javax.swing.Timer(50, this);
						timer.start();
						
						}
					else if(babyY==12) 
						{
						timer.stop();
						}
						timer_count++;
			}
					compass_label.setIcon(south);
					direction_textfield.setText(" S ");
					int squares =babyY*13+babyX;
					String sqr = String.valueOf(squares);
					square_textfield.setText(sqr);
					
					
	}
					
					
				
	// Code for right button			
	private void right_method() 
		{
			if(layout==v_layout) 
				{
					if((babyX+1)!=16)// Only moves if right wall is not reached
						{
						//If to check if right space is a white space(0), and moves it down if true
						if(vertical_array[babyY][babyX+1]==0) 
							{
							image_array[babyY][babyX].setIcon(white);
							image_array[babyY][babyX+=1].setIcon(baby);
							}
					
						else // Keeps baby in place
							{
							image_array[babyY][babyX].setIcon(baby);
							}
						}
						//when in vertical only starts timer
						if(timer_count==1) 
						{
						timer = new javax.swing.Timer(50, this);
						timer.start();
						}
						else if(babyX==15)// stops timer at the end  
						{
						timer.stop();
						}
						timer_count++;
				}
			else 
				{
				// Executes similar code only for horrizontal layout
				if((babyX+1)!=16)
					{
						if(horizontal_array[babyY][babyX+1]==0) 
							{
							image_array[babyY][babyX].setIcon(white);
							image_array[babyY][babyX+=1].setIcon(baby);
							}
		
						else if(horizontal_array[babyY][babyX+1]==2) 
							{
							image_array[babyY][babyX].setIcon(white);
							image_array[babyY][babyX+=1].setIcon(baby);
							}
		
						else 
						{
						image_array[babyY][babyX].setIcon(baby);
						}
					}
					//when in horizontal mode stops timer at the bottom
					if(timer_count==1) 
						{
						timer = new javax.swing.Timer(50, this);
						timer.start();
						}
					
					timer_count++;
					
				}
					//Updates right panel
					compass_label.setIcon(east);
					direction_textfield.setText(" E ");
					int squares =babyY*13+babyX;
					String sqr = String.valueOf(squares);
					square_textfield.setText(sqr);
					
		}
	
	//Left button code			
	private void left_method() 
		{
			if(layout==v_layout) 
				{
					if((babyX-1)!=-1) //checks for left boundary
						{	
						//moves baby left					
						if(vertical_array[babyY][babyX-1]==0) 
							{
							image_array[babyY][babyX].setIcon(white);
							image_array[babyY][babyX-=1].setIcon(baby);
							}
							
						else 
							{
							image_array[babyY][babyX].setIcon(baby);
							}
						}
				}
			else 
				{
					//Left movement in horizontal layout
					if((babyX-1)!=-1) 
					
						{						
							if(horizontal_array[babyY][babyX-1]==0) 
								{
								//moves baby left
								image_array[babyY][babyX].setIcon(white);
								image_array[babyY][babyX-=1].setIcon(baby);
								}
							
							//moves baby past 2 in the array
							else if(horizontal_array[babyY][babyX-1]==2) 
								{
								image_array[babyY][babyX].setIcon(white);
								image_array[babyY][babyX-=1].setIcon(baby);
								}
													
							else 
								{
								image_array[babyY][babyX].setIcon(baby);
								}
						}
				}
						//updates right panel
						compass_label.setIcon(west);
						direction_textfield.setText(" W ");
						int squares =babyY*13+babyX;
						String sqr = String.valueOf(squares);
						square_textfield.setText(sqr);
						
						//if function to start and stop timer
						if(timer_count==1) 
							{
							timer = new javax.swing.Timer(50, this);
							timer.start();
							}
							timer_count++;
		}
				
				
	//Method for Act button 
	private void act_method() //(Cross.2020)
		{
		if(layout==v_layout)// act button for vertical layout
			{	
			//this first if is to check whether it has reached the right wall of the maze					
			if(babyX<16) 
				{
					image_array[babyY][babyX].setIcon(baby);
						
					//this if is to check whether the top wall of the maze is equal to 0 and if so movement=down	
					if(babyY==0) 
						{
						movement=down;
						}
					//here it checks whether the bottom wall hasn't been reached, if reached move up
					else if(babyY==13) 
						{
						movement=up;
						}
					
					//The baby moves up if it hits a brick on  the right and down when it reaches the top and up when at the bottom
					if(movement==up) 
						{
							if(vertical_array[babyY][babyX+1]==0) // check whether one step to the right is white 
								{
								image_array[babyY][babyX+=1].setIcon(baby);//replace image to the right with baby
								image_array[babyY][babyX-1].setIcon(white);//replace current position with white space
								compass_label.setIcon(east);
								direction_textfield.setText(" E ");
								}
							
							else
								{
								image_array[babyY-=1][babyX].setIcon(baby);//replaces up position with baby image making the baby move up
								image_array[babyY+1][babyX].setIcon(white);//places a white image where the baby was
								compass_label.setIcon(north);
								direction_textfield.setText(" N ");
								}
							}
					
					else 
						{
							if(vertical_array[babyY][babyX+1]==0) //if right space is white  move right
								{
								image_array[babyY][babyX+=1].setIcon(baby);
								image_array[babyY][babyX-1].setIcon(white);
								compass_label.setIcon(east);
								direction_textfield.setText(" E ");
								movement=up;
								}
							else // moves baby down
								{
								image_array[babyY+=1][babyX].setIcon(baby);
								image_array[babyY-1][babyX].setIcon(white);
								compass_label.setIcon(south);
								direction_textfield.setText(" S ");
								}
							
						}
				}
				
					if(timer_count==1) 
						{
						timer = new javax.swing.Timer(50, this);
						timer.start();
						ticks=0;
						}
					else if(babyX==15) 
						{
						timer.stop();
						}
					timer_count++;
			}
	else// act button for horizontal layout 
		{
			
			//this first if is to check whether it has reached the right wall of the maze					
			if(babyY<13) 
				{
					image_array[babyY][babyX].setIcon(baby);
						
					//this if is to check whether the top wall of the maze is equal to 0 and if so moves right	
					if(babyX==0) 
						{
						movement=right;
						}
					//here it checks whether the bottom wall hasn't been reached if reached move left
					else if(babyX==15) 
						{
						movement=left;
						}
					
					//coding to make the baby move up if it hits a brick and down when it reaches the top and up when at the bottom
					if(movement==right) 
						{
							if(horizontal_array[babyY+1][babyX]==0) // check whether one step to the right is white 
								{
								image_array[babyY+=1][babyX].setIcon(baby);//replace image to the right with baby
								image_array[babyY-1][babyX].setIcon(white);//replace current position with white space
								compass_label.setIcon(south);
								direction_textfield.setText(" S ");
								}
							
							else
								{
								image_array[babyY][babyX+=1].setIcon(baby);//replaces up position with baby image making the baby move up
								image_array[babyY][babyX-1].setIcon(white);//places a white image where the baby was
								compass_label.setIcon(east);
								direction_textfield.setText(" E ");
								}
							
							
						}
					
					else 
						{
							if(horizontal_array[babyY+1][babyX]==0) //if right space is white move baby right
								{
								image_array[babyY+=1][babyX].setIcon(baby);
								image_array[babyY-1][babyX].setIcon(white);
								compass_label.setIcon(south);
								direction_textfield.setText(" S ");
								movement=right;
								}
							else 
								{
								image_array[babyY][babyX-=1].setIcon(baby);
								image_array[babyY][babyX+1].setIcon(white);
								compass_label.setIcon(west);
								direction_textfield.setText(" W ");
								}
						
						}
				}
				
				if(timer_count==1) 
						{
						timer = new javax.swing.Timer(50, this);
						timer.start();
						ticks=0;
						}
					else if(babyY==12) 
						{
						timer.stop();
						}
					timer_count++;
				
		}
					
		
		int squares =babyY*13+babyX;
		String sqr = String.valueOf(squares);
		square_textfield.setText(sqr);
		
			
						
		}
	
	//resets to default view				
	private void reset_method() 
		{
			//reseting the timer and run integers
			
			run=0;
					
			//initialising movement to up
			movement=up;
			
			//Reseting the baby to position(0,9)
			babyY = 9;
			babyX = 0;
			
			//reseting the right panel		
			Icon baby = new ImageIcon("images/baby2.png");
			Icon brick = new ImageIcon("images/bricks2.jpg");
			Icon white = new ImageIcon("images/white32x32.jpg");
			options_textfield.setText("  Vertical  ");
			int squares =babyY*13+babyX;
			String sqr = String.valueOf(squares);
			square_textfield.setText(sqr);
					
						
			//reseting layout to vertical layout		
			layout=v_layout;
					
			//re-arranging the maze to a vertical setup with the baby at the start position
			image_array[babyY][babyX].setIcon(baby);
					
			for(int i=0;i<13;i++)
			for(int j=0;j<16;j++) 
				{
					if (vertical_array[i][j]==1)
						{
						image_array[i][j].setIcon(brick);
						}
								
					else if(vertical_array[i][j]==0) 
						{
						image_array[i][j].setIcon(white);
						}
							
					else 
						{ 
						image_array[i][j].setIcon(baby);
						}
				}
						
						
			//Restarting the digital timer						
			timer.restart();
			ticks=0;
			nTimeDelay=0;
			timer_count=1;		
		}
					

	//method for run button		
	public void run_method() 
			//loop to be executed to reach end of maze
			{
			while(run<86) 
				{
				act_method();
				run++;
				}
			
			run=0;
				
				
			if(timer_count==1) 
			{
			timer = new javax.swing.Timer(50, this);
			timer.start();
			ticks=0;
			}
		else if((babyX==15)||(babyY==12)) 
			{
			timer.stop();
			}
		timer_count++;		
	}				
	//Slider Adjustments
	public void stateChanged(ChangeEvent e) 
			{
	        nTimeDelay = 100 - speed_slider.getValue();
	        timer.setDelay(nTimeDelay);
		    }	
}



