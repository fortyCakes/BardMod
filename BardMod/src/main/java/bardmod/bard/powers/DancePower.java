package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.ITriggerOnScale;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DancePower
        extends AbstractPower
        implements ITriggerOnScale {
    public static final String POWER_ID = "DancePower";
    public static final String NAME = "Dance";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Whenever you #yScale, draw a card.", "Whenever you #yScale, draw ", " cards."
    };


    public DancePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        }
        else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onScale() {
        addToBot(new DrawCardAction(owner, amount));
    }
}