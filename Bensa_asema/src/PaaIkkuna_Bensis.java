import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

public class PaaIkkuna_Bensis extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnAdmin;
	private JButton btnKayttaja;

	private AdminLogin adminlogin = new AdminLogin();
	private PaaIkkuna_Kayttaja paaikkunakayttaja = new PaaIkkuna_Kayttaja();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaaIkkuna_Bensis frame = new PaaIkkuna_Bensis();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaaIkkuna_Bensis() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PaaIkkuna_Bensis.class.getResource("/img/Bensis kuva.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Suljetaanko ohjelma?", "Varoitus!",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
				if (result == JOptionPane.NO_OPTION) {
				}
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 302, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnKayttaja = new JButton("K\u00E4ytt\u00E4j\u00E4 kirjautuminen");
		btnKayttaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paaikkunakayttaja.setVisible(true);
			}
		});
		btnKayttaja.setBounds(10, 11, 265, 60);
		contentPane.add(btnKayttaja);
		
		btnAdmin = new JButton("Admin kirjautuminen");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminlogin.setVisible(true);
			}
		});
		btnAdmin.setBounds(10, 76, 265, 60);
		contentPane.add(btnAdmin);
	}

}
