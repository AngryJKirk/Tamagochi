package angry.naz;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * Created by Deadliest Baddest Fattest Motherfucker in Universe on 23.06.2016.
 */


// TODO make it work
public class TelegramBot extends TelegramLongPollingBot{
    String chatID = null;
    Message message;

    public void registerBot(){
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void notify(String answer){
         sendMsg(answer);
    }


    @Override
    public void onUpdateReceived(Update update) {
        message = update.getMessage();
        //getMessage();
        if (chatID==null) setChatID();
        if (message != null && message.hasText()) {
            sendMsg(message.getText());
        }
//            if (message.getText().equals("/help"))
//                sendMsg(message, "Привет, я робот");
//            else
//                sendMsg(message, "Я не знаю что ответить на это");
//        }
    }

    public String getMessage(){
        //sendMsg(message.getText());
        return message.getText();
    }




    private void sendMsg(String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatID);
       // sendMessage.setReplayToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername(){
        return "jvmtamagotchi_bot";
    }
    @Override
    public String getBotToken(){
        return "238621355:AAG2FovcN7RPN759Fkd_Qe5-rGwdZ4iPJPQ";
    }
    private void setChatID(){
        while(true){
           // System.out.println("blabla");
            if (message != null && message.getText().equals("/start")) {
               System.out.println("blabla");

                this.chatID = message.getChatId().toString();
                sendMsg(chatID);
                break;
            }
        }
    }

    public boolean isBotAlive(){
        if (chatID!=null) return true;
            else return false;
    }
}
