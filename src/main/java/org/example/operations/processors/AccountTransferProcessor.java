package org.example.operations.processors;

import org.example.account.Account;
import org.example.account.AccountService;
import org.example.operations.OperationCommandProcessor;
import org.example.user.UserService;
import org.example.user.User;
import org.example.operations.ConsoleOperationType;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountTransferProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final AccountService accountService;

    public AccountTransferProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter source account id:");
        var fromAccountId = Long.parseLong(scanner.nextLine());
        System.out.println("Enter destination account id:");
        var toAccountId = Long.parseLong(scanner.nextLine());
        System.out.println("Enter amount to transfer:");
        int amountToTransfer = Integer.parseInt(scanner.nextLine());
        accountService.transfer(fromAccountId, toAccountId, amountToTransfer);
        System.out.println(
                "Successfully transferred %s from accountId %s to accountId %s"
                        .formatted(amountToTransfer, fromAccountId, toAccountId)
        );
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }
}
