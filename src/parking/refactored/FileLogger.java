package parking.refactored;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger{

    private final String filename;

    public FileLogger(String filename){
        this.filename=filename;
    }

    @Override
    public void log(String message){
        try(FileWriter writer=new FileWriter(filename,true)){
            writer.write(message+"\n");
        }catch(IOException e){
            throw new RuntimeException("Error writing log file",e);
        }
    }

}
