package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.ITriggerOnScale;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GoWithTheFlowPower
        extends AbstractPower
        implements ITriggerOnScale {
    public static final String POWER_ID = "GoWithTheFlowPower";
    public static final String NAME = "The Flow";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Whenever you #yScale, gain ", " #yHappy."
    };


    public GoWithTheFlowPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.updateDescription();
        this.amount = amount;
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onScale() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new HappyPower(owner, amount), amount));
    }
}