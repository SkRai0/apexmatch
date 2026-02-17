package cto.iamskrai.apexmatch.repository;

import cto.iamskrai.apexmatch.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
