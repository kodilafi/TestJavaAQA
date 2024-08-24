package MyUtils;

import io.qameta.allure.Attachment;

public class MyUtils {
    public static final String URL_DEMOBLASE = "https://www.demoblaze.com/";

    public static final String URL_REQRES = "https://reqres.in/";

    public static final String USERNAME = "Аркадий";

    public static final String PASSWORD = "08761239";

    @Attachment(value = "Информация:", type = "text/plain")
    public static String TextLog(String message) {
        return message;
    }
}
