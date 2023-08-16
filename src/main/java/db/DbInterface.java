package db;

import java.util.List;

public interface DbInterface<T> {
    List<T> getList(String name);
}
