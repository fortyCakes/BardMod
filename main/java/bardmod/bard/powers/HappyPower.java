package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HappyPower
        extends AbstractPower {
    public static final String POWER_ID = "HappyPower";
    public static final String NAME = "Happy";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Increases attack damage by ",
            " (per hit). Reduces by 1 at end of turn."
    };

    private boolean justApplied;

    public HappyPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
        this.justApplied = true;
    }

    public void playApplyPowerSfx() {
        /* 37 */     CardCrawlGame.sound.play("POWER_STRENGTH", 0.05F);
        /*    */   }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage + this.amount;
        }
        return damage;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        flash();

        if (this.amount == 0) {
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Happy"));
        } else {
            addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Happy", 1));
        }
/*    */   }
}