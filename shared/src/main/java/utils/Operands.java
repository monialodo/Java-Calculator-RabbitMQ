package utils;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Operands implements Serializable {

    private final BigDecimal a;
    private final BigDecimal b;


    public Operands(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }


    public BigDecimal getA() {
        return a;
    }

     public BigDecimal getB() {
        return b;
    }


    @Override
    public String toString() {
        return "Operands{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operands operands = (Operands) o;
        return a.equals(operands.a) && b.equals(operands.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }


}
