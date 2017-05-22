import javax.swing.JFrame;

public class Calculator {

	public static void main(String[] args){
		Gui g = new Gui();
		g.setSize(250, 400);
		g.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		g.setVisible(true);
		g.setResizable(false);
	}
}
