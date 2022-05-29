package utils;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Operands implements Serializable {

    private BigDecimal a;
    private BigDecimal b;

    public Operands() {

    }

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

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public void setB(BigDecimal b) {
        this.b = b;
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
