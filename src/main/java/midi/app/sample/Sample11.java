package midi.app.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class Sample11 {
	public static void main(String[] args) throws Exception {
		Receiver receiver = MidiSystem.getReceiver();
		ShortMessage message = new ShortMessage();

		System.out.println("Enter キーを押してプログラムを終了します>");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("開始数値");
			String start = reader.readLine();
			if (start.equals("")) {
				break;
			}
			int startPow = Integer.parseInt(start);

			message.setMessage(ShortMessage.NOTE_ON, startPow, 127);
			receiver.send(message, -1);
			Thread.sleep(200);
			message.setMessage(ShortMessage.NOTE_OFF, startPow, 127);
			receiver.send(message, -1);

		}

	}
}
