package types;

import java.util.List;

public class RowOfStrings {
    private List<String> row;

    public RowOfStrings(List<String> row) {
        this.row = row;
    }

    public List<String> row() {
        return row;
    }

    public void setRow(List<String> row) {
        this.row = row;
    }
}
