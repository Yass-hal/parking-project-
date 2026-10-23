package parking.refactored;

public class DatabaseLogger implements Logger{
    @Override
    public void log(String message){
        System.out.println("[DATABASE] "+message);
    }
}
