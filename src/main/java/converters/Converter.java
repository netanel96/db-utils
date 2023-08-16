package converters;

import java.util.List;

public interface Converter<F, T> {
    List<T> convertList(List<F> list);
}
