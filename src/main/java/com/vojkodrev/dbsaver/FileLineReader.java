package com.vojkodrev.dbsaver;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLineReader implements ObservableOnSubscribe<String> {

  private final String filename;

  FileLineReader(String filename) {
    this.filename = filename;
  }

  @Override
  public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
    try {
      Files.lines(Paths.get(filename)).forEach(observableEmitter::onNext);
      observableEmitter.onComplete();
    } catch (IOException e) {
      observableEmitter.onError(e);
    }
  }
}
