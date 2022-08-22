package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SadnessPower
        extends AbstractPower {
    public static final String POWER_ID = "SadnessPower";
    public static final String NAME = "Sadness";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Decreases attack damage by ",
            " (per hit). Reduces by 1 at end of turn."
    };

    public SadnessPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
        this.type = PowerType.DEBUFF;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage - this.amount;
        }
        return damage;
    }

    public void atEndOfTurn(boolean isPlayer) {

        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, SadnessPower.POWER_ID));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, SadnessPower.POWER_ID, 1));
        }
   }
}