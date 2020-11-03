package uz.pdp.online;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import uz.pdp.online.service.YandexTranslate;
import java.io.IOException;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();
        try {
            api.registerBot(new TranslateBot());
            logger.info("{SUCCESS} registered TranslateBot");
        } catch (TelegramApiRequestException e) {
            logger.error("{ERROR} don't register Translate Bot\n"+e);
        }
    }

}
