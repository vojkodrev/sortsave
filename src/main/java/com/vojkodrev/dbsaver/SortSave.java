package com.vojkodrev.dbsaver;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class SortSave {
  final static Logger logger = Logger.getLogger(SortSave.class);

  public static void main(String [] args)
  {


    logger.info("START + PARSE!");

    Observable
      .create(new FileLineReader(args[0]))
      .skip(1)
//      .subscribeOn(Schedulers.computation())
//      .flatMap(SortSaveRegexParser::new)
      .flatMap(line -> {
        return Observable.create(new SortSaveRegexParser(line)).subscribeOn(Schedulers.computation());
      }, 10)
      .buffer(10, TimeUnit.SECONDS)
      .flatMap(SortSaveLineSorter::new)
      .flatMap(SortSaveLineDbSaver::new)
      .blockingSubscribe(
        list -> {
          logger.info(list.size());
          for (int i = 0; i < 5 && i < list.size(); i++) {
            SortSaveLine ss = list.get(i);
            logger.info(ss.toString());
          }
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

