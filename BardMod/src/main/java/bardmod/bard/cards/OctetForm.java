package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import bardmod.bard.powers.OctetPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OctetForm extends CustomCard {
    public static final String ID = "OctetForm";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 3;

    public OctetForm() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.POWER, BardColor.BARD_ORANGE, CardRarity.RARE, CardTarget.SELF);

        this.isEthereal = true;
    }

    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new OctetPower(p, 1), 1));
    }

    public AbstractCard makeCopy() {
        return new OctetForm();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
