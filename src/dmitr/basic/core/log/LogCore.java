package dmitr.basic.core.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogCore {

    public static void log(String message, LogType logType) {
        String caption = logType.getCaption();
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.printf("[%s] |%s| %s\n", timeStamp, caption, message);
    }

}
