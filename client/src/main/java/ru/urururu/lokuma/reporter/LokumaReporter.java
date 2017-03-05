package ru.urururu.lokuma.reporter;

import com.codahale.metrics.*;
import com.codahale.metrics.Timer;

import java.io.*;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:dmitriy.g.matveev@gmail.com">Dmitry Matveev</a>
 */
public class LokumaReporter extends ScheduledReporter {
    /**
     * Returns a new {@link LokumaReporter.Builder} for {@link com.codahale.metrics.ConsoleReporter}.
     *
     * @param registry the registry to report
     * @return a {@link LokumaReporter.Builder} instance for a {@link LokumaReporter}
     */
    public static Builder forRegistry(MetricRegistry registry) {
        return new Builder(registry);
    }

    public static class Builder {
        private final MetricRegistry registry;
        private PrintStream output;
        private Locale locale;
        private Clock clock;
        private TimeZone timeZone;
        private TimeUnit rateUnit;
        private TimeUnit durationUnit;
        private MetricFilter filter;
        private ScheduledExecutorService executor;
        private boolean shutdownExecutorOnStop;

        private Builder(MetricRegistry registry) {
            this.registry = registry;
            this.output = System.out;
            this.locale = Locale.getDefault();
            this.clock = Clock.defaultClock();
            this.timeZone = TimeZone.getDefault();
            this.rateUnit = TimeUnit.SECONDS;
            this.durationUnit = TimeUnit.MILLISECONDS;
            this.filter = MetricFilter.ALL;
            this.executor = null;
            this.shutdownExecutorOnStop = true;
        }

        /**
         * Specifies whether or not, the executor (used for reporting) will be stopped with same time with reporter.
         * Default value is true.
         * Setting this parameter to false, has the sense in combining with providing external managed executor via {@link #scheduleOn(ScheduledExecutorService)}.
         *
         * @param shutdownExecutorOnStop if true, then executor will be stopped in same time with this reporter
         * @return {@code this}
         */
        public Builder shutdownExecutorOnStop(boolean shutdownExecutorOnStop) {
            this.shutdownExecutorOnStop = shutdownExecutorOnStop;
            return this;
        }

        /**
         * Specifies the executor to use while scheduling reporting of metrics.
         * Default value is null.
         * Null value leads to executor will be auto created on start.
         *
         * @param executor the executor to use while scheduling reporting of metrics.
         * @return {@code this}
         */
        public Builder scheduleOn(ScheduledExecutorService executor) {
            this.executor = executor;
            return this;
        }

        /**
         * Only report metrics which match the given filter.
         *
         * @param filter a {@link MetricFilter}
         * @return {@code this}
         */
        public Builder filter(MetricFilter filter) {
            this.filter = filter;
            return this;
        }

        /**
         * Builds a {@link LokumaReporter} with the given properties.
         *
         * @return a {@link LokumaReporter}
         */
        public LokumaReporter build() {
            return new LokumaReporter(
                    registry,
                    filter,
                    executor,
                    shutdownExecutorOnStop
            );
        }
    }

    private LokumaReporter(MetricRegistry registry,
                            MetricFilter filter,
                            ScheduledExecutorService executor,
                            boolean shutdownExecutorOnStop) {
        super(registry, "lokuma-reporter", filter, TimeUnit.MILLISECONDS, TimeUnit.MILLISECONDS, executor, shutdownExecutorOnStop);
    }

    @Override
    public void report(SortedMap<String, Gauge> gauges,
                       SortedMap<String, Counter> counters,
                       SortedMap<String, Histogram> histograms,
                       SortedMap<String, Meter> meters,
                       SortedMap<String, Timer> timers) {
        LokumaReport report = new LokumaReport();

        for (Map.Entry<String, Gauge> entry : gauges.entrySet()) {
            report.addGauge(entry.getKey(), entry.getValue().getValue());
        }

        for (Map.Entry<String, Counter> entry : counters.entrySet()) {
            report.addCounter(entry.getKey(), entry.getValue().getCount());
        }

        for (Map.Entry<String, Histogram> entry : histograms.entrySet()) {
            Histogram histogram = entry.getValue();
            Snapshot snapshot = histogram.getSnapshot();

            report.addHistogram(
                    entry.getKey(),
                    histogram.getCount(),
                    snapshot.getMin(),
                    snapshot.getMax(),
                    snapshot.getMean(),
                    snapshot.getStdDev(),
                    snapshot.getMedian(),
                    snapshot.get75thPercentile(),
                    snapshot.get95thPercentile(),
                    snapshot.get98thPercentile(),
                    snapshot.get99thPercentile(),
                    snapshot.get999thPercentile()
            );
        }

        for (Map.Entry<String, Meter> entry : meters.entrySet()) {
            Meter meter = entry.getValue();

            report.addMeter(
                    entry.getKey(),
                    meter.getCount(),
                    meter.getMeanRate(),
                    meter.getOneMinuteRate(),
                    meter.getFiveMinuteRate(),
                    meter.getFifteenMinuteRate()
            );
        }

        for (Map.Entry<String, Timer> entry : timers.entrySet()) {
            Timer timer = entry.getValue();
            final Snapshot snapshot = timer.getSnapshot();

            report.addTimer(
                    entry.getKey(),
                    timer.getCount(),
                    timer.getMeanRate(),
                    timer.getOneMinuteRate(),
                    timer.getFiveMinuteRate(),
                    timer.getFifteenMinuteRate(),
                    snapshot.getMin(),
                    snapshot.getMax(),
                    snapshot.getMean(),
                    snapshot.getStdDev(),
                    snapshot.getMedian(),
                    snapshot.get75thPercentile(),
                    snapshot.get95thPercentile(),
                    snapshot.get98thPercentile(),
                    snapshot.get99thPercentile(),
                    snapshot.get999thPercentile()
            );
        }

        // todo send
    }
}