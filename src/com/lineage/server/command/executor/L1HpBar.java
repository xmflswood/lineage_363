package com.lineage.server.command.executor;

import static com.lineage.server.model.skill.L1SkillId.GMSTATUS_HPBAR;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lineage.server.model.L1Object;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_HPMeter;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 显示物件血条(参数:ON显示/关闭OFF)
 * 
 * @author dexc
 * 
 */
public class L1HpBar implements L1CommandExecutor {

    private static final Log _log = LogFactory.getLog(L1HpBar.class);

    private L1HpBar() {
    }

    public static L1CommandExecutor getInstance() {
        return new L1HpBar();
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        if (arg.equalsIgnoreCase("on")) {
            pc.setSkillEffect(GMSTATUS_HPBAR, 0);

        } else if (arg.equalsIgnoreCase("off")) {
            pc.removeSkillEffect(GMSTATUS_HPBAR);

            for (final L1Object obj : pc.getKnownObjects()) {
                if (pc.isGmHpBarTarget(obj)) {
                    pc.sendPackets(new S_HPMeter(obj.getId(), 0xFF));
                }
            }
        } else {
            _log.error("错误的GM指令格式: " + this.getClass().getSimpleName()
                    + " 执行的GM:" + pc.getName());
            // 261 \f1指令错误。
            pc.sendPackets(new S_ServerMessage(261));
        }
    }
}
