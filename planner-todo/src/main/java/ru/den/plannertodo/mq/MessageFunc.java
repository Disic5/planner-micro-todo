//package ru.den.plannertodo.mq;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.Message;
//import ru.den.plannertodo.service.TestDataService;
//
//import java.util.function.Consumer;
//
//@RequiredArgsConstructor
//@Configuration
//public class MessageFunc {
//
//    private final TestDataService dataService;
//
//    @Bean
//    public Consumer<Message<Long>> messageConsumer(){
//        return message -> dataService.init(message.getPayload());
//    }
//}
