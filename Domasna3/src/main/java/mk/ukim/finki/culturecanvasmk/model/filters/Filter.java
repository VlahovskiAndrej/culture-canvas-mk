package mk.ukim.finki.culturecanvasmk.model.filters;

public interface Filter<T> {
    T execute(T input);
}