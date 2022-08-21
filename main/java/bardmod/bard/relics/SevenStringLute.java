package bardmod.bard.relics;

import bardmod.BardMod;
import bardmod.bard.powers.HarmonyPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SevenStringLute extends CustomRelic {
    public static final String ID = "SevenStringLute";

    public SevenStringLute() {
        super(ID, new Texture(BardMod.makeRelicImagePath(ID)), RelicTier.STARTER, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new HarmonyPower(AbstractDungeon.player, 1)));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SevenStringLute();
    }

}
