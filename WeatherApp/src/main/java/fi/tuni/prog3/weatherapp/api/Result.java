package fi.tuni.prog3.weatherapp.api;

public class Result<T> {
    private T value;
    private String errorMessage;

    private Result(T value, String errorMessage) {
        this.value = value;
        this.errorMessage = errorMessage;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> error(String errorMessage) {
        return new Result<>(null, errorMessage);
    }

    public T getValue() {
        return value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return errorMessage == null;
    }
}
