package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.ITriggerOnScale;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DancePower
        extends AbstractPower
        implements ITriggerOnScale {
    public static final String POWER_ID = "DancePower";
    public static final String NAME = "Dance";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Whenever you #yScale, apply ", " #yPuppet to ALL enemies."
    };


    public DancePower(AbstractCreature owner, int amount) {
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
        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, owner, new PuppetPower(owner, amount), amount));
        }
    }
}