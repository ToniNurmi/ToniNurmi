import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class AdminLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTunnus;
	private JPasswordField salasanaField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnKirjaudu;
	private File userData;
	private File inputFile;
	
	
	private HinnatMaarat hinnatmaarat = new HinnatMaarat();

	String tunnukset = "src/Resources/tunnukset.txt";
	private JButton btnPeruuta;
	private JLabel lblNewLabel_2;

	/**
	 * Create the frame.
	 */
	public AdminLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Suljetaanko ikkuna?", "Varoitus!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					textTunnus.setText("");
					salasanaField.setText("");
					AdminLogin.this.setVisible(false);
				}
				if (result == JOptionPane.NO_OPTION) {
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminLogin.class.getResource("/img/Bensis kuva.png")));
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 320, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textTunnus = new JTextField();
		textTunnus.setBounds(134, 23, 151, 20);
		contentPane.add(textTunnus);
		textTunnus.setColumns(10);

		salasanaField = new JPasswordField();
		salasanaField.setBounds(134, 65, 151, 20);
		contentPane.add(salasanaField);

		lblNewLabel = new JLabel("K\u00E4ytt\u00E4j\u00E4tunnus:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(31, 26, 112, 14);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Salasana:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(31, 68, 93, 14);
		contentPane.add(lblNewLabel_1);

		btnKirjaudu = new JButton("Kirjaudu");
		btnKirjaudu.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				setInputFile(new File(tunnukset));

				try {
					Scanner in = new Scanner(new File(tunnukset));
					while (in.hasNextLine()) {
						String s = in.nextLine();
						String[] sArray = s.split(",");
						
						String oikea_tunnus = sArray[0];
						String oikea_salasana = crypt(sArray[1]);
//						System.out.println(oikea_salasana);
						
						String salasana_crypted = "";
						try {
							salasana_crypted = crypt(salasanaField.getText());
						} catch (Exception e) {
						}

						if (textTunnus.getText().equals(oikea_tunnus) && oikea_salasana.equals(crypt(salasana_crypted))) {
							JOptionPane.showMessageDialog(null, "Kirjautuminen onnistui!");
							textTunnus.setText("");
							salasanaField.setText("");
							AdminLogin.this.setVisible(false);
							hinnatmaarat.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Väärä käyttäjätunnus/salasana");
						}
					}

					in.close();

				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Tunnuksia ei löydy!");
				}
			}
		});
		btnKirjaudu.setBounds(31, 93, 89, 23);
		contentPane.add(btnKirjaudu);
		
		btnPeruuta = new JButton("Peruuta");
		btnPeruuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textTunnus.setText("");
				salasanaField.setText("");
				AdminLogin.this.setVisible(false);
			}
		});
		btnPeruuta.setBounds(196, 96, 89, 23);
		contentPane.add(btnPeruuta);
		
		lblNewLabel_2 = new JLabel("Unohditko salasanan?");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setToolTipText("Mene t\u00F6ihin");
		lblNewLabel_2.setBounds(31, 140, 144, 14);
		contentPane.add(lblNewLabel_2);
	}

	public static String crypt(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("Salattava string ei voi olla null tai tyhjä.");
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

	public File getUserData() {
		return userData;
	}

	public void setUserData(File userData) {
		this.userData = userData;
	}

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

}