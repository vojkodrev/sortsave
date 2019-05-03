package com.vojkodrev.dbsaver;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import org.apache.log4j.Logger;

import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

public class SortSaveCSVParser implements ObservableOnSubscribe<List<SortSaveLine>> {

  final static Logger logger = Logger.getLogger(SortSaveCSVParser.class);
  private final String path;

  public SortSaveCSVParser(String path) {
    this.path = path;
  }

  @Override
  public void subscribe(ObservableEmitter<List<SortSaveLine>> observableEmitter) throws Exception {

    try {
      logger.info("BEFORE PARSE");
//      CsvTransfer csvTransfer = new CsvTransfer();
//      ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
//      ms.setType(clazz);

      Reader reader = Files.newBufferedReader(FileSystems.getDefault().getPath(path));

      CsvToBean cb = new CsvToBeanBuilder(reader)
        .withType(SortSaveLine.class)
        .withQuoteChar('\'')
        .withSeparator('|')
//        .withThrowExceptions(true)
        .build();

//      csvTransfer.setCsvList(cb.parse());
      List parse = cb.parse();

//      List capturedExceptions = cb.getCapturedExceptions();

      reader.close();

      logger.info("AFTER PARSE");

//      return csvTransfer.getCsvList();



    } catch (Throwable t) {
      observableEmitter.onError(t);
    }


//    Matcher matcher = pattern.matcher(line);
//
//    if (!matcher.find()) {
//      observer.onError(new Exception("Unable to parse \"" + line + "\"!"));
//      return;
//    }
//
//    String specifiers = matcher.group(5);
//    if (specifiers == null) {
//      specifiers = "";
//    }
//
//    SortSaveLine sortSaveLine = new SortSaveLine(
//      Integer.parseInt(matcher.group(1)),
//      Integer.parseInt(matcher.group(2)),
//      matcher.group(3),
//      specifiers,
//      System.currentTimeMillis());
//
//    observer.onNext(sortSaveLine);
//    observer.onComplete();
  }
}

