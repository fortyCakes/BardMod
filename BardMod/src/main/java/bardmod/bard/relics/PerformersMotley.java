package bardmod.bard.relics;

import bardmod.BardMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PerformersMotley extends CustomRelic {
    public static final String ID = "PerformersMotley";
    public static final int BLOCK = 2;
    public PerformersMotley() {
        super(ID, new Texture(BardMod.makeRelicImagePath(ID)), RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public static void receivePowersModified() {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, 2));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PerformersMotley();
    }
}
