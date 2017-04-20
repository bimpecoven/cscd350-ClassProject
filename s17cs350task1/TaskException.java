package s17cs350task1;


/**
 * Created by: Impecoven, Blake
 * Created on: 4/14/17
 */
public class TaskException extends RuntimeException {
    private String message;

    public TaskException(String message) {
        super(message);
    }//end EVC

    public TaskException() {
        super("Default task error.");
    }//end DVC
}//end class
