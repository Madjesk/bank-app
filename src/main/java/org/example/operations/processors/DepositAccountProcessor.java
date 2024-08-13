package org.example.operations.processors;

import org.example.account.AccountService;
import org.example.operations.OperationCommandProcessor;
import org.example.operations.ConsoleOperationType;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DepositAccountProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final AccountService accountService;

    public DepositAccountProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id:");
        var accountId = Long.parseLong(scanner.nextLine());
        System.out.println("Enter amount to deposit:");
        var amountToDeposit = Integer.parseInt(scanner.nextLine());
        accountService.depositAccount(accountId, amountToDeposit);
        System.out.println("Successfully deposited amount=%s to accountId=%s"
                .formatted(amountToDeposit, accountId));
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_DEPOSIT;
    }
}