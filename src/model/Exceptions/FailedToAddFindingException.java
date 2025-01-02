package src.model.Exceptions;

public class FailedToAddFindingException extends Exception{
    public FailedToAddFindingException(Exception message){
        super("Failed to add finding: " + message);
    }
}
