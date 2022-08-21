package bardmod.bard.powers;

import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ShowMustGoOnUpgradedPower
        extends AbstractPower {
    public static final String POWER_ID = "ShowMustGoOnUpgradedPower";
    public static final String NAME = "The Show Must Go On";
    public static final String[] DESCRIPTIONS = new String[]{
            "Whenever you take unblocked damage, gain a Happy and the enemy gains a Sadness."
    };


    public ShowMustGoOnUpgradedPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    public void playApplyPowerSfx() {
        /* 37 */
        CardCrawlGame.sound.play("POWER_STRENGTH", 0.1F);
        /*    */
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new HappyPower(this.owner, this.amount), this.amount));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(info.owner, this.owner, new SadnessPower(this.owner, this.amount), this.amount));
        }

        return damageAmount;
    }
}
