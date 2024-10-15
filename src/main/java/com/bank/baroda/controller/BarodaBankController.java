package com.bank.baroda.controller;

import com.bank.baroda.model.AccountInfo;
import com.bank.baroda.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/baroda")
public class BarodaBankController {
    @Autowired
    private AccountService accountService;

    // @GET
    //@Path("/{account-no}")
    //@Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(path = "/{branch}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountInfo> getAccountDetails(@PathVariable("branch") String branch) {
        return accountService.getAccountDetails(branch);
    }

   // @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createAccount(@RequestBody AccountInfo accountInfo) {
        return accountService.createAccount(accountInfo);
    }

   //@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(path="/{account-no}/{update}", method = RequestMethod.PUT)
    public String updatePhoneNo(@PathVariable("update") String update, @PathVariable("account-no") String accountNo, @MatrixVariable("update-type") String updateType) {
        return accountService.updateAccount(update, accountNo, updateType);
    }

   //@DeleteMapping
    @RequestMapping(path="/{account-no}", method = RequestMethod.DELETE)
    public String deleteAccount(@PathVariable("account-no") String accountNo) {
        return accountService.deleteAccount(accountNo);
    }

}
