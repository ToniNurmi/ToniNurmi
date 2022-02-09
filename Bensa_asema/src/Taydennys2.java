import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class Taydennys2 extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	
	String paivaykset = "src/Resources/tilaus_paivaykset.txt";
	String maarat = "src/Resources/tankeissa_bensaa.txt";
	static String maara95;
	static String maara98;
	static String maarad;
	String e98;
	private JTextField tekstikentta2;
	private static BufferedReader br;
	private JLabel tankki98;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Taydennys2() {
		setResizable(false);
		setTitle("T\u00E4ydennys 98E10");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Taydennys1.class.getResource("/Img/Bensis kuva.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton TilaaNappi1 = new JButton("Tilaa");
			TilaaNappi1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					try {
						int uusMaara = 1000 - Integer.parseInt(maara98);	//TULOSTAA TIEDOSTOON OIKEAN MÄÄRÄN BENSAA JOS MENEE YLI 1000L
						int jokuMaara = Integer.parseInt(maara98)+Integer.parseInt(tekstikentta2.getText());
						if (jokuMaara > 1000)
						{
						jokuMaara = 1000;
						maara98 = String.valueOf(jokuMaara);
						kirjoitaTiedostoon(maara95, maara98, maarad, maarat);
						tankki98.setText(maara98 + " l");
						JOptionPane.showMessageDialog(null, "Tankin maksimi kapasiteetti ylittyy, tankki täytetty 1000l.");
						BensisTyo.textArea_1.setText(maara98 + " l");
						tekstikentta2.setText("");
						BensisTyo.progressBar_1.setValue(Integer.parseInt(Taydennys2.maara98));
						maara98 = String.valueOf(uusMaara);
						kirjoitaPaivays(maara98, paivaykset);
						Taydennys2.this.setVisible(false);
						} else
						{
						maara98 = String.valueOf(jokuMaara);
						kirjoitaTiedostoon(maara95, maara98, maarad, maarat);
						kirjoitaPaivays(maara98, paivaykset);
						tankki98.setText(maara98 + " l");
						JOptionPane.showMessageDialog(null, "Tilaus lähetetty");
						BensisTyo.textArea_1.setText(maara98 + " l");
						tekstikentta2.setText("");
						BensisTyo.progressBar_1.setValue(Integer.parseInt(Taydennys2.maara98));
						Taydennys2.this.setVisible(false);
						}
					} catch (NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "Virheellinen syöte");
						tekstikentta2.setText("");
					}
					
				}
				
			});
			TilaaNappi1.setBounds(310, 207, 97, 25);
			contentPanel.add(TilaaNappi1);
		}
		{
			JButton PeruutaNappi1 = new JButton("Peruuta");
			PeruutaNappi1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					Taydennys2.this.setVisible(false);
				}
			});
			PeruutaNappi1.setBounds(190, 207, 97, 25);
			contentPanel.add(PeruutaNappi1);
		}
		{
			JLabel lblNewLabel = new JLabel("M\u00E4\u00E4r\u00E4 tankissa");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(31, 64, 99, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("(litraa)");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1.setBounds(31, 81, 56, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Paljon tilataan lis\u00E4\u00E4?");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_2.setBounds(240, 64, 137, 16);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("(litraa)");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1.setBounds(240, 81, 56, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Tankkien maksimi kapasiteetti 1000l");
			lblNewLabel_1.setForeground(Color.RED);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_1.setBounds(12, 13, 328, 30);
			contentPanel.add(lblNewLabel_1);
		}
		
		tekstikentta2 = new JTextField();
		tekstikentta2.setFont(new Font("Tahoma", Font.BOLD, 16));
		tekstikentta2.setBounds(240, 101, 79, 25);
		contentPanel.add(tekstikentta2);
		tekstikentta2.setColumns(10);
		
		tankki98 = new JLabel("");
		tankki98.setFont(new Font("Tahoma", Font.BOLD, 16));
		tankki98.setBounds(31, 98, 79, 30);
		contentPanel.add(tankki98);
		
		lueTiedostosta(maarat);
		lueMaarat(maarat);
	}
	
	 //KÄSITTELEE RIVIT TEKSTI TIEDOSTOSTA
		public String kasitteleRivi(String txt) {
			txt = txt.replace(" ", "");
			String[] maarat = txt.split("=");
			
			return maarat[1];
		}

	

	public void lueMaarat(String maarat) {
		
		try {
			FileReader freader = new FileReader(maarat);
			BufferedReader br = new BufferedReader(freader);
			e98 = br.readLine();
			e98 = br.readLine();
			e98 = kasitteleRivi(e98);
			tankki98.setText(e98 + " l");
			br.close();
			
				
		} catch (Exception e) {
			e.printStackTrace();		}
	}
	
	//KIRJOITA TIEDOSTOON BENSAN MÄÄRÄT
	
	public static void kirjoitaTiedostoon(String teksti1, String teksti2, String teksti3,String maarat) {
		try 
		{
			FileWriter fwrite = new FileWriter(maarat, false);
			teksti1 = teksti1.replace(" ", "");
			teksti2 = teksti2.replace(" ", "");
			teksti3 = teksti3.replace(" ", "");
			fwrite.write("95e = " + teksti1 + "\n");
			fwrite.write("98e = " + teksti2 + "\n");
			fwrite.write("diesel = " + teksti3 + "\n");
			fwrite.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
		
		public static void lueTiedostosta(String maarat)
		{
			try
			{
				FileReader freader = new FileReader(maarat);
				br = new BufferedReader(freader);
				
				maara95 = br.readLine();
				maara95 = kasitteleRivi95(maara95);

				
				maara98 = br.readLine();
				maara98 = kasitteleRivi98(maara98);

				
				maarad = br.readLine();
				maarad = kasitteleRivi95(maarad);

				
				
		} catch (Exception e)
			{
			e.printStackTrace();
			}
		}
		
		public static String kasitteleRivi95(String txt) {
	        txt = txt.replace(" ", "");
	        String[] array = txt.split("=");

	        return array[1];
	    }
		
		public static String kasitteleRivi98(String txt) {
	        txt = txt.replace(" ", "");
	        String[] array = txt.split("=");

	        return array[1];
	    }
		
		public String kasitteleRivid(String txt) {
	        txt = txt.replace(" ", "");
	        String[] array = txt.split("=");

	        return array[1];
	    }
		
		//KIRJOITTAA TILAUKSEN MÄÄRÄN JA PÄIVÄMÄÄRÄN YLÖS
		public void kirjoitaPaivays(String txt, String paivaykset) {
			try 
			{
				java.util.Date date = new java.util.Date();
				FileWriter fwrite = new FileWriter(paivaykset, true);
				fwrite.write(date.toString());
				fwrite.write(" Tilattu e98 täydennys:" + txt + " litraa" + "\n");
				fwrite.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
}
