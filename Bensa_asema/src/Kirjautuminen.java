import java.awt.BorderLayout;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class Kirjautuminen extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblSalasana;
	private JButton nappi;

	private Tankkaus tankkaus = new Tankkaus();

	String salasana = "src/resources/koodi.txt";
	private JPasswordField passwordField;
	private File koodi;
	private JButton peruuta;
	private JLabel tausta;

	/**
	 * Create the dialog.
	 */
	public Kirjautuminen() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Suljetaanko ikkuna?", "Varoitus!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					passwordField.setText("");
					Kirjautuminen.this.setVisible(false);
				}
				if (result == JOptionPane.NO_OPTION) {
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Kirjautuminen.class.getResource("/img/Bensis kuva.png")));
		setResizable(false);
		setTitle("Kirjautuminen");
		setBounds(100, 100, 376, 271);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblSalasana = new JLabel("Sy\u00F6t\u00E4 pin-koodi:");
		lblSalasana.setForeground(Color.WHITE);
		lblSalasana.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSalasana.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalasana.setBounds(129, 93, 114, 13);
		contentPanel.add(lblSalasana);

		nappi = new JButton("Tunnistaudu");
		nappi.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				setInputFile(new File(salasana));

				try {
					Scanner in = new Scanner(new File(salasana));
					while (in.hasNextLine()) {
						String s = in.nextLine();

						String[] sArray = s.split("=");
						String oikea_pinkoodi = crypt(sArray[1]);
						String pinkoodi_crypted = "";

						try {
							pinkoodi_crypted = crypt(passwordField.getText());
						} catch (Exception e) {
						}

						if (oikea_pinkoodi.equals(crypt(pinkoodi_crypted))) {
							passwordField.setText("");
							Kirjautuminen.this.setVisible(false);
							tankkaus.setVisible(true);
							tankkaus.hinta95.setText(PaaIkkuna_Kayttaja.label95.getText());
							tankkaus.hinta98.setText(PaaIkkuna_Kayttaja.label98.getText());
							tankkaus.hintadiesel.setText(PaaIkkuna_Kayttaja.labelD.getText());
							
							
						} else {
							JOptionPane.showMessageDialog(null, "V‰‰r‰ pin-koodi!");
						}
					}
					
				}

				catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Tunnistautuminen ei onnistu");
				}
			}
		});
		nappi.setBounds(129, 160, 114, 21);
		contentPanel.add(nappi);

		passwordField = new JPasswordField();
		passwordField.setBounds(129, 130, 114, 19);
		contentPanel.add(passwordField);
		
		peruuta = new JButton("Peruuta");
		peruuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kirjautuminen.this.setVisible(false);
				passwordField.setText("");
			}
		});
		peruuta.setBounds(129, 192, 114, 23);
		contentPanel.add(peruuta);
		
		tausta = new JLabel("");
		tausta.setIcon(new ImageIcon(Kirjautuminen.class.getResource("/img/pintausta.png")));
		tausta.setBounds(0, 0, 370, 242);
		contentPanel.add(tausta);

	}

	public static String crypt(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}

		MessageDigest digester;
		try {
			digester = MessageDigest.getInstance("MD5");

			digester.update(str.getBytes());
			byte[] hash = digester.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void setInputFile(File inputFile) {
		this.setKoodi(inputFile);
	}

	public File getKoodi() {
		return koodi;
	}

	public void setKoodi(File koodi) {
		this.koodi = koodi;
	}

}
