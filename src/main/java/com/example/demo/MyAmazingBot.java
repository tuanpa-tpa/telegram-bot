package com.example.demo;


import com.example.demo.model.BotState;
import com.example.demo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MyAmazingBot extends TelegramLongPollingBot {
    private Map<Long, BotState> userStates = new HashMap<>();
    private final UserRepository userRepository;

    //    public void sendMainMenu(Long chatId) {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId.toString());
//        message.setText("Chọn một tùy chọn:");
//
//        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
//
//        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
//
//        // Tạo nút tùy chọn 1
//        List<InlineKeyboardButton> row1 = new ArrayList<>();
//        InlineKeyboardButton button1 = new InlineKeyboardButton();
//        button1.setText("Tùy chọn 1");
//        button1.setCallbackData("option1");
//        row1.add(button1);
//
//        // Tạo nút tùy chọn 2
//        List<InlineKeyboardButton> row2 = new ArrayList<>();
//        InlineKeyboardButton button2 = new InlineKeyboardButton();
//        button2.setText("Tùy chọn 2");
//        button2.setCallbackData("option2");
//        row2.add(button2);
//
//        // Thêm các nút vào menu
//        keyboard.add(row1);
//        keyboard.add(row2);
//
//        markup.setKeyboard(keyboard);
//        message.setReplyMarkup(markup);
//
//        try {
//            execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            processCommand(chatId, messageText);
        }
    }

    private void processCommand(Long chatId, String command) {
        switch (command) {
            case "/start":
                // Xử lý câu lệnh /start
                sendMessage(chatId, "Xin chào! Chào mừng bạn đến với Csign.");
                break;
            case "/regis":
                // Kiểm tra xem người dùng đã có trạng thái đang chờ nhập email chưa
                if (userStates.get(chatId) == BotState.WAITING_FOR_EMAIL) {
                    sendMessage(chatId, "Bạn đã ở trong trạng thái đang chờ nhập email. Vui lòng nhập email của bạn.");
                } else {
                    // Nếu chưa, chuyển người dùng vào trạng thái đang chờ nhập email
                    userStates.put(chatId, BotState.WAITING_FOR_EMAIL);
                    sendMessage(chatId, "Xin vui lòng nhập email của bạn.");
                }
                break;
            default:
                // Xử lý các câu lệnh khác
                sendMessage(chatId, "Tôi không hiểu câu lệnh này: " + command);
                break;
        }
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Thiết lập danh sách các câu lệnh bot hỗ trợ
    @Override
    public void onRegister() {
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("start", "Bắt đầu sử dụng"));
        commands.add(new BotCommand("regis", "Đăng ký"));
        commands.add(new BotCommand("regisotp", "Xác thực OTP"));
        SetMyCommands setMyCommands = new SetMyCommands();
        setMyCommands.setCommands(commands);
        try {
            execute(setMyCommands);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "pat_cmc_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "6670723908:AAEuURQNvDnysYsxfHLxWYzVA8I7ujwMIXA";
    }
}
