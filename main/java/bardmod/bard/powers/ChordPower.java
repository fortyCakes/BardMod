package bardmod.bard.powers;


import bardmod.BardMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChordPower
        extends AbstractPower {
    public static final String POWER_ID = "ChordPower";
    public static final String NAME = "Chord";
    public static final String[] DESCRIPTIONS =  new String[]{
            "If you play cards with Note A, B and C, play them again."
    };

    public AbstractCard CardA = null;
    public AbstractCard CardB = null;
    public AbstractCard CardC = null;

    public ChordPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
    }

    @Override
    public void updateDescription() {
        String desc = DESCRIPTIONS[0];

        if (CardA != null) {
            desc = desc + " NL #yNote #yA: " + CardA.name;
        }

        if (CardB != null) {
            desc = desc + " NL #yNote #yB: " + CardB.name;
        }

        if (CardC != null) {
            desc = desc + " NL #yNote #yC: " + CardC.name;
        }


        this.description = desc;
    }

    public boolean SetNoteA(AbstractCard card) {
        if (CardA != null) return false;
        CardA = card;
        AbstractDungeon.player.limbo.addToTop(card);
        updateDescription();
        return true;
    }

    public boolean SetNoteB(AbstractCard card) {
        if (CardB != null) return false;
        CardB = card;
        AbstractDungeon.player.limbo.addToTop(card);
        updateDescription();
        return true;
    }

    public boolean SetNoteC(AbstractCard card) {
        if (CardC != null) return false;
        CardC = card;
        AbstractDungeon.player.limbo.addToTop(card);
        updateDescription();
        return true;
    }

    public boolean ChordComplete(){
        return !(CardA == null || CardB == null || CardC == null);
    }


}