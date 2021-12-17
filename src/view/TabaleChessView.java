package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Location;
import model.TableChess;

public class TabaleChessView extends JFrame implements ActionListener {

	private JPanel contentPane;
	JButton[] btn = new JButton[154];
	JPanel panel;
	JPanel panel_1;
	TableChess tableChess;
	int[] hauCung = { 26, 27, 28, 37, 38, 39, 48, 49, 50, 103, 104, 105, 114, 115, 116, 125, 126, 127 };

	String[] arrayCapDo = { "1", "2", "3", "4", "5", "6" };
	String[] arrayCachThuc = { "Chơi hai người", "Minimax", "Minnimax Apha&Beta" };
	String[] arrayCachThuc2 = { "Minimax", "Minnimax Apha&Beta" };

	private JPanel panel_2;
	JLabel lbNotify;
	JButton btnUndo;
	JComboBox comboBoxCapDo, comboBoxCachThuc;
	JButton btnStart;

	String cachThuc = "";
	int luotDi = TableChess.RED;
	
	
	public TabaleChessView() {
		tableChess = new TableChess();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1051, 788);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(3, 3, 50 * 11, 20 * 14);

		panel_1 = new JPanel();
		panel_1.setBounds(3, 340, 50 * 11, 20 * 14);

		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_2.setBounds(10, 30, 555, 622);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		panel_2.add(panel_1);
		panel_2.add(panel);

		lbNotify = new JLabel();
		lbNotify.setHorizontalAlignment(SwingConstants.CENTER);
		lbNotify.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbNotify.setBounds(13, 288, 532, 44);
		panel_2.add(lbNotify);

		btnUndo = new JButton("Đi lại");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
				if(cachThuc.equals(arrayCachThuc[0])) {
					tableChess.undo();
					luotDi = (luotDi == TableChess.BLACK?TableChess.RED:TableChess.BLACK);

				}else {
					tableChess.undo();

					tableChess.undo();
//					


				}
				
