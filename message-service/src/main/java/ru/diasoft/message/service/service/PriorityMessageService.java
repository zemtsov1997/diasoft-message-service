package ru.diasoft.message.service.service;

import org.springframework.stereotype.Service;
import ru.diasoft.message.api.comparator.MessageComparator;
import ru.diasoft.message.api.model.IMessage;

import java.util.Collections;
import java.util.List;

@Service
public class PriorityMessageService {

    public void sort(List<IMessage> list) {
        Collections.sort(list, new MessageComparator());
    }

}
