package ru.mab.siaod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

interface Constraint {
    void processNewValue();
}

interface Connector {
    boolean hasValue();

    BigDecimal getValue();

    void setValue(BigDecimal value, Constraint initiator);

    void addConstraint(Constraint constraint);
}

abstract class AbstractNode implements Constraint {
    Connector a1;
    Connector a2;
    Connector result;

    public AbstractNode(Connector a1, Connector a2, Connector result) {
        this.a1 = a1;
        this.a2 = a2;
        this.result = result;

        a1.addConstraint(this);
        a2.addConstraint(this);
        result.addConstraint(this);
    }

    public void processNewValue() {
        if (a1.hasValue() && a2.hasValue()) {
            result.setValue(op1(a1.getValue(), a2.getValue()), this);
        } else if (a1.hasValue() && result.hasValue()) {
            a2.setValue(op2(a1.getValue(), result.getValue()), this);
        } else if (a2.hasValue() && result.hasValue()) {
            a1.setValue(op3(a2.getValue(), result.getValue()), this);
        }
    }

    protected abstract BigDecimal op1(BigDecimal v1, BigDecimal v2);

    protected abstract BigDecimal op2(BigDecimal v1, BigDecimal res);

    protected abstract BigDecimal op3(BigDecimal v2, BigDecimal res);
}

class Adder extends AbstractNode {
    public Adder(Connector a1, Connector a2, Connector result) {
        super(a1, a2, result);
    }

    @Override
    protected BigDecimal op1(BigDecimal v1, BigDecimal v2) {
        return v1.add(v2);
    }

    @Override
    protected BigDecimal op2(BigDecimal v1, BigDecimal res) {
        return res.subtract(v1);
    }

    @Override
    protected BigDecimal op3(BigDecimal v2, BigDecimal res) {
        return res.subtract(v2);
    }
}

class Multiplier extends AbstractNode {
    public Multiplier(Connector a1, Connector a2, Connector result) {
        super(a1, a2, result);
    }

    @Override
    protected BigDecimal op1(BigDecimal v1, BigDecimal v2) {
        return v1.multiply(v2);
    }

    @Override
    protected BigDecimal op2(BigDecimal v1, BigDecimal res) {
        return res.divide(v1, RoundingMode.HALF_EVEN);
    }

    @Override
    protected BigDecimal op3(BigDecimal v2, BigDecimal res) {
        return res.divide(v2, RoundingMode.HALF_EVEN);
    }
}

class VarConnector implements Connector {
    private final String name;
    private final List<Constraint> constraints = new ArrayList<>();
    protected BigDecimal value;

    public VarConnector(String name) {
        this.name = name;
    }

    @Override
    public boolean hasValue() {
        return value != null;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public void setValue(BigDecimal value, Constraint initiator) {
        this.value = value;

        for (Constraint constraint : constraints) {
            if (constraint != initiator) {
                constraint.processNewValue();
            }
        }
    }

    @Override
    public void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }

    @Override
    public String toString() {
        return "VarConnector{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

class ConstConnector extends VarConnector {
    public ConstConnector(String name, int value) {
        super(name);
        this.value = new BigDecimal(value);
    }

    @Override
    public void setValue(BigDecimal value, Constraint initiator) {

    }
}

/**
 * Формула конвертации градусов Цельсия / Фаренгейта <br/>
 * 9С = 5 (F - 32)
 */
class CelsiusFahrenheitConverter {
    Connector c;
    Connector f;

    public CelsiusFahrenheitConverter() {
        c = new VarConnector("c");
        f = new VarConnector("f");

        Connector v = new VarConnector("v");
        Connector u = new VarConnector("u");
        ConstConnector w = new ConstConnector("w", 9);
        ConstConnector x = new ConstConnector("x", 5);
        ConstConnector y = new ConstConnector("y", 32);

        new Multiplier(c, w, u);
        new Adder(v, y, f);
        new Multiplier(v, x, u);
    }

    /**
     * Фаренгейт -> Цельсий
     *
     * @param value значение в градусах Фаренгейта
     * @return значение в градусах Цельсия
     */
    public BigDecimal fToC(double value) {
        // TODO сбросить цепь
        f.setValue(new BigDecimal(value), null);
        return c.getValue();
    }

    /**
     * Цельсий -> Фаренгейт
     *
     * @param value значение в градусах Цельсия
     * @return значение в градусах Фаренгейта
     */
    public BigDecimal cToF(double value) {
        // TODO сбросить цепь
        c.setValue(new BigDecimal(value), null);
        return f.getValue();
    }
}

/**
 * Распространение ограничений
 */
public class RestrictionPropagation {
    public static void main(String[] args) {
        CelsiusFahrenheitConverter converter = new CelsiusFahrenheitConverter();

        System.out.println(converter.fToC(212));
        System.out.println(converter.cToF(25));
    }
}
