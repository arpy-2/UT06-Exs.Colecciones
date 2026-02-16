package org.ies.tierno.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Bank {
    private String name;
    private List<Customer> customers;
    private Map<String, Account> accountsByIban;

    public void deposit(String iban, double amount) {
        if (accountsByIban.containsKey(iban)) {
            var account = accountsByIban.get(iban);
            account.deposit(amount);
        } else {
            System.out.println("No se ha encontrado la cuenta");
        }
    }

    public List<Account> getCustomerAccounts(String nif) {
        Customer customer = findCustomer(nif);
        if (customer != null) {
            List<Account> customerAccounts = new ArrayList<>();
            for (var account : accountsByIban.values()) {
                if (account.getNif().equals(nif)) {
                    customerAccounts.add(account);
                }
            }
            return customerAccounts;
        } else {
            System.out.println("No se ha encontrado el cliente");
            return null;
        }
    }


    public Customer findCustomer(String nif) {
        for (var customer : customers) {
            if (customer.getNif().equals(nif)) {
                return customer;
            }
        }
        return null;
    }
}
