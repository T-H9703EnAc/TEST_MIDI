package midi.app;

public class MidiConst {

	/** MID1の鍵盤キー*/
	public enum NOTE_4 {
		// ド
		C4(60),
		// ド#
		CS4(61),
		// レ
		D4(62),
		// レ#
		DS4(63),
		// ミ
		E4(64),
		// ファ
		F4(65),
		// ファ#
		FS4(66),
		// ソ
		G4(67),
		// ソ#
		GS4(68),
		// ラ
		A4(69),
		// ラ#
		AS4(70),
		// シ		
		B4(71);

		private int scale;

		private NOTE_4(int scale) {
			this.scale = scale;
		}

		public int getScale() {
			return scale;
		}
	}
}
