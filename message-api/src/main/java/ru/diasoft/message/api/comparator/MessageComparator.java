package ru.diasoft.message.api.comparator;

import ru.diasoft.message.api.model.IMessage;
import ru.diasoft.message.api.model.discord.IDiscordMessage;
import ru.diasoft.message.api.model.mail.IMail;
import ru.diasoft.message.api.model.notification.IPushMessage;
import ru.diasoft.message.api.model.sms.ISms;
import ru.diasoft.message.api.model.telegram.ITelegramMessage;
import ru.diasoft.message.api.model.vk.IVkMessage;

import java.util.Comparator;

public class MessageComparator implements Comparator<IMessage> {

    @Override
    public int compare(IMessage o1, IMessage o2) {
        if (o1 instanceof IMail) {
            if (o2 instanceof IMail) return 0; else
            if (o2 instanceof IPushMessage) return -1; else
            if (o2 instanceof IVkMessage) return -1; else
            if (o2 instanceof ITelegramMessage) return -1; else
            if (o2 instanceof IDiscordMessage) return -1; else
            if (o2 instanceof ISms) return -1;
        } else
        if (o1 instanceof IPushMessage) {
            if (o2 instanceof IMail) return 1; else
            if (o2 instanceof IPushMessage) return 0; else
            if (o2 instanceof IVkMessage) return -1; else
            if (o2 instanceof ITelegramMessage) return -1; else
            if (o2 instanceof IDiscordMessage) return -1; else
            if (o2 instanceof ISms) return -1;
        } else
        if (o2 instanceof IVkMessage) {
            if (o2 instanceof IMail) return 1; else
            if (o2 instanceof IPushMessage) return 1; else
            if (o2 instanceof IVkMessage) return 0; else
            if (o2 instanceof ITelegramMessage) return -1; else
            if (o2 instanceof IDiscordMessage) return -1; else
            if (o2 instanceof ISms) return -1;
        } else
        if (o2 instanceof ITelegramMessage) {
            if (o2 instanceof IMail) return 1; else
            if (o2 instanceof IPushMessage) return 1; else
            if (o2 instanceof IVkMessage) return 1; else
            if (o2 instanceof ITelegramMessage) return 0; else
            if (o2 instanceof IDiscordMessage) return -1; else
            if (o2 instanceof ISms) return -1;
        } else
        if (o2 instanceof IDiscordMessage) {
            if (o2 instanceof IMail) return 1; else
            if (o2 instanceof IPushMessage) return 1; else
            if (o2 instanceof IVkMessage) return 1; else
            if (o2 instanceof ITelegramMessage) return 1; else
            if (o2 instanceof IDiscordMessage) return 0; else
            if (o2 instanceof ISms) return -1;
        } else
        if (o2 instanceof ISms) {
            if (o2 instanceof IMail) return 1; else
            if (o2 instanceof IPushMessage) return 1; else
            if (o2 instanceof IVkMessage) return 1; else
            if (o2 instanceof ITelegramMessage) return 1; else
            if (o2 instanceof IDiscordMessage) return 1; else
            if (o2 instanceof ISms) return 0;
        }
        return 0;
    }
}
