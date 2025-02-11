package com.microservices.cards.constants;

public class CardConstants {
    private CardConstants() {}

    public static final String SAVINGS = "Savings";
    public static final String CREDITS = "Credits";
    public static final String DEBITS = "Debits";
    public static final int LIMIT = 10000000;
    public static final int USED = 0;
    public static final int AVAILABLE = 0;
    public static final String  STATUS_201 = "201";
    public static final String  MESSAGE_201 = "Card created successfully";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";
    public static final String  STATUS_417 = "417";
    public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
    public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
    public static final String  STATUS_500 = "500";
    public static final String  MESSAGE_500 = "An error occured. Please try again or contact Dev team";
}
