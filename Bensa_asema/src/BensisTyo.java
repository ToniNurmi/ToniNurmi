import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class BensisTyo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton Tilaus1;
	private JButton Tilaus2;
	private JButton Tilaus3;
	static JProgressBar progressBar;
	static JProgressBar progressBar_1;
	static JProgressBar progressBar3;
	private JLabel lblNewLabel;
	private JLabel lble;
	private JLabel lblDiesel;

	private Taydennys1 taydennys1 = new Taydennys1();
	private Taydennys2 taydennys2 = new Taydennys2();
	private Taydennys3 taydennys3 = new Taydennys3();
	public static JLabel textArea;
	public static JLabel textArea_1;
	public static JLabel textArea_2;
	private JLabel lblNewLabel_1;

	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton tyhjennaNappi;
	static JLabel vahissa95;
	static JLabel vahissa98;
	static JLabel vahissad;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem tyhjennaMenu;
	private JMenuItem poistuMenu;
	private JLabel lblNewLabel_5;
	
	String e95;
	String e98;
	String diesel;

	String maarat = "src/Resources/tankeissa_bensaa.txt";

	/**
	 * Create the frame.
	 */
	public BensisTyo() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				Taydennys1.lueTiedostosta(maarat);
				Taydennys2.lueTiedostosta(maarat);
				Taydennys3.lueTiedostosta(maarat);
				lueMaarat(maarat);
				if (Integer.parseInt(Taydennys1.maara95) <= 250 && (Integer.parseInt(Taydennys1.maara95) >= 1)) {
					BensisTyo.vahissa95.setText("TANKKI 95E MELKEIN TYHJÄ");
					progressBar.setForeground(Color.ORANGE);
				} else if (Integer.parseInt(Taydennys1.maara95) == 0) {
					BensisTyo.vahissa95.setText("TANKKI 95E TYHJÄ");
				} else {
					BensisTyo.vahissa95.setText("");
					progressBar.setForeground(Color.GREEN);
				}
				// TÄHÄN LOPPUU 95

				if (Integer.parseInt(Taydennys2.maara98) <= 250 && (Integer.parseInt(Taydennys2.maara98) >= 1)) {
					BensisTyo.vahissa98.setText("TANKKI 98E MELKEIN TYHJÄ");
					progressBar_1.setForeground(Color.ORANGE);
				} else if (Integer.parseInt(Taydennys2.maara98) == 0) {
					BensisTyo.vahissa98.setText("TANKKI 98E TYHJÄ");
				} else {
					BensisTyo.vahissa98.setText("");
					progressBar_1.setForeground(Color.GREEN);
				}
				// TÄHÄN LOPPUU 98

				if (Integer.parseInt(Taydennys3.maarad) <= 250 && (Integer.parseInt(Taydennys3.maarad) >= 1)) {
					BensisTyo.vahissad.setText("TANKKI DIESEL MELKEIN TYHJÄ");
					progressBar3.setForeground(Color.ORANGE);
				} else if (Integer.parseInt(Taydennys3.maarad) == 0) {
					BensisTyo.vahissad.setText("TANKKI DIESEL TYHJÄ");
				} else {
					BensisTyo.vahissad.setText("");
					progressBar3.setForeground(Color.GREEN);
				} // DIESEL LOPPUU

				// HARMAAT NAPIT ALKAAA
				if (Integer.parseInt(Taydennys1.maara95) == 1000) {
					Tilaus1.setEnabled(false);
				} else {
					Tilaus1.setEnabled(true);
				}

				if (Integer.parseInt(Taydennys2.maara98) == 1000) {
					Tilaus2.setEnabled(false);
				} else {
					Tilaus2.setEnabled(true);
				}

				if (Integer.parseInt(Taydennys3.maarad) == 1000) {
					Tilaus3.setEnabled(false);
				} else {
					Tilaus3.setEnabled(true);
				} // Jos kaikki tankit on tyhjiä, ei voi tyhjentää
				if (Integer.parseInt(Taydennys1.maara95) == 0 && Integer.parseInt(Taydennys2.maara98) == 0
						&& Integer.parseInt(Taydennys3.maarad) == 0) {
					tyhjennaNappi.setEnabled(false);
					tyhjennaMenu.setEnabled(false);
				} else {
					tyhjennaNappi.setEnabled(true);
					tyhjennaMenu.setEnabled(true);
				}

			}

			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Suljetaanko ikkuna?", "Varoitus!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					BensisTyo.this.setVisible(false);
				}
				if (result == JOptionPane.NO_OPTION) {
				}
			}
		});
		setTitle("Tankit");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BensisTyo.class.getResource("/Img/Bensis kuva.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 640, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Tilaus1 = new JButton("Tilaa t\u00E4ydennys");
		Tilaus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				taydennys1.dispose();
				taydennys1 = new Taydennys1();
				taydennys1.setModal(true);
				taydennys1.setVisible(true);
			}
		});
		Tilaus1.setBounds(474, 56, 128, 40);
		contentPane.add(Tilaus1);

		Tilaus2 = new JButton("Tilaa t\u00E4ydennys");
		Tilaus2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				taydennys2.dispose();
				taydennys2 = new Taydennys2();
				taydennys2.setModal(true);
				taydennys2.setVisible(true);
			}
		});
		Tilaus2.setBounds(474, 122, 128, 40);
		contentPane.add(Tilaus2);

		Tilaus3 = new JButton("Tilaa t\u00E4ydennys");
		Tilaus3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				taydennys3.dispose();
				taydennys3 = new Taydennys3();
				taydennys3.setModal(true);
				taydennys3.setVisible(true);
			}
		});
		Tilaus3.setBounds(474, 188, 128, 40);
		contentPane.add(Tilaus3);

		progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setMaximum(1000);
		progressBar.setValue(Integer.parseInt(Taydennys1.maara95));
		progressBar.setBounds(131, 56, 229, 40);
		contentPane.add(progressBar);

		progressBar_1 = new JProgressBar();
		progressBar_1.setMaximum(1000);
		progressBar_1.setForeground(Color.GREEN);
		progressBar_1.setValue(Integer.parseInt(Taydennys2.maara98));
		progressBar_1.setBounds(131, 122, 229, 40);
		contentPane.add(progressBar_1);

		progressBar3 = new JProgressBar();
		progressBar3.setMaximum(1000);
		progressBar3.setValue(Integer.parseInt(Taydennys3.maarad));
		progressBar3.setForeground(Color.GREEN);
		progressBar3.setBounds(131, 188, 229, 40);
		contentPane.add(progressBar3);

		lblNewLabel = new JLabel("95E10");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(63, 56, 56, 40);
		contentPane.add(lblNewLabel);

		lble = new JLabel("98E");
		lble.setFont(new Font("Tahoma", Font.BOLD, 17));
		lble.setBounds(63, 122, 56, 40);
		contentPane.add(lble);

		lblDiesel = new JLabel("Diesel");
		lblDiesel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDiesel.setBounds(63, 188, 56, 40);
		contentPane.add(lblDiesel);

		textArea = new JLabel();
		textArea.setFont(new Font("Tahoma", Font.BOLD, 16));
		textArea.setBounds(365, 44, 73, 39);
		contentPane.add(textArea);

		textArea_1 = new JLabel();
		textArea_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textArea_1.setBounds(365, 109, 73, 39);
		contentPane.add(textArea_1);

		textArea_2 = new JLabel();
		textArea_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		textArea_2.setBounds(365, 177, 73, 39);
		contentPane.add(textArea_2);

		lblNewLabel_1 = new JLabel("Tankkien maksimi kapasiteetti 1000l");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(63, 25, 328, 30);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("/1000 l");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(390, 72, 76, 30);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("/1000 l");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(390, 141, 73, 26);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("/1000 l");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(390, 205, 66, 30);
		contentPane.add(lblNewLabel_4);

		tyhjennaNappi = new JButton("Tyhjenn\u00E4 tankit"); // TANKIT TYHJENNETÄÄ
		tyhjennaNappi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Oletko varma että haluat tyhjentää tankit?",
						"Varoitus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					Taydennys1.maara95 = "" + 0;
					Taydennys2.maara98 = "" + 0;
					Taydennys3.maarad = "" + 0;
					Taydennys1.kirjoitaTiedostoon(Taydennys1.maara95, Taydennys2.maara98, Taydennys3.maarad, maarat);
					textArea.setText(Taydennys1.maara95 + " l");
					textArea_1.setText(Taydennys2.maara98 + " l");
					textArea_2.setText(Taydennys3.maarad + " l");
					BensisTyo.progressBar.setValue(Integer.parseInt(Taydennys1.maara95));
					BensisTyo.progressBar_1.setValue(Integer.parseInt(Taydennys2.maara98));
					BensisTyo.progressBar3.setValue(Integer.parseInt(Taydennys3.maarad));
				}
			}
		});
		tyhjennaNappi.setFont(new Font("Tahoma", Font.BOLD, 14));
		tyhjennaNappi.setBounds(390, 251, 212, 40);
		contentPane.add(tyhjennaNappi);

		vahissa95 = new JLabel("");
		vahissa95.setForeground(Color.RED);
		vahissa95.setBounds(63, 241, 188, 26);
		contentPane.add(vahissa95);

		vahissa98 = new JLabel("");
		vahissa98.setForeground(Color.RED);
		vahissa98.setBounds(63, 264, 188, 26);
		contentPane.add(vahissa98);

		vahissad = new JLabel("");
		vahissad.setForeground(Color.RED);
		vahissad.setBounds(63, 286, 188, 26);
		contentPane.add(vahissad);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 39, 21);
		contentPane.add(menuBar);

		mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		tyhjennaMenu = new JMenuItem("Tyhjenn\u00E4 tankit");
		tyhjennaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Oletko varma että haluat tyhjentää tankit?",
						"Varoitus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					Taydennys1.maara95 = "" + 0;
					Taydennys2.maara98 = "" + 0;
					Taydennys3.maarad = "" + 0;
					Taydennys1.kirjoitaTiedostoon(Taydennys1.maara95, Taydennys2.maara98, Taydennys3.maarad, maarat);
					textArea.setText(Taydennys1.maara95 + " l");
					textArea_1.setText(Taydennys2.maara98 + " l");
					textArea_2.setText(Taydennys3.maarad + " l");
					BensisTyo.progressBar.setValue(Integer.parseInt(Taydennys1.maara95));
					BensisTyo.progressBar_1.setValue(Integer.parseInt(Taydennys2.maara98));
					BensisTyo.progressBar3.setValue(Integer.parseInt(Taydennys3.maarad));
				}
			}
		});
		mnNewMenu.add(tyhjennaMenu);

		poistuMenu = new JMenuItem("Poistu");
		poistuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BensisTyo.this.setVisible(false);
			}
		});
		mnNewMenu.add(poistuMenu);
		
		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(BensisTyo.class.getResource("/img/jakari.png")));
		lblNewLabel_5.setBounds(-2, -1, 636, 333);
		contentPane.add(lblNewLabel_5);

		lueMaarat(maarat);
	}

	// LUKEE BENSOJEN MÄÄRÄT TEKSTI TIEDOSTOSTA
	public void lueMaarat(String maarat) {

		try {
			FileReader freader = new FileReader(maarat);
			BufferedReader br = new BufferedReader(freader);
			e95 = br.readLine();
			e95 = kasitteleRivi(e95);
			textArea.setText(e95 + " l");

			e98 = br.readLine();
			e98 = kasitteleRivi(e98);
			textArea_1.setText(e98 + " l");

			diesel = br.readLine();
			diesel = kasitteleRivi(diesel);
			textArea_2.setText(diesel + " l");

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// KÄSITTELEE RIVIT TEKSTI TIEDOSTOSTA
	public String kasitteleRivi(String txt) {
		txt = txt.replace(" ", "");
		String[] maarat = txt.split("=");

		return maarat[1];
	}
}
