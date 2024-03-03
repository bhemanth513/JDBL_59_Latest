package com.gfg.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.constants.Constants;
import com.gfg.model.Wallet;
import com.gfg.repository.WalletRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Value("${wallet-default-balance}")
    private Double balance;

    @Autowired
    JSONParser jsonParser;

    private static Logger logger = LoggerFactory.getLogger(WalletService.class);

    @KafkaListener(topics = {Constants.USER_CREATED_TOPIC},groupId = "test_123")
    public void createWallet(String msg) throws ParseException {
        JSONObject event = (JSONObject) jsonParser.parse(msg);

        String userId = String.valueOf(event.get("userId"));
        if(userId==null){
            logger.warn("createWallet(): unable to find userId in the event, data = {}",event);
            return;
        }

        Wallet wallet = Wallet.builder()
                .balance(balance)
                .userId(userId)
                .build();
        this.walletRepository.save(wallet);
    }
}
