package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;
import java.time.DateTimeException;
import java.time.LocalTime;


public class ExceptionProcessor implements Processor {
    public LocalTime time;

    @Override
    public Message process(Message message) {
      if(time==null)
          this.time = LocalTime.now();
if (this.time.getSecond()%2 ==0){
    throw new DateTimeException("An exception in an even second");
}        return message;
    }
}
