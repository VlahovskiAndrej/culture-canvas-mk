package mk.ukim.finki.culturecanvasmk.model.filters.impl;

import mk.ukim.finki.culturecanvasmk.model.filters.Filter;

public class ExcludeBoundaryStonesFilter implements Filter<String> {
    @Override
    public String execute(String input) {
        if (input == null || input.contains("boundary_stone"))
            return null;
        return input;
    }
}
