package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import bardmod.bard.powers.ShowMustGoOnPower;
import bardmod.bard.powers.ShowMustGoOnUpgradedPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShowMustGoOn extends CustomCard {
    public static final String ID = "ShowMustGoOn";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 3;


    public ShowMustGoOn() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.POWER, BardColor.BARD_ORANGE, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
        if (!upgraded)
        {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new ShowMustGoOnUpgradedPower(p, 1), 1));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new ShowMustGoOnPower(p, 1), 1));
        }
    }
}
