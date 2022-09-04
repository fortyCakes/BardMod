package bardmod.bard.powers;

import bardmod.BardMod;
import bardmod.bard.relics.BearPuppet;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PuppetPower
        extends AbstractPower {
    public static final String POWER_ID = "PuppetPower";
    public static final String NAME = "Puppet";
    public static final int THRESHOLD = 10;
    public static final int LOSE_ON_ACTIVATION = 10;
    public static final String[] DESCRIPTIONS =  new String[]{
            "When an enemy with " + THRESHOLD + " Puppet attacks, they lose " + LOSE_ON_ACTIVATION + " Puppet and the attack is redirected to a random enemy."
    };

    public PuppetPower(AbstractCreature owner, int amount) {
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

    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (this.amount >= getThreshold())
        {
            AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
            if (this.amount == getLoseOnActivation()) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, PuppetPower.POWER_ID));
            }
            else {
                addToBot(new ReducePowerAction(owner, owner, this.ID, getLoseOnActivation()));
            }
            addToBot(new DamageAction(m, new DamageInfo(owner, info.base), AbstractGameAction.AttackEffect.FIRE));

            return 0;
        }
        else return damageAmount;
    }

    private int getLoseOnActivation() {
        if (AbstractDungeon.player.hasRelic(BearPuppet.ID))
        {
            return LOSE_ON_ACTIVATION - BearPuppet.BEAR_EFFECT;
        }
        else return LOSE_ON_ACTIVATION;
    }

    private int getThreshold() {
        if (AbstractDungeon.player.hasRelic(BearPuppet.ID))
        {
            return THRESHOLD - BearPuppet.BEAR_EFFECT;
        }
        else return THRESHOLD;
    }
}