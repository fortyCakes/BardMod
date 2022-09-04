package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.ITriggerOnScale;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class MaimingMelodyPower
        extends AbstractPower
        implements ITriggerOnScale {
    public static final String POWER_ID = "MaimingMelodyPower";
    public static final String NAME = "Maiming Melody";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Whenever you #yScale, deal ", " damage."
    };


    public MaimingMelodyPower(int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onScale() {
        addToBot(new SFXAction("TINGSHA"));
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(new CleaveEffect()));
        } else {
            addToBot(new VFXAction(this.owner, new CleaveEffect(), 0.2F));
        }

        addToBot(new DamageAllEnemiesAction(
                this.owner,
                DamageInfo.createDamageMatrix(this.amount, true),
                DamageInfo.DamageType.THORNS,
                AbstractGameAction.AttackEffect.NONE,
                true));
    }
}