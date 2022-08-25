package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NeowPower
        extends AbstractPower {
    public static final String POWER_ID = "NeowPower";
    public static final String NAME = "Neow's Resurrection";
    public static final String[] DESCRIPTIONS =  new String[]{
            "If you die, heal ", "% of your max HP, then lose this blessing."
    };


    public NeowPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    public void playApplyPowerSfx() {
        /* 37 */     CardCrawlGame.sound.play("SLIME_ATTACK", 0.1F);
        /*    */   }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    // This hooks into onVictory because I don't know how to SpirePatch an import to call a custom method.
    @Override
    public void onVictory(){
        System.out.println("Called NeowPower hook.");
        if (AbstractDungeon.player.currentHealth <= 0) {
            AbstractDungeon.player.currentHealth = 0;
            activate();
        }
    }

    private void activate() {
        flash();
        int healAmt = AbstractDungeon.player.maxHealth * amount / 100;
        if (healAmt < 1) {
            healAmt = 1;
        }
        AbstractDungeon.player.heal(healAmt, true);
        System.out.println("Health after healing is " + AbstractDungeon.player.currentHealth);

        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));

    }
}

