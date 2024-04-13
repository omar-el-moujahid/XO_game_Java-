package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.border.EmptyBorder;


public class XO extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel ; 
	private JPanel panel_top ;
	private JPanel panel_top1 ;
	private JPanel panel_top2 ;
	private JPanel panel_botuum ;
	private int scoureX ;
	private JLabel scoreLabelX; 
	private JLabel turn; 
	private int scoureO ;
	private JLabel scoreLabelO; 
	private boolean X_turn=true;
	private int margeX=50;
	private int margeY=50;
	private int width=300;
	private int hight=300;
	private String playerX="X";
	private String playerO="O";
	private int width_celulle=width/3;
	private int hight_celulle=hight/3;
    private JButton[][] cellbotun = new JButton[3][3];
    Timer horloge ;
	public XO() {
		this.setTitle("XO Game ");
		this.setSize(margeX *2 + width , margeY *2 + hight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		scoureX=0;
		scoureO=0;
		scoreLabelX = new JLabel("Scoure X :"+scoureX );
		turn = new JLabel( playerX + "'s turn" );
		scoreLabelO = new JLabel(scoureO + ": Scoure O" );
		Font font = new Font("Verdana", Font.PLAIN, 14); // Using Verdana font with size 14
		scoreLabelO.setFont(font);
		scoreLabelX.setFont(font);
		turn.setFont(font);
		panel_top = new JPanel( new GridLayout(2,1,0,0)) {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
			}};
			
			panel_top1 = new JPanel( ) {
				@Override
				public void paint(Graphics g) {
					super.paint(g);
				}};
				panel_top2 = new JPanel(   new FlowLayout(FlowLayout.CENTER , 0,0)) {
					@Override
					public void paint(Graphics g) {
						super.paint(g);
					}};			
			
						
		panel_botuum = new JPanel( new GridLayout(3,3,0,0)) {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
			}};
			panel = new JPanel( new FlowLayout()) {
				@Override
				public void paint(Graphics g) {
					super.paint(g);
				}};
			
				this.add(panel);
				this.setVisible(true);
				
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	cellbotun[i][j] = new JButton();
            	final int row = i;
                final int col = j;
            	cellbotun[i][j].addActionListener( new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(cellbotun[row][col].getText().equals("")) {
							if( X_turn) {
							cellbotun[row][col].setText("X");
							cellbotun[row][col].setFont( new Font("Arial", Font.PLAIN, 40));
							cellbotun[row][col].setForeground(Color.red);
							X_turn=false;
							turn.setText(playerO + "'s turn");
							if (checkWin(playerX)) {
								scoureX++;
								scoreLabelX.setText("Scoure X :"+scoureX );
								JOptionPane.showMessageDialog(null, "Player "+ playerX +" wins!");
								int option = JOptionPane.showConfirmDialog(null, " Do you want to repeat the game?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
								if (option == JOptionPane.YES_OPTION) {
									resetGame();
								} else {
								    XO.this.dispose(); // Close the current frame
								}
								
	                        }
							
							}
							else{
								cellbotun[row][col].setText("O");
								cellbotun[row][col].setFont( new Font("Arial", Font.PLAIN, 40));
								cellbotun[row][col].setForeground(Color.blue);
								X_turn=true;
								turn.setText(playerX + " 's turn");
								if (checkWin(playerO)) {
									scoureO++;
									scoreLabelO.setText(scoureO + ": Scoure O" );
		                            JOptionPane.showMessageDialog(null, "Player "+ playerO +" wins!");
		                        }
								}
							
						}
						
					}

					
				});
            	panel_botuum.add(cellbotun[i][j]);
            }
            
            
        }
		
		EmptyBorder margin = new EmptyBorder(0, 20, 0, 20); // Adjust margins as needed
		panel_botuum.setBorder(margin);
		this.add(panel);
		this.setVisible(true);
		panel_top.setPreferredSize(new Dimension(panel.getWidth(), panel.getWidth()/3));
		panel_botuum.setPreferredSize(new Dimension(panel.getWidth(), panel.getWidth()/3));
		System.out.println(panel.getWidth());
        panel_top1.add(scoreLabelX);
        panel_top1.add(scoreLabelO);
        panel_top2.add(turn);
        panel_top.add(panel_top1);
        panel_top.add(panel_top2);
        panel.add(panel_top);
        panel.add(panel_botuum);
        this.setVisible(true);
        panel_top.setPreferredSize(new Dimension(panel.getWidth(), panel.getWidth()/3));
		panel_botuum.setPreferredSize(new Dimension(panel.getWidth(), panel.getWidth()/3));
		this.setVisible(true);
	
	}
	
	
	private void resetGame() {
	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            cellbotun[i][j].setText(""); 
	        }
	    }
	    turn.setText(playerX + "'s turn");
	    X_turn = true;
	}
	private boolean checkWin(String symbol) {
        for (int i = 0; i < 3; i++) {
            if (cellbotun[i][0].getText().equals(symbol) && cellbotun[i][1].getText().equals(symbol)
                    && cellbotun[i][2].getText().equals(symbol)) {
                return true; // Horizontal win
            }
            if (cellbotun[0][i].getText().equals(symbol) && cellbotun[1][i].getText().equals(symbol)
                    && cellbotun[2][i].getText().equals(symbol)) {
                return true; // Vertical win
            }
        }
        if (cellbotun[0][0].getText().equals(symbol) && cellbotun[1][1].getText().equals(symbol)
                && cellbotun[2][2].getText().equals(symbol)) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (cellbotun[0][2].getText().equals(symbol) && cellbotun[1][1].getText().equals(symbol)
                && cellbotun[2][0].getText().equals(symbol)) {
            return true; // Diagonal win (top-right to bottom-left)
        }
        return false;
    } 
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		XO xo = new XO();

	}

}
