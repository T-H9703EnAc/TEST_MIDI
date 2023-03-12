package midi.app.sample;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Sample8 extends JFrame implements ActionListener {
	private static final String SELECT_COMMAND = "Select";
	private static final String SEND_COMMAND = "Send";

	private JComboBox deviceList;
	private JTextField messageTextField;
	private JButton sendButton;

	private MidiDevice device;
	private Receiver receiver;

	public Sample8() {
		MidiDevice.Info[] devices = MidiSystem.getMidiDeviceInfo();

		deviceList = new JComboBox(devices);
		deviceList.setActionCommand(SELECT_COMMAND);
		deviceList.addActionListener(this);
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(2, 1));
		listPanel.add(new JLabel("デバイスの選択："));
		listPanel.add(deviceList);

		messageTextField = new JTextField("90 3C 7F");

		sendButton = new JButton("送信");
		sendButton.setActionCommand(SEND_COMMAND);
		sendButton.addActionListener(this);

		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		messagePanel.add(new JLabel("メッセージ："), BorderLayout.WEST);
		messagePanel.add(messageTextField, BorderLayout.CENTER);
		messagePanel.add(sendButton, BorderLayout.EAST);

		Container contentPane = getContentPane();
		contentPane.add(listPanel, BorderLayout.NORTH);
		contentPane.add(messagePanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals(SELECT_COMMAND)) {
			selectCommand();
		} else if (command.equals(SEND_COMMAND)) {
			sendCommand();
		}
	}

	public void selectCommand() {
		try {
			if (device != null)
				device.close();

			MidiDevice.Info deviceInfo = (MidiDevice.Info) deviceList.getSelectedItem();
			device = MidiSystem.getMidiDevice(deviceInfo);
			device.open();
			receiver = device.getReceiver();
		} catch (Exception err) {
			String msg = "例外が発生しました：\n" + err.toString();
			JOptionPane.showMessageDialog(this, msg);
		}
	}

	private void sendCommand() {
		if (receiver == null) {
			JOptionPane.showMessageDialog(this, "MIDI デバイスを選択してください");
			return;
		}

		String text = messageTextField.getText();
		text = text.trim();
		String[] midi = text.split(" ");
		if (midi.length != 3) {
			JOptionPane.showMessageDialog(this, "不正なメッセージです");
			return;
		}

		try {
			int status = Integer.parseInt(midi[0], 16);
			int data1 = Integer.parseInt(midi[1], 16);
			int data2 = Integer.parseInt(midi[2], 16);

			ShortMessage message = new ShortMessage();
			message.setMessage(status, data1, data2);
			receiver.send(message, -1);
		} catch (Exception err) {
			String msg = "例外が発生しました：\n" + err.toString();
			JOptionPane.showMessageDialog(this, msg);
		}
	}

	public static void main(String[] args) throws MidiUnavailableException {
		JFrame frame = new Sample8();
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
}
