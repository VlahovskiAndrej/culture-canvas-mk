package mk.finki.ukim.mk.lab.model.filters.impl;

import mk.finki.ukim.mk.lab.model.filters.Filter;

public class ExcludeBoundaryStonesFilter implements Filter<String> {
    @Override
    public String execute(String input) {
        if (input == null || input.contains("boundary_stone"))
            return null;
        return input;
    }
}
