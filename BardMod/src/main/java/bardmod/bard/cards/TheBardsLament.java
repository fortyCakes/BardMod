package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import bardmod.bard.BardColor;
import bardmod.bard.LibraryTypeEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class TheBardsLament extends CustomCard {
    public static final String ID = "TheBardsLament";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public TheBardsLament() {
        super(ID, NAME, BardMod.makeCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL, BardColor.BARD_ORANGE, CardRarity.RARE, CardTarget.ALL);
        tags.add(BardCardTags.STORY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard cardToGive = generateStoryCard();
        if (cardToGive == null)
        {
            return;
        }
        if (upgraded)
        {
            cardToGive.upgrade();
        }
        addToBot(new MakeTempCardInHandAction(cardToGive, 1));

    }

    private AbstractCard generateStoryCard() {
        ArrayList<AbstractCard> validCards = new ArrayList<>();
        for(AbstractCard card : CardLibrary.getCardList(LibraryTypeEnum.BARD_ORANGE)) {
            if (card.hasTag(BardCardTags.STORY) && !card.cardID.equals(this.cardID))
            {
                validCards.add(card);
            }
        }
        if (validCards.size() > 0)
        {
            int index = AbstractDungeon.cardRng.random(validCards.size());
            return validCards.get(index);
        }
        else {
            return null;
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
