package com.vojkodrev.dbsaver;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SortSaveRegexParser implements ObservableOnSubscribe<SortSaveLine> {

  final static Logger logger = Logger.getLogger(SortSave.class);
  private static final Pattern pattern = Pattern.compile("'.+?(\\d+?)'\\|(\\d+?)\\|'(.+?)'\\|('(.+?)')?");
  private final String line;

  public SortSaveRegexParser(String line) {
    this.line = line;
  }

  @Override
  public void subscribe(ObservableEmitter<SortSaveLine> observer) throws Exception {


//    logger.info("Parsing");

    Matcher matcher = pattern.matcher(line);

    if (!matcher.find()) {
      observer.onError(new Exception("Unable to parse \"" + line + "\"!"));
      return;
    }

    String specifiers = matcher.group(5);
    if (specifiers == null) {
      specifiers = "";
    }

    SortSaveLine sortSaveLine = new SortSaveLine(
      Integer.parseInt(matcher.group(1)),
      Integer.parseInt(matcher.group(2)),
      matcher.group(3),
      specifiers,
      System.currentTimeMillis());

    observer.onNext(sortSaveLine);
    observer.onComplete();
  }
}

