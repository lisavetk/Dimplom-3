package ru.yandex.praktikum.helpers;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static String getRandomName() {
        return RandomStringUtils.randomAlphabetic(10);
    }
    public static String getRandomEmail() {
        return RandomStringUtils.randomAlphabetic(10).toLowerCase() + "@test.com";
    }
    public static String getRandomPassword(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
