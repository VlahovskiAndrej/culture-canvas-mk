package mk.ukim.finki.culturecanvasmk.model.filters.impl;

import mk.ukim.finki.culturecanvasmk.model.filters.Filter;

public class CityFilter implements Filter<String>
{

    @Override
    public String execute(String input) {

        String[] parts = input.split(",", -1);
        StringBuilder result = new StringBuilder();

        if (parts[3].equals("monument")) {
            result.append(parts[0]).append(",");
            result.append(parts[1]).append(",");
            result.append(parts[2]).append(",");
            result.append(parts[3]);
        }
        return result.toString();
    }
}
