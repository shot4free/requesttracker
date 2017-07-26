package com.training.requesttracker.stats;

import com.training.requesttracker.entity.TimeUnit;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * Created by sony on 7/22/2017.
 */
@Component
public class RequestStats {

    private Map<TimeUnit,AtomicInteger> stats = new HashMap<>();
    private static final int TIMEOUT = 10;
    private Calendar calendar = Calendar.getInstance();

    @PostConstruct
    public void init() {
        Thread cleaningThread = new Thread(() -> {
            for (;;) {
                try {
                    sleep(java.util.concurrent.TimeUnit.MINUTES.toMillis(TIMEOUT));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Date currDate = new Date();
                for (Iterator<Map.Entry<TimeUnit,AtomicInteger>> itr = stats.entrySet().iterator(); itr.hasNext();) {
                    Map.Entry<TimeUnit,AtomicInteger> entry = itr.next();
                    if (entry.getKey().diffInMinutes(currDate) >= TIMEOUT)
                        itr.remove();
                }
            }
        });

        cleaningThread.start();
    }

    public void calcRequest(HttpServletRequest request) {
//        long creationTime = request.getSession().getCreationTime();
//        Date date = new Date(creationTime);
        Date date = new Date();

        TimeUnit timeUnit = new TimeUnit(date);
        AtomicInteger counter = stats.get(timeUnit);
        if (counter != null) {
            counter.incrementAndGet();
        } else {
            counter = new AtomicInteger(1);
            stats.put(timeUnit, counter);
        }
    }

    public Map<TimeUnit,AtomicInteger> getStats() {
        return stats;
    }
}
