package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class TheSilentsSaga extends CustomCard {
    public static final String ID = "TheSilentsSaga";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int DRAW_DISCARD_AMOUNT = 1;
    private static final int SHIV_NUMBER = 1;
    private static final int POISON_AMOUNT = 3;
    private static final int WEAK_AMOUNT = 2;

    public TheSilentsSaga() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.UNCOMMON, CardTarget.ALL);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, DRAW_DISCARD_AMOUNT));
        addToBot(new DiscardAction(p, p, DRAW_DISCARD_AMOUNT, false));
        addToBot(new MakeTempCardInHandAction(new Shiv(), SHIV_NUMBER));
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new PoisonPower(mo, p, POISON_AMOUNT), POISON_AMOUNT, true, AbstractGameAction.AttackEffect.POISON));
            if (upgraded)
            {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, WEAK_AMOUNT, false), WEAK_AMOUNT, true, AbstractGameAction.AttackEffect.POISON));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
