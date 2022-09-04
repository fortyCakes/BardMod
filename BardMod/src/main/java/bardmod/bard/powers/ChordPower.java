package bardmod.bard.powers;


import bardmod.BardMod;
import bardmod.bard.relics.BrokenLuteString;
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

    private String currentImage;

    public ChordPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.updateDescription();
        this.img = new Texture(BardMod.makePowerImagePath(POWER_ID));
        this.currentImage = POWER_ID;
    }

    @Override
    public void updateDescription() {
        String desc = DESCRIPTIONS[0];
        int notes = 0;

        if (CardA != null) {
            desc = desc + " NL #yNote #yA: " + CardA.name;
            notes += 1;
        }

        if (CardB != null) {
            desc = desc + " NL #yNote #yB: " + CardB.name;
            notes += 1;
        }

        if (CardC != null) {
            desc = desc + " NL #yNote #yC: " + CardC.name;
            //noinspection UnusedAssignment
            notes += 1;
        }

        this.description = desc;
    }

    public void updateImage(){
        String suffix = "_";
        suffix += CardA == null ? "X" : "A";
        suffix += CardB == null ? "X" : "B";
        suffix += CardC == null ? "X" : "C";

        if (!currentImage.equals(POWER_ID + suffix))
        {
            this.img = new Texture(BardMod.makePowerImagePath(POWER_ID+suffix));
            currentImage = POWER_ID+suffix;
        }

    }

    public boolean SetNoteA(AbstractCard card) {
        if (CardA != null) return false;
        CardA = card;
        updateDescription();
        return true;
    }

    public boolean SetNoteB(AbstractCard card) {
        if (CardB != null) return false;
        CardB = card;
        updateDescription();
        return true;
    }

    public boolean SetNoteC(AbstractCard card) {
        if (CardC != null) return false;
        CardC = card;
        updateDescription();
        return true;
    }

    public boolean ChordComplete(){
        int cardsComplete = 0;
        if (CardA != null) cardsComplete++;
        if (CardB != null) cardsComplete++;
        if (CardC != null) cardsComplete++;

        if (AbstractDungeon.player.hasRelic(BrokenLuteString.ID)) return cardsComplete >= 2;

        return cardsComplete == 3;
    }


}