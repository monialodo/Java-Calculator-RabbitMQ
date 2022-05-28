package utils;

import java.io.Serializable;
import java.math.BigDecimal;

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

}
