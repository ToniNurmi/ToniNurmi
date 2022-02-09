import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Mainostaulu extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	public static JTextArea lblTeksti;
	public static JLabel lbl95;
	public static JLabel lbl98;
	public static JLabel lblD;
	private JLabel lblNewLabel_1;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem_1;
	private BufferedReader br;
	
	String mainos = "src/Resources/mainosteksti.txt";
	String mainosT;
	

	/**
	 * Create the dialog.
	 */
	public Mainostaulu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				lueMainos(mainos);
				lblTeksti.setText(mainosT);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mainostaulu.class.getResource("/img/Bensis kuva.png")));
		setResizable(false);
		setBounds(100, 100, 640, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 634, 21);
		contentPanel.add(menuBar);

		mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		{
			{
				mntmNewMenuItem_1 = new JMenuItem("Poistu");
				mnNewMenu.add(mntmNewMenuItem_1);
				mntmNewMenuItem_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Mainostaulu.this.setVisible(false);
					}
				});
			}
		}
		{
			lblTeksti = new JTextArea();
			lblTeksti.setEditable(false);
			lblTeksti.setRows(5);
			lblTeksti.setWrapStyleWord(true);
			lblTeksti.setLineWrap(true);
			lblTeksti.setBackground(new Color(0, 0, 102));
			lblTeksti.setForeground(Color.WHITE);
			lblTeksti.setFont(new Font("Impact", Font.PLAIN, 13));
			lblTeksti.setBounds(221, 133, 207, 54);
			contentPanel.add(lblTeksti);
		}
		{
			lbl95 = new JLabel();
			lbl95.setForeground(Color.RED);
			lbl95.setFont(new Font("OCR A Std", Font.BOLD, 20));
			lbl95.setHorizontalAlignment(SwingConstants.CENTER);
			lbl95.setBounds(237, 198, 141, 34);
			contentPanel.add(lbl95);
		}
		{
			lbl98 = new JLabel();
			lbl98.setForeground(Color.RED);
			lbl98.setFont(new Font("OCR A Std", Font.BOLD, 20));
			lbl98.setHorizontalAlignment(SwingConstants.CENTER);
			lbl98.setBounds(237, 255, 141, 28);
			contentPanel.add(lbl98);
		}
		{
			lblD = new JLabel();
			lblD.setForeground(Color.RED);
			lblD.setFont(new Font("OCR A Std", Font.BOLD, 20));
			lblD.setHorizontalAlignment(SwingConstants.CENTER);
			lblD.setBounds(237, 305, 141, 40);
			contentPanel.add(lblD);
		}

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Mainostaulu.class.getResource("/img/Bensis tausta.png")));
		lblNewLabel_1.setBounds(0, 0, 634, 345);
		contentPanel.add(lblNewLabel_1);
		
	}
	
	public void lueMainos(String mainos) {
		try {
			FileReader freader = new FileReader(mainos);
			br = new BufferedReader(freader);
			
			mainosT = br.readLine();
			lblTeksti.setText(mainosT);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
