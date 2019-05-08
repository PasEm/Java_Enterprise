package ru.itis.localization;

import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Localizations {
    private Map<String, Map<String, String>> localization;

    public Localizations(Map<String, Map<String, String>> localization) {
        this.localization = localization;
    }

    public Map<String, String> getLocalizationByLanguage(String language) {
        Map<String, String> defaultLocalization = localization.get("En");

        for (Map.Entry<String, Map<String, String>> locale: localization.entrySet()) {
            if (locale.getKey().equals(language)) {
                return locale.getValue();
            }
        }

        return defaultLocalization;
    }

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
