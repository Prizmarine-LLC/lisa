package com.accountingservices.lisa.bot;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Bot extends TelegramLongPollingBot {

    private final Long chatId = -1001791234990L;

    @Override
    public String getBotUsername() {
        return "LisaAccountingServicesBot";
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {

        var msg = update.getMessage();
        if (msg.isCommand()) {
            if (update.hasMessage() && "/file@x".equals(update.getMessage().getText()) || "/file".equals(update.getMessage().getText())) {
                sendExcelFile();
            }
            return;
        }
//        if (update.hasMessage() && msg.isCommand()) {
//            if (msg.getText().equals("/getfile")) {
//                sendExcelFile();
//            }
//            return;
//        }
    }


    public void sendExcelFile() {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream("requests.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SendDocument document = new SendDocument();
        document.setChatId(chatId.toString());
        document.setDocument(new InputFile(stream, "Заявки.xlsx"));
        try {
            execute(document);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendText(String text) {
        SendMessage sm = SendMessage.builder()
                .chatId(chatId.toString()) //Who are we sending a message to
                .text(text).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}