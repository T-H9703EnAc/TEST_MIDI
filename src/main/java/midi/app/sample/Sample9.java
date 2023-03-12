package midi.app.sample;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

public class Sample9 {

	public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException {
		Sequence sequence = new Sequence(Sequence.PPQ, 480);
	    Track track = sequence.createTrack();
	    track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 127), 480 * 4));
	    track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 0), 480 * 5));
	    track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, 64, 127), 480 * 5));
	    track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, 64, 0), 480 * 6));
	    track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, 67, 127), 480 * 6));
	    track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, 67, 0), 480 * 7));
	    Synthesizer synthesizer = MidiSystem.getSynthesizer();
	    synthesizer.open();
	    Sequencer sequencer = MidiSystem.getSequencer(false);
	    sequencer.open();
	    sequencer.setSequence(sequence);
	    sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());
	    System.out.println("start");
	    sequencer.start();

	}

}
