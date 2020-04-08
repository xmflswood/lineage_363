package com.lineage.server.serverpackets;

import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1SummonInstance;

/**
 * 物件封包 - 招唤兽
 * 
 * @author dexc
 * 
 */
public class S_NPCPack_Summon extends ServerBasePacket {

    private static final int STATUS_POISON = 1;
    /*
     * private static final int STATUS_INVISIBLE = 2; private static final int
     * STATUS_PC = 4; private static final int STATUS_FREEZE = 8; private static
     * final int STATUS_BRAVE = 16; private static final int STATUS_ELFBRAVE =
     * 32; private static final int STATUS_FASTMOVABLE = 64; private static
     * final int STATUS_GHOST = 128;
     */

    private byte[] _byte = null;

    /**
     * 物件封包 - 招唤兽
     * 
     * @param pet
     * @param pc
     */
    public S_NPCPack_Summon(final L1SummonInstance pet, final L1PcInstance pc) {
        this.buildPacket(pet, pc, true);
    }

    public S_NPCPack_Summon(final L1SummonInstance pet, final L1PcInstance pc,
            final boolean isCheckMaster) {
        this.buildPacket(pet, pc, isCheckMaster);
    }

    private void buildPacket(final L1SummonInstance pet, final L1PcInstance pc,
            final boolean isCheckMaster) {
        this.writeC(S_OPCODE_CHARPACK);
        this.writeH(pet.getX());
        this.writeH(pet.getY());
        this.writeD(pet.getId());
        this.writeH(pet.getGfxId()); // SpriteID in List.spr
        this.writeC(pet.getStatus()); // Modes in List.spr
        this.writeC(pet.getHeading());
        this.writeC(pet.getChaLightSize()); // (Bright) - 0~15
        this.writeC(pet.getMoveSpeed()); // スピード - 0:normal, 1:fast, 2:slow
        this.writeD(0x00000000);
        this.writeH(0x0000);
        this.writeS(pet.getNameId());
        this.writeS(pet.getTitle());
        int status = 0;
        if (pet.getPoison() != null) { // 毒状态
            if (pet.getPoison().getEffectId() == 1) {
                status |= STATUS_POISON;
            }
        }
        this.writeC(status); // 状态
        this.writeD(0x00000000); // 主人血盟OBJID
        this.writeS(null); // 血盟名称

        final StringBuilder stringBuilder = new StringBuilder();
        if (isCheckMaster && pet.isExsistMaster()) {
            if (pet.getMaster() instanceof L1PcInstance) {
                final L1PcInstance master = (L1PcInstance) pet.getMaster();
                if (master != null) {
                    if (master.get_other().get_color() != 0) {
                        stringBuilder.append(master.get_other().color());
                    }
                    stringBuilder.append(master.getName());
                }
            }

        } else {
            stringBuilder.append("");
        }

        if (pet.getMaster() instanceof L1NpcInstance) {
            L1NpcInstance npc = (L1NpcInstance) pet.getMaster();
            stringBuilder.append(npc.getNameId());
        }

        // 主人名称
        this.writeS(stringBuilder.toString());

        this.writeC(0x00); // 物件分类
        if (!(pet.getMaster() instanceof L1NpcInstance)) {
            // HP显示
            if ((pet.getMaster() != null)
                    && (pet.getMaster().getId() == pc.getId())) {
                final int percent = pet.getMaxHp() != 0 ? 100
                        * pet.getCurrentHp() / pet.getMaxHp() : 100;
                this.writeC(percent);
            } else {
                this.writeC(0xFF);
            }
        } else {
            this.writeC(0xFF);
        }

        this.writeC(0x00);
        this.writeC(0x00); // LV
        // this.writeC(pet.getLevel()); // LV
        this.writeC(0x00);
        this.writeC(0xFF);
        this.writeC(0xFF);
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
