package com.vojkodrev.dbsaver;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;

@Entity
public class SortSaveLine {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  public int id;

  public long createdAt;
  public long savedAt;

  @CsvBindByName(column = "MATCH_ID")
  public String matchId;

  @CsvBindByName(column = "MARKET_ID")
  public int marketId;

  @CsvBindByName(column = "OUTCOME_ID")
  public String outcomeId;

  @CsvBindByName(column = "SPECIFIERS")
  public String specifiers;

  public SortSaveLine() {
  }

  public SortSaveLine(String matchId, int marketId, String outcomeId, String specifiers, long createdAt) {
    this.matchId = matchId;
    this.marketId = marketId;
    this.outcomeId = outcomeId;
    this.specifiers = specifiers;
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return String.format("Match Id = %s, Market Id = %d, Outcome Id = %s, Specifiers = %s", matchId, marketId, outcomeId, specifiers);
  }
}
