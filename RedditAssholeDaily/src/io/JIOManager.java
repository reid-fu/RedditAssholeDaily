package io;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.*;

public class JIOManager implements IOManager {
	@Override
	public String inputUser() {
		JDialog dialog = new JDialog((Frame)null, true);
		dialog.setLayout(new BorderLayout());
		dialog.add(this.confirmPanel(), BorderLayout.PAGE_END);
		return null;
	}
	private JPanel inputPanel(){
		JPanel panel = new JPanel();
		return panel;
	}
	private JPanel confirmPanel(){
		JPanel panel = new JPanel();
		panel.add(new JButton("OK"));
		panel.add(new JButton("Cancel"));
		return panel;
	}
}
