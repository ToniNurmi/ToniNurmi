import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaaIkkuna_Kayttaja extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton nappi;
	public static JLabel label95;
	public static JLabel label98;
	public static JLabel labelD;
	private JLabel tausta;
	private JTextArea lblTeksti;
	private BufferedReader br;

	private Kirjautuminen kirjautuminen = new Kirjautuminen();


	String hinnat = "src/resources/hinnat.txt";
	String e95;
	String e98;
	String diesel;
	
	String mainos = "src/resources/mainosteksti.txt";
	String mainosT;
	private JButton peruutaNappi;

	/**
	 * Create the frame.
	 */
	public PaaIkkuna_Kayttaja() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Suljetaanko ikkuna?", "Varoitus!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					PaaIkkuna_Kayttaja.this.setVisible(false);
				}
				if (result == JOptionPane.NO_OPTION) {
				}
			}
			@Override
			public void windowActivated(WindowEvent e) {
				lueBensa(hinnat);
				lueMainos(mainos);
				lblTeksti.setText(mainosT);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(PaaIkkuna_Kayttaja.class.getResource("/img/Bensis kuva.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 640, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nappi = new JButton("Tankkaa auto, maksu pankkikortilla");
		nappi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kirjautuminen.setVisible(true);
				PaaIkkuna_Kayttaja.this.setVisible(false);
			}
		});
		
		peruutaNappi = new JButton("Peruuta");
		peruutaNappi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaaIkkuna_Kayttaja.this.setVisible(false);
			}
		});
		peruutaNappi.setBounds(496, 322, 89, 23);
		contentPane.add(peruutaNappi);
		nappi.setBounds(203, 71, 250, 23);
		contentPane.add(nappi);

		// *********************************************************
		
		label95 = new JLabel("");
		label95.setForeground(Color.RED);
		label95.setFont(new Font("OCR A Std", Font.BOLD, 20));
		label95.setHorizontalAlignment(SwingConstants.CENTER);
		label95.setBounds(237, 204, 141, 34);
		contentPane.add(label95);

		// *********************************************************

		label98 = new JLabel("");
		label98.setForeground(Color.RED);
		label98.setFont(new Font("OCR A Std", Font.BOLD, 20));
		label98.setHorizontalAlignment(SwingConstants.CENTER);
		label98.setBounds(238, 255, 141, 33);
		contentPane.add(label98);

		// *********************************************************

		labelD = new JLabel("");
		labelD.setForeground(Color.RED);
		labelD.setFont(new Font("OCR A Std", Font.BOLD, 20));
		labelD.setHorizontalAlignment(SwingConstants.CENTER);
		labelD.setBounds(237, 305, 141, 40);
		contentPane.add(labelD);
		
		lueMainos(mainos);

		lblTeksti = new JTextArea();
		lblTeksti.setWrapStyleWord(true);
		lblTeksti.setRows(5);
		lblTeksti.setLineWrap(true);
		lblTeksti.setForeground(Color.WHITE);
		lblTeksti.setFont(new Font("Impact", Font.PLAIN, 13));
		lblTeksti.setEditable(false);
		lblTeksti.setBackground(new Color(0, 0, 102));
		lblTeksti.setBounds(221, 141, 207, 54);
		contentPane.add(lblTeksti);

		tausta = new JLabel("");
		tausta.setIcon(new ImageIcon(PaaIkkuna_Kayttaja.class.getResource("/img/Bensis tausta.png")));
		tausta.setBounds(0, 0, 634, 352);
		contentPane.add(tausta);
		
		


	}

	public void lueBensa(String hinnat) {

		try {
			FileReader freader = new FileReader(hinnat);
			BufferedReader br = new BufferedReader(freader);

			e95 = br.readLine();
			e95 = kasitteleRivi95(e95);
			label95.setText(e95);
//******************
			e98 = br.readLine();
			e98 = kasitteleRivi98(e98);
			label98.setText(e98);
//******************
			diesel = br.readLine();
			diesel = kasitteleRiviD(diesel);
			labelD.setText(diesel);

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void kirjoitaTiedostoon(String txt, String hinnat) {
		try {
			// Valinta true lopussa aiheuttaa sen, että ei ylikirjoiteta vaan jatketaan
			// olemassa olevan perään
			FileWriter fwriter = new FileWriter(hinnat, true);
			fwriter.write(txt + "\n");
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
