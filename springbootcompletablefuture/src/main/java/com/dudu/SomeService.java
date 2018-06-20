package com.dudu;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

@Component
public class SomeService {

  @AsyncTimed
  public CompletableFuture<String> getMessage() {
    return CompletableFuture.supplyAsync(() -> {
      heavyWork();
      return "hello async world";
    }, ForkJoinPool.commonPool());
  }

  private void heavyWork() {
    try {
      System.out.println("heaveWork start");
      Thread.sleep(5000);
      System.out.println("heaveWork end");
    }
    catch (InterruptedException e) {
      Thread.interrupted();
    }
  }
}