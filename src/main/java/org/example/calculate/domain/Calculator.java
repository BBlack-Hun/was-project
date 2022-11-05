package org.example.calculate.domain;

import org.example.calculate.tobe.*;

import java.util.Arrays;
import java.util.List;


public class Calculator {
    private static final List<NewArithmeticOperator> arithmeticOperators = Arrays.asList(new AddictionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DivisionOperator());

    public static int calculator(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        return arithmeticOperators.stream()
                .filter(arithmeticOperators -> arithmeticOperators.supports(operator))
                .map(arithmeticOperators -> arithmeticOperators.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
    }
}
