package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.unique.GreedAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GrabTheLute extends CustomCard {
    public static final String ID = "GrabTheLute";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 10;
    private static final int GOLD_AMOUNT = 10;
    private static final int UPGRADE_AMOUNT = 5;

    public GrabTheLute() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK, BardColor.BARD_ORANGE, CardRarity.RARE, CardTarget.ENEMY);

        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = GOLD_AMOUNT;
        this.magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new GreedAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), magicNumber));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_AMOUNT);
            upgradeMagicNumber(UPGRADE_AMOUNT);
            exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
