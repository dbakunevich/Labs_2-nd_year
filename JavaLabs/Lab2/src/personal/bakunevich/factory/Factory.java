package personal.bakunevich.factory;

import personal.bakunevich.commands.Command;
import personal.bakunevich.exeptions.MyExceptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

public class Factory implements IFactory {
    private final Map<String, Class<?>> commandsMap = new HashMap<>();

    public Factory() {
        InputStream propIn = getClass().getResourceAsStream("commands.properties");

        PropertyResourceBundle bundle;
        try {
            bundle = new PropertyResourceBundle(propIn);
        } catch (IOException e) {
            //log
            throw new MissingResourceException("Unable to commands.properties bundle", getClass().getName(), "");
        }
        var propKeys = bundle.getKeys();

        while (propKeys.hasMoreElements()){
            String key = propKeys.nextElement();
            Class<?> commandClass;
            try {
                commandClass = Class.forName(bundle.getString(key));
            } catch (ClassNotFoundException e){
                //log
                throw new RuntimeException();
            }

            if (commandClass.getSuperclass() == Command.class) {
                commandsMap.put(key, commandClass);
            }

        }
        try {
            propIn.close();
        } catch (IOException e) {
            // log
            e.printStackTrace();
        }
    }

    @Override
    public Command getCommand(String command) throws MyExceptions {
        var commandClass = commandsMap.get(command);
        if (commandClass == null){
            throw new MyExceptions("Incorrect input command");
        }
        Command cmd;
        try {
            cmd = (Command) commandClass.getDeclaredConstructor().newInstance();
        } catch (Exception e){
            //log
            throw new RuntimeException();
        }
        return cmd;
    }
}
