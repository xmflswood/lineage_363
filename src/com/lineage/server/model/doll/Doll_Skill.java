package com.lineage.server.model.doll;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 娃娃能力 模组:一定机率施展技能<BR>
 * 一定机率施展技能 参数：TYPE1(技能编号) TYPE2(机率1/1000)
 * 
 * @author dexc
 * 
 */
public class Doll_Skill extends L1DollExecutor {

    private static final Log _log = LogFactory.getLog(Doll_Skill.class);

    private int _int1;// 值1

    private int _int2;// 值2

    /**
     * 娃娃效果:一定机率施展“缓速术”
     */
    public Doll_Skill() {
    }

    public static L1DollExecutor get() {
        return new Doll_Skill();
    }

    @Override
    public void set_power(int int1, int int2, int int3) {
        try {
            _int1 = int1;
            _int2 = int2;

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void setDoll(L1PcInstance pc) {
        try {
            /*
             * if (pc.getDoll() != null) { pc.getDoll().set_skill(_int1, _int2);
             * }
             */

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void removeDoll(L1PcInstance pc) {
        try {
            /*
             * if (pc.getDoll() != null) { pc.getDoll().set_skill(-1, -1); }
             */

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    public int[] get_int() {
        try {
            return new int[] { _int1, _int2 };

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    @Override
    public boolean is_reset() {
        return false;
    }
}
