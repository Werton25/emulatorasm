import java.util.ArrayList;

/**
 * Created by werton on 12.01.14.
 *
 * Это переписанный код Ермоченко с одним дополнением ввиде массива регистров
 */
public class Memory {
    private char n;
    private char cells[];
    private int register[];

    public Memory(char size) {
        this.n = size;
        this.cells = new char[size];
        this.register = new int[8];
    }

    public char getSize() {
        return this.n;
    }
}
