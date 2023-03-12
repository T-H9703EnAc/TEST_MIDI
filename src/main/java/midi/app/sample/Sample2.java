package midi.app.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * MIDIプロトコルはシリアル転送方式であり、複数のメッセージを同時に送ることはできません。
 * そのため和音を完全に表現する方法はなく、複数のメッセージを逐次、瞬間的に送信する以外にありません。
 * また、音源においてもコンピュータである以上は、受け取ったメッセージを順に処理するしかありません。
 * しかし、違和感を感じるほどの問題が発生することはないでしょう。
 * サンプル02は、for文による繰り返しで連続にノートナンバー60、64、67を表すノートオンを送信しています。
 * 当然、メッセージは逐次的に送信されますが、極めて短い間隔なので和音として認識することができます。
 * 実行するとC、E、Gの音が重なって聞こえるでしょう。
 *
 */
public class Sample2 {

	public static void main(String[] args) throws Exception {
        int[] notenums = { 60, 64, 67 };

        Receiver receiver = MidiSystem.getReceiver();
        ShortMessage message = new ShortMessage();

        for(int num : notenums) {
            message.setMessage(ShortMessage.NOTE_ON, num, 127);
            receiver.send(message, -1);
        }

        System.out.println("Enter キーを押してプログラムを終了します>");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();

	}

}
