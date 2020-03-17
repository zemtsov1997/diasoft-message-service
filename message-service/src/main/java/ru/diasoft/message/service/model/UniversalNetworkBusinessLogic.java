package ru.diasoft.message.service.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.service.entity.VerificationHistory;
import ru.diasoft.message.service.repository.VerificationHistoryRepository;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class UniversalNetworkBusinessLogic {

    private static final String GET_CODE_COMMAND = "Хочу получить код";
    private static final String ANSWER_CREATE_CODE = "Ваш проверочный код:";

    private static final String VERIFICATION_CODE_COMMAND = "Хочу верифицировать код №";
    private static final String ANSWER_VERIFICATION_CODE = "Ваш проверочный код верифицирован";
    private static final String ANSWER_NOT_VERIFICATION_CODE = "Ваш проверочный код не верифицирован";

    private static final String ERROR_CODE = "Извините, я вас не понял";

    @Autowired private VerificationHistoryRepository verificationHistoryRepository;

    public UniversalNetworkBusinessLogic() { }

    public String setMessage(String message, Long socialUserId) {

        if (message.toLowerCase().equals(GET_CODE_COMMAND.toLowerCase())) {
            Integer code = UniversalNetworkBusinessLogic.getRandomNumberInRange(1000,9999);

            VerificationHistory verificationHistory = new VerificationHistory();
            verificationHistory.setDateTime(LocalDateTime.now());
            verificationHistory.setProcessUuid(UUID.randomUUID());
            verificationHistory.setVerificationCode(code);
            verificationHistory.setVerificationStatus(VerificationStatus.SEND);
            verificationHistory.setSocialUserId(socialUserId);

            verificationHistoryRepository.save(verificationHistory);

            return ANSWER_CREATE_CODE + " " + code;
        } else if (message.toLowerCase().contains(VERIFICATION_CODE_COMMAND.toLowerCase())) {
            VerificationHistory verificationHistory = verificationHistoryRepository.findFirstBySocialUserIdOrderByDateTimeDesc(socialUserId);
            String code = message.toLowerCase().substring(message.toLowerCase().indexOf("№")+1);
            Integer integerCode = code.length() > 0 ? Integer.valueOf(code): null;
            if (code.length() > 0 && integerCode.equals(verificationHistory.getVerificationCode())) {
                verificationHistory.setVerificationStatus(VerificationStatus.CONFIRMED);
                return ANSWER_VERIFICATION_CODE;
            } else {
                verificationHistory.setVerificationStatus(VerificationStatus.REFUSED);
                return ANSWER_NOT_VERIFICATION_CODE;
            }
        }
        return ERROR_CODE;
    }

    private static Integer getRandomNumberInRange(int min, int max) {
        if (min >= max) throw new IllegalArgumentException("max must be greater than min");
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
