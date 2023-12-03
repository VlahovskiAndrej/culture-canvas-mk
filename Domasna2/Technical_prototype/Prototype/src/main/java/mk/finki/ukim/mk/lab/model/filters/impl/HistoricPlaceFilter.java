package mk.finki.ukim.mk.lab.model.filters.impl;

import mk.finki.ukim.mk.lab.model.filters.Filter;

public class HistoricPlaceFilter implements Filter<String> {
    @Override
    public String execute(String input) {
        if (input.contains("type") || input.isEmpty()) {
            return "";
        }

        String[] parts = input.split(",", -1);
        StringBuilder result = new StringBuilder();

        if ((parts[1].equals("memorial") || parts[1].equals("tomb")) ||
                (parts[1].equals("archaeological_site") || parts[1].equals("monument"))) {
            result.append(parts[0]).append(",");
            result.append(parts[1]).append(",");
            result.append(parts[2]).append(",");
            result.append(parts[3]);
        }

        return result.toString();
    }
}
