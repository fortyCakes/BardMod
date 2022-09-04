package bardmod.bard.relics;

import bardmod.BardMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BrokenLuteString extends CustomRelic {
    public static final String ID = "BrokenLuteString";
    public BrokenLuteString() {
        super(ID, new Texture(BardMod.makeRelicImagePath(ID)), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BrokenLuteString();
    }
}
