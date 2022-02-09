import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Tankkaus extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel_95;
	private JLabel lblNewLabel_98;
	private JLabel lblNewLabel_diesel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton osto95;
	private JButton osto98;
	private JButton ostodiesel;
	private JLabel lblhinnat;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	public JLabel hinta95;
	public JLabel hinta98;
	public JLabel hintadiesel;
	private JLabel veloitettu;
	private JLabel pic;

	public BufferedReader br;

	String filename = "src/resources/tankeissa_bensaa.txt";
	String hinnat = "src/resources/hinnat.txt";
	String kuitti = "src/resources/kuitti.txt";

	String e95_1;
	String e98_1;
	String diesel_1;

	String e95_tankattu;
	String e98_tankattu;
	String diesel_tankattu;

	String hinta95s;
	String hinta98s;
	String hintaDs;
	private JLabel lbl95;
	private JLabel lbl98;
	private JLabel lbld;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmNewMenuItem;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Tankkaus() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Suljetaanko ikkuna?", "Varoitus!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					Tankkaus.this.setVisible(false);
				}
				if (result == JOptionPane.NO_OPTION) {
				}
			}

			@Override
			public void windowActivated(WindowEvent e) {
				lueHinta(hinnat);
				lueTiedostosta(filename);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tankkaus.class.getResource("/img/Bensis kuva.png")));
		setTitle("tankkaus");

		setSize(new Dimension(640, 360));
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 45, 21);
		contentPanel.add(menuBar);
		
		mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		mntmNewMenuItem = new JMenuItem("Poistu");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				Tankkaus.this.setVisible(false);
			}
		});
		mnMenu.add(mntmNewMenuItem);

		lblNewLabel_98 = new JLabel("98E10");
		lblNewLabel_98.setForeground(Color.WHITE);
		lblNewLabel_98.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_98.setBounds(181, 63, 45, 13);
		contentPanel.add(lblNewLabel_98);

		lblNewLabel_95 = new JLabel("95E10");
		lblNewLabel_95.setForeground(Color.WHITE);
		lblNewLabel_95.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_95.setBounds(65, 63, 45, 13);
		contentPanel.add(lblNewLabel_95);

		lblNewLabel_diesel = new JLabel("Diesel");
		lblNewLabel_diesel.setForeground(Color.WHITE);
		lblNewLabel_diesel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_diesel.setBounds(290, 63, 45, 13);
		contentPanel.add(lblNewLabel_diesel);

		textField = new JTextField();
		textField.setBounds(60, 86, 60, 19);
		contentPanel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(171, 86, 60, 19);
		contentPanel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(280, 86, 60, 19);
		contentPanel.add(textField_2);

		lblNewLabel_1 = new JLabel("litraa");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(127, 89, 45, 13);
		contentPanel.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("litraa");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(238, 89, 45, 13);
		contentPanel.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("litraa");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(346, 89, 45, 13);
		contentPanel.add(lblNewLabel_3);

		// ******e95
		osto95 = new JButton("Tankkaa");
		osto95.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DecimalFormat df = new DecimalFormat(".00");

				try {

					int result = JOptionPane.showConfirmDialog(null, "Hyväksytkö oston?", "Osto?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (result == JOptionPane.OK_OPTION) {

						if (Integer.parseInt(e95_1) >= Integer.parseInt(textField.getText())) {
							int uusi_maara95 = Integer.parseInt(e95_1) - Integer.parseInt(textField.getText());
							e95_1 = String.valueOf(uusi_maara95);

							kirjoitatiedostoon(e95_1, e98_1, diesel_1, filename);

							// hinnan tulostaminen

							Double veloitus = Double.parseDouble(lbl95.getText())
									* Double.parseDouble(textField.getText());
							veloitettu.setText("Tililtä veloitettu: " + df.format(veloitus) + "€");

							// kuitti
							tulostaTiedostoon("Ostot: " + Integer.parseInt(textField.getText())
									+ " litraa 95E10. Summa: " + df.format(veloitus) + "€.");
							textField.setText("");
							BensisTyo.progressBar.setValue(uusi_maara95);
						} else {
							JOptionPane.showMessageDialog(null,
									"Ei riittävästi 95E10 tankissa! Tankissa " + Integer.parseInt(e95_1) + " litraa.");
							textField.setText("");
						}
					}

					if (result == JOptionPane.NO_OPTION) {
						textField.setText("");
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Virheellinen syöte!");
					textField.setText("");
				}

			}

		});
		osto95.setBounds(51, 115, 85, 21);
		contentPanel.add(osto95);

		// ******e98
		osto98 = new JButton("Tankkaa");
		osto98.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DecimalFormat df = new DecimalFormat(".00");

				try {

					int result = JOptionPane.showConfirmDialog(null, "Hyväksytkö oston?", "Osto?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {

						if (Integer.parseInt(e98_1) >= Integer.parseInt(textField_1.getText())) {
							int uusi_maara98 = Integer.parseInt(e98_1) - Integer.parseInt(textField_1.getText());
							e98_1 = String.valueOf(uusi_maara98);

							kirjoitatiedostoon(e95_1, e98_1, diesel_1, filename);

							// hinnan tulostaminen
							Double veloitus = Double.parseDouble(lbl98.getText())
									* Double.parseDouble(textField_1.getText());
							veloitettu.setText("Tililtä veloitettu: " + df.format(veloitus) + "€");

							// kuitti
							tulostaTiedostoon("Ostot: " + Integer.parseInt(textField_1.getText())
									+ " litraa 98E5. Summa: " + df.format(veloitus) + "€.");
							textField_1.setText("");
							BensisTyo.progressBar_1.setValue(uusi_maara98);
						} else {
							JOptionPane.showMessageDialog(null,
									"Ei riittävästi 98E5 tankissa! Tankissa " + Integer.parseInt(e98_1) + " litraa.");
							textField_1.setText("");
						}

					}

					if (result == JOptionPane.NO_OPTION) {
						textField_1.setText("");
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Virheellinen syöte!");
					textField_1.setText("");
				}

			}
		});
		osto98.setBounds(164, 115, 85, 21);
		contentPanel.add(osto98);

		// ******diesel
		ostodiesel = new JButton("Tankkaa");
		ostodiesel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DecimalFormat df = new DecimalFormat(".00");

				try {

					int result = JOptionPane.showConfirmDialog(null, "Hyväksytkö oston?", "Osto?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {

						if (Integer.parseInt(diesel_1) >= Integer.parseInt(textField_2.getText())) {
							int uusi_maaraD = Integer.parseInt(diesel_1) - Integer.parseInt(textField_2.getText());
							diesel_1 = String.valueOf(uusi_maaraD);

							kirjoitatiedostoon(e95_1, e98_1, diesel_1, filename);

							// hinnan tulostaminen
							Double veloitus = Double.parseDouble(lbld.getText())
									* Double.parseDouble(textField_2.getText());
							veloitettu.setText("Tililtä veloitettu: " + df.format(veloitus) + "€");

							// kuitti
							tulostaTiedostoon("Ostot: " + Integer.parseInt(textField_2.getText())
									+ " litraa Dieseliä. Summa: " + df.format(veloitus) + "€.");
							textField_2.setText("");
							BensisTyo.progressBar3.setValue(uusi_maaraD);

						} else {
							JOptionPane.showMessageDialog(null, "Ei riittävästi Dieseliä tankissa! Tankissa "
									+ Integer.parseInt(diesel_1) + " litraa.");
							textField_2.setText("");
						}

					}

					if (result == JOptionPane.NO_OPTION) {
						textField_2.setText("");
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Virheellinen syöte!");
					textField_2.setText("");

				}
				;

			}
		});
		ostodiesel.setBounds(272, 115, 85, 21);
		contentPanel.add(ostodiesel);

		lblhinnat = new JLabel("Hinnat");
		lblhinnat.setForeground(Color.WHITE);
		lblhinnat.setBounds(346, 184, 45, 13);
		contentPanel.add(lblhinnat);

		lblNewLabel_5 = new JLabel("e95");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(290, 208, 45, 13);
		contentPanel.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("e98");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(290, 222, 45, 13);
		contentPanel.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("diesel");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(290, 238, 45, 13);
		contentPanel.add(lblNewLabel_7);

		hinta95 = new JLabel("");
		hinta95.setForeground(Color.WHITE);
		hinta95.setBounds(346, 208, 45, 13);
		contentPanel.add(hinta95);

		hinta98 = new JLabel("");
		hinta98.setForeground(Color.WHITE);
		hinta98.setBounds(346, 222, 45, 13);
		contentPanel.add(hinta98);

		hintadiesel = new JLabel("");
		hintadiesel.setForeground(Color.WHITE);
		hintadiesel.setBounds(346, 238, 45, 13);
		contentPanel.add(hintadiesel);

		veloitettu = new JLabel("");
		veloitettu.setForeground(Color.WHITE);
		veloitettu.setBounds(36, 237, 166, 14);
		contentPanel.add(veloitettu);

		pic = new JLabel("");
		pic.setBounds(0, 0, 444, 265);
		contentPanel.add(pic);

		lueTiedostosta(filename);

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("src/img/gasstation.jpg"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		ImageIcon imageIcon = new ImageIcon(fitimage(image, pic.getWidth(), pic.getHeight()));
		pic.setIcon(imageIcon);

		lbl95 = new JLabel("New label");
		lbl95.setBounds(36, 237, 46, 14);
		contentPanel.add(lbl95);

		lbl98 = new JLabel("New label");
		lbl98.setBounds(36, 237, 46, 14);
		contentPanel.add(lbl98);

		lbld = new JLabel("New label");
		lbld.setBounds(36, 237, 46, 14);
		contentPanel.add(lbld);
	}

	private Image fitimage(Image img, int w, int h) {
		BufferedImage resizedimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedimage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		return resizedimage;

	}

	// ***** kuitin tulostaminen
	public void tulostaTiedostoon(String txt) {

		try {

			FileWriter fwriter = new FileWriter(kuitti, true);
			fwriter.write(txt + "\n");
			fwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void kirjoitatiedostoon(String txt1, String txt2, String txt3, String filename) {

		try {
			FileWriter fwriter = new FileWriter(filename, false);
			txt1 = txt1.replace(" ", "");
			txt2 = txt2.replace(" ", "");
			txt3 = txt3.replace(" ", "");
			fwriter.write("e95 = " + txt1 + "\n");
			fwriter.write("e98 = " + txt2 + "\n");
			fwriter.write("diesel = " + txt3 + "\n");
			fwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void lueTiedostosta(String filename) {

		try {
			FileReader freader = new FileReader(filename);
			BufferedReader br = new BufferedReader(freader);

			e95_1 = br.readLine();
			e95_1 = kasittele95(e95_1);

			e98_1 = br.readLine();
			e98_1 = kasittele98(e98_1);

			diesel_1 = br.readLine();
			diesel_1 = kasitteleD(diesel_1);

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String kasittele95(String txt) {
		txt = txt.replace(" ", "");
		String[] array = txt.split("=");

		return array[1];
	}

	public String kasittele98(String txt) {
		txt = txt.replace(" ", "");
		String[] array = txt.split("=");

		return array[1];
	}

	public String kasitteleD(String txt) {
		txt = txt.replace(" ", "");
		String[] array = txt.split("=");

		return array[1];
	}

	public void lueHinta(String hinnat) {
		try {
			FileReader freader = new FileReader(hinnat);
			br = new BufferedReader(freader);

			hinta95s = br.readLine();
			hinta95s = kasitteleRivi95(hinta95s);
			lbl95.setText(hinta95s);

			hinta98s = br.readLine();
			hinta98s = kasitteleRivi98(hinta98s);
			lbl98.setText(hinta98s);

			hintaDs = br.readLine();
			hintaDs = kasitteleRiviD(hintaDs);
			lbld.setText(hintaDs);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String kasitteleRivi95(String txt) {
		txt = txt.replace(" ", "");
		txt = txt.replace("€", "");
		String[] array = txt.split("=");

		return array[1];
	}

	public String kasitteleRivi98(String txt) {
		txt = txt.replace(" ", "");
		txt = txt.replace("€", "");
		String[] array = txt.split("=");

		return array[1];
	}

	public String kasitteleRiviD(String txt) {
		txt = txt.replace(" ", "");
		txt = txt.replace("€", "");
		String[] array = txt.split("=");

		return array[1];
	}
}