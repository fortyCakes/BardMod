package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.ScaleHelper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ScalePower
        extends AbstractPower {
    public static final String POWER_ID = "ScalePower";
    public static final String NAME = "Scale";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Determines the power of Scale cards. Gain Scale when you play a card that costs 1 more or less than your last card; otherwise, your Scale resets to 1.",
            " NL Play a card costing #y", " or #y", " to gain Scale.", " NL Play a card costing #y1 to gain Scale."
    };


    public ScalePower(int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    @Override
    public void updateDescription() {
        if (ScaleHelper.LastCost > 0) {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + (ScaleHelper.LastCost-1) + DESCRIPTIONS[2] + (ScaleHelper.LastCost+1) + DESCRIPTIONS[3];
        }
        else {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[4];
        }
    }
}