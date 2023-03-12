package midi.app.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * MIDIデバイスのチャンネルには音色が割り当てられています。
 * MIDIデバイスが初期設定値である場合、これまでのプログラムで再生した音はすべてピアノだったと思います。
 * チャンネルの音色を変更することで、ヴァイオリンやオルガンなど、他の楽器の音を再生できます。
 * ただし、楽器の音質は音源に依存するため、本物に近い音質を求めるのであれば相応のハードウェア音源が必要になるでしょう。
 * 音色の変更にはプログラムチェンジと呼ばれるチャンネルメッセージを使います。
 * プログラムチェンジは、ステータスバイトに0xC0～0xCFの値を指定し、第1データバイトに音色番号を指定します。
 * 第2データバイトは持たないため無視されます。
 * プログラムチェンジのステータスバイトはShortMessageクラスのPROGRAM_CHANGEで宣言されています。
 * 音色番号と音色の関係は音源に依存しますが、その関係が音源によって異なっていては、
 * 同じMIDIメッセージでも音源ごとに違う音になってしまいます。
 * そこで、音源の音色マップ(音色と番号の対応表)をGENERAL MIDI、略称GMと呼ばれる標準規格が定めています。
 * よって、GMに対応しているデバイスであれば、GMで定められている音色を再現してくれます。
 * サンプル05は、ノートオンを送信する前にプログラムチェンジを送信して音色を49番に変更しています。
 * GMの音色マップでは49番はString Ensembles 1と定められているため、その後のノートオンで再生した音はストリングスになります。
 *
 */
public class Sample5 {

	public static void main(String[] args) throws Exception {
		Receiver receiver = MidiSystem.getReceiver();
		ShortMessage message = new ShortMessage();

		message.setMessage(ShortMessage.PROGRAM_CHANGE, 49, 0);
		receiver.send(message, -1);

		message.setMessage(ShortMessage.NOTE_ON, 60, 127);
		receiver.send(message, -1);

		System.out.println("Enter キーを押してプログラムを終了します>");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		reader.readLine();

	}

}
