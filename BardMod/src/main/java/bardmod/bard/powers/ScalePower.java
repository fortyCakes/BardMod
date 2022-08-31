package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ScalePower
        extends AbstractPower {
    public static final String POWER_ID = "ScalePower";
    public static final String NAME = "Scale";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Determines the power of Scale cards. Gain Scale when you play a card that costs 1 more or less than your last card; otherwise, your Scale resets to 1."
    };


    public ScalePower(int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.updateDescription();
        this.amount = amount;
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}