import javax.swing.JFrame;


public class BlockImageController {
	
	void createWindow(){
		JFrame frame = new JFrame("BlockImage");
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
