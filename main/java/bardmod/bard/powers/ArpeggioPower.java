package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ArpeggioPower
        extends AbstractPower {
    public static final String POWER_ID = "ArpeggioPower";
    public static final String NAME = "Arpeggio";
    public static final String[] DESCRIPTIONS =  new String[]{
            "Attacks gain Note A. Skills that gain Block gain Note B and other Skills gain Note C."
    };


    public ArpeggioPower(AbstractCreature owner) {
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
}