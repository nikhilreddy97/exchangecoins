package com.exchange.coins.exchangecoins.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exchange.coins.exchangecoins.entity.Exchange;

@Repository
public interface BillExchangeRepository extends JpaRepository<Exchange, Double> {

}
