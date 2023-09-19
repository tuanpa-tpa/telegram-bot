package com.example.demo;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MyAmazingBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            // Kiểm tra nội dung tin nhắn để bắt sự kiện /start
            if (messageText.equals("/start")) {
                // Xử lý sự kiện khi người dùng gửi /start
                sendMessage(chatId, "Xin chào! Đây là thông điệp khi bạn gửi /start.");
            }
            // Kiểm tra nội dung tin nhắn để bắt sự kiện /regis
            else if (messageText.startsWith("/regis")) {
                // Xử lý sự kiện khi người dùng gửi /regis
                sendMessage(chatId, "nội dung sau: " + messageText.substring(messageText.indexOf(" ")));
                sendMessage(chatId, "Bạn đã gửi /regis.");
            }
            // Xử lý các sự kiện khác tùy theo nội dung tin nhắn
            else {
                // Xử lý các sự kiện khác ở đây
                sendMessage(chatId, "Xin lỗi, tôi không hiểu bạn muốn gì.");
            }


//            // Kiểm tra nếu người dùng đã đăng ký qua email
//            if (isUserRegisteredWithEmail(messageText)) {
//                // Người dùng đã đăng ký qua email, gửi thông tin đến email của họ
//                sendInfoToUserEmail(messageText, "Thông tin cần gửi");
//                sendMessage(chatId, "Thông tin đã được gửi đến email của bạn.");
//            } else {
//                sendMessage(chatId, "Vui lòng đăng ký qua email trước khi sử dụng tính năng này.");
//            }
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

    private void sendInfoToUserEmail(String email, String info) {
        // Gửi thông tin đến email của người dùng
        // Sử dụng EmailService hoặc thư viện gửi email của bạn
    }

    private boolean isUserRegisteredWithEmail(String email) {
        return true;
        // Kiểm tra trong cơ sở dữ liệu xem email đã đăng ký hay chưa
        // Trả về true nếu đã đăng ký, ngược lại trả về false
        // Đây là ví dụ đơn giản, bạn cần thay thế bằng logic xác thực thực tế
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

    public static void main(String[] args) throws TelegramApiException {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyAmazingBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }    }
}
