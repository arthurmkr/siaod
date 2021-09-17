package ru.mab.siaod.registerMachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Machine {
    private final Map<String, Integer> registers = new HashMap<>();
    private final Stack stack = new Stack();
    private final Map<String, Operation> operations;
    private boolean flag;

    public Machine(List<String> registers, Map<String, Operation> operations) {
        for (String register : registers) {
            this.registers.put(register, 0);
        }

        this.operations = new HashMap<>(operations);
    }

    public void setRegister(String name, int value) {
        if (!registers.containsKey(name)) {
            throw new RuntimeException("Register [" + name + "] is not exists");
        }

        registers.put(name, value);
    }

    public int getRegister(String name) {
        if (!registers.containsKey(name)) {
            throw new RuntimeException("Register [" + name + "] is not exists");
        }

        return registers.get(name);
    }

    public void start() {

    }
}
