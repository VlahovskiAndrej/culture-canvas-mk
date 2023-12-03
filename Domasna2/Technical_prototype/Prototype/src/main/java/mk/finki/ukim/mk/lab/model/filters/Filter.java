package mk.finki.ukim.mk.lab.model.filters;

public interface Filter<T> {
     T execute(T input);
}