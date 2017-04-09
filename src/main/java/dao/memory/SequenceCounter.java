package dao.memory;

public class SequenceCounter {
	static int sequence = 0;
	
	public static int getId() {
		return ++sequence;
	}
}
