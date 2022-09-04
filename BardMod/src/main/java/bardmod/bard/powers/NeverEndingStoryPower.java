package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.StoryHelper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NeverEndingStoryPower
        extends AbstractPower {
    public static final String POWER_ID = "NeverEndingStoryPower";
    public static final String NAME = "Never Ending Story";
    public static final String[] DESCRIPTIONS =  new String[]{
            "At the start of your turn, add a Story card to your hand.",
            "At the start of your turn, add ", " Story cards to your hand."
    };


    public NeverEndingStoryPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.updateDescription();
        this.amount = 1;
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    public void playApplyPowerSfx() {
        /* 37 */     CardCrawlGame.sound.play("POWER_STRENGTH", 0.1F);
        /*    */   }

    @Override
    public void updateDescription() {
        if (amount == 1) {
             this.description = DESCRIPTIONS[0];
        }
        else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    public void atStartOfTurnPostDraw() {
        flash();
        AbstractCard cardToGive = StoryHelper.generateStoryCard(false);
        if (cardToGive == null)
        {
            return;
        }

        addToBot(new MakeTempCardInHandAction(cardToGive, 1));
    }
}