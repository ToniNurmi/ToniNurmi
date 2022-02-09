import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PaaIkkuna_Hinnat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JLabel e95Hintalbl;
	private JLabel e98Hintalbl;
	private JLabel dieselHintalbl;

	private JButton btnPaivita95;
	private JButton btnPaivita98;
	private JButton btnPaivitaD;

	private JTextField text95;
	private JTextField text98;
	private JTextField textDiesel;

	private JLabel lblEuro1;
	private JLabel lblEuro2;
	private JLabel lblEuro3;

	public JLabel e95Hinta;
	public JLabel e98Hinta;
	public JLabel dHinta;
	private BufferedReader br;
	
	private JCheckBox chkSuhde;

	private JTextField textMainos;
	private JLabel lblMainos;
	private JButton btnMainos;
	
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem poistuMenu;
	private JMenuItem mainosMenu;
	private JButton btnNewButton;

	public static JLabel lblTeksti;

	private Mainostaulu mainostaulu = new Mainostaulu();

	String mainos = "src/Resources/mainosteksti.txt";
	String mainosT;
	String hinnat = "src/Resources/hinnat.txt";
	String hinta95;
	String hinta98;
	String hintaD;

	/**
	 * Create the frame.
	 */

	public PaaIkkuna_Hinnat() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Suljetaanko ohjelma?", "Varoitus!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					text95.setText("");
					text98.setText("");
					textDiesel.setText("");
					textMainos.setText("");
					PaaIkkuna_Hinnat.this.setVisible(false);
				}
				if (result == JOptionPane.NO_OPTION) {
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(PaaIkkuna_Hinnat.class.getResource("/img/Bensis kuva.png")));
		

		setResizable(false);
		setTitle("Hinnat");
		setBounds(100, 100, 640, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		e95Hintalbl = new JLabel("95E Hinta:");
		e95Hintalbl.setBounds(75, 54, 96, 14);
		contentPane.add(e95Hintalbl);

		e98Hintalbl = new JLabel("98E Hinta:");
		e98Hintalbl.setBounds(75, 90, 96, 14);
		contentPane.add(e98Hintalbl);

		dieselHintalbl = new JLabel("Diesel Hinta:");
		dieselHintalbl.setBounds(75, 127, 96, 14);
		contentPane.add(dieselHintalbl);

		text95 = new JTextField();
		text95.setToolTipText("K\u00E4yt\u00E4 \".\" \u00E4l\u00E4k\u00E4 kirjoita \"\u20AC\"");
		text95.setBounds(315, 51, 107, 20);
		contentPane.add(text95);

		text98 = new JTextField();
		text98.setToolTipText("K\u00E4yt\u00E4 \".\" \u00E4l\u00E4k\u00E4 kirjoita \"\u20AC\"");
		text98.setBounds(315, 87, 107, 20);
		contentPane.add(text98);

		textDiesel = new JTextField();
		textDiesel.setToolTipText("K\u00E4yt\u00E4 \".\" \u00E4l\u00E4k\u00E4 kirjoita \"\u20AC\"");
		textDiesel.setBounds(315, 124, 107, 20);
		contentPane.add(textDiesel);

		btnPaivita95 = new JButton("P\u00E4ivit\u00E4 hinta");
		btnPaivita95.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					int result = JOptionPane.showConfirmDialog(null, "Hyväksy hinnanmuutos!", "Tämä on boxi.",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (result == JOptionPane.OK_OPTION) {
						hinta95 = "" + Double.parseDouble(text95.getText());
						e95Hinta.setText(hinta95 + "€");
						
						if (chkSuhde.isSelected()) {	
							double double98 = Double.parseDouble(text95.getText())  * 1.0682;
							double doubleD = Double.parseDouble(text95.getText()) / 1.0739;
							
							hinta98 = String.valueOf(round(double98,3));
							e98Hinta.setText(hinta98 + "€");

							hintaD = String.valueOf(round(doubleD,3));
							dHinta.setText(hintaD + "€");
							
							kirjoitaTiedostoon(hinta95, hinta98, hintaD, hinnat);
							text95.setText("");
						} else {
							kirjoitaTiedostoon(hinta95, hinta98, hintaD, hinnat);
							text95.setText("");
						}
					}

					if (result == JOptionPane.NO_OPTION) {
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Virheellinen syöte!");
					text95.setText("");
				}
			}
		});
		btnPaivita95.setBounds(456, 50, 107, 23);
		contentPane.add(btnPaivita95);

		btnPaivita98 = new JButton("P\u00E4ivit\u00E4 hinta");
		btnPaivita98.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					int result = JOptionPane.showConfirmDialog(null, "Hyväksy hinnanmuutos!", "Tämä on boxi.",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (result == JOptionPane.OK_OPTION) {
						hinta98 = "" + Double.parseDouble(text98.getText());
						e98Hinta.setText(hinta98 + "€");
						
						if (chkSuhde.isSelected()) {
							double double95 = Double.parseDouble(text98.getText()) / 1.0682;
							double doubleD = Double.parseDouble(text98.getText()) / 1.1471;
							
							hinta95 = String.valueOf(round(double95,3));
							e95Hinta.setText(hinta95 + "€");
							
							hintaD = String.valueOf(round(doubleD,3));
							dHinta.setText(hintaD + "€");
							
							kirjoitaTiedostoon(hinta95, hinta98, hintaD, hinnat);
							text98.setText("");
						} else {
							kirjoitaTiedostoon(hinta95, hinta98, hintaD, hinnat);
							text98.setText("");
						}
					}

					if (result == JOptionPane.NO_OPTION) {
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Virheellinen syöte!");
					text98.setText("");
				}
			}
		});
		btnPaivita98.setBounds(456, 86, 107, 23);
		contentPane.add(btnPaivita98);

		btnPaivitaD = new JButton("P\u00E4ivit\u00E4 hinta");
		btnPaivitaD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int result = JOptionPane.showConfirmDialog(null, "Hyväksy hinnanmuutos!", "Tämä on boxi.",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (result == JOptionPane.OK_OPTION) {
						hintaD = "" + Double.parseDouble(textDiesel.getText());
						dHinta.setText(hintaD + "€");
						if (chkSuhde.isSelected()) {		
							double double95 = Double.parseDouble(textDiesel.getText()) * 1.0739;
							double double98 = Double.parseDouble(textDiesel.getText()) * 1.1471;
							
							hinta95 = String.valueOf(round(double95,3));
							e95Hinta.setText(hinta95 + "€");
							
							hinta98 = String.valueOf(round(double98,3));
							e98Hinta.setText(hinta98 + "€");
							
							kirjoitaTiedostoon(hinta95, hinta98, hintaD, hinnat);
							textDiesel.setText("");
						} else {
							kirjoitaTiedostoon(hinta95, hinta98, hintaD, hinnat);
							textDiesel.setText("");
						}
						
					}

					if (result == JOptionPane.NO_OPTION) {
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Virheellinen syöte!");
					textDiesel.setText("");
				}
			}
		});
		btnPaivitaD.setBounds(456, 123, 107, 23);
		contentPane.add(btnPaivitaD);

		e95Hinta = new JLabel();
		e95Hinta.setFont(new Font("Tahoma", Font.BOLD, 13));
		e95Hinta.setBounds(199, 54, 84, 14);
		contentPane.add(e95Hinta);

		e98Hinta = new JLabel();
		e98Hinta.setFont(new Font("Tahoma", Font.BOLD, 13));
		e98Hinta.setBounds(199, 90, 84, 14);
		contentPane.add(e98Hinta);

		dHinta = new JLabel();
		dHinta.setFont(new Font("Tahoma", Font.BOLD, 13));
		dHinta.setBounds(199, 127, 84, 14);
		contentPane.add(dHinta);

		textMainos = new JTextField();
		textMainos.setToolTipText("Mit\u00E4 haluat mainostaa asiakkaillesi?");
		textMainos.setBounds(75, 212, 476, 20);
		contentPane.add(textMainos);
		textMainos.setColumns(10);

		lblMainos = new JLabel("Mainostaulun teksti:");
		lblMainos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMainos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainos.setBounds(0, 181, 634, 20);
		contentPane.add(lblMainos);

		btnMainos = new JButton("P\u00E4ivit\u00E4 mainostaulu");
		btnMainos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainosT = textMainos.getText();
				if (mainosT.equals("")) {
					Mainostaulu.lbl95.setText(e95Hinta.getText());
					Mainostaulu.lbl98.setText(e98Hinta.getText());
					Mainostaulu.lblD.setText(dHinta.getText());
					mainostaulu.setVisible(true);
				} else {
				kirjoitaMainos(mainosT, mainos);
				Mainostaulu.lblTeksti.setText(textMainos.getText());
				Mainostaulu.lbl95.setText(e95Hinta.getText());
				Mainostaulu.lbl98.setText(e98Hinta.getText());
				Mainostaulu.lblD.setText(dHinta.getText());
				mainostaulu.setVisible(true);
				}
			}
		});
		btnMainos.setBounds(242, 243, 150, 23);
		contentPane.add(btnMainos);

		lblEuro1 = new JLabel("\u20AC");
		lblEuro1.setBounds(432, 54, 46, 14);
		contentPane.add(lblEuro1);

		lblEuro2 = new JLabel("\u20AC");
		lblEuro2.setBounds(432, 90, 46, 14);
		contentPane.add(lblEuro2);

		lblEuro3 = new JLabel("\u20AC");
		lblEuro3.setBounds(432, 127, 46, 14);
		contentPane.add(lblEuro3);
		
		chkSuhde = new JCheckBox("P\u00E4ivit\u00E4 suhteessa");
		chkSuhde.setBounds(456, 164, 150, 23);
		contentPane.add(chkSuhde);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 46, 21);
		contentPane.add(menuBar);
		
		mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		mainosMenu = new JMenuItem("P\u00E4ivit\u00E4 mainostaulu");
		mainosMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainosT = textMainos.getText();
				if (mainosT.equals("")) {
					Mainostaulu.lbl95.setText(e95Hinta.getText());
					Mainostaulu.lbl98.setText(e98Hinta.getText());
					Mainostaulu.lblD.setText(dHinta.getText());
					mainostaulu.setVisible(true);
				} else {
				kirjoitaMainos(mainosT, mainos);
				Mainostaulu.lblTeksti.setText(textMainos.getText());
				Mainostaulu.lbl95.setText(e95Hinta.getText());
				Mainostaulu.lbl98.setText(e98Hinta.getText());
				Mainostaulu.lblD.setText(dHinta.getText());
				mainostaulu.setVisible(true);
				}
			}
		});
		mnNewMenu.add(mainosMenu);
		
		poistuMenu = new JMenuItem("Poistu");
		poistuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text95.setText("");
				text98.setText("");
				textDiesel.setText("");
				textMainos.setText("");
				PaaIkkuna_Hinnat.this.setVisible(false);
			}
		});
		mnNewMenu.add(poistuMenu);
		
		btnNewButton = new JButton("Poistu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text95.setText("");
				text98.setText("");
				textDiesel.setText("");
				textMainos.setText("");
				PaaIkkuna_Hinnat.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(242, 277, 150, 23);
		contentPane.add(btnNewButton);

		lueHinta(hinnat);

	}

	public void lueHinta(String hinnat) {
		try {
			FileReader freader = new FileReader(hinnat);
			br = new BufferedReader(freader);

			hinta95 = br.readLine();
			hinta95 = kasitteleRivi95(hinta95);
			e95Hinta.setText(hinta95);

			hinta98 = br.readLine();
			hinta98 = kasitteleRivi98(hinta98);
			e98Hinta.setText(hinta98);

			hintaD = br.readLine();
			hintaD = kasitteleRiviD(hintaD);
			dHinta.setText(hintaD);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void kirjoitaTiedostoon(String txt1, String txt2, String txt3, String hinnat) {
		try {
			FileWriter fwriter = new FileWriter(hinnat, false);
			txt1 = txt1.replace(" ", "");
			txt2 = txt2.replace(" ", "");
			txt3 = txt3.replace(" ", "");
			fwriter.write("95E = " + txt1 + "€\n");
			fwriter.write("98E = " + txt2 + "€\n");
			fwriter.write("Diesel = " + txt3 + "€");
			fwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String kasitteleRivi95(String txt) {
		txt = txt.replace(" ", "");
		txt = txt.replace("€€", "€");
		String[] array = txt.split("=");

		return array[1];
	}

	public String kasitteleRivi98(String txt) {
		txt = txt.replace(" ", "");
		txt = txt.replace("€€", "€");
		String[] array = txt.split("=");

		return array[1];
	}

	public String kasitteleRiviD(String txt) {
		txt = txt.replace(" ", "");
		txt = txt.replace("€€", "€");
		String[] array = txt.split("=");

		return array[1];
	}
	
	static Double round(Double d, int precise) 
	{
	BigDecimal bigDecimal = BigDecimal.valueOf(d);
	bigDecimal = bigDecimal.setScale(precise, RoundingMode.HALF_UP);
	return bigDecimal.doubleValue();
	}
	
	public void kirjoitaMainos (String txt, String mainos) {
		try {
			FileWriter fwriter = new FileWriter(mainos, false);
			fwriter.write(txt);
			fwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void lueMainos(String mainos) {
		try {
			FileReader freader = new FileReader(mainos);
			br = new BufferedReader(freader);
			
			mainosT = br.readLine();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
