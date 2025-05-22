package ua.kpi.model.service.exception;

import ua.kpi.exception.ApplicationException;
import ua.kpi.utils.ErrorsMessages;

public class ServiceException extends ApplicationException {
    public ServiceException(){
        super(ErrorsMessages.SERVICE_ERROR);
    }

    public ServiceException(Exception cause) {
        super(ErrorsMessages.SERVICE_ERROR, cause);
    }

    public ServiceException(String messageKey) {
        super(messageKey);
    }

    @Override
    public ServiceException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }
}
