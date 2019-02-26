package ru.itis.context;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationContextImpl implements ApplicationContext{
    private static ApplicationContextImpl context;

    private Map<String, Object> components;
    private List<Class> uploadClasses;

    static {
        context = new ApplicationContextImpl();
    }

    private ApplicationContextImpl() {
        components = new HashMap<>();
        uploadClasses = new ArrayList<>();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/MusicService");

        uploadClasses.add(dataSource.getClass());
        components.put(dataSource.getClass().toString(), dataSource);
    }

    public static ApplicationContextImpl getContext() {
        return context;
    }

    @Override
    public void addResources(ArrayList<File> files) {
        for (File listFile: files) {
            processDirectory(listFile);
        }
    }

    private void processDirectory (File directory) {
        if (directory.isFile()) {
            addNewClass(directory);
        } else {
            if (!directory.isDirectory()) {
                System.err.println("Wrong file - " + directory.getAbsolutePath());
                throw new RuntimeException();
            }
            for (File file : directory.listFiles()) {
                processDirectory(file);
            }
        }
    }

    private void addNewClass (File file) {
        String className = getClassName(file);
        try {
            Class newClass = Class.forName(className);
            if (!uploadClasses.contains(newClass) && !newClass.isInterface() && !newClass.isAnnotation()) {
                uploadClasses.add(newClass);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("You haven`t got this file - " + className);
        } catch (NoClassDefFoundError e) {
            System.err.println("It`s impossible to add this file - " + className);
        }
    }

    private String getClassName (File file) {
        StringBuilder className = new StringBuilder(file.getName()).delete(file.getName().indexOf('.'), file.getName().length());
        File readFile = file.getParentFile();
        while (readFile != null && !readFile.getName().equals("") && !readFile.getName().equals("java")) {
            className.insert(0, '.');
            className.insert(0, readFile.getName());
            readFile = readFile.getParentFile();
        }
        return className.toString();
    }

    @Override
    public <T> T getComponent (Class<T> componentClass) {
        for (Object component : components.values()) {
            if (componentClass.isAssignableFrom(component.getClass())) {
                return (T) component;
            }
        }
        Object searchObject = processClass(componentClass);
        return searchObject == null ? null : (T) searchObject;
    }

    private Object processClass (Class processingClass) {
        Class currentClass = findClass(processingClass);

        if (currentClass == null) {
            return null;
        }

        for (Object component : components.values()) {
            if (currentClass.isAssignableFrom(component.getClass())) {
                return component;
            }
        }

        for (Constructor constructor : currentClass.getConstructors()) {
            List<Object> args = new ArrayList<>();
            for (Class componentClass : constructor.getParameterTypes()) {
                if (componentClass.isPrimitive()) {
                    if (componentClass.equals(boolean.class)) {
                        args.add(false);
                    } else args.add(0);
                    continue;
                }

                args.add(processClass(componentClass));
            }
            try {
                Object newComponent = currentClass.getConstructor(constructor.getParameterTypes()).newInstance(args.toArray());
                components.put(currentClass.toString(), newComponent);
                return newComponent;
            } catch (IllegalAccessException | InvocationTargetException | SecurityException | NoSuchMethodException |
                    InstantiationException | IllegalArgumentException ignored) {

            }
        }
        return null;
    }

    private Class findClass (Class componentClass) {
        if (uploadClasses.contains(componentClass)) {
            return componentClass;
        }

        for (Class currentClass: uploadClasses) {
            if (componentClass.isAssignableFrom(currentClass)) {
                return currentClass;
            }
        }
        return null;
    }
}