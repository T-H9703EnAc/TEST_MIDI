package midi.app.sample;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;

public class Sample7 {
	public static void main(String[] args) {
        System.out.println("--このシステムのMIDIデバイス一覧--");
        for(MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
            System.out.println(info.getName() + " " + info.getVersion());
            System.out.println("供給会社名：" + info.getVendor());
            System.out.println("説明：" + info.getDescription());
            System.out.println("---");
        }
    }
}
