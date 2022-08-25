package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;

public class WhispersPower
        extends AbstractPower {
    public static final String POWER_ID = "WhispersPower";
    public static final String NAME = "Whispers";
    public static final String[] DESCRIPTIONS =  new String[]{
            "At the start of your turn, exit your Stance."
    };


    public WhispersPower(AbstractCreature owner) {
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

    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, WhispersPower.POWER_ID));
        addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(owner.hb.cX, owner.hb.cY), 0.1F)));
        addToBot(new ChangeStanceAction("Neutral"));
    }
}