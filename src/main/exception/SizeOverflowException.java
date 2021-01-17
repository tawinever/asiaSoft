package src.main.exception;

public class SizeOverflowException extends Exception {
    String msg;
    public SizeOverflowException(String s) {
        msg = s;
    }

    @Override
    public String toString() {
        return ("SizeOverflowException: " + msg);
    }
}
