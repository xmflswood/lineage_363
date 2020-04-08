package com.lineage.data.item_armor.set;

import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 套装效果:套装增加物理伤害
 * 
 * @author daien
 * 
 */
public class Effect_Modifier_dmg implements ArmorSetEffect {

    private final int _add;

    /**
     * 套装效果:套装增加物理伤害
     * 
     * @param add
     *            精神
     */
    public Effect_Modifier_dmg(final int add) {
        this._add = add;
    }

    @Override
    public void giveEffect(final L1PcInstance pc) {
        pc.addDmgup(this._add);
    }

    @Override
    public void cancelEffect(final L1PcInstance pc) {
        pc.addDmgup(-this._add);
    }

    @Override
    public int get_mode() {
        return this._add;
    }
}
