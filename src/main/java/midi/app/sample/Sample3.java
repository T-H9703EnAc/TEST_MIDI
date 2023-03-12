package midi.app.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * MIDIには音の長さを表すパラメータが存在しないため、
 * ノートオンを送信するとプログラムが終了するまで再生され続けます。
 * ピアノのような打鍵楽器であれば自然に消音しますが、
 * 弦楽器や管楽器であれば音が鳴り続けることになります。
 * ノートオンは、ピアノの鍵盤を押したということを意味するメッセージなので、
 * 音源はノートオンによって鍵盤が叩かれたと認識します。
 * 当然、鍵盤から指を離さない限り音が持続することになります。
 * サンプル03は、ノートナンバー60の音Cから半音ずつ上げてオクターブ上のCまで200ミリ秒の間隔でノートオンを送信するプログラムです。
 * 直前に送信したメッセージによって再生された音が残っている状態で次の半音上の音が再生されるため、不協和音となって聞こえるでしょう
 *s
 */
public class Sample3 {

	public static void main(String[] args) throws Exception {
        Receiver receiver = MidiSystem.getReceiver();
        ShortMessage message = new ShortMessage();

        for(int i = 60 ; i <= 72 ; i++) {
            message.setMessage(ShortMessage.NOTE_ON, i, 127);
            receiver.send(message, -1);
            Thread.sleep(200);
        }

        System.out.println("Enter キーを押してプログラムを終了します>");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
    }

}
