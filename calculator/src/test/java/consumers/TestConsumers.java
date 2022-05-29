package consumers;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import utils.Operands;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;


public class TestConsumers {

    private SumConsumer sumConsumer;
    private SubtractConsumer subtractConsumer;
    private MultiplyConsumer multiplyConsumer;
    private DivideConsumer divideConsumer;

    @Before
    public void setup() {
        sumConsumer = new SumConsumer();
        subtractConsumer = new SubtractConsumer();
        multiplyConsumer = new MultiplyConsumer();
        divideConsumer = new DivideConsumer();
    }

    @Test
    @DisplayName("Return a sum when succesfull")
   public void returnSumWhenSucessfull(){
        Operands operands = new Operands(new BigDecimal(45435), new BigDecimal(5452));
        assertEquals(sumConsumer.receiveMessage(operands), new BigDecimal("50887"));
    }

    @Test
    @DisplayName("Return a subtraction when sucessfull")
    public void returnSubtractionWhenSucessfull(){
        Operands operands = new Operands(new BigDecimal(454), new BigDecimal(54));
        assertEquals(subtractConsumer.receiveMessage(operands), new BigDecimal("400"));
    }

    @Test
    @DisplayName("Return a multiplication when sucessfull")
    public void returnMultiplicationWhenSucessfull(){
        Operands operands = new Operands(new BigDecimal(652), new BigDecimal(15));
        assertEquals(multiplyConsumer.receiveMessage(operands), new BigDecimal("9780"));
    }

    @Test
    @DisplayName("Return an division when sucessfull")
    public void returnDivisionWhenSucessfull(){
        Operands operands = new Operands(new BigDecimal(3600), new BigDecimal(25));
        assertEquals(divideConsumer.receiveMessage(operands), new BigDecimal("144.000"));
    }






}
