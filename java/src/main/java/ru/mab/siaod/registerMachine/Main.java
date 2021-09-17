package ru.mab.siaod.registerMachine;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

interface Input {

}

interface Instruction {
    void eval();
}

public class Main {
    static Label label(String v) {
        return new Label(v);
    }

    static Test test(Operation op, Input... inputs) {
        return new Test(op, inputs);
    }

    static Operation op(String name) {
        return new Operation(name);
    }

    static Register reg(String name) {
        return new Register(name);
    }

    static Constant _const(int v) {
        return new Constant(v);
    }

    static Assign assign(String target, Input... inputs) {
        return new Assign(target, inputs);
    }

    static Branch branch(Label label) {
        return new Branch(label);
    }

    static Goto _goto(Input target) {
        return new Goto(target);
    }

    public static void main(String[] args) {
        new Machine(Arrays.asList("a", "b", "t"), Map.of());

        new Controller(
                label("test-b"),
                test(op("="), reg("b"), _const(0)),
                branch(label("gcd-done")),
                assign("t", reg("a")),

                label("rem-loop"),
                test(op("<"), reg("t"), reg("b")),
                branch(label("rem-done")),
                assign("t", op("-"), reg("t"), reg("b")),
                _goto(label("rem-loop")),
                label("rem-done"),

                assign("a", reg("b")),
                assign("b", reg("t")),
                _goto(label("test-b")),
                label("gcd-done")
        );

        // подпроцедура gcd с возвратом в место вызова
        new Controller(
                label("gcd"),
                test(op("="), reg("b"), _const(0)),
                branch(label("gcd-done")),
                assign("t", op("rem"), reg("a"), reg("b")),
                assign("a", reg("b")),
                assign("b", reg("t")),
                _goto(label("gcd")),
                label("gcd-done"),
                _goto(reg("continue")),


                assign("continue", label("after-gcd-1")),
                _goto(label("gcd")),
                label("after-gcd-1"),


                assign("continue", label("after-gcd-2")),
                _goto(label("gcd")),
                label("after-gcd-2")
        );
    }
}

@Value
class Controller {
    List<Instruction> instructions;

    public Controller(Instruction... instructions) {
        this.instructions = List.of(instructions);
    }
}

@Value
class Perform implements Instruction {
    String operation;
    Input input;

    @Override
    public void eval() {

    }
}

@Value
class Test implements Instruction {
    Operation operation;
    List<Input> inputs;

    public Test(Operation operation, Input... inputs) {
        this.operation = operation;
        this.inputs = List.of(inputs);
    }

    @Override
    public void eval() {

    }
}

@Value
class Assign implements Instruction {
    String target;
    String operation;
    List<Input> inputs;

    public Assign(String target, String operation, Input... inputs) {
        this.target = target;
        this.operation = operation;
        this.inputs = List.of(inputs);
    }

    public Assign(String target, Input... inputs) {
        this.target = target;
        this.operation = null;
        this.inputs = List.of(inputs);
    }

    @Override
    public void eval() {

    }
}

@Value
class Branch implements Instruction {
    Label target;

    @Override
    public void eval() {

    }
}

@Value
class Goto implements Instruction {
    Input target;

    @Override
    public void eval() {

    }
}


@Value
class Operation implements Input {
    String name;
    List<Input> inputs;

    Operation(String name, Input... inputs) {
        this.name = name;
        this.inputs = List.of(inputs);
    }
}

@Value
class Constant implements Input {
    int value;
}

@Value
class Register implements Input {
    String name;
}

@Value
class Label implements Instruction, Input {
    String name;

    @Override
    public void eval() {

    }
}