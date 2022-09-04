package bardmod.bard.relics;

import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class RoughRuff extends CustomRelic {
    public static final String ID = "RoughRuff";
    public static final int GOLD = 10;
    public RoughRuff() {
        super(ID, new Texture(BardMod.makeRelicImagePath(ID)), RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void onEnterRestRoom() {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.hasTag(BardCardTags.STORY)) {
                flash();
                addToBot(new GainGoldAction(GOLD));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RoughRuff();
    }
}
