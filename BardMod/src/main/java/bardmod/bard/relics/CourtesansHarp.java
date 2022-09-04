package bardmod.bard.relics;

import bardmod.BardMod;
import bardmod.bard.ScaleHelper;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CourtesansHarp extends CustomRelic {
    public static final String ID = "CourtesansHarp";
    public static int lastKnownScale = 0;
    public CourtesansHarp() {
        super(ID, new Texture(BardMod.makeRelicImagePath(ID)), RelicTier.RARE, LandingSound.HEAVY);
    }

    public static void receivePowersModified() {
        int scale = ScaleHelper.ScaleAmount;
        if (scale > 0 && scale % 5 == 0 && scale != lastKnownScale) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
        }
        lastKnownScale = scale;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CourtesansHarp();
    }
}
