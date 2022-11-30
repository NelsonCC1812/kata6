package dto.ExchangeRate;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

abstract public class ExchangeRate {
    public String base;
    public Date date;
    public Map<String, Double> rates;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("{\n")
                .append("\"base\": \"").append(base).append("\",\n")
                .append("\"date\": \"").append(date).append("\",\n")
                .append("\"rates\": ").append(ratesToString())
                .append("}")
                .toString();
    }

    private Object ratesToString() {
        return String.format("{%s}", rates.keySet().stream()
                .map((String key) -> (String.format("\"%s\":\"%s\"", key, rates.get(key))))
                .collect(Collectors.joining(", ")));
    }
}
