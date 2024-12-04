

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;
// import java.util.concurrent.CountDownLatch;
// import java.util.concurrent.TimeUnit;
// import java.util.concurrent.atomic.AtomicInteger;
// import java.util.concurrent.atomic.AtomicLong;


// import text_adventure.objects.AsyncTimer;

// import static org.junit.Assert.*;

// public class AsyncTimerTest {
//     private AsyncTimer timer;

//     @Before
//     public void setUp() {
//         timer = new AsyncTimer();
//     }

//     @After
//     public void tearDown() {
//         timer.shutdown();
//     }

//     @Test
//     public void testSimpleTimer() throws InterruptedException {
//         AtomicInteger counter = new AtomicInteger(0);
//         CountDownLatch latch = new CountDownLatch(3); // Wait for 3 ticks

//         timer.start(() -> {
//             counter.incrementAndGet();
//             latch.countDown();
//         }, 0, 100); // Tick every 100ms

//         // Wait for 3 ticks or timeout after 1 second
//         assertTrue("Timer didn't tick 3 times within timeout",
//                   latch.await(1, TimeUnit.SECONDS));
//         assertEquals("Timer should have ticked 3 times", 3, counter.get());
//     }

//     @Test
//     public void testTimerStop() throws InterruptedException {
//         AtomicInteger counter = new AtomicInteger(0);
//         CountDownLatch latch = new CountDownLatch(2); // Wait for 2 ticks

//         timer.start(() -> {
//             counter.incrementAndGet();
//             latch.countDown();
//         }, 0, 100);

//         // Wait for 2 ticks
//         assertTrue(latch.await(500, TimeUnit.MILLISECONDS));
//         timer.stop();

//         // Wait a bit longer and verify no more ticks occurred
//         Thread.sleep(300);
//         assertEquals("Timer should have stopped after 2 ticks", 2, counter.get());
//     }

//     @Test
//     public void testCountdownTimer() throws InterruptedException {
//         AtomicInteger tickCount = new AtomicInteger(0);
//         AtomicLong lastRemaining = new AtomicLong();
//         CountDownLatch completeLatch = new CountDownLatch(1);

//         timer.startCountdown(
//             remaining -> {
//                 tickCount.incrementAndGet();
//                 lastRemaining.set(remaining);
//             },

//             () -> completeLatch.countDown(),
//             200, // 500ms total duration
//             1  // 100ms tick interval
//         );

//         // Wait for completion
//         assertTrue("Countdown didn't complete in time",
//                   completeLatch.await(1, TimeUnit.SECONDS));

//         assertTrue("Should have ticked at least 4 times", tickCount.get() >= 4);
//         assertEquals("Final remaining time should be 0", 0, lastRemaining.get());
//     }

//     @Test
//     public void testConcurrentTimers() throws InterruptedException {
//         AtomicInteger timer1Count = new AtomicInteger(0);
//         AtomicInteger timer2Count = new AtomicInteger(0);
//         CountDownLatch latch = new CountDownLatch(6); // Wait for 3 ticks from each timer

//         AsyncTimer timer1 = new AsyncTimer();
//         AsyncTimer timer2 = new AsyncTimer();

//         try {
//             timer1.start(() -> {
//                 timer1Count.incrementAndGet();
//                 latch.countDown();
//             }, 0, 100);

//             timer2.start(() -> {
//                 timer2Count.incrementAndGet();
//                 latch.countDown();
//             }, 0, 100);

//             assertTrue(latch.await(1, TimeUnit.SECONDS));
//             assertEquals(3, timer1Count.get());
//             assertEquals(3, timer2Count.get());
//         } finally {
//             timer1.shutdown();
//             timer2.shutdown();
//         }
//     }

//     @Test
//     public void testExceptionHandling() throws InterruptedException {
//         CountDownLatch latch = new CountDownLatch(3);
//         AtomicInteger successCount = new AtomicInteger(0);

//         timer.start(() -> {
//             try {
//                 if (successCount.get() == 1) {
//                     throw new RuntimeException("Planned failure");
//                 }
//                 successCount.incrementAndGet();
//                 latch.countDown();
//             } catch (Exception e) {
//                 // Exception should be caught here
//                 latch.countDown();
//             }
//         }, 0, 100);

//         assertTrue(latch.await(1, TimeUnit.SECONDS));
//         assertEquals("Timer should continue running after exception",
//                     1, successCount.get());
//     }

//     @Test
//     public void testInitialDelay() throws InterruptedException {
//         AtomicInteger counter = new AtomicInteger(0);
//         CountDownLatch latch = new CountDownLatch(1);

//         long startTime = System.currentTimeMillis();
//         timer.start(() -> {
//             counter.incrementAndGet();
//             latch.countDown();
//         }, 500, 100); // 500ms initial delay

//         assertTrue(latch.await(1, TimeUnit.SECONDS));
//         long elapsed = System.currentTimeMillis() - startTime;

//         assertTrue("Initial delay should be at least 500ms",
//                   elapsed >= 500);
//         assertEquals(1, counter.get());
//     }
// }
