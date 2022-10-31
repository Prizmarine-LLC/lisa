package com.accountingservices.lisa;

import com.accountingservices.lisa.bot.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class LisaApplication {

    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(LisaApplication.class, args);
    }

}
