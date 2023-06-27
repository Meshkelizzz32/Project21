package project23;
import java.util.concurrent.*;
import java.util.*;


public class Philosopher implements Runnable {
  private Chopstick left;
  private Chopstick right;
  private LinkedBlockingQueue<Chopstick> bin;
  private final int id;
  private final int ponderFactor;
  private Random rand = new Random(47);
  private void pause() throws InterruptedException {
    if(ponderFactor == 0) return;
    TimeUnit.MILLISECONDS.sleep(
      rand.nextInt(ponderFactor * 250));
  }
  public Philosopher(Chopstick left, Chopstick right,LinkedBlockingQueue<Chopstick> bin,
    int ident, int ponder) {
    this.left = left;
    this.right = right;
    this.bin=bin;
    id = ident;
    ponderFactor = ponder;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        System.out.println(this + " " + "thinking");
        pause();
        System.out.println(this+" taking right chopstick(the first)");
       right=bin.take();
       System.out.println(this+" taking left chopstick(the second)");
       left=bin.take();
       System.out.println(this + " " + "eating");
        pause();
        System.out.println(this+" returning both chopsticks");
        bin.put(right);
        bin.put(left);
      }
    } catch(InterruptedException e) {
    	 System.out.println(this + " " + "exiting via interrupt");
    }
  }
  public String toString() { return "Philosopher " + id; }
}