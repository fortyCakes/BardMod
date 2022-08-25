package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChaosPower
        extends AbstractPower {
    public static final String POWER_ID = "ChaosPower";
    public static final String NAME = "Chaos";
    public static final String[] DESCRIPTIONS =  new String[]{
            "At the start of your turn, channel ",
            " random orbs."
    };

    public ChaosPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn(){
        for (int i = 0; i < amount; i++)
        {
            addToBot(new ChannelAction(AbstractOrb.getRandomOrb(true)));
        }
    }
}