package com.bank.baroda.service;

import com.bank.baroda.dao.AccountDao;
import com.bank.baroda.businessobject.AccountDetails;
import com.bank.baroda.model.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;


    public List<AccountInfo> getAccountDetails(String branch) {
        List<AccountDetails> accountDetailsList = accountDao.getAccountDetails(branch);
        List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();

        for (AccountDetails accountDetails:accountDetailsList) {
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setAccountHolderName(accountDetails.getAccountHolderName());
            accountInfo.setAccountType(accountDetails.getAccountType());
            accountInfo.setBranch(accountDetails.getBranch());
            accountInfo.setPhoneNo(accountDetails.getPhoneNo());
            accountInfo.setPanCard(accountDetails.getPanCard());
            accountInfo.setBalance(accountDetails.getBalance());
            accountInfos.add(accountInfo);
        }
        return accountInfos;
    }

    public String createAccount(AccountInfo accountInfo) {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountHolderName(accountInfo.getAccountHolderName());
        accountDetails.setAccountType(accountInfo.getAccountType());
        accountDetails.setBranch(accountInfo.getBranch());
        accountDetails.setPhoneNo(accountInfo.getPhoneNo());
        accountDetails.setPanCard(accountInfo.getPanCard());
        accountDetails.setBalance(accountInfo.getBalance());
        return accountDao.createAccount(accountDetails);
    }

    public String updateAccount(String update, String accountNumber, String updateType) {

        return accountDao.updateAccount(update, accountNumber, updateType);
    }

    public String deleteAccount(String accountNumber) {
        return accountDao.deleteAccount(accountNumber);
    }
}
