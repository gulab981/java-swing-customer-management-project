import javax.swing.UIManager;
class MainLoginPage{
	public static void main(String[] args)throws Exception
	{
		UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
		new LoginPage();
	}
}