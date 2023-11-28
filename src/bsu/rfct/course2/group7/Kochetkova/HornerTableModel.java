package bsu.rfct.course2.group7.Kochetkova;

import javax.swing.table.AbstractTableModel;

public class HornerTableModel extends AbstractTableModel {
    private final Double[] coefficients;
    private final Double from;
    private final Double to;
    private final Double step;

    public HornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return (int) Math.ceil((to - from) / step) + 1;
    }

    public Object getValueAt(int row, int col) {
        double x = from + step * row;
        return switch (col) {
            case (0) -> x;
            case (1) -> calculateHorner(x);
            case (2) -> isSmall(calculateHorner(x));
            default -> 0;
        };
    }

    private Object isSmall(double x) {
       return (int)x == 0;
    }

    public String getColumnName(int col) {
        return switch (col) {
            case 0 -> "Значение X";
            case 1 -> "Значение многочлена";
            case 2 -> "Малое число";
            default -> "";
        };
    }

    public Class<?> getColumnClass(int col) {
        return switch (col) {
            case (2) -> Boolean.class;
            case (0), (1) -> Double.class;
            default -> Double.class;
        };
    }

    private double calculateHorner(double x) {
        Double b = coefficients[coefficients.length - 1];
        for (int i = coefficients.length - 2; i >= 0; i--) {
            b = b * x + coefficients[i];
        }
        return b;
    }
}

