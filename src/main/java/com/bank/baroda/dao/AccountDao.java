package com.bank.baroda.dao;

import com.bank.baroda.businessobject.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.UUID;

@Repository
public class AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

  /*  ResultSetExtractor<AccountDetails> resultSetExtractor = (ResultSet rs) -> {
        AccountDetails accountDetails = new AccountDetails();
        if (rs.next()) {

            accountDetails.setAccountHolderName(rs.getString(3));
            accountDetails.setAccountType(rs.getString(4));
            accountDetails.setPhoneNo(rs.getString(5));
            accountDetails.setBranch(rs.getString(6));
            accountDetails.setPanCard(rs.getString(7));
            accountDetails.setBalance(rs.getFloat(8));
        }
        return accountDetails;
    };*/

    RowMapper<AccountDetails> rowMapper=new RowMapper<AccountDetails>() {

        @Override
        public AccountDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            AccountDetails details=new AccountDetails();
            details.setAccountHolderName(rs.getString(3));
            details.setAccountType(rs.getString(4));
            details.setPhoneNo(rs.getString(5));
            details.setBranch(rs.getString(6));
            details.setPanCard(rs.getString(7));
            details.setBalance(rs.getFloat(8));
            return details;
        }
    };

    public List<AccountDetails> getAccountDetails(String accountNumber) {
        return jdbcTemplate.query(new PrepareStatementCreatorImpl(accountNumber), rowMapper);
    }

  /*  public String createAccount(AccountDetails accountDetails) {
        String data = "failed to create account";
        String quary = "insert into accounts(account_no,account_holder_name,account_type,phone_no,branch,pancard,balance) values(?,?,?,?,?,?,?) ";
        String accountNo = UUID.randomUUID().toString();
        int count = jdbcTemplate.update(quary, accountNo, accountDetails.getAccountHolderName(), accountDetails.getAccountType(), accountDetails.getPhoneNo(), accountDetails.getBranch(), accountDetails.getPanCard(), accountDetails.getBalance());
        if (count > 0) {
            data = "Successfully created account and the accountNo :  " + accountNo;
        }
        return data;
    }*/

    public String createAccount(AccountDetails accountDetails) {
       int count=jdbcTemplate.update(new PrepareStatementCreatorImpl1(accountDetails));
       if(count>0){
           return "Account created successfully :  " + accountDetails.getAccountHolderName();
       }
       throw new RuntimeException("Account creation failed");
    }


    public String updateAccount(String update, String accountNo, String updateType) {
        String data = "Account update is failed";
        String quary = "update accounts set " + updateType + "=? where account_no=?";
        int count = jdbcTemplate.update(quary,update,accountNo);
        if (count > 0) {
            data = "Successfully updated account ";
        }
        return data;
    }

    public String deleteAccount(String accountNo) {
        String data = "Account delete is failed";
        String quary = "delete from accounts where account_no=?";
        int count = jdbcTemplate.update(quary, accountNo);
        if (count > 0) {
            data = "Successfully deleted account ";
        }
        return data;
    }

}
