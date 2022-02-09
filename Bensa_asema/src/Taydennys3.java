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

public class Taydennys3 extends JDialog {

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
	String diesel;
	private JTextField tekstikentta3;
	private static BufferedReader br;
	private JLabel tankkid;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Taydennys3() {
		setResizable(false);
		setTitle("T\u00E4ydennys Diesel");
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
					try 
					{
						int uusMaara = 1000 - Integer.parseInt(maarad);	//TULOSTAA TIEDOSTOON OIKEAN M��R�N BENSAA JOS MENEE YLI 1000L
						int jokuMaara = Integer.parseInt(maarad)+Integer.parseInt(tekstikentta3.getText());
						if (jokuMaara > 1000)
						{
						jokuMaara = 1000;
						maarad = String.valueOf(jokuMaara);
						kirjoitaTiedostoon(maara95, maara98, maarad, maarat);
						tankkid.setText(maarad + " l");
						JOptionPane.showMessageDialog(null, "Tankin maksimi kapasiteetti ylittyy, tankki t�ytetty 1000l.");
						BensisTyo.textArea_2.setText(maarad + " l");
						tekstikentta3.setText("");
						BensisTyo.progressBar3.setValue(Integer.parseInt(Taydennys3.maarad));
						maarad = String.valueOf(uusMaara);
						kirjoitaPaivays(maarad, paivaykset);
						Taydennys3.this.setVisible(false);
					
						} else
						{
						maarad = String.valueOf(jokuMaara);
						kirjoitaTiedostoon(maara95, maara98, maarad, maarat);
						kirjoitaPaivays(maarad, paivaykset);
						tankkid.setText(maarad + " l");
						JOptionPane.showMessageDialog(null, "Tilaus l�hetetty");
						BensisTyo.textArea_2.setText(maarad + " l");
						tekstikentta3.setText("");
						BensisTyo.progressBar3.setValue(Integer.parseInt(Taydennys3.maarad));
						Taydennys3.this.setVisible(false);
						}

					} catch (NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "Virheellinen sy�te");
						tekstikentta3.setText("");
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
					Taydennys3.this.setVisible(false);
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
		
		tekstikentta3 = new JTextField();
		tekstikentta3.setFont(new Font("Tahoma", Font.BOLD, 16));
		tekstikentta3.setBounds(240, 101, 79, 25);
		contentPanel.add(tekstikentta3);
		tekstikentta3.setColumns(10);
		
		tankkid = new JLabel("");
		tankkid.setFont(new Font("Tahoma", Font.BOLD, 16));
		tankkid.setBounds(31, 98, 79, 30);
		contentPanel.add(tankkid);
		
		lueTiedostosta(maarat);
		lueMaarat(maarat);
	}
	
	 //K�SITTELEE RIVIT TEKSTI TIEDOSTOSTA
		public String kasitteleRivi(String txt) {
			txt = txt.replace(" ", "");
			String[] maarat = txt.split("=");
			
			return maarat[1];
		}

	

	public void lueMaarat(String maarat) {
		
		try {
			FileReader freader = new FileReader(maarat);
			BufferedReader br = new BufferedReader(freader);
			diesel = br.readLine();
			diesel = br.readLine();
			diesel = br.readLine();
			diesel = kasitteleRivi(diesel);
			tankkid.setText(diesel + " l");
			br.close();
				
		} catch (Exception e) {
			e.printStackTrace();		}
	}
	
	//KIRJOITA TIEDOSTOON BENSAN M��R�T
	
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
		
		public static String kasitteleRivid(String txt) {
	        txt = txt.replace(" ", "");
	        String[] array = txt.split("=");

	        return array[1];
	    }
		
		//KIRJOITTAA TILAUKSEN M��R�N JA P�IV�M��R�N YL�S
		public void kirjoitaPaivays(String txt, String paivaykset) {
			try 
			{
				java.util.Date date = new java.util.Date();
				FileWriter fwrite = new FileWriter(paivaykset, true);
				fwrite.write(date.toString());
				fwrite.write(" Tilattu diesel t�ydennys:" + txt + " litraa" + "\n");
				fwrite.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
}
