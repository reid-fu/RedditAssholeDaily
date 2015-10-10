package io;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JIOManager implements IOManager {
	private static int dialogWidth = 400, dialogHeight = 200;
	@Override
	public String inputUser() {
		UsernameDialog dialog = new UsernameDialog();
		dialog.setSize(dialogWidth, dialogHeight);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		return dialog.cancelled() ? null : dialog.username();
	}
	@SuppressWarnings("serial")
	private class UsernameDialog extends JDialog {
		private JTextField userField;
		private boolean cancelled;
		public UsernameDialog(){
			super((Frame)null, "RedditAssholeDaily", true);
			this.cancelled = true;
			setLayout(new BorderLayout());
			add(this.inputPanel(), BorderLayout.CENTER);
			add(this.confirmPanel(), BorderLayout.PAGE_END);
		}
		public String username(){
			return this.userField.getText();
		}
		public boolean cancelled(){
			return cancelled;
		}
		private JPanel inputPanel(){
			JPanel panel = new JPanel();
			JTextArea instructions =
					new JTextArea("This app analyzes comments by Reddit "
					+ "users to see how much of an asshole they are. Enter a Reddit "
					+ "username in the box below:");
			instructions.setSize(dialogWidth-20, dialogHeight/2);
			instructions.setLineWrap(true);
			instructions.setBackground(Color.gray);
			panel.add(instructions);
			this.userField = new JTextField(25);
			panel.add(this.userField);
			return panel;
		}
		private JPanel confirmPanel(){
			JPanel panel = new JPanel();
			JButton ok = new JButton("OK");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cancelled = false;
					setVisible(false);
				}
			});
			panel.add(ok);
			JButton cancel = new JButton("Cancel");
			cancel.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			panel.add(cancel);
			return panel;
		}
	}
}
