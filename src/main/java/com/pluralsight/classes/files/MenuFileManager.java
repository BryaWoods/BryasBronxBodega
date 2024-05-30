package com.pluralsight.classes.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuFileManager {
    private Map<String, List<String>> menu;

    public MenuFileManager() {
        menu = new HashMap<>();
    }

    public void readMenuFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String currentCategory = "";
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#")) {
                    currentCategory = line.substring(1).trim();
                    menu.put(currentCategory, new ArrayList<>());
                } else if (!line.isEmpty()) {
                    String[] parts = line.split("\\.", 2);
                    if (parts.length == 2) {
                        menu.get(currentCategory).add(parts[1].trim());
                    }
                }
            }
        }
    }

    /**
     * Gets the stuff
     *
     * @param category The category to get
     * @return The stuff
     */
    public List<String> getMenuItems(String category) {
        return menu.get(category);
    }
}
