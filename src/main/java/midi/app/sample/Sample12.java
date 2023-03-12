package midi.app.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import midi.app.MidiConst.NOTE_4;

public class Sample12 {
	public static void main(String[] args) throws Exception {
		Receiver receiver = MidiSystem.getReceiver();
		ShortMessage message = new ShortMessage();

		for (NOTE_4 note : NOTE_4.values()) {
			message.setMessage(ShortMessage.NOTE_ON, note.getScale(), 127);
			receiver.send(message, -1);
			Thread.sleep(200);
			message.setMessage(ShortMessage.NOTE_OFF, note.getScale(), 127);
			receiver.send(message, -1);
		}
		System.out.println("Enter キーを押してプログラムを終了します>");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		reader.readLine();

	}
}
