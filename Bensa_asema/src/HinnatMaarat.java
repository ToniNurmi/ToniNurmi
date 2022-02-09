import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HinnatMaarat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnPivitMri;
	private JButton btnPoistu;
	
	private PaaIkkuna_Hinnat paaikkuna_hinnat = new PaaIkkuna_Hinnat();
	private BensisTyo bensistyo = new BensisTyo();

	/**
	 * Create the frame.
	 */
	public HinnatMaarat() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Suljetaanko ikkuna?", "Varoitus!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					HinnatMaarat.this.setVisible(false);
				}
				if (result == JOptionPane.NO_OPTION) {
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(HinnatMaarat.class.getResource("/img/Bensis kuva.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 221, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("P\u00E4ivit\u00E4 hintoja");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paaikkuna_hinnat.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 11, 185, 23);
		contentPane.add(btnNewButton);
		
		btnPivitMri = new JButton("P\u00E4ivit\u00E4 m\u00E4\u00E4ri\u00E4");
		btnPivitMri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bensistyo.setVisible(true);
			}
		});
		btnPivitMri.setBounds(10, 45, 185, 23);
		contentPane.add(btnPivitMri);
		
		btnPoistu = new JButton("Kirjaudu ulos");
		btnPoistu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Kirjaudutaanko ulos?", "HALOO!?",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					HinnatMaarat.this.setVisible(false);
				}
				if (result == JOptionPane.NO_OPTION) {
				}
			}
		});
		btnPoistu.setBounds(10, 79, 185, 23);
		contentPane.add(btnPoistu);
	}

}
