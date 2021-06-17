package com.exchange.coins.exchangecoins.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.exchange.coins.exchangecoins.exceptions.CoinMachineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.coins.exchangecoins.entity.Exchange;
import com.exchange.coins.exchangecoins.repository.BillExchangeRepository;

@Service
@Transactional
public class BillExchangeServiceImpl implements BillExchangeService {

    @Autowired
    BillExchangeRepository billExchangeRepository;

    @Override
    public List<Exchange> getCoinsForAmount(double amount) {

        if (amount < 0.01) {
            throw new CoinMachineException("Please Enter more Bill");
        }

        List<Exchange> exchangeDatas = new ArrayList<>();
        boolean flag = true;
        int cent25 = 0;
        int cent10 = 0;
        int cent5 = 0;
        int cent1 = 0;
        // Fetching All types of cents from Database Table i.e.; 0.25 = 100, 0.05 =100 etc
        List<Exchange> coinsResponse = billExchangeRepository.findAll();

        for (Exchange exchange : coinsResponse) {
            if (exchange.getCents() == 0.25) {
                cent25 = exchange.getMaxCoins();
            } else if (exchange.getCents() == 0.10) {
                cent10 = exchange.getMaxCoins();
            } else if (exchange.getCents() == 0.05) {
                cent5 = exchange.getMaxCoins();
            } else if (exchange.getCents() == 0.01) {
                cent1 = exchange.getMaxCoins();
            }
        }
        int count = 100;
        Exchange[] machine = new Exchange[4];
        machine[0] = new Exchange(0.25, cent25);
        machine[1] = new Exchange(0.10, cent10);
        machine[2] = new Exchange(0.05, cent5);
        machine[3] = new Exchange(0.01, cent1);

        Exchange[] finalResult;

        finalResult = new Exchange[4];

        double updateBill = amount;

        for (int i = 0; i < machine.length; i++) {
            Exchange coinMachine = machine[i];
            if (updateBill > 0 && coinMachine.getMaxCoins() > 0) {
                int count1 = coinMachine.getMaxCoins();
                int j = 0;
                for (j = 0; j < count1; j++) {
                    if (updateBill - coinMachine.getCents() >= 0) {
                        updateBill = updateBill - coinMachine.getCents();
                        coinMachine.setMaxCoins(coinMachine.getMaxCoins() - 1);
                    } else {

                        finalResult[i] = new Exchange(coinMachine.getCents(), j);
                        if (updateBill > 0) {
                            coinMachine.setMaxCoins(coinsResponse.get(0).getMaxCoins() - j);
                        }

                        break;
                    }
                }
                if (updateBill > 0 || coinMachine.getMaxCoins() == 0) {
                    finalResult[i] = new Exchange(coinMachine.getCents(), j);
                }
            }

        }

        int final_count = 0;
        for (Exchange coinMachine : machine) {
            final_count += coinMachine.getMaxCoins();
        }
        if (final_count == 0) {
            flag = false;
        }

        double final_change = 0;
        for (Exchange coinMachine : finalResult) {

            if (coinMachine != null) {
                final_change += coinMachine.getCents() * coinMachine.getMaxCoins();
                Exchange exchangeData = new Exchange();
                exchangeData.setCents(coinMachine.getCents());
                exchangeData.setMaxCoins(coinMachine.getMaxCoins());
                exchangeDatas.add(exchangeData);
            }

        }
        if (amount > final_change) {
            throw new CoinMachineException("**No enough change**");
        }
        return exchangeDatas;
    }

}
