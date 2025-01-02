package src.model.Exceptions;

public class NoFindingException extends RuntimeException{
    public NoFindingException() {
        System.out.println("Position has no finding.");
    }
}
