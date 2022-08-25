package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.SlowPower;

public class RemoveSlowPower
        extends AbstractPower {
    public static final String POWER_ID = "RemoveSlowPower";
    public static final String NAME = "Remove Slow";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Lose Slow at the end of your turn."
    };


    public RemoveSlowPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
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

    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        addToBot(new RemoveSpecificPowerAction(owner, owner, SlowPower.POWER_ID));
        addToBot(new RemoveSpecificPowerAction(owner, owner, RemoveSlowPower.POWER_ID));
    }
}