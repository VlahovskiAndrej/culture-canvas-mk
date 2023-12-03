package mk.finki.ukim.mk.lab.model.filters.impl;

import mk.finki.ukim.mk.lab.model.filters.Filter;

public class ExcludeForeignPlacesFilter implements Filter<String> {

    @Override
    public String execute(String input) {
        if (input == null || input.contains("Ερείπια") || input.contains("Naim Frasheri") || input.contains("Varri i Metalise")
                || input.contains("Bllaca 99") || input.contains("Muri i Kujtesës") || input.contains("Qendra Përkujtimore e Dëbimit të Shqiptarëve \"Bllacë 1999\""))
            return null;
        return input;
    }
}
