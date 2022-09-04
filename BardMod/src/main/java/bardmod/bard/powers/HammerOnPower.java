package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import bardmod.bard.CopyHelper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HammerOnPower
        extends AbstractPower {
    public static final String POWER_ID = "HammerOnPower";
    public static final String NAME = "Hammer On";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Whenever you play a Note, copy it", ".", " ",  " times."
    };


    public HammerOnPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    public void playApplyPowerSfx() {
        /* 37 */     CardCrawlGame.sound.play("POWER_STRENGTH", 0.1F);
        /*    */   }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (amount > 1 ? (DESCRIPTIONS[2] + amount + DESCRIPTIONS[3]) : DESCRIPTIONS[1]);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse) {
            if (card.hasTag(BardCardTags.NOTE_A) || card.hasTag(BardCardTags.NOTE_B) || card.hasTag(BardCardTags.NOTE_C)) {
                for (int i = 0; i < amount; i++) CopyHelper.CopyAndPlayCard(card);
            }
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, HammerOnPower.POWER_ID));
    }
}