package com.algostrategix.alpacatrdeapi.service;

import com.algostrategix.alpacatrdeapi.config.AlpacaApiConfig;
import com.algostrategix.alpacatrdeapi.model.AccountInfo;
import com.algostrategix.alpacatrdeapi.util.ZonedDateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.model.endpoint.account.Account;
import net.jacobpeterson.alpaca.model.properties.DataAPIType;
import net.jacobpeterson.alpaca.model.properties.EndpointAPIType;
import net.jacobpeterson.alpaca.rest.AlpacaClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class AlpacaApiClientService {

    private final AlpacaAPI alpacaAPI;
    private final Gson gson;


    @Autowired
    public AlpacaApiClientService(AlpacaApiConfig alpacaApiConfig, Gson gson) {
        this.alpacaAPI = new AlpacaAPI(
                alpacaApiConfig.getApiKey(),
                alpacaApiConfig.getApiSecret(), EndpointAPIType.PAPER, DataAPIType.SIP
        );
        this.gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeConverter())
                .create();
    }

    public ResponseEntity<AccountInfo> getAccountInfo() {
        try {
            Account account = alpacaAPI.account().get();
            AccountInfo accountInfo = convertAccountToAccountInfo(account);
            return new ResponseEntity<>(accountInfo, HttpStatus.OK);
        } catch (AlpacaClientException e) {
            System.err.println("Alpaca API Exception: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private AccountInfo convertAccountToAccountInfo(Account account) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setId(account.getId());
        accountInfo.setAccountNumber(account.getAccountNumber());
        accountInfo.setStatus(String.valueOf(account.getStatus()));
        accountInfo.setCurrency(account.getCurrency());
        accountInfo.setCash(Double.parseDouble(account.getCash()));
        accountInfo.setPortfolioValue(Double.parseDouble(account.getPortfolioValue()));
        accountInfo.setPatternDayTrader(account.getPatternDayTrader());
        accountInfo.setTradeSuspendedByUser(account.getTradeSuspendedByUser());
        accountInfo.setTradingBlocked(account.getTradingBlocked());
        accountInfo.setTransfersBlocked(account.getTransfersBlocked());
        accountInfo.setAccountBlocked(account.getAccountBlocked());
        accountInfo.setCreatedAt(account.getCreatedAt());
        accountInfo.setShortingEnabled(account.getShortingEnabled());
        accountInfo.setLongMarketValue(Double.parseDouble(account.getLongMarketValue()));
        accountInfo.setShortMarketValue(Double.parseDouble(account.getShortMarketValue()));
        accountInfo.setEquity(Double.parseDouble(account.getEquity()));
        accountInfo.setLastEquity(Double.parseDouble(account.getLastEquity()));
        accountInfo.setMultiplier(Integer.parseInt(account.getMultiplier()));
        accountInfo.setBuyingPower(Double.parseDouble(account.getBuyingPower()));
        accountInfo.setInitialMargin(Double.parseDouble(account.getInitialMargin()));
        accountInfo.setMaintenanceMargin(Double.parseDouble(account.getMaintenanceMargin()));
        accountInfo.setSma(Double.parseDouble(account.getSma()));
        accountInfo.setDaytradeCount(account.getDaytradeCount());
        accountInfo.setLastMaintenanceMargin(Double.parseDouble(account.getLastMaintenanceMargin()));
        accountInfo.setDaytradingBuyingPower(Double.parseDouble(account.getDaytradingBuyingPower()));
        accountInfo.setRegtBuyingPower(Double.parseDouble(account.getRegtBuyingPower()));
        return accountInfo;
    }
}
