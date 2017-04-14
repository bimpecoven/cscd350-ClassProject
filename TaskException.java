package s17cs350task1;


/**
 * Created by: Impecoven, Blake
 * Created on: 4/14/17
 */
public class TaskException extends RuntimeException {
    private String message;

    public TaskException(String message) {
        this.message = message;
    }//end EVC

    public TaskException() {

    }//end DVC
}//end class
