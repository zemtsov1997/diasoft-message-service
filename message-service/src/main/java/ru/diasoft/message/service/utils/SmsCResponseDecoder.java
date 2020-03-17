package ru.diasoft.message.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.diasoft.message.service.props.SmsCUrlResponseParams;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class SmsCResponseDecoder {

    private static final Logger logger = LogManager.getLogger(SmsCResponseDecoder.class);

    public void getDefualtSendSmsResponseEncoder(Map<String, Object> map) {
        if (map.containsKey((SmsCUrlResponseParams.ERROR))) {
            String msg = "Произошла ошибка: "+
                    map.get(SmsCUrlResponseParams.ERROR) +
                    " Код ошибки: " +
                    map.get(SmsCUrlResponseParams.ERROR_CODE);
            if (map.containsKey((SmsCUrlResponseParams.ERROR_ID))) {
                msg += " ID: " + map.get(SmsCUrlResponseParams.ERROR_ID);
            }
            logger.error(msg);
        } else {
            String msg = "Сообщение отправлено успешно. ID: " +
                    map.get(SmsCUrlResponseParams.ID) +
                    ", всего количество частей (при отправке SMS-сообщения): " +
                    map.get(SmsCUrlResponseParams.COUNT);
            logger.debug(msg);
        }
    }

    public BigDecimal getDefualtCostSmsResponseEncoder(Map<String, Object> map) {
        if (map.containsKey((SmsCUrlResponseParams.ERROR))) {
            String msg = "Произошла ошибка: "+
                    map.get(SmsCUrlResponseParams.ERROR) +
                    " Код ошибки: " +
                    map.get(SmsCUrlResponseParams.ERROR_CODE);
            if (map.containsKey((SmsCUrlResponseParams.ERROR_ID))) {
                msg += " ID: " + map.get(SmsCUrlResponseParams.ERROR_ID);
            }
            logger.error(msg);
        } else {
            String msg = "Запрос отправлен успешно. Стоимость сообщения: " +
                    map.get(SmsCUrlResponseParams.COST) +
                    " всего количество частей (при отправке SMS-сообщения): " +
                    map.get(SmsCUrlResponseParams.COUNT);
            logger.debug(msg);
            return new BigDecimal(map.get(SmsCUrlResponseParams.COST).toString().replaceAll(",", "."));
        }
        return null;
    }

    public BigDecimal getDefualtBalanceResponseEncoder(Map<String, Object> map) {
        if (map.containsKey((SmsCUrlResponseParams.ERROR))) {
            String msg = "Произошла ошибка: "+
                    map.get(SmsCUrlResponseParams.ERROR) +
                    " Код ошибки: " +
                    map.get(SmsCUrlResponseParams.ERROR_CODE);
            if (map.containsKey((SmsCUrlResponseParams.ERROR_ID))) {
                msg += " ID: " + map.get(SmsCUrlResponseParams.ERROR_ID);
            }
            logger.error(msg);
        } else {
            String msg = "Запрос отправлен успешно." +
                    " Tекущее состояние баланса: " + map.get(SmsCUrlResponseParams.BALANCE) +
                    " Текущее состояние установленного кредита: " + map.get(SmsCUrlResponseParams.CREDIT) +
                    " Валюта: " + map.get(SmsCUrlResponseParams.CURRENCY);
            logger.debug(msg);
            Float balance = Float.valueOf(map.get(SmsCUrlResponseParams.BALANCE).toString());
            return BigDecimal.valueOf(balance);
        }
        return null;
    }

}
