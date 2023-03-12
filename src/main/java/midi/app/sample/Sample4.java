package midi.app.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * 音を消すには鍵盤から指を放したという意味を持つノートオフを使います。
 * ノートオフは、ステータスバイト0x80から始まるチャンネルメッセージで、構造はノートオンと同じです。
 * 第1データバイトには消音するノートナンバーを、第2データバイトには消音の強さを表すベロシティを指定します。
 * ただし、第2データバイトは性質的に使われることはありません。
 * ノートオフのステータスバイトはShortMessageクラスのNOTE_OFFフィールドでも宣言されています。
 * ノートオフが音源に送られると、音源は指定されたチャンネルの再生中のノートナンバーの音を消します。
 * ノートオンで再生している音を消すときにこのメッセージを送信してください。
 * サンプル04は、ノートオンを送信した後200ミリ秒プログラムを停止させ、
 * 次の音を再生する前にノートオフで直前にノートオンで再生した音を消しています。
 * そのため、サンプル03のような不協和音にはなりません。ノートオフの他に、
 * ノートオンをベロシティ0で送信しても同じ効果が得られます。
 */
public class Sample4 {
	public static void main(String[] args) throws Exception {
		Receiver receiver = MidiSystem.getReceiver();
		ShortMessage message = new ShortMessage();

		for (int i = 60; i <= 72; i++) {
			message.setMessage(ShortMessage.NOTE_ON, i, 127);
			receiver.send(message, -1);
			Thread.sleep(200);
			message.setMessage(ShortMessage.NOTE_OFF, i, 127);
			receiver.send(message, -1);
		}

		System.out.println("Enter キーを押してプログラムを終了します>");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		reader.readLine();
	}
}
