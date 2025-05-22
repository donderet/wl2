package ua.kpi.controller.validator;

public class RegExp {
    public static final String EMAIL =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String NAME = "^[\\p{L}\\.'\\-]+$";
    public static final String NUMBER = "/\\d+";
}
