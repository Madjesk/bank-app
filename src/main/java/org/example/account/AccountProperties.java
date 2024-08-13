package org.example.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {
    public int getDefaultAccountAmount() {
        return defaultAccountAmount;
    }

    public double getTransferCommission() {
        return transferComission;
    }

    private final int defaultAccountAmount;
    private final double transferComission;
    public AccountProperties(
            @Value("${account.default-amount}") int defaultAccountAmount,
            @Value("${account.transfer-commission}") double transferComission
    ) {
        this.defaultAccountAmount = defaultAccountAmount;
        this.transferComission = transferComission;
    }



}
