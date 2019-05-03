package com.vojkodrev.dbsaver;

import io.reactivex.Observable;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class SortSave {
  final static Logger logger = Logger.getLogger(SortSave.class);

  public static void main(String [] args)
  {


    logger.info("START!");

    Observable
      .create(new SortSaveCSVParser(args[0]))
      .buffer(10, TimeUnit.SECONDS)
//      .flatMap(list -> new SortSaveLineSorter(list))
//      .flatMap(list -> new SortSaveLineDbSaver(list))
      .subscribe(
        list -> {
//          logger.info(list.size());
//          for (int i = 0; i < 5 && i < list.size(); i++) {
//            SortSaveLine ss = list.get(i);
//            logger.info(ss.toString());
//          }
        },
        error -> {
          logger.error(error.getMessage(), error);
        },
        () -> {
          logger.info("DONE!");
        }
      );


  }
}

