package bardmod.bard.relics;

import bardmod.BardMod;
import bardmod.bard.ITriggerOnChord;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SilverStringedLute extends CustomRelic implements ITriggerOnChord {
    public static final String ID = "SilverStringedLute";
    public static final int HARMONY = 1;
    public SilverStringedLute() {
        super(ID, new Texture(BardMod.makeRelicImagePath(ID)), RelicTier.BOSS, LandingSound.FLAT);
    }

    public boolean canSpawn() {
      return AbstractDungeon.player.hasRelic(SevenStringLute.ID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HARMONY + DESCRIPTIONS[1];
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, HARMONY)));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new DexterityPower(AbstractDungeon.player, HARMONY)));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SilverStringedLute();
    }

    @Override
    public void onChord() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, HARMONY)));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new DexterityPower(AbstractDungeon.player, HARMONY)));
    }
}
