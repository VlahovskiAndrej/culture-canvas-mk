package filters;

public interface Filter<T> {
    public T execute(T input);
}