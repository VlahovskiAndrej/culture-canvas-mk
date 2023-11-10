package filters;

public class ExcludeBoundaryStonesFilter implements Filter<String> {
    @Override
    public String execute(String input) {
        if (input == null || input.contains("boundary_stone"))
            return null;
        return input;
    }
}
