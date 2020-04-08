package com.lineage.server.model.doll;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 娃娃能力 模组:MP回复相关<BR>
 * MP回复提高 参数：TYPE1
 * 
 * @author dexc
 * 
 */
public class Doll_MpR extends L1DollExecutor {

    private static final Log _log = LogFactory.getLog(Doll_MpR.class);

    private int _int1;// 值1

    /**
     * 娃娃效果:MP增加
     */
    public Doll_MpR() {
    }

    public static L1DollExecutor get() {
        return new Doll_MpR();
    }

    @Override
    public void set_power(int int1, int int2, int int3) {
        try {
            _int1 = int1;

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void setDoll(L1PcInstance pc) {
        try {
            pc.addMpr(_int1);

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void removeDoll(L1PcInstance pc) {
        try {
            pc.addMpr(-_int1);

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public boolean is_reset() {
        return false;
    }
}
