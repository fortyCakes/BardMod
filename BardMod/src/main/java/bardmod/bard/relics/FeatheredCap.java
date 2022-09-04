package bardmod.bard.relics;

import bardmod.BardMod;
import bardmod.bard.StoryHelper;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FeatheredCap extends CustomRelic {
    public static final String ID = "FeatheredCap";
    public FeatheredCap() {
        super(ID, new Texture(BardMod.makeRelicImagePath(ID)), RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractCard freeStory = StoryHelper.generateStoryCard(true);
        if (freeStory != null) {
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(freeStory));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new FeatheredCap();
    }
}
