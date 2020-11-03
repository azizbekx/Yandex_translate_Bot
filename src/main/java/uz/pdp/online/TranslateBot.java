package uz.pdp.online;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.online.enums.Language;

import uz.pdp.online.model.TrItem;
import uz.pdp.online.model.UserModel;
import uz.pdp.online.service.YandexTranslate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslateBot extends TelegramLongPollingBot {
    Map<Long, UserModel> userMap = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(TranslateBot.class);

    public String getBotUsername() {
        return "yandexapi_translatebot";
    }

    public String getBotToken() {
        return "1309181273:AAHQtEV4Fl5B_jh1ZUy25p4a5Hjkbu2WeJc";
    }

    public void onUpdateReceived(Update update) {
        String userMessage;
        UserModel user;

        SendMessage sendMessage = new SendMessage();
        if (update.hasMessage()) {

            userMessage = update.getMessage().getText();

            if (userMessage.equals("/start")) {
                user = new UserModel();
                userMap.put(update.getMessage().getChatId(), user);
                logger.info("{SUCCESS} added new User chat ID=>"+update.getMessage().getChatId());

                user.setId(update.getMessage().getChatId());
                sendMessage.setChatId(user.getId());
                sendMessage.setReplyMarkup(createSetLangReplyButton());
                sendMessage.setText("Welcome to the Yandex translate bot! \n"+
                        "Set yourself your native language and set another foreign language \n" +
                        "You can also choose your language using the /mylang command \n" +
                        "With the /tolang command, you can select a foreign language");
//                sendMessage.setText("Hello\n" +
//                        "Welcome to Yandex Translate bot! \uD83C\uDF0E\n" +
//                        "You can choose your native language and foreign language for translate word or phrase \n" +
//                        " \n" +
//                        "Bot translate in this method\n" +
//                        "/setMyLang  ➡️  /toLang\n" +
//                        "\n" +
//                        "This bot powered by Yandex Translate API");
            } else {
                user = userMap.get(update.getMessage().getChatId());
                logger.info("{SUCCESS} get User chat ID=>"+update.getMessage().getChatId());
                if (userMessage.equals("/mylang") ||
                        userMessage.equals("Mahalliy tilni o'rnatish")) {
                    sendMessage.setChatId(user.getId());
                    sendMessage.setReplyMarkup(createReplyButton());
                    if (user.getOwnLang() != null) {
                        sendMessage.setText("Your language is " + user.getOwnLang() + ". Choose your own language.");
                    } else {
                        sendMessage.setText("Choose your own language");

                    }
                    user.setLangQuery("own");
                }else if (userMessage.equals("/tolang")||
                            userMessage.equals("Xorijiy tilni o'rnatish")) {
                    sendMessage.setChatId(user.getId());
                    sendMessage.setReplyMarkup(createReplyButton());
                    if (user.getToLang() != null) {
                        sendMessage.setText("Translate to language is " + user.getToLang() + ". Choose your foreign language.");
                    } else {
                        sendMessage.setText("Choose Translate to language");

                    }
                    user.setLangQuery("to");
                }

                sendMessage.setChatId(user.getId());

                switch (userMessage) {
                    case "English":
                        sendMessage.setReplyMarkup(createSetLangReplyButton());
                        if (user.getLangQuery()!=null && user.getLangQuery().equals("own")) {
                            user.setOwnLang(Language.ENGLISH.code);
                            logger.info("{SUCCESS} set own native language => "+Language.ENGLISH.name());
                            sendMessage.setText("Success selected\n" +
                                    "Your own native language English");
                        } else {
                            user.setToLang(Language.ENGLISH.code);
                            logger.info("{SUCCESS} set for translate to language => "+Language.ENGLISH.name());
                            sendMessage.setText("Success selected\n" +
                                    "Translate to language English");
                        }
                        user.setLangQuery(null);

                        break;
                    case "Russian":
                        sendMessage.setReplyMarkup(createSetLangReplyButton());
                        if (user.getLangQuery()!=null && user.getLangQuery().equals("own")) {
                            user.setOwnLang(Language.RUSSIAN.code);
                            logger.info("{SUCCESS} set for translate to language => "+Language.RUSSIAN.name());
                            sendMessage.setText("Success selected\n" +
                                    "Your own native language Russian");
                        } else {
                            user.setToLang(Language.RUSSIAN.code);
                            logger.info("{SUCCESS} set for translate to language => "+Language.RUSSIAN.name());
                            sendMessage.setText("Success selected\n" +
                                    "Translate to language Russian");

                        }
                        user.setLangQuery(null);
                        break;
                    case "Turkish":
                        sendMessage.setReplyMarkup(createSetLangReplyButton());
                        if (user.getLangQuery()!=null && user.getLangQuery().equals("own")) {
                            user.setOwnLang(Language.TURKISH.code);
                            logger.info("{SUCCESS} set for translate to language => "+Language.RUSSIAN.name());
                            sendMessage.setText("Success selected\n" +
                                    "Your own native language Turkish");
                        } else {
                            user.setToLang(Language.TURKISH.code);
                            logger.info("{SUCCESS} set for translate to language => "+Language.TURKISH.name());
                            sendMessage.setText("Success selected\n" +
                                    "Translate to language Turkish");
                        }
                        user.setLangQuery(null);
                        break;
                    default:
                        if (user.getToLang() != null && user.getOwnLang() != null && user.getLangQuery()==null) {
                            sendMessage = yandexTranslate(sendMessage, user, userMessage);
                            break;
                        } else {
                            sendMessage.setText("Please set your native language and foreign language");
                        }
                }
            }


        }
        try {
            execute(sendMessage);
            logger.info("{SUCCESS} send sendMessage in execute");
        } catch (TelegramApiException e) {
            logger.error("{ERROR} don't send send message in Execute \n"+e);
            e.printStackTrace();
        }
    }

    public ReplyKeyboardMarkup createReplyButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keybord = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();


        row.add(new KeyboardButton("English"));
        row.add(new KeyboardButton("Russian"));
        row.add(new KeyboardButton("Turkish"));
        keybord.add(row);
        replyKeyboardMarkup.setKeyboard(keybord);
        return replyKeyboardMarkup;
    }
    public ReplyKeyboardMarkup createSetLangReplyButton(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keybord = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("Mahalliy tilni o'rnatish"));
        keybord.add(row);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Xorijiy tilni o'rnatish"));
        keybord.add(row2);

        replyKeyboardMarkup.setKeyboard(keybord);
        return replyKeyboardMarkup;
    }


    public SendMessage yandexTranslate(SendMessage sendMessage, UserModel user, String userMessage) {
        try {
            if (userMessage.contains(" ")) {
                userMessage = userMessage.replace(" ", "+");
            }

            String flags = Language.findByCode(user.getOwnLang()).flagEmoji +
                    "  ➡️ " + Language.findByCode(user.getToLang()).flagEmoji;

            TrItem trItem = YandexTranslate.getTranslation(
                    user.getOwnLang() + "-" + user.getToLang(), userMessage).getDef().get(0).getTr().get(0);

            if (userMessage.contains("+")){
                sendMessage.setText(flags+"\n" + user.getToLang().toUpperCase() + ":" +
                        " -(" + trItem.getPos() + ")  `" + trItem.getText() + "\n\n" );

            }else {
                sendMessage.setText(flags+"\n" + user.getToLang().toUpperCase() + ":" +
                        " -(" + trItem.getPos() + ")  " + trItem.getText() + "\n\n" +
                        "Example:\n" + trItem.getEx().get(0).getText() + "   =>️ " + trItem.getEx().get(0).getTr().get(0).getText());
            }
            if (trItem != null){
                logger.info("{SUCCESS} get translate Item");
            }else {
                logger.error("{ERROR} don't get translate item");
            }
        } catch (IOException e) {
          logger.error("[ERROR] Response is Null "+e);
        }
        return sendMessage;
    }
}
