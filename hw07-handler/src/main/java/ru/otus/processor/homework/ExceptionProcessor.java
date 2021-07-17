package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Supplier;


public class ExceptionProcessor implements Processor {


    private final Supplier<LocalDateTime> dateTimeSupplier;

    public ExceptionProcessor(Supplier<LocalDateTime> dateTimeSupplier) {
        this.dateTimeSupplier=dateTimeSupplier;

    }

    @Override
    public Message process(Message message) {
        if (dateTimeSupplier.get().getSecond() % 2 == 0) {
            throw new DateTimeException("An exception in an even second");
        }
        return message;
    }
}
