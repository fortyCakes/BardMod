package bardmod.bard.cards;

import bardmod.BardMod;
import bardmod.bard.BardCardTags;
import bardmod.bard.BardColor;
import bardmod.bard.StoryHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

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
        AbstractCard cardToGive = StoryHelper.generateStoryCard(false);
        if (cardToGive == null)
        {
            return;
        }
        if (upgraded)
        {
            cardToGive.upgrade();
        }

        cardToGive.setCostForTurn(0);

        addToBot(new MakeTempCardInHandAction(cardToGive, 1));

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
