

package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;



/**
 *a gui that represent the game.
 * ***does not support a resize ability yet*** 
 * @author ofra and shira
 */
public class MyFrame extends JFrame implements MouseListener{

	private Map map;
	private Container window;
	private JPanel _panel;
	private Graphics _paper;
	private int isGamer;
	private Game game;
	private int id=1;
	private boolean stop;

	private Path path;

	private boolean isRun;
	private MenuBar menuBar;
	public static Image scaledImage;

	public MyFrame(Map map){
		super("Map Demo"); //setTitle("Map Counter");  // "super" Frame sets its title
		//	Moves and resizes this component. 
		//	The new location of the top-left corner is  specified by x and y, 
		//	and the new size is specified by width and height
		//	setBounds(x,y,width,height)
		setMap(map);
		//      Exit the program when the close-window button clicked
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		game=new Game(map);
		this.setBounds(0,0,1300,600); //setSize(1433,700);        // "super" Frame sets its initial window size
		createGui();
		setVisible(true);
		setResizable(false);

	}


	public void createGui(){ 

		//	A Container is a component  that can contain other GUI components
		window = this.getContentPane(); 
		window.setLayout(new FlowLayout());

		//Add "panel" to be used for drawing            
		_panel = new JPanel();
		Dimension d= new Dimension(getWidth(),getHeight());
		_panel.setPreferredSize(d);		               
		window.add(_panel);

		// A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
		// the menu-bar
		Menu menu, menu2,kml,csv,clearGame;         // each menu in the menu-bar
		MenuItem fruit,run, packman,save,saveKML,clear; // an item in a menu
		menuBar = new MenuBar();

		clearGame= new Menu("clear game");
		menuBar.add(clearGame);
		clear= new MenuItem("clear");
		clearGame.add(clear);
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				game= new Game(getMap());

			}
		});



		csv= new Menu("saved games");
		menuBar.add(csv);

		Iterator<String> it=read("data","csv","game").iterator();
		while(it.hasNext()) {
			String s= it.next();
			MenuItem m= new MenuItem(s);
			csv.add(m);
			m.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					repaint();

					game= new Game(s,map);
					isGamer=4;
					//stop=true;

				}
			});
		}
		Iterator<String> it0=read(".","csv","game").iterator();
		while(it0.hasNext()) {
			String s= it0.next();
			MenuItem m= new MenuItem(s);
			csv.add(m);
			m.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					repaint();

					game= new Game(s,map);
					isGamer=4;
					//stop=true;

				}
			});
		}



		// First Menu
		menu = new Menu("Play");
		//menu.setMnemonic(KeyEvent.VK_A);  // alt short-cut key
		menuBar.add(menu);  // the menu-bar adds this menu


		save= new MenuItem("Save game");
		menu.add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer=5;
				SimpleJButton(csv);


			}
		});





		menu2= new Menu("Run");
		menuBar.add(menu2);
		run= new MenuItem("Run");
		menu2.add(run);
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
				isGamer=3;
				isRun=true;

			}
		});

		saveKML= new MenuItem("Save path");
		menu2.add(saveKML);
		saveKML.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer=6;

				SimpleJButton(menu2);

			}
		});
		fruit = new MenuItem("fruit");
		menu.add(fruit); // the menu adds this item
		fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 1;
			}
		});
		packman = new MenuItem("packman");
		menu.add(packman); // the menu adds this item
		packman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 2;
			}
		}); 






		setMenuBar(menuBar);  // "this" JFrame sets its menu-bar
		JLabel labelMap = new JLabel();
		//labelMap.setIcon(imgBck);
		_panel.add(labelMap);

		// panel (source) fires the MouseEvent.
		//panel adds "this" object as a MouseEvent listener.
		_panel.addMouseListener(this);


	}

	protected void paintElement() {
		//	The method getGraphics is called to obtain a Graphics object

		_paper = _panel.getGraphics();
		if(isGamer!=3) {
			//			getMap().setX(12);
			//			getMap().setY(10);
			//			getMap().setWidth(1300-20);
			//			getMap().setHeight(600-60);
			Iterator<Fruit> fruits= game.getFruits().iterator();
			Iterator<Packman> packmans= game.getPackmans().iterator();
			while(packmans.hasNext()) {
				_paper.setColor(Color.yellow);
				Packman p=packmans.next();
				_paper.fillOval(p.getLocation().getX()-10,p.getLocation().getY()-10,20,20);
				_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
				_paper.drawString("("+Double.toString(p.getLocationGPS().get_x()).substring(0, 7)+", "
						+Double.toString(p.getLocationGPS().get_y()).substring(0, 7)+")"
						,p.getLocation().getX()-10,p.getLocation().getY()-10);
			}
			while(fruits.hasNext()) {
				_paper.setColor(Color.RED);
				Fruit f=fruits.next();
				_paper.fillOval(f.getLocation().getX()-5,f.getLocation().getY()-5,10,10);

				_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
				_paper.drawString("("+Double.toString(f.getLocationGPS().get_x()).substring(0, 7)+", "
						+Double.toString(f.getLocationGPS().get_y()).substring(0, 7)+")"
						,f.getLocation().getX()-10,f.getLocation().getY()-10);
			}
		}
		if(isGamer==3) {
			ShortestPathAlgo algo= new ShortestPathAlgo(game);

			path= algo.forGPS();
			System.out.println("the time it took:"+(path.getData().getTime()-10));
			Iterator<Packman> packmans= game.getPackmans().iterator();

			while(packmans.hasNext()) {
				_paper.setColor(Color.yellow);
				Packman p= new Packman(packmans.next());

				_paper.fillOval(p.getLocation().getX()-10,p.getLocation().getY()-10,20,20);

				Iterator<Fruit> fruits= p.getDataP().getEat().iterator();
				Iterator <Fruit> next= p.getDataP().getEat().iterator();
				if(next.hasNext()) {
					Fruit first= next.next();
					_paper.setColor(Color.BLACK);
					_paper.drawLine(p.getLocation().getX(), p.getLocation().getY(), first.getLocation().getX(),first.getLocation().getY());
				}
				while(fruits.hasNext()) {
					_paper.setColor(Color.RED);
					Fruit f=fruits.next();
					_paper.fillOval(f.getLocation().getX()-5,f.getLocation().getY()-5,10,10);

					if(next.hasNext()) {
						_paper.setColor(Color.BLACK);
						Fruit n= next.next();
						_paper.drawLine(f.getLocation().getX(), f.getLocation().getY(), n.getLocation().getX(),n.getLocation().getY());
					}
				}
			}
		}
	}


	public void paint(Graphics g) {
		//g.clearRect(-8, 0, map.getMyImage().getWidth(), map.getMyImage().getHeight());
		//repaint();
		//		scaledImage = map.getMyImage().getScaledInstance(this.getWidth()-20,this.getHeight()-60,Image.SCALE_SMOOTH);
		//		g.drawImage(scaledImage, 10, 50, this);

		Image temp=getMap().getMyImage().getScaledInstance(this.getWidth()-5, this.getHeight()-50,getMap().getMyImage().SCALE_SMOOTH);
		g.drawImage(temp, 0, 45,null);

		//		


		paintElement();

	}


	@Override
	public void mousePressed(MouseEvent event) {
		if(isGamer==1&&!stop ) {
			Fruit f= new Fruit(event.getX(), event.getY(),map,id++);
			game.getFruits().add(f);
		}
		if(isGamer==2&&!stop) {
			Packman p= new Packman(event.getX(),event.getY(),map,id++);
			game.getPackmans().add(p);
		}

		repaint();


	}

	public void SimpleJButton(Menu csv){    
		JFrame f=new JFrame("Button Example"); 
		//submit button
		JButton b=new JButton("Submit");    
		b.setBounds(100,100,140, 40);    
		//enter name label
		JLabel label = new JLabel();		
		label.setText("file name:");
		label.setBounds(10, 10, 100, 100);
		//empty label which will show event after button clicked
		JLabel label1 = new JLabel();
		label1.setBounds(10, 110, 200, 100);
		//textfield to enter name
		JTextField textfield= new JTextField();
		textfield.setBounds(110, 50, 130, 30);
		//add to frame
		f.add(label1);
		f.add(textfield);
		f.add(label);
		f.add(b);    
		f.setSize(300,300);    
		f.setLayout(null);    
		f.setVisible(true);    

		//action listener
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isGamer==5) {
					getGame().toCSV("game_"+textfield.getText());
					label1.setText("The file saved in this folder");
					Iterator<String> it0=read(".","csv","game").iterator();
					while(it0.hasNext()) {
						String s= it0.next();
						MenuItem m= new MenuItem(s);
						csv.add(m);
						m.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								repaint();

								game= new Game(s,map);
								isGamer=4;
								//stop=true;

							}
						});
					}

				}
				if(isGamer==6) {
					if(isRun==true) {
						new Path2KML(getPath(), "Path_"+textfield.getText());
						label1.setText("The file saved in this folder");	
					}
					else {
						label1.setText("please run first");	


					}

				}

			}         
		});

	}         
	// Not Used, but need to provide an empty body for compilation
	public void mouseReleased(MouseEvent event){}
	public void mouseClicked(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}

	public Map getMap() {
		return map;
	}
	private void setMap(Map map) {
		this.map = map ;
	}
	public Container getWindow() {
		return window;
	}
	private void setWindow(Container window) {
		this.window = window;
	}
	public JPanel get_panel() {
		return _panel;
	}
	private void set_panel(JPanel _panel) {
		this._panel = _panel;
	}
	public Graphics get_paper() {
		return _paper;
	}
	private void set_paper(Graphics _paper) {
		this._paper = _paper;
	}

	public int getIsGamer() {
		return isGamer;
	}
	private void setIsGamer(int isGamer) {
		this.isGamer = isGamer;
	}
	public Game getGame() {
		return game;
	}

	private  ArrayList<String> read(String parentDirectory,String type, String startWith){
		File[] filesInDirectory = new File(parentDirectory).listFiles();
		ArrayList<String> s= new ArrayList<String>();
		for(File f : filesInDirectory){
			if(f.isDirectory()){
				read(f.getAbsolutePath(),type,startWith);
			}
			String filePath = f.getAbsolutePath();
			String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());

			if(type.equals(fileExtenstion) &&filePath.contains(startWith)){
				//add to a list or array

				s.add(filePath);
			}

		} 

		return s;
	}


	public int getId() {
		return id;
	}


	public boolean isStop() {
		return stop;
	}


	public Path getPath() {
		return path;
	}




}

