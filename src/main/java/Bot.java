import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.IOException;
import java.util.Objects;

public class Bot {
    private static final String token = "5247380242:AAEaCpLJi-e0e3QxnmZCslPpWa5Sppel3s8";
    private static final String usdLink = "http://www.finmarket.ru/currency/rates/?id=10123&pv=1#archive";
    private static final String eurLink = "http://www.finmarket.ru/currency/rates/?id=10123&pv=1&cur=52170&bd=26&bm=2&by=2022&ed=29&em=3&ey=2022&x=46&y=9#archive";
    private static final String rubLink = "http://www.finmarket.ru/currency/rates/?id=10123&pv=1&cur=52144#archive";
    private final TelegramBot bot = new TelegramBot(token);


    public void server() {
        bot.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

    private void process(Update update) {
        Message message = update.message();

        long chatId = message.chat().id();

        if(Objects.equals(message.text(), "/start")){
            bot.execute(new SendMessage(chatId, "Выберите валюту").replyMarkup(new ReplyKeyboardMarkup("USD", "EUR" , "RUB")));
        }

        if(Objects.equals(message.text(), "USD") || Objects.equals(message.text(), "usd")){
            try {
                bot.execute(new SendMessage(chatId , Main.getCurr(usdLink)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(Objects.equals(message.text(), "RUB") || Objects.equals(message.text(), "rub")){
            try {
                bot.execute(new SendMessage(chatId , Main.getCurr(rubLink)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(Objects.equals(message.text(), "EUR") || Objects.equals(message.text(), "eur")){
            try {
                bot.execute(new SendMessage(chatId , Main.getCurr(eurLink)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }


}
