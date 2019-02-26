package ru.itis.context;

import java.io.File;
import java.util.ArrayList;

public interface ApplicationContext {
    <T> T getComponent(Class<T> componentClass);

    void addResources(ArrayList<File> files);
}
