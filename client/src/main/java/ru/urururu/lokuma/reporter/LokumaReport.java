package ru.urururu.lokuma.reporter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:dmitriy.g.matveev@gmail.com">Dmitry Matveev</a>
 */
public class LokumaReport {
    List<Meter> meters = new ArrayList<>();
    List<Gauge> gauges = new ArrayList<>();
    List<Counter> counters = new ArrayList<>();
    List<Histogram> histograms = new ArrayList<>();
    List<Timer> timers = new ArrayList<>();

    public void addMeter(String key, long count, double meanRate, double oneMinuteRate, double fiveMinuteRate, double fifteenMinuteRate) {
        meters.add(new Meter(key, count, meanRate, oneMinuteRate, fiveMinuteRate, fifteenMinuteRate));
    }

    public void addGauge(String key, Object value) {
        gauges.add(new Gauge(key, value));
    }

    public void addCounter(String key, long count) {
        counters.add(new Counter(key, count));
    }

    public void addHistogram(String key, long count, long min, long max, double mean, double stdDev, double median, double _75thPercentile, double _95thPercentile, double _98thPercentile, double _99thPercentile, double _999thPercentile) {
        histograms.add(new Histogram(key, count, min, max, mean, stdDev, median, _75thPercentile, _95thPercentile, _98thPercentile, _99thPercentile, _999thPercentile));
    }

    public void addTimer(String key, long count, double meanRate, double oneMinuteRate, double fiveMinuteRate, double fifteenMinuteRate, long min, long max, double mean, double stdDev, double median, double _75thPercentile, double _95thPercentile, double _98thPercentile, double _99thPercentile, double _999thPercentile) {
        timers.add(new Timer(key, count, meanRate, oneMinuteRate, fiveMinuteRate, fifteenMinuteRate, min, max, mean, stdDev, median, _75thPercentile, _95thPercentile, _98thPercentile, _99thPercentile, _999thPercentile));
    }

    private class Meter {
        private final String key;
        private final long count;
        private final double meanRate;
        private final double oneMinuteRate;
        private final double fiveMinuteRate;
        private final double fifteenMinuteRate;

        public Meter(String key, long count, double meanRate, double oneMinuteRate, double fiveMinuteRate, double fifteenMinuteRate) {
            this.key = key;
            this.count = count;
            this.meanRate = meanRate;
            this.oneMinuteRate = oneMinuteRate;
            this.fiveMinuteRate = fiveMinuteRate;
            this.fifteenMinuteRate = fifteenMinuteRate;
        }
    }

    private class Gauge {
        private final String key;
        private final Object value;

        public Gauge(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private class Counter {
        private final String key;
        private final long count;

        public Counter(String key, long count) {
            this.key = key;
            this.count = count;
        }
    }

    private class Histogram {
        private final String key;
        private final long count;
        private final long min;
        private final long max;
        private final double mean;
        private final double stdDev;
        private final double median;
        private final double _75thPercentile;
        private final double _95thPercentile;
        private final double _98thPercentile;
        private final double _99thPercentile;
        private final double _999thPercentile;

        public Histogram(String key, long count, long min, long max, double mean, double stdDev, double median, double _75thPercentile, double _95thPercentile, double _98thPercentile, double _99thPercentile, double _999thPercentile) {
            this.key = key;
            this.count = count;
            this.min = min;
            this.max = max;
            this.mean = mean;
            this.stdDev = stdDev;
            this.median = median;
            this._75thPercentile = _75thPercentile;
            this._95thPercentile = _95thPercentile;
            this._98thPercentile = _98thPercentile;
            this._99thPercentile = _99thPercentile;
            this._999thPercentile = _999thPercentile;
        }
    }

    private class Timer {
        private final String key;
        private final long count;
        private final double meanRate;
        private final double oneMinuteRate;
        private final double fiveMinuteRate;
        private final double fifteenMinuteRate;
        private final long min;
        private final long max;
        private final double mean;
        private final double stdDev;
        private final double median;
        private final double _75thPercentile;
        private final double _95thPercentile;
        private final double _98thPercentile;
        private final double _99thPercentile;
        private final double _999thPercentile;

        public Timer(String key, long count, double meanRate, double oneMinuteRate, double fiveMinuteRate, double fifteenMinuteRate, long min, long max, double mean, double stdDev, double median, double _75thPercentile, double _95thPercentile, double _98thPercentile, double _99thPercentile, double _999thPercentile) {
            this.key = key;
            this.count = count;
            this.meanRate = meanRate;
            this.oneMinuteRate = oneMinuteRate;
            this.fiveMinuteRate = fiveMinuteRate;
            this.fifteenMinuteRate = fifteenMinuteRate;
            this.min = min;
            this.max = max;
            this.mean = mean;
            this.stdDev = stdDev;
            this.median = median;
            this._75thPercentile = _75thPercentile;
            this._95thPercentile = _95thPercentile;
            this._98thPercentile = _98thPercentile;
            this._99thPercentile = _99thPercentile;
            this._999thPercentile = _999thPercentile;
        }
    }
}
