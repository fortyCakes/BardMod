package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TripleTimePower
        extends AbstractPower {
    public static final String POWER_ID = "TripleTimePower";
    public static final String NAME = "TripleTime";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Whenever you play a card that costs 2 or more, gain [R]."
    };


    public TripleTimePower(AbstractCreature owner, int amount) {
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
        this.description = DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.freeToPlayOnce && card.costForTurn >= 2) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(amount));
        }
    }
}