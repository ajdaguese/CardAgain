package graphics;

import javax.swing.JPanel;

public class GameJPanel
{
	private JPanel userPanel;
	private int panelOwner = -1;
	public GameJPanel()
	{
		userPanel = new JPanel();
	}
	public void setPlayer(int player)
	{
		panelOwner = player;
	}
	public int getPlayer()
	{
		return panelOwner;
	}
	public JPanel getPanel()
	{
		return userPanel;
	}
}
