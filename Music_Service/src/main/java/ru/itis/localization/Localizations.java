package ru.itis.localization;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Localizations {
    @SneakyThrows
    public static Map<String, String> loadLocalization(String locale) {
        Scanner scanner = new Scanner(new File
                ("C:\\Users\\bloof\\Desktop\\Java_Enterprise\\Music_Service\\src\\main\\resources\\messages\\messages_" + locale + ".properties"));

        Map<String, String> localeMap = new HashMap<>();

        while (scanner.hasNext()) {
            String currentValue = scanner.nextLine();
            String localeValue[] = currentValue.split("=");
            localeMap.put(localeValue[0], localeValue[1]);
        }

        return localeMap;
    }
}
