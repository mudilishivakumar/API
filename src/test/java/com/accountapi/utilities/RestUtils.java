package com.accountapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

    // Generates a random account name with "John" prefix
    public static String accountName() {
        String generatedString = RandomStringUtils.randomAlphabetic(4);
        return ("John" + generatedString);
    }

    // Generates a random numeric account number
    public static String accountNum() {
        String generatedString = RandomStringUtils.randomNumeric(9);
        return (generatedString);
    }

    // Generates a random alphanumeric account type
    public static String accountType() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return (generatedString);
    }

    // Generates a random numeric ID
    public static String id() {
        String generatedString = RandomStringUtils.randomNumeric(10);
        return (generatedString);
    }

    // Generates a random numeric Swift code
    public static String swiftCode() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return (generatedString);
    }
}
