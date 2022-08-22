package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardColor;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OneManBand extends CustomCard {
    public static final String ID = "OneManBand";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public OneManBand() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < p.hand.size(); i++)
        {
            AbstractCard card = p.hand.getNCardFromTop(i);
            if (card.hasTag(CardTags.STARTER_STRIKE) || card.hasTag(CardTags.STARTER_DEFEND)){
                addToBot(new ExhaustSpecificCardAction(card, p.hand, true));
            }
        }
        for(int i = 0; i < p.drawPile.size(); i++)
        {
            AbstractCard card = p.drawPile.getNCardFromTop(i);
            if (card.hasTag(CardTags.STARTER_STRIKE) || card.hasTag(CardTags.STARTER_DEFEND)){
                addToBot(new ExhaustSpecificCardAction(card, p.drawPile, true));
            }
        }
        for(int i = 0; i < p.discardPile.size(); i++)
        {
            AbstractCard card = p.discardPile.getNCardFromTop(i);
            if (card.hasTag(CardTags.STARTER_STRIKE) || card.hasTag(CardTags.STARTER_DEFEND)){
                addToBot(new ExhaustSpecificCardAction(card, p.discardPile, true));
            }
        }
        for(int i = 0; i < 8; i++)
        {
            CardRarity cardRarity;
            if (upgraded)
            {
                int roll = AbstractDungeon.cardRandomRng.random(99);
                if (roll < 55) {
                  cardRarity = CardRarity.COMMON;
                } else if (roll < 85) {
                  cardRarity = CardRarity.UNCOMMON;
                } else {
                  cardRarity = CardRarity.RARE;
                }
            }
            else {
                cardRarity = CardRarity.COMMON;
            }

            AbstractCard card;
            do {
               card = CardLibrary.getAnyColorCard(AbstractCard.CardType.ATTACK, cardRarity).makeCopy();
            } while (card.color == BardColor.BARD_ORANGE);

            addToBot(new MakeTempCardInDrawPileAction(card, 1, true, true));
        }
        if (AbstractDungeon.player.chosenClass != AbstractPlayer.PlayerClass.DEFECT && AbstractDungeon.player.masterMaxOrbs == 0)
        {
            AbstractDungeon.player.increaseMaxOrbSlots(1, false);
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }
}
