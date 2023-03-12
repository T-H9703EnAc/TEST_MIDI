package midi.app.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/** 
 * NOTE_ONフィールドは0x90を表します。
 * 1 番以外のチャンネルを指定したい場合は、バイト単位の論理和演算子 | で結合していください。
 * ノートオンの第1データバイトには音の高さを表すノートナンバーを、
 * 第2データバイトには強さを表すベロシティを指定します。ノートナンバーは、
 * ピアノの中心のC(音名は米式)を60とし、インクリメントするごとに半音上がり、
 * デクリメントするごとに半音下がります。61であればC#、59であればBとなります。
 * チューニングなどで基準となる440HzのAは69です。ベロシティは0が消音、127が最も強い音を表します。
 * サンプル01は、デフォルトのReceiverを取得してMIDIメッセージのノートオンを送信しています。
 * Receiverが適切な音源であればノートナンバー60に対応しているCを再生するでしょう。
 * プログラムの最後では、標準入力から行を読み込んでいますが、
 * これはプログラムが即座に終了するとReceiverが割り当てているシステムリソースも解放されて再生が停止されるのを防ぐためです。*/

public class Sample1 {

	public static void main(String[] args) throws Exception {
		Receiver receiver = MidiSystem.getReceiver();
		ShortMessage message = new ShortMessage();

		message.setMessage(ShortMessage.NOTE_ON, 60, 127);
		receiver.send(message, -1);

		System.out.println("Enter キーを押してプログラムを終了します>");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		reader.readLine();

	}

}
