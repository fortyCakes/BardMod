package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.ITriggerOnChord;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RoarOfTheCrowdPower
        extends AbstractPower
        implements ITriggerOnChord {
    public static final String POWER_ID = "RoarOfTheCrowdPower";
    public static final String NAME = "Roar of the Crowd";
    public static final String[] DESCRIPTIONS =  new String[]{
            "When you play a Chord, gain ",
            " Happy."
    };

    public RoarOfTheCrowdPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    public void onChord() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HappyPower(AbstractDungeon.player, amount), amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}