package com.lineage.server.timecontroller.pc;

import java.util.Collection;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.thread.PcOtherThreadPool;
import com.lineage.server.world.WorldKnight;

/**
 * PC HP回复时间轴(骑士)
 * 
 * @author dexc
 * 
 */
public class HprTimerKnight extends TimerTask {

    private static final Log _log = LogFactory.getLog(HprTimerKnight.class);

    private ScheduledFuture<?> _timer;

    public void start() {
        final int timeMillis = 1000;// 1秒
        _timer = PcOtherThreadPool.get().scheduleAtFixedRate(this, timeMillis,
                timeMillis);
    }

    @Override
    public void run() {
        try {
            final Collection<L1PcInstance> allPc = WorldKnight.get().all();
            // 不包含元素
            if (allPc.isEmpty()) {
                return;
            }

            for (final Iterator<L1PcInstance> iter = allPc.iterator(); iter
                    .hasNext();) {
                final L1PcInstance tgpc = iter.next();
                final HprExecutor hpr = HprExecutor.get();
                if (hpr.check(tgpc)) {
                    hpr.checkRegenHp(tgpc);
                    Thread.sleep(1);
                }
            }

            /*
             * for (final L1PcInstance tgpc : allPc) { final HprExecutor hpr =
             * HprExecutor.get(); if (hpr.check(tgpc)) { hpr.checkRegenHp(tgpc);
             * Thread.sleep(1); } }
             */

        } catch (final Exception e) {
            _log.error("Pc(骑士) HP自然回复时间轴异常重启", e);
            PcOtherThreadPool.get().cancel(_timer, false);
            final HprTimerKnight hprKnight = new HprTimerKnight();
            hprKnight.start();
        }
    }
}
