package com.lineage.server.model.doll;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 娃娃能力 模组:伤害减免相关<BR>
 * 伤害减免提高 参数：TYPE1(伤害减免质) TYPE2(机率1/100)
 * 
 * @author dexc
 * 
 */
public class Doll_DmgDownR extends L1DollExecutor {

    private static final Log _log = LogFactory.getLog(Doll_DmgDownR.class);

    private int _int1;// 值1

    private int _int2;// 值2

    /**
     * 娃娃效果:伤害减免增加
     */
    public Doll_DmgDownR() {
    }

    public static L1DollExecutor get() {
        return new Doll_DmgDownR();
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
            pc.set_dmgDowe(_int1, _int2);

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void removeDoll(L1PcInstance pc) {
        try {
            pc.set_dmgDowe(-_int1, -_int2);

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public boolean is_reset() {
        return false;
    }
}
