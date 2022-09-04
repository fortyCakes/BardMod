package bardmod.bard.relics;

import bardmod.BardMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BearPuppet extends CustomRelic {
    public static final String ID = "BearPuppet";
    public static final int BEAR_EFFECT = 3;
    public BearPuppet() {
        super(ID, new Texture(BardMod.makeRelicImagePath(ID)), RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BearPuppet();
    }
}
