package ru.diasoft.message.api.model;

import ru.diasoft.message.api.enums.VerificationStatus;

import java.util.UUID;

public interface IVerificationMessage {

    UUID getProcessUuid();

    VerificationStatus getVerificationStatus();

    Integer getVerificationCode();

}
