package ua.kpi.model.dao.exception;

import ua.kpi.exception.ApplicationException;
import ua.kpi.utils.ErrorsMessages;

public class DaoException extends ApplicationException {

    public DaoException(){
        super(ErrorsMessages.DAO_ERROR);
    }

    public DaoException(Exception cause) {
        super(ErrorsMessages.DAO_ERROR, cause);
    }
}
