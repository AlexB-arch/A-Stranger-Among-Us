package text_adventure.objects;

import java.util.concurrent.*;
import java.util.function.Consumer;

public class AsyncTimer {
    private final ScheduledExecutorService executor;
    private ScheduledFuture<?> scheduledTask;
    private boolean isRunning;

    public AsyncTimer() {
        // Create a single-threaded executor for timer tasks
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.isRunning = false;
    }

    /**
     * Start a timer that executes a task at fixed intervals
     * @param task The task to execute
     * @param initialDelay Initial delay before first execution (milliseconds)
     * @param interval Interval between executions (milliseconds)
     */
    public void start(Runnable task, long initialDelay, long interval) {
        if (!isRunning) {
            scheduledTask = executor.scheduleAtFixedRate(
                () -> {
                    try {
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                initialDelay,
                interval,
                TimeUnit.MILLISECONDS
            );
            isRunning = true;
        }
    }

    /**
     * Start a timer that executes a task with a countdown
     * @param onTick Called on each tick with remaining time
     * @param onComplete Called when timer completes
     * @param durationMs Total duration in milliseconds
     * @param tickMs Interval between ticks in milliseconds
     */
    public void startCountdown(Consumer<Long> onTick, Runnable onComplete, 
                             long durationMs, long tickMs) {
        if (!isRunning) {
            final long startTime = System.currentTimeMillis();
            final long endTime = startTime + durationMs;

            scheduledTask = executor.scheduleAtFixedRate(
                () -> {
                    long currentTime = System.currentTimeMillis();
                    long remaining = endTime - currentTime;

                    if (remaining < 0) {
                        stop();
                        onComplete.run();
                    } else {
                        onTick.accept(remaining);
                    }
                },
                0,
                tickMs,
                TimeUnit.MILLISECONDS
            );
            isRunning = true;
        }
    }

    /**
     * Stop the timer
     */
    public void stop() {
        if (isRunning && scheduledTask != null) {
            scheduledTask.cancel(false);
            isRunning = false;
        }
    }

    /**
     * Clean up resources when timer is no longer needed
     */
    public void shutdown() {
        stop();
        executor.shutdown();
    }
}
