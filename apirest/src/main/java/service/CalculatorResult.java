package service;

import java.math.BigDecimal;
import java.util.Objects;


public class CalculatorResult {
    private BigDecimal result;

    public CalculatorResult(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculatorResult that = (CalculatorResult) o;
        return result.equals(that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    @Override
    public String toString() {
        return "CalculatorResult{" +
                "result=" + result +
                '}';
    }

}

