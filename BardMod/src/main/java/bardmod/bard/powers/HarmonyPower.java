package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HarmonyPower
        extends AbstractPower {
    public static final String POWER_ID = "HarmonyPower";
    public static final String NAME = "Harmony";
    public static final String[] DESCRIPTIONS =  new String[]{
            "When you play a Chord, you play its cards with ",
            " extra Strength and Dexterity."
    };

    public HarmonyPower(AbstractCreature owner, int amount) {
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
}