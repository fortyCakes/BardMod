package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.CopyHelper;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChorusPower
        extends AbstractPower {
    public static final String POWER_ID = "ChorusPower";
    public static final String NAME = "Chorus";
    public static final String[] DESCRIPTIONS =  new String[]{
            "At the start of your turn, play a copy of ", "."
    };
    private final AbstractCard card;
    private static int chorusIdOffset;


    public ChorusPower(AbstractCreature owner, AbstractCard card) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
        this.card = card;
        this.ID = POWER_ID + chorusIdOffset;
        chorusIdOffset++;
        this.updateDescription();
    }

    public void playApplyPowerSfx() {
        /* 37 */     CardCrawlGame.sound.play("POWER_TIME_WARP", 0.5F);
        /*    */   }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (card == null ? "the selected card" : card.name) + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        flash();
        CopyHelper.CopyAndPlayCard(card);
    }


}