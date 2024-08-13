package org.example.operations.processors;

import org.example.account.AccountService;
import org.example.operations.OperationCommandProcessor;
import org.example.operations.ConsoleOperationType;
import org.springframework.stereotype.Component;


import java.util.Scanner;

@Component
public class AccountWithdrawProcessor  implements OperationCommandProcessor {

    private final Scanner scanner;
    private final AccountService accountService;

    public AccountWithdrawProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id:");
        var accountId = Long.parseLong(scanner.nextLine());
        System.out.println("Enter amount to withdraw:");
        int amountToWithdraw = Integer.parseInt(scanner.nextLine());
        accountService.withdrawFromAccount(accountId, amountToWithdraw);
        System.out.println("Successfully withdrawn amount=%s to accountId=%s"
                .formatted(amountToWithdraw, accountId));
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }
}