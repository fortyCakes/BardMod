package bardmod.bard.powers;

import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SubliminalTonesPower
        extends AbstractPower {
    public static final String POWER_ID = "SubliminalTonesPower";
    public static final String NAME = "Subliminal Tones";
    public static final String[] DESCRIPTIONS =  new String[]{
            "At the start of your turn, apply ", " Puppet to ALL enemies."
    };

    public SubliminalTonesPower(AbstractCreature owner, int amount) {
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
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters)
        {
            addToBot(new ApplyPowerAction(m, owner, new PuppetPower(owner, amount), amount));
        }
    }
}