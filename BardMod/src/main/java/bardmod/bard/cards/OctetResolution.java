package bardmod.bard.cards;

import bardmod.BardMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OctetResolution extends CustomCard {
    public static final String ID = "OctetResolution";
    public static final String NAME = "Resolution";
    public static final String DESCRIPTION = "End your turn.";
    private static final int COST = 3;

    public OctetResolution() {
        super(ID, NAME, BardMod.makeCardImagePath(OctetForm.ID), COST, DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.RARE, CardTarget.NONE);

        this.exhaust = true;
    }

    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
        addToBot(new PressEndTurnButtonAction());
    }

    public AbstractCard makeCopy() {
        return new OctetResolution();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }
}
