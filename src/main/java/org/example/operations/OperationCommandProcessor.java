package org.example.operations;

public interface OperationCommandProcessor {
    void processOperation();

    ConsoleOperationType getOperationType();
}
