package ru.mycity.core.utils;

import java.util.UUID;

public class Utils {
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
