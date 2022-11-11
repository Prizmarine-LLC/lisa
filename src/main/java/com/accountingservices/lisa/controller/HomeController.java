package com.accountingservices.lisa.controller;

import com.accountingservices.lisa.bot.Bot;
import com.accountingservices.lisa.excel.ExcelService;
import com.accountingservices.lisa.models.UserRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {
    private ExcelService excelService;
    private Bot bot;

    @SneakyThrows
    @Autowired
    public HomeController() {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        bot = new Bot();
        botsApi.registerBot(bot);
        excelService = new ExcelService();
    }

    @ModelAttribute("organizationTypes")
    public List<String> organizationTypes(Model model) {
        List<String> organizationTypes = new ArrayList<>();
        organizationTypes.add("ООО");
        organizationTypes.add("ИП");
        organizationTypes.add("Другое");
        return organizationTypes;
    }


    @ModelAttribute("userRequest")
    public UserRequest userRequest() {
        return new UserRequest();
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @SneakyThrows
    @PostMapping("/")
    public String add(
            @Valid
//            @ModelAttribute("userRequest")
                    UserRequest userRequest,
            Errors errors,
            Model model
    ) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Ошибка при отправке заявки!");
            return "index";
        }
        model.addAttribute("message", "Заявка успешно отправлена!");


        dataProcessing(userRequest);


        return "redirect:";
    }

    private void dataProcessing(UserRequest userRequest) {
        excelService.addUserRequestExcel(userRequest);
        bot.sendText(userRequest.toString());
    }


}
