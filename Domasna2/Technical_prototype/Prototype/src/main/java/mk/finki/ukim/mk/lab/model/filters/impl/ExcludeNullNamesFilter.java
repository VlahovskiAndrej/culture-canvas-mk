package mk.finki.ukim.mk.lab.model.filters.impl;

import mk.finki.ukim.mk.lab.model.filters.Filter;

public class ExcludeNullNamesFilter implements Filter<String> {
    @Override
    public String execute(String input) {
        String[] parts = input.split(",");
        if (parts.length <= 3 || (parts[3].isEmpty() && parts[4].isEmpty())) {
            return null;
        }
        return input;
    }
}