				 if(tableChess.getSaveLocations().size() == 0) {
			        	btnUndo.setEnabled(false);
			        }else {
			        	btnUndo.setEnabled(true);

			        }
				restTableChess();
			}
		});
		btnUndo.setBounds(669, 95, 100, 23);
		contentPane.add(btnUndo);

		btnStart = new JButton("Bắt đầu");
		btnStart.setBounds(669, 30, 100, 23);
		contentPane.add(btnStart);
		btnStart.addActionListener(this);

		comboBoxCapDo = new JComboBox(arrayCapDo);
		comboBoxCapDo.setBounds(669, 210, 100, 23);
		contentPane.add(comboBoxCapDo);
		comboBoxCapDo.setVisible(false);

		comboBoxCachThuc = new JComboBox(arrayCachThuc);
		comboBoxCachThuc.setBounds(669, 142, 100, 23);
		contentPane.add(comboBoxCachThuc);
		 cachThuc = comboBoxCachThuc.getSelectedItem().toString();

        comboBoxCachThuc.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 cachThuc = comboBoxCachThuc.getSelectedItem().toString();
				 if(cachThuc.equals(arrayCachThuc[0])) {
						comboBoxCapDo.setVisible(false);

				 }else {
						comboBoxCapDo.setVisible(true);

				 }
				
			}
		});
        
        if(tableChess.getSaveLocations().size() == 0) {
        	btnUndo.setEnabled(false);
        }else {
        	btnUndo.setEnabled(true);

        }


		setVisible(true);
	}

	private void createTable() {
		
		
		panel.removeAll();
		panel.repaint();
		panel_1.removeAll();;
		panel_1.repaint();
		check = false;
		list = new ArrayList<Location>();
		luotDi = TableChess.RED;
		tableChess.createTableChess("r3k3r/9/9/9/9/9/9/9/9/3K5");
//		tableChess.createTableChess("rheakaehr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RHEAKAEHR");

		
		lbNotify.setText("Lượt của RED");
		int[] chess = tableChess.tableChess;
		panel.setLayout(new GridLayout(5, 9, 1, 1));
		panel_1.setLayout(new GridLayout(5, 9, 1, 1));

		int count = 0;
		for (int i = 0; i < btn.length; i++) {
			if (chess[i] != TableChess.EMPTY) {
				btn[i] = new JButton(TableChess.nameChess2[chess[i]]);
				btn[i].setFont(new Font("", 1, 18));
			} else
				btn[i] = new JButton();

			if (TableChess.PIECE_COLOR[chess[i]] == TableChess.RED) {
				btn[i].setForeground(Color.RED);
			} else {
				btn[i].setForeground(Color.BLACK);

			}
			btn[i].setBackground(Color.white);

			if (chess[i] != TableChess.OUT) {
				btn[i].addActionListener(this);
				if (count < 45) {
					panel.add(btn[i]);

				} else {
					panel_1.add(btn[i]);
				}
				count++;
			}

		}
		for (int i = 0; i < hauCung.length; i++) {
			btn[hauCung[i]].setBackground(Color.green);
		}

	}

	private void restTableChess() {
		int[] chess = tableChess.tableChess;
		 if(tableChess.getSaveLocations().size() == 0) {
	        	btnUndo.setEnabled(false);
	        }else {
	        	btnUndo.setEnabled(true);

	        }
		for (int i = 0; i < btn.length; i++) {
			btn[i].setBackground(Color.white);
			if (chess[i] != TableChess.EMPTY) {
				btn[i].setText(TableChess.nameChess2[chess[i]]);
				btn[i].setFont(new Font("", 1, 18));

			} else {
				btn[i].setText("");
			}

			if (TableChess.PIECE_COLOR[chess[i]] == TableChess.RED) {
				btn[i].setForeground(Color.RED);
			} else {
				btn[i].setForeground(Color.BLACK);

			}
		}
		for (int i = 0; i < hauCung.length; i++) {
			btn[hauCung[i]].setBackground(Color.green);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnStart) {
			String nameSt = btnStart.getText();
			
			if(nameSt.equals("Bắt đầu")) {
				createTable();
				if(cachThuc.equals(arrayCachThuc[0])) {
					comboBoxCapDo.setVisible(false);
					comboBoxCachThuc.setVisible(false);
				}else {

			        comboBoxCachThuc.removeItemAt(0);
				}
				btnStart.setText("Chơi Lại");
			}else {
				
				createTable();
			}
			
		}
		
		for (int i = 0; i < btn.length; i++) {
			if(e.getSource() == btn[i]) {
				if(cachThuc.equals(arrayCachThuc[0])) {	
					danhHaiNGuoi(i);
				}else {
					choiVoiMay(i);

				}
				break;
			}
		}

	}

	private void danhHaiNGuoi(int i) {
		restTableChess();
		String name = btn[i].getText();
		if(check) {
			if(list.size() >0 ) {
				for (Location location : list) {
					if(i == location.getPointerNew()) {
						if(tableChess.makeChess(location)) {
							int color = luotDi;
							luotDi = (luotDi == TableChess.BLACK?TableChess.RED:TableChess.BLACK);
                            String t = (luotDi == TableChess.BLACK?"Luot di cua Black":"Luot di cua Red");
                            lbNotify.setText(t);
                            if(tableChess.checkHetCo(luotDi)) {
                            	String benThang = (luotDi == TableChess.BLACK?"Bên RED Thắng":"Bên Black Thang");
                            	lbNotify.setText(benThang);
                            	break;
                            }
                            
                            
                            if(tableChess.checkBiTanCong(tableChess.pointerKing(luotDi), color)) {
                            	String benChieu = ( (luotDi == TableChess.BLACK?"Chieu Tuong BLACK":"Chieu tuong RED"));
                            	 lbNotify.setText(benChieu);
                            }
							
						}else {
							lbNotify.setText("Đang Bị chiếu");

						}
					
						break;
					}
				}
			}
			check = false;
			list = new ArrayList<Location>();
			restTableChess();


		}else {
			if(!name.trim().isEmpty()) {
				choiHaiNGuoi(i,getIndexChess(name));
			}else {
				check = false;
				
				list = new ArrayList<Location>();
			}
		}
		
	}

	

     private void choiVoiMay(int i) {

 		String name = btn[i].getText();
 		
 		if(check) {
 			if(list.size() >0 ) {
 				for (Location location : list) {
 					if(i == location.getPointerNew()) {
 						if(dichuyen(location)) {
 								Thread thread2 = new Thread(new Runnable() {
 									
 									@Override
 									public void run() {
 										int depth = Integer.parseInt(comboBoxCapDo.getSelectedItem()+"");
 										int thuatToan = comboBoxCachThuc.getSelectedIndex();
 										
// 										System.out.println(depth);
// 										System.out.println(thuatToan);
 										
 										Location location2 = tableChess.getPointerMinimax(depth,thuatToan);
 										
 										if(location2 != null) {
 			 					     	   restTableChess();

 			 	 						   dichuyen(location2);

 										}
 										
 									}
 								});
 		 						thread2.start();

 							break;
 						}
 					
 						break;
 					}
 				}
 			}
 			check = false;
 			list = new ArrayList<Location>();
 			restTableChess();


 		}else {
 			if(!name.trim().isEmpty()) {
 				choiHaiNGuoi(i,getIndexChess(name));
 			}else {
 				check = false;
 				
 				list = new ArrayList<Location>();
 			}
 		}
 		

		
	}
     
     private boolean dichuyen(Location location) {
    	 if(tableChess.makeChess(location)) {
				int color = luotDi;
				luotDi = (luotDi == TableChess.BLACK?TableChess.RED:TableChess.BLACK);
              String t = (luotDi == TableChess.BLACK?"Luot di cua Black":"Luot di cua Red");
              lbNotify.setText(t);
              if(tableChess.checkHetCo(luotDi)) {
              	String benThang = (luotDi == TableChess.BLACK?"Bên RED Thắng":"Bên Black Thang");
              	lbNotify.setText(benThang);
				restTableChess();

              	return false;
              }
              
              
              if(tableChess.checkBiTanCong(tableChess.pointerKing(luotDi), color)) {
              	String benChieu = ( (luotDi == TableChess.BLACK?"Chieu Tuong BLACK":"Chieu tuong RED"));
              	 lbNotify.setText(benChieu);
              }
              
            
				restTableChess();

              return true;
				
			}else {
				lbNotify.setText("Đang Bị chiếu");
				return false;
			}
     }
     
     
     boolean check = false;
     List<Location> list = new ArrayList<Location>();
	private void choiHaiNGuoi(int i,int target) {
		if(TableChess.PIECE_COLOR[target] == luotDi) {
			list = tableChess.generateMoves(i, target);
			check = true;
			for (int j = 0; j < list.size(); j++) {
				btn[list.get(j).getPointerNew()].setBackground(Color.yellow);
			}
		}
		
		
		
//		
	
		
		
	}
	
	private int getIndexChess(String s) {
		
		for (int i = 0; i < TableChess.nameChess2.length; i++) {
			if(TableChess.nameChess2[i].equals(s)) {
				return i;
			}
		}
	    return 0;
	}

//	private boolean checkNuocDiHopLe(int square, List<Location> list) {
//		for (int j = 0; j < list.size(); j++) {
//			Location l = list.get(j);
//			if (square == l.getNewLocation()) {
//				return true;
//			}
//		}
//		return false;
//	}

	public static void main(String[] args) {
		new TabaleChessView();

	}
}
